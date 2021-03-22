package com.gbcom.gwifi.p221a.p222a;

import p419io.netty.channel.ChannelHandlerContext;

/* renamed from: com.gbcom.gwifi.a.a.c */
public interface MsgDispatcherListener {
    void channelActive(ChannelHandlerContext channelHandlerContext);

    void channelInactive(ChannelHandlerContext channelHandlerContext);

    void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th);

    void messageReceived(ChannelHandlerContext channelHandlerContext, Object obj);
}
