package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
import android.support.p009v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.template.p252a.CommonClickListener;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.ScrollTextView;
import java.util.ArrayList;
import org.apache.xalan.templates.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.template.builder.x */
public class ScrollTextBuilder implements ViewBuilder {
    @Override // com.gbcom.gwifi.functions.template.builder.ViewBuilder
    /* renamed from: a */
    public View mo26982a(final Context context, ViewGroup viewGroup, JSONObject jSONObject, FragmentManager fragmentManager) throws JSONException {
        JSONArray jSONArray;
        if (jSONObject == null || (jSONArray = jSONObject.getJSONArray(Constants.ATTRNAME_ELEMENTS)) == null || jSONArray.length() < 2) {
            return null;
        }
        View inflate = GBApplication.instance().getLayoutInflater().inflate(C2425R.layout.tp_scroll_text, viewGroup, false);
        final ScrollTextView scrollTextView = (ScrollTextView) inflate.findViewById(C2425R.C2427id.scroll_tv);
        final RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(C2425R.C2427id.scroll_rl);
        ImageTools.m14149a(jSONArray.getJSONObject(0).getString("icon_url"), (ImageView) inflate.findViewById(C2425R.C2427id.iv_new), GBApplication.instance().options);
        final ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (int i = 1; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            arrayList.add(jSONObject2.getString("title"));
            arrayList4.add(jSONObject2.getString("title_color"));
            arrayList2.add(jSONObject2.getString("sub_title"));
            arrayList3.add(jSONObject2);
        }
        scrollTextView.mo28409a(arrayList);
        scrollTextView.mo28413b(arrayList2);
        scrollTextView.mo28416c(arrayList4);
        if (!scrollTextView.mo28410a()) {
            scrollTextView.mo28411b();
        }
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.builder.ScrollTextBuilder.View$OnClickListenerC31771 */

            public void onClick(View view) {
                if (!C3467s.m14513e(scrollTextView.mo28418d())) {
                    String d = scrollTextView.mo28418d();
                    for (int i = 0; i < arrayList.size(); i++) {
                        JSONObject jSONObject = (JSONObject) arrayList3.get(i);
                        if (d.equals((String) arrayList.get(i))) {
                            relativeLayout.setTag(new CommonClickListener(context, jSONObject));
                            ((CommonClickListener) view.getTag()).onClick(view);
                        }
                    }
                }
            }
        });
        return inflate;
    }
}
