package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.p009v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.template.p252a.CommonClickListener;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.umeng.analytics.pro.UContent;
import java.text.DecimalFormat;
import org.apache.xalan.templates.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.builder.n */
public class LeftOneRightTowBuilder implements ViewBuilder {

    /* renamed from: d */
    private static final int f11853d = 0;

    /* renamed from: a */
    Handler f11854a = new Handler() {
        /* class com.gbcom.gwifi.functions.template.builder.LeftOneRightTowBuilder.HandlerC31571 */

        public void handleMessage(Message message) {
            long j;
            long j2;
            long j3 = 0;
            switch (message.what) {
                case 0:
                    if (LeftOneRightTowBuilder.this.f11855b > 0) {
                        long j4 = LeftOneRightTowBuilder.this.f11855b;
                        if (j4 > 60) {
                            long j5 = j4 / 60;
                            long j6 = j4 % 60;
                            j2 = j5;
                            j = j6;
                        } else {
                            j = j4;
                            j2 = 0;
                        }
                        if (j2 > 60) {
                            j3 = j2 / 60;
                            j2 %= 60;
                        }
                        String format = LeftOneRightTowBuilder.this.f11856c.format(j3);
                        String format2 = LeftOneRightTowBuilder.this.f11856c.format(j2);
                        String format3 = LeftOneRightTowBuilder.this.f11856c.format(j);
                        LeftOneRightTowBuilder.this.f11859g.setText(format);
                        LeftOneRightTowBuilder.this.f11860h.setText(format2);
                        LeftOneRightTowBuilder.this.f11861i.setText(format3);
                        LeftOneRightTowBuilder.m12932f(LeftOneRightTowBuilder.this);
                        LeftOneRightTowBuilder.this.f11854a.removeMessages(0);
                        LeftOneRightTowBuilder.this.f11854a.sendEmptyMessageDelayed(0, 1000);
                        return;
                    } else if (LeftOneRightTowBuilder.this.f11855b == 0) {
                        LeftOneRightTowBuilder.this.f11854a.removeMessages(0);
                        LeftOneRightTowBuilder.this.m12926a();
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    };

    /* renamed from: b */
    private long f11855b = 0;

    /* renamed from: c */
    private DecimalFormat f11856c = new DecimalFormat("00");

    /* renamed from: e */
    private LinearLayout f11857e;

    /* renamed from: f */
    private ImageView f11858f;

    /* renamed from: g */
    private TextView f11859g;

    /* renamed from: h */
    private TextView f11860h;

    /* renamed from: i */
    private TextView f11861i;

    /* renamed from: j */
    private String f11862j;

    /* renamed from: f */
    static /* synthetic */ long m12932f(LeftOneRightTowBuilder nVar) {
        long j = nVar.f11855b;
        nVar.f11855b = j - 1;
        return j;
    }

    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        JSONArray jSONArray;
        if (jSONObject == null || (jSONArray = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS)) == null || jSONArray.length() < 3) {
            return null;
        }
        View inflate = LayoutInflater.from(context).inflate(C2425R.layout.tp_left_one_right_two, viewGroup, false);
        FrameLayout frameLayout = (FrameLayout) inflate.findViewById(C2425R.C2427id.left_one);
        TextView textView = (TextView) inflate.findViewById(C2425R.C2427id.left_one_title);
        TextView textView2 = (TextView) inflate.findViewById(C2425R.C2427id.left_one_sub_title);
        ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.left_one_img);
        this.f11857e = (LinearLayout) inflate.findViewById(C2425R.C2427id.time_ll);
        this.f11858f = (ImageView) inflate.findViewById(C2425R.C2427id.time_iv);
        this.f11859g = (TextView) inflate.findViewById(C2425R.C2427id.time_hour);
        this.f11860h = (TextView) inflate.findViewById(C2425R.C2427id.time_min);
        this.f11861i = (TextView) inflate.findViewById(C2425R.C2427id.time_second);
        JSONObject jSONObject2 = jSONArray.getJSONObject(0);
        String string = jSONObject2.getString("title");
        String string2 = jSONObject2.getString("sub_title");
        String string3 = jSONObject2.getString("icon_url");
        String string4 = jSONObject2.getString("title_color");
        if (jSONObject2.has(UContent.f21486q)) {
            this.f11862j = jSONObject2.getString(UContent.f21486q);
        }
        if (C3467s.m14513e(this.f11862j)) {
            this.f11857e.setVisibility(8);
        } else if (!C3467s.m14516h(this.f11862j.trim())) {
            this.f11857e.setVisibility(8);
        } else if (this.f11862j.trim().length() == 10) {
            this.f11857e.setVisibility(0);
            m12927a(this.f11862j);
        } else {
            this.f11857e.setVisibility(8);
        }
        textView.setText(string);
        if (!C3467s.m14513e(string4)) {
            textView.setTextColor(Color.parseColor(string4));
        }
        textView2.setText(string2);
        ImageTools.m14149a(string3, imageView, GBApplication.instance().options);
        frameLayout.setOnClickListener(new CommonClickListener(context, jSONObject2));
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(C2425R.C2427id.right_top);
        TextView textView3 = (TextView) inflate.findViewById(C2425R.C2427id.right_top_title);
        TextView textView4 = (TextView) inflate.findViewById(C2425R.C2427id.right_top_sub_title);
        ImageView imageView2 = (ImageView) inflate.findViewById(C2425R.C2427id.right_top_img);
        JSONObject jSONObject3 = jSONArray.getJSONObject(1);
        String string5 = jSONObject3.getString("title");
        String string6 = jSONObject3.getString("sub_title");
        String string7 = jSONObject3.getString("icon_url");
        String string8 = jSONObject3.getString("title_color");
        textView3.setText(string5);
        if (!C3467s.m14513e(string8)) {
            textView3.setTextColor(Color.parseColor(string8));
        }
        textView4.setText(string6);
        ImageTools.m14149a(string7, imageView2, GBApplication.instance().options);
        relativeLayout.setOnClickListener(new CommonClickListener(context, jSONObject3));
        RelativeLayout relativeLayout2 = (RelativeLayout) inflate.findViewById(C2425R.C2427id.right_bottom);
        TextView textView5 = (TextView) inflate.findViewById(C2425R.C2427id.right_bottom_title);
        TextView textView6 = (TextView) inflate.findViewById(C2425R.C2427id.right_bottom_sub_title);
        ImageView imageView3 = (ImageView) inflate.findViewById(C2425R.C2427id.right_bottom_img);
        JSONObject jSONObject4 = jSONArray.getJSONObject(2);
        String string9 = jSONObject4.getString("title");
        String string10 = jSONObject4.getString("sub_title");
        String string11 = jSONObject4.getString("icon_url");
        String string12 = jSONObject4.getString("title_color");
        textView5.setText(string9);
        if (!C3467s.m14513e(string12)) {
            textView5.setTextColor(Color.parseColor(string12));
        }
        textView5.setText(string9);
        textView6.setText(string10);
        ImageTools.m14149a(string11, imageView3, GBApplication.instance().options);
        relativeLayout2.setOnClickListener(new CommonClickListener(context, jSONObject4));
        return inflate;
    }

    /* renamed from: a */
    private void m12927a(String str) {
        long time = C3467s.m14515g(str.trim() + " 24:00:00").getTime() - System.currentTimeMillis();
        if (time > 0) {
            this.f11858f.setImageResource(C2425R.C2426drawable.end_red);
            long j = time / 86400000;
            if (j > 4) {
                this.f11859g.setText("剩");
                this.f11860h.setText(j + "");
                this.f11861i.setText("天");
            } else if (j <= 4 && j >= 0) {
                this.f11855b = time / 1000;
                this.f11854a.sendMessage(this.f11854a.obtainMessage(0));
            }
        } else {
            m12926a();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12926a() {
        this.f11858f.setImageResource(C2425R.C2426drawable.end_gray);
        this.f11859g.setText("00");
        this.f11860h.setText("00");
        this.f11861i.setText("00");
    }
}
