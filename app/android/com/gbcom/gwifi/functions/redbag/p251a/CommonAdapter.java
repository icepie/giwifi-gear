package com.gbcom.gwifi.functions.redbag.p251a;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.functions.redbag.domain.HitRecord;
import com.gbcom.gwifi.util.Constant;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.gbcom.gwifi.functions.redbag.a.a */
public class CommonAdapter extends BaseAdapter {

    /* renamed from: a */
    private List<HitRecord> f11336a = new ArrayList();

    public CommonAdapter(List<HitRecord> list) {
        this.f11336a = list;
    }

    public int getCount() {
        if (this.f11336a == null) {
            return 0;
        }
        return this.f11336a.size();
    }

    public Object getItem(int i) {
        return this.f11336a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        HitRecord hitRecord = this.f11336a.get(i);
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.redbag_item_text_listview, viewGroup, false);
            TextView textView = (TextView) view.findViewById(C2425R.C2427id.result_user);
            int deductBeans = hitRecord.getDeductBeans();
            if (deductBeans == 0) {
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(hitRecord.getNickName() + "获得了" + (hitRecord.getHitBeans() + hitRecord.getLuckyNumberBonus()) + Constant.f13309cr);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(-65536), hitRecord.getNickName().length() + 3, spannableStringBuilder.length(), 33);
                textView.setText(spannableStringBuilder);
            } else {
                SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(hitRecord.getNickName() + "获得了" + (hitRecord.getHitBeans() + hitRecord.getLuckyNumberBonus()) + Constant.f13309cr + "(最少),扣除" + deductBeans + Constant.f13309cr);
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(-65536);
                ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(-65536);
                spannableStringBuilder2.setSpan(foregroundColorSpan, hitRecord.getNickName().length() + 3, hitRecord.getNickName().length() + 3 + (hitRecord.getHitBeans() + "").length() + 7, 33);
                spannableStringBuilder2.setSpan(foregroundColorSpan2, (hitRecord.getHitBeans() + "").length() + hitRecord.getNickName().length() + 3 + 9, spannableStringBuilder2.length(), 33);
                textView.setText(spannableStringBuilder2);
            }
        }
        return view;
    }
}
