package com.gbcom.gwifi.functions.redbag.p251a;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.redbag.domain.HitRecord;
import com.gbcom.gwifi.functions.redbag.domain.JoinHistory;
import com.gbcom.gwifi.functions.redbag.domain.LastRound;
import com.gbcom.gwifi.functions.redbag.domain.ThisRound;
import com.gbcom.gwifi.functions.redbag.p251a.CommonListAdapter;
import com.gbcom.gwifi.functions.redbag.view.MiddleImageSpan;
import com.gbcom.gwifi.util.Constant;
import java.util.ArrayList;
import java.util.List;
import org.apache.xpath.compiler.PsuedoNames;

/* renamed from: com.gbcom.gwifi.functions.redbag.a.c */
public class RedbagListAdapter extends BaseAdapter {

    /* renamed from: a */
    private static final int f11343a = 0;

    /* renamed from: b */
    private static final int f11344b = 1;

    /* renamed from: c */
    private static final int f11345c = 2;

    /* renamed from: d */
    private static final int f11346d = 3;

    /* renamed from: e */
    private final int f11347e;

    /* renamed from: f */
    private List<Object> f11348f = new ArrayList();

    /* renamed from: g */
    private AbstractC3068b f11349g;

    /* renamed from: h */
    private String f11350h;

    /* renamed from: i */
    private AbstractC3067a f11351i;

    /* renamed from: com.gbcom.gwifi.functions.redbag.a.c$a */
    /* compiled from: RedbagListAdapter */
    public interface AbstractC3067a {
        void onOpenClick(View view, ArrayList<HitRecord> arrayList);
    }

    /* renamed from: com.gbcom.gwifi.functions.redbag.a.c$b */
    /* compiled from: RedbagListAdapter */
    public interface AbstractC3068b {
        void onOpenClick(View view, String str);
    }

    public RedbagListAdapter(List<Object> list, String str, int i) {
        this.f11348f = list;
        this.f11350h = str;
        this.f11347e = i;
    }

    public int getCount() {
        if (this.f11348f == null) {
            return 0;
        }
        return this.f11348f.size();
    }

    public Object getItem(int i) {
        return this.f11348f.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        getItemViewType(i);
        final Object obj = this.f11348f.get(i);
        if (obj instanceof List) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.redbag_result, viewGroup, false);
            final ArrayList arrayList = (ArrayList) obj;
            ((ListView) inflate.findViewById(C2425R.C2427id.lv_last)).setAdapter((ListAdapter) new CommonListAdapter(arrayList, C2425R.layout.redbag_result_history, new CommonListAdapter.AbstractC3060a<HitRecord>() {
                /* class com.gbcom.gwifi.functions.redbag.p251a.RedbagListAdapter.C30621 */

                /* renamed from: a */
                public void mo24662a(CommonListAdapter.C3061b bVar, HitRecord hitRecord, int i) {
                    bVar.mo26112a(C2425R.C2427id.tv_time, (CharSequence) hitRecord.getCreateAt());
                    TextView textView = (TextView) bVar.mo26111a(2131755200);
                    String nickName = hitRecord.getNickName();
                    if (nickName.length() > 13) {
                        nickName = nickName.substring(0, 13) + "...";
                    }
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("l " + nickName + "参与了" + RedbagListAdapter.this.f11350h + "红包场");
                    ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(-65536);
                    spannableStringBuilder.setSpan(new MiddleImageSpan(GBApplication.instance(), C2425R.C2426drawable.smallredbag), 0, 1, 18);
                    spannableStringBuilder.setSpan(foregroundColorSpan, nickName.length() + 5, spannableStringBuilder.length(), 33);
                    textView.setText(spannableStringBuilder);
                }
            }));
            ((ListView) inflate.findViewById(C2425R.C2427id.lv_result)).setAdapter((ListAdapter) new CommonListAdapter(arrayList, C2425R.layout.redbag_textview, new CommonListAdapter.AbstractC3060a<HitRecord>() {
                /* class com.gbcom.gwifi.functions.redbag.p251a.RedbagListAdapter.C30632 */

                /* renamed from: a */
                public void mo24662a(CommonListAdapter.C3061b bVar, HitRecord hitRecord, int i) {
                    TextView textView = (TextView) bVar.mo26111a(C2425R.C2427id.result_userone);
                    int deductBeans = hitRecord.getDeductBeans();
                    String nickName = hitRecord.getNickName();
                    if (nickName.length() > 13) {
                        nickName = nickName.substring(0, 13) + "...";
                    }
                    if (deductBeans == 0) {
                        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(nickName + "获得了" + hitRecord.getHitBeans() + Constant.f13309cr);
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(-65536), nickName.length() + 3, spannableStringBuilder.length(), 33);
                        textView.setText(spannableStringBuilder);
                        return;
                    }
                    SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(nickName + "获得了" + hitRecord.getHitBeans() + Constant.f13309cr + "(最少),扣除" + deductBeans + Constant.f13309cr);
                    ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(-65536);
                    ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(-65536);
                    spannableStringBuilder2.setSpan(foregroundColorSpan, nickName.length() + 3, hitRecord.getNickName().length() + 3 + (hitRecord.getHitBeans() + "").length() + 7, 33);
                    spannableStringBuilder2.setSpan(foregroundColorSpan2, nickName.length() + 3 + (hitRecord.getHitBeans() + "").length() + 9, spannableStringBuilder2.length(), 33);
                    textView.setText(spannableStringBuilder2);
                }
            }));
            ((ImageView) inflate.findViewById(C2425R.C2427id.redbag_result)).setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.redbag.p251a.RedbagListAdapter.View$OnClickListenerC30643 */

                public void onClick(View view) {
                    if (RedbagListAdapter.this.f11351i != null) {
                        RedbagListAdapter.this.f11351i.onOpenClick(view, arrayList);
                    }
                }
            });
            ((TextView) inflate.findViewById(C2425R.C2427id.tv_redbagname_finish)).setText(this.f11350h + "红包场");
            return inflate;
        } else if (obj instanceof String) {
            if (this.f11348f.size() == 2) {
                View inflate2 = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.redbag_image, viewGroup, false);
                ((TextView) inflate2.findViewById(C2425R.C2427id.tv_redbagname_init)).setText(this.f11350h + "红包场");
                ((ImageView) inflate2.findViewById(C2425R.C2427id.redbag_init)).setOnClickListener(new View.OnClickListener() {
                    /* class com.gbcom.gwifi.functions.redbag.p251a.RedbagListAdapter.View$OnClickListenerC30654 */

                    public void onClick(View view) {
                        if (RedbagListAdapter.this.f11349g != null) {
                            RedbagListAdapter.this.f11349g.onOpenClick(view, (String) obj);
                        }
                    }
                });
                return inflate2;
            }
            View inflate3 = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.redbag_image_running, viewGroup, false);
            ((TextView) inflate3.findViewById(C2425R.C2427id.tv_redbagname_running)).setText(this.f11350h + "红包场");
            TextView textView = (TextView) inflate3.findViewById(C2425R.C2427id.redbag_join_num);
            ImageView imageView = (ImageView) inflate3.findViewById(C2425R.C2427id.iv_open_redbag);
            if (this.f11348f.size() > 2) {
                textView.setText((this.f11348f.size() - 2) + PsuedoNames.PSEUDONAME_ROOT + this.f11347e);
            }
            imageView.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.redbag.p251a.RedbagListAdapter.View$OnClickListenerC30665 */

                public void onClick(View view) {
                    if (RedbagListAdapter.this.f11349g != null) {
                        RedbagListAdapter.this.f11349g.onOpenClick(view, (String) obj);
                    }
                }
            });
            return inflate3;
        } else if (!(obj instanceof JoinHistory)) {
            return view;
        } else {
            View inflate4 = LayoutInflater.from(viewGroup.getContext()).inflate(C2425R.layout.redbag_result_one, viewGroup, false);
            TextView textView2 = (TextView) inflate4.findViewById(C2425R.C2427id.tv_thisround_name);
            JoinHistory joinHistory = (JoinHistory) obj;
            ((TextView) inflate4.findViewById(C2425R.C2427id.tv_thisround_time)).setText(joinHistory.getCreateAt());
            String nickName = joinHistory.getNickName();
            if (nickName.length() > 13) {
                nickName = nickName.substring(0, 13) + "...";
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(nickName + "参与了" + this.f11350h + "红包场");
            spannableStringBuilder.setSpan(new ForegroundColorSpan(-65536), nickName.length() + 3, spannableStringBuilder.length(), 33);
            textView2.setText(spannableStringBuilder);
            return inflate4;
        }
    }

    public int getViewTypeCount() {
        return 4;
    }

    public int getItemViewType(int i) {
        Object obj = this.f11348f.get(i);
        if (obj instanceof LastRound) {
            return 1;
        }
        if ((obj instanceof ThisRound) || !(obj instanceof JoinHistory)) {
            return 0;
        }
        return 3;
    }

    /* renamed from: a */
    public void mo26116a(AbstractC3068b bVar) {
        this.f11349g = bVar;
    }

    /* renamed from: a */
    public void mo26115a(AbstractC3067a aVar) {
        this.f11351i = aVar;
    }
}
