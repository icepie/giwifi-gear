package com.gbcom.gwifi.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.gbcom.gwifi.util.cache.CacheAuth;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.p456io.IOException;

/* renamed from: com.gbcom.gwifi.util.m */
public class NetworkUtils {

    /* renamed from: a */
    public static final String[] f13466a = {"34:cb", "c8:55"};

    /* renamed from: b */
    private static final String f13467b = "NetworkUtils";

    /* renamed from: c */
    private static final int f13468c = 8282;

    /* renamed from: d */
    private static final String f13469d = "228.0.0.8";

    /* renamed from: a */
    public static boolean m14426a(Context context) {
        NetworkInfo[] allNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (allNetworkInfo = connectivityManager.getAllNetworkInfo()) == null) {
            return false;
        }
        for (int i = 0; i < allNetworkInfo.length; i++) {
            if (allNetworkInfo[i].getState() == NetworkInfo.State.CONNECTED || allNetworkInfo[i].getState() == NetworkInfo.State.CONNECTING) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m14427a(String str, int i) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(str, i));
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } catch (IOException e2) {
            e2.printStackTrace();
            try {
                socket.close();
                return false;
            } catch (IOException e3) {
                e3.printStackTrace();
                return false;
            }
        } catch (Throwable th) {
            try {
                socket.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static boolean m14428a(String str, int i, Integer num) {
        Boolean bool = false;
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(str, i), num.intValue());
            bool = Boolean.valueOf(socket.isConnected());
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        } catch (IOException e2) {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e3) {
                }
            }
        } catch (Throwable th) {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e4) {
                }
            }
            throw th;
        }
        return bool.booleanValue();
    }

    /* renamed from: a */
    public static boolean m14429a(String str, Integer num) {
        try {
            return InetAddress.getByName(str).isReachable(num.intValue());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return false;
    }

    /* renamed from: a */
    public static String m14425a(String str) {
        try {
            return InetAddress.getByName(str).getHostAddress();
        } catch (UnknownHostException e) {
            Log.m14403e(f13467b, "不能根据域名获得主机IP地址：" + e.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    public static String m14424a() {
        String str = null;
        byte[] bArr = new byte[8];
        System.arraycopy((Object) FormatUtil.m14218a(1), 0, (Object) bArr, 0, 4);
        System.arraycopy((Object) FormatUtil.m14219a((short) 1), 0, (Object) bArr, 4, 2);
        System.arraycopy((Object) FormatUtil.m14219a((short) 6), 0, (Object) bArr, 6, 2);
        Log.m14403e(f13467b, FormatUtil.m14222b(bArr));
        try {
            InetAddress byName = InetAddress.getByName(f13469d);
            DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length, byName, (int) f13468c);
            try {
                MulticastSocket multicastSocket = new MulticastSocket((int) f13468c);
                multicastSocket.setSoTimeout(3000);
                multicastSocket.setLoopbackMode(true);
                multicastSocket.joinGroup(byName);
                multicastSocket.send(datagramPacket);
                Log.m14398b(f13467b, ">>>send packet ok");
                byte[] bArr2 = new byte[256];
                DatagramPacket datagramPacket2 = new DatagramPacket(bArr2, bArr2.length);
                multicastSocket.receive(datagramPacket2);
                Log.m14398b(f13467b, "receiver: " + new String(datagramPacket2.getData()).trim());
                String b = FormatUtil.m14222b(bArr2);
                Log.m14398b(f13467b, "string1: " + b);
                String substring = b.substring(24);
                Log.m14398b(f13467b, "substring: " + substring);
                str = FormatUtil.m14217a(substring.trim());
                Log.m14398b(f13467b, "s: " + str);
                multicastSocket.leaveGroup(byName);
                multicastSocket.close();
                return str;
            } catch (IOException e) {
                e.printStackTrace();
                return str;
            }
        } catch (UnknownHostException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static boolean m14430b(String str) {
        if (str == null || str.equals("") || !CheckUtil.m14084b(str)) {
            return false;
        }
        if (str.toLowerCase().substring(3, 8).equals(f13466a[0]) || str.toLowerCase().substring(3, 8).equals(f13466a[1])) {
            return true;
        }
        int authMethod = CacheAuth.getInstance().getAuthMethod();
        return authMethod == 1 || authMethod == 2;
    }
}
