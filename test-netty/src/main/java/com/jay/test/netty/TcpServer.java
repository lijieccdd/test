package com.jay.test.netty;

import com.jay.test.netty.decoder.BaseTCPDecoder;
import com.jay.test.netty.encoder.BaseTCPPrepender;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.nio.ByteOrder;

/**
 * @Author : lijie
 * @Description :
 * @Date : Create in 2018/5/8 13:50
 * @Modified by :
 */
public class TcpServer {
    public static void start(int port){
        /*EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("decoder",new BaseTCPDecoder(ByteOrder.LITTLE_ENDIAN,Integer.MAX_VALUE,0,4,0,4,true));
                            ch.pipeline().addLast("encoder",new BaseTCPPrepender(4,false));
                            ch.pipeline().addLast("handler",new TCPServerHandler());
                        }
                    });

            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }*/
    }
}
