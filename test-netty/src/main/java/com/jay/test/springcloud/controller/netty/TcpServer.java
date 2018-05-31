package com.jay.test.springcloud.controller.netty;

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
