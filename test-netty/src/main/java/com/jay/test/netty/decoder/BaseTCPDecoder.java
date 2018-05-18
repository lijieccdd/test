package com.jay.test.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.TooLongFrameException;

import java.nio.ByteOrder;
import java.util.List;

public class BaseTCPDecoder extends ByteToMessageDecoder {

	private final ByteOrder byteOrder;
	private final int maxFrameLength;
	private final int lengthFieldOffset;
	private final int lengthFieldLength;
	private final int lengthFieldEndOffset;
	private final int lengthAdjustment;
	private final int initialBytesToStrip;
	private final boolean failFast;
	private boolean discardingTooLongFrame;
	private long tooLongFrameLength;
	private long bytesToDiscard;

	public BaseTCPDecoder(int maxFrameLength, int lengthFieldOffset,
                          int lengthFieldLength) {
		this(maxFrameLength, lengthFieldOffset, lengthFieldLength, 0, 0);
	}

	public BaseTCPDecoder(int maxFrameLength, int lengthFieldOffset,
                          int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
		this(maxFrameLength, lengthFieldOffset, lengthFieldLength,
				lengthAdjustment, initialBytesToStrip, true);
	}

	public BaseTCPDecoder(int maxFrameLength, int lengthFieldOffset,
                          int lengthFieldLength, int lengthAdjustment,
                          int initialBytesToStrip, boolean failFast) {
		this(ByteOrder.BIG_ENDIAN, maxFrameLength, lengthFieldOffset,
				lengthFieldLength, lengthAdjustment, initialBytesToStrip,
				failFast);
	}

	public BaseTCPDecoder(ByteOrder byteOrder, int maxFrameLength,
                          int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment,
                          int initialBytesToStrip, boolean failFast) {
		if (byteOrder == null) {
			throw new NullPointerException("byteOrder");
		}

		if (maxFrameLength <= 0) {
			throw new IllegalArgumentException(
					"maxFrameLength must be a positive integer: "
							+ maxFrameLength);
		}

		if (lengthFieldOffset < 0) {
			throw new IllegalArgumentException(
					"lengthFieldOffset must be a non-negative integer: "
							+ lengthFieldOffset);
		}

		if (initialBytesToStrip < 0) {
			throw new IllegalArgumentException(
					"initialBytesToStrip must be a non-negative integer: "
							+ initialBytesToStrip);
		}

		if (lengthFieldOffset > maxFrameLength - lengthFieldLength) {
			throw new IllegalArgumentException("maxFrameLength ("
					+ maxFrameLength + ") "
					+ "must be equal to or greater than "
					+ "lengthFieldOffset (" + lengthFieldOffset + ") + "
					+ "lengthFieldLength (" + lengthFieldLength + ").");
		}

		this.byteOrder = byteOrder;
		this.maxFrameLength = maxFrameLength;
		this.lengthFieldOffset = lengthFieldOffset;
		this.lengthFieldLength = lengthFieldLength;
		this.lengthAdjustment = lengthAdjustment;
		lengthFieldEndOffset = lengthFieldOffset + lengthFieldLength;
		this.initialBytesToStrip = initialBytesToStrip;
		this.failFast = failFast;
	}

	@Override
	protected final void decode(ChannelHandlerContext ctx, ByteBuf in,
                                List<Object> out) throws Exception {
		Object decoded = decode(ctx, in);
		if (decoded != null) {
			out.add(decoded);
		}
	}

	protected Object decode(ChannelHandlerContext ctx, ByteBuf in)
			throws Exception {
		if (discardingTooLongFrame) {
			long bytesToDiscard = this.bytesToDiscard;
			int localBytesToDiscard = (int) Math.min(bytesToDiscard,
					in.readableBytes());
			in.skipBytes(localBytesToDiscard);
			bytesToDiscard -= localBytesToDiscard;
			this.bytesToDiscard = bytesToDiscard;

			failIfNecessary(false);
		}

		if (in.readableBytes() < lengthFieldEndOffset) {
			return null;
		}

		int actualLengthFieldOffset = in.readerIndex() + lengthFieldOffset;
		long frameLength = getUnadjustedFrameLength(in,
				actualLengthFieldOffset, lengthFieldLength, byteOrder);

		if (frameLength < 0) {
			in.skipBytes(lengthFieldEndOffset);
			throw new CorruptedFrameException(
					"negative pre-adjustment length field: " + frameLength);
		}

		frameLength += lengthAdjustment + lengthFieldEndOffset;

		if (frameLength < lengthFieldEndOffset) {
			in.skipBytes(lengthFieldEndOffset);
			throw new CorruptedFrameException("Adjusted frame length ("
					+ frameLength + ") is less "
					+ "than lengthFieldEndOffset: " + lengthFieldEndOffset);
		}

		if (frameLength > maxFrameLength) {
			long discard = frameLength - in.readableBytes();
			tooLongFrameLength = frameLength;

			if (discard < 0) {
				// buffer contains more bytes then the frameLength so we can
				// discard all now
				in.skipBytes((int) frameLength);
			} else {
				// Enter the discard mode and discard everything received so
				// far.
				discardingTooLongFrame = true;
				bytesToDiscard = discard;
				in.skipBytes(in.readableBytes());
			}
			failIfNecessary(true);
			return null;
		}

		// never overflows because it's less than maxFrameLength
		int frameLengthInt = (int) frameLength;
		if (in.readableBytes() < frameLengthInt) {
			return null;
		}

		if (initialBytesToStrip > frameLengthInt) {
			in.skipBytes(frameLengthInt);
			throw new CorruptedFrameException("Adjusted frame length ("
					+ frameLength + ") is less " + "than initialBytesToStrip: "
					+ initialBytesToStrip);
		}
		in.skipBytes(initialBytesToStrip);

		// extract frame
		int readerIndex = in.readerIndex();
		int actualFrameLength = frameLengthInt - initialBytesToStrip;
		ByteBuf frame = extractFrame(ctx, in, readerIndex, actualFrameLength);
		in.readerIndex(readerIndex + actualFrameLength);
		return frame;
	}

	protected long getUnadjustedFrameLength(ByteBuf buf, int offset,
                                            int length, ByteOrder order) {
		buf = buf.order(order);
		long frameLength;
		switch (length) {
		case 1:
			frameLength = buf.getUnsignedByte(offset);
			break;
		case 2:
			frameLength = buf.getUnsignedShort(offset);
			break;
		case 3:
			frameLength = buf.getUnsignedMedium(offset);
			break;
		case 4:
			frameLength = buf.getUnsignedInt(offset);
			break;
		case 8:
			frameLength = buf.getLong(offset);
			break;
		default:
			throw new DecoderException("unsupported lengthFieldLength: "
					+ lengthFieldLength + " (expected: 1, 2, 3, 4, or 8)");
		}
		return frameLength;
	}

	private void failIfNecessary(boolean firstDetectionOfTooLongFrame) {
		if (bytesToDiscard == 0) {
			// Reset to the initial state and tell the handlers that
			// the frame was too large.
			long tooLongFrameLength = this.tooLongFrameLength;
			this.tooLongFrameLength = 0;
			discardingTooLongFrame = false;
			if (!failFast || failFast && firstDetectionOfTooLongFrame) {
				fail(tooLongFrameLength);
			}
		} else {
			// Keep discarding and notify handlers if necessary.
			if (failFast && firstDetectionOfTooLongFrame) {
				fail(tooLongFrameLength);
			}
		}
	}

	protected ByteBuf extractFrame(ChannelHandlerContext ctx, ByteBuf buffer,
                                   int index, int length) {
		ByteBuf frame = ctx.alloc().buffer(length);
		frame.writeBytes(buffer, index, length);
		return frame;
	}

	private void fail(long frameLength) {
		if (frameLength > 0) {
			throw new TooLongFrameException("Adjusted frame length exceeds "
					+ maxFrameLength + ": " + frameLength + " - discarded");
		} else {
			throw new TooLongFrameException("Adjusted frame length exceeds "
					+ maxFrameLength + " - discarding");
		}
	}
}
