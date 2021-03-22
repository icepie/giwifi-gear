package com.gbcom.gwifi.third.kefu.easemob;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.util.Config;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.chat.EMClient;
import com.hyphenate.helpdesk.easeui.DemoHelper;
import com.hyphenate.helpdesk.easeui.DemoMessageHelper;
import com.hyphenate.helpdesk.easeui.Preferences;
import com.hyphenate.helpdesk.easeui.p261ui.gwifi.GwifiChatActivity;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.hyphenate.util.PathUtil;
import com.umeng.p377fb.common.C6299a;
import java.p456io.File;
import java.p456io.FileNotFoundException;
import java.p456io.FileOutputStream;
import java.p456io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.apache.xpath.compiler.PsuedoNames;

public class GiwifiHXService {
    private static GiwifiHXService instance = null;
    private Context context = null;

    public static GiwifiHXService getInstance(Context context2) {
        if (instance == null) {
            instance = new GiwifiHXService(context2);
        }
        return instance;
    }

    public GiwifiHXService(Context context2) {
        this.context = context2;
        Preferences.getInstance();
        Preferences.init(context2);
        Preferences.getInstance().setAppKey(Config.m14094a().mo27800c());
        DemoHelper.getInstance().init(context2);
    }

    public void loginHX(Activity activity) {
        String d = Config.m14094a().mo27801d();
        String loginPhone = CacheAccount.getInstance().getLoginPhone();
        activity.startActivity(new IntentBuilder(activity).setTargetClass(GwifiChatActivity.class).setServiceIMNumber(d).setVisitorInfo(DemoMessageHelper.createVisitorInfo(loginPhone, loginPhone)).setScheduleQueue(DemoMessageHelper.createQueueIdentity("kefu")).setTitleName(GBActivity.ONLINE_SERVICE).setShowUserNick(true).build());
    }

    public boolean isLoggedInBefore() {
        return ChatClient.getInstance().isLoggedInBefore();
    }

    public String saveDeviceTestPic(Bitmap bitmap) {
        new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.f33126US);
        File file = new File(PathUtil.getInstance().getImagePath(), EMClient.getInstance().getCurrentUser());
        if (!file.isDirectory()) {
            try {
                file.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String str = ((Object) file) + PsuedoNames.PSEUDONAME_ROOT + System.currentTimeMillis() + C6299a.f22754m;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            if (fileOutputStream != null) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        return str;
    }
}
