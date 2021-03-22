package com.gbcom.gwifi.codec;

import android.util.Log;
import com.gbcom.gwifi.util.GsonUtil;
import java.util.List;
import p419io.netty.buffer.ByteBuf;
import p419io.netty.channel.ChannelHandlerContext;
import p419io.netty.handler.codec.ByteToMessageDecoder;

/* renamed from: com.gbcom.gwifi.codec.f */
public class MsgDecoder extends ByteToMessageDecoder {
    /* access modifiers changed from: protected */
    @Override // p419io.netty.handler.codec.ByteToMessageDecoder
    public void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() >= 2) {
            try {
                GsonUtil.m14241a();
                int readShort = (short) (byteBuf.readShort() - 2);
                byteBuf.readShort();
                if (readShort > 0) {
                    byte[] bArr = new byte[readShort];
                    byteBuf.readBytes(bArr);
                    String str = new String(bArr, "UTF-8");
                    Log.d("aaaaaa", "json=" + str);
                    channelHandlerContext.fireChannelRead((Object) str);
                }
            } catch (Exception e) {
                Log.d("kkk", "Exception e" + e.toString());
            }
        }
    }
}
