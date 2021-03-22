package com.gbcom.gwifi.codec;

import android.util.Log;
import com.gbcom.gwifi.functions.redbag.msg.AbstractMsg;
import com.gbcom.gwifi.functions.redbag.msg.RequestMsgBase;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.GsonUtil;
import com.gbcom.gwifi.util.JsonUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import p419io.netty.buffer.ByteBuf;
import p419io.netty.channel.ChannelHandlerContext;
import p419io.netty.handler.codec.MessageToByteEncoder;

/* renamed from: com.gbcom.gwifi.codec.g */
public class MsgEncoder extends MessageToByteEncoder<AbstractMsg> {
    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void encode(ChannelHandlerContext channelHandlerContext, AbstractMsg abstractMsg, ByteBuf byteBuf) {
        String a;
        try {
            String b = GsonUtil.m14241a().mo21597b(abstractMsg);
            if ((abstractMsg instanceof RequestMsgBase) && (a = m10827a(b)) != null) {
                b = b.replaceFirst("\\}", ",\"sign\":\"" + a + "\"\\}");
            }
            byte[] bytes = b.getBytes("UTF-8");
            byteBuf.writeShort(bytes.length + 2);
            byteBuf.writeShort(abstractMsg.getType());
            byteBuf.writeBytes(bytes);
        } catch (Exception e) {
            Log.d("aaaaaa", "Exception e:" + e.toString());
        }
    }

    /* renamed from: a */
    public static String m10827a(String str) {
        new HashMap();
        try {
            HashMap<String, String> b = JsonUtil.m14391b(str);
            StringBuffer stringBuffer = new StringBuffer();
            ArrayList<Map.Entry> arrayList = new ArrayList();
            arrayList.addAll(b.entrySet());
            Collections.sort(arrayList, new Comparator<Map.Entry<String, String>>() {
                /* class com.gbcom.gwifi.codec.MsgEncoder.C25971 */

                /* renamed from: a */
                public int compare(Map.Entry<String, String> entry, Map.Entry<String, String> entry2) {
                    return entry.getKey().compareTo(entry2.getKey());
                }
            });
            for (Map.Entry entry : arrayList) {
                if (!((String) entry.getKey()).equals("sign")) {
                    stringBuffer.append((String) entry.getKey());
                    stringBuffer.append((String) entry.getValue());
                }
            }
            stringBuffer.append(Constant.f13312cu);
            return MD5Util.m10825a(stringBuffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
