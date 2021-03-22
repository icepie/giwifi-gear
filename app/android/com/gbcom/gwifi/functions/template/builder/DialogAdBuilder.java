package com.gbcom.gwifi.functions.template.builder;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.p009v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.template.p252a.CommonClickListener;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import org.apache.xalan.templates.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.builder.d */
public class DialogAdBuilder implements ViewBuilder {
    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(final Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        JSONArray jSONArray;
        if (!(jSONObject == null || (jSONArray = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS)) == null || jSONArray.length() == 0)) {
            final JSONObject jSONObject2 = jSONArray.getJSONObject(0);
            String string = jSONObject2.getString("icon_url");
            int a = DensityUtil.m14127a(context);
            if (GBApplication.instance().getCurrentActivity() != null) {
                GBApplication.instance().getCurrentActivity().showDialogAd(context, string, a, new GBActivity.AbstractC2517a() {
                    /* class com.gbcom.gwifi.functions.template.builder.DialogAdBuilder.C31381 */

                    @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                    public void onClick(Dialog dialog, View view) {
                        dialog.dismiss();
                        new CommonClickListener(context, jSONObject2);
                    }
                }, new DialogInterface.OnCancelListener() {
                    /* class com.gbcom.gwifi.functions.template.builder.DialogAdBuilder.DialogInterface$OnCancelListenerC31392 */

                    public void onCancel(DialogInterface dialogInterface) {
                    }
                });
            }
        }
        return null;
    }
}
