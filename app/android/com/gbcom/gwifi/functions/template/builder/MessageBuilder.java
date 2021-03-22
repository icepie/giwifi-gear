package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
import android.support.p009v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.util.C3467s;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.builder.q */
public class MessageBuilder implements ViewBuilder {
    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        if (jSONObject == null || C3467s.m14513e(jSONObject.getString("child_layout_title")) || C3467s.m14513e(jSONObject.getString("child_layout_sub_title"))) {
            return null;
        }
        View inflate = LayoutInflater.from(context).inflate(C2425R.layout.tp_message, viewGroup, false);
        String string = jSONObject.getString("child_layout_title");
        String string2 = jSONObject.getString("child_layout_sub_title");
        ((TextView) inflate.findViewById(C2425R.C2427id.message_title)).setText(string);
        ((TextView) inflate.findViewById(C2425R.C2427id.message_sub_title)).setText(string2);
        return inflate;
    }
}
