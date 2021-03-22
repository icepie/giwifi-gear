package com.gbcom.gwifi.p221a.p222a;

import com.gbcom.gwifi.functions.redbag.msg.HeartBeatMsg;
import java.util.concurrent.CountDownLatch;
import p419io.netty.channel.ChannelHandlerContext;
import p419io.netty.channel.SimpleChannelInboundHandler;
import p419io.netty.handler.timeout.IdleStateEvent;

/* renamed from: com.gbcom.gwifi.a.a.a */
public class ClientDispatcherHandler extends SimpleChannelInboundHandler<String> {

    /* renamed from: a */
    private static CountDownLatch f8463a;

    /* renamed from: b */
    private static boolean f8464b = false;

    /* renamed from: c */
    private MsgDispatcherListener f8465c;

    /* renamed from: d */
    private ChannelHandlerContext f8466d;

    public ClientDispatcherHandler(MsgDispatcherListener cVar) {
        this.f8465c = cVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void channelRead0(ChannelHandlerContext channelHandlerContext, String str) throws Exception {
        if (this.f8465c != null) {
            this.f8465c.messageReceived(channelHandlerContext, str);
        }
    }

    @Override // p419io.netty.channel.ChannelInboundHandlerAdapter, p419io.netty.channel.ChannelInboundHandler
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.f8466d = channelHandlerContext;
        if (this.f8465c != null) {
            this.f8465c.channelActive(channelHandlerContext);
        }
    }

    @Override // p419io.netty.channel.ChannelInboundHandlerAdapter, p419io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.f8465c != null) {
            this.f8465c.channelInactive(channelHandlerContext);
        }
    }

    @Override // p419io.netty.channel.ChannelInboundHandlerAdapter, p419io.netty.channel.ChannelInboundHandler
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (obj instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) obj;
            if (!f8464b) {
                m10162a(channelHandlerContext);
            }
        }
    }

    /* renamed from: a */
    private void m10162a(ChannelHandlerContext channelHandlerContext) {
        channelHandlerContext.writeAndFlush(new HeartBeatMsg());
    }

    @Override // p419io.netty.channel.ChannelHandlerAdapter, p419io.netty.channel.ChannelInboundHandlerAdapter, p419io.netty.channel.ChannelInboundHandler, p419io.netty.channel.ChannelHandler
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        th.printStackTrace();
        channelHandlerContext.close();
        if (this.f8465c != null) {
            this.f8465c.exceptionCaught(channelHandlerContext, th);
        }
    }
}
