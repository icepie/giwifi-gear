package com.gbcom.gwifi.third.kefu.easemob;

import android.content.Context;
import com.gbcom.gwifi.util.Log;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.callback.Callback;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GwifiHXHelper {
    private static GwifiHXHelper instance;
    private ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    public interface LoginCallBack {
        void error(String str);

        void success();
    }

    public static GwifiHXHelper getInstance() {
        if (instance == null) {
            instance = new GwifiHXHelper();
        }
        return instance;
    }

    public void LoginHX(final String str, final String str2, final LoginCallBack loginCallBack) {
        this.singleThreadExecutor.execute(new Runnable() {
            /* class com.gbcom.gwifi.third.kefu.easemob.GwifiHXHelper.RunnableC34151 */

            @Override // java.lang.Runnable
            public void run() {
                ChatClient.getInstance().register(str, str2, new Callback() {
                    /* class com.gbcom.gwifi.third.kefu.easemob.GwifiHXHelper.RunnableC34151.C34161 */

                    @Override // com.hyphenate.EMCallBack, com.hyphenate.helpdesk.callback.Callback
                    public void onSuccess() {
                        GwifiHXHelper.this.login(str, str2, loginCallBack);
                    }

                    @Override // com.hyphenate.EMCallBack, com.hyphenate.helpdesk.callback.Callback
                    public void onError(int i, String str) {
                        if (i == 2) {
                            loginCallBack.error("网络异常，请检查网络！");
                        } else if (i == 203) {
                            GwifiHXHelper.this.login(str, str2, loginCallBack);
                        } else if (i == 202) {
                            loginCallBack.error("注册失败，无权限！");
                        } else if (i == 205) {
                            loginCallBack.error("用户名不合法");
                        } else {
                            loginCallBack.error("注册失败");
                        }
                    }

                    @Override // com.hyphenate.EMCallBack, com.hyphenate.helpdesk.callback.Callback
                    public void onProgress(int i, String str) {
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void login(String str, String str2, final LoginCallBack loginCallBack) {
        ChatClient.getInstance().login(str, str2, new Callback() {
            /* class com.gbcom.gwifi.third.kefu.easemob.GwifiHXHelper.C34172 */

            @Override // com.hyphenate.EMCallBack, com.hyphenate.helpdesk.callback.Callback
            public void onSuccess() {
                loginCallBack.success();
            }

            @Override // com.hyphenate.EMCallBack, com.hyphenate.helpdesk.callback.Callback
            public void onProgress(int i, String str) {
            }

            @Override // com.hyphenate.EMCallBack, com.hyphenate.helpdesk.callback.Callback
            public void onError(int i, String str) {
                if (i == 2) {
                    loginCallBack.error("网络异常，请检查网络！");
                } else {
                    loginCallBack.error("登录失败");
                }
            }
        });
    }

    public void logout(Context context) {
        ChatClient.getInstance().logout(true, new Callback() {
            /* class com.gbcom.gwifi.third.kefu.easemob.GwifiHXHelper.C34183 */

            @Override // com.hyphenate.EMCallBack, com.hyphenate.helpdesk.callback.Callback
            public void onSuccess() {
                Log.m14400c("123", "环信下线成功");
            }

            @Override // com.hyphenate.EMCallBack, com.hyphenate.helpdesk.callback.Callback
            public void onProgress(int i, String str) {
            }

            @Override // com.hyphenate.EMCallBack, com.hyphenate.helpdesk.callback.Callback
            public void onError(int i, String str) {
                Log.m14400c("123", "环信下线失败");
            }
        });
    }
}
