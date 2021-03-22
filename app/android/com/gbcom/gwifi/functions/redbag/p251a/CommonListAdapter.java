package com.gbcom.gwifi.functions.redbag.p251a;

import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.gbcom.gwifi.functions.redbag.a.b */
public class CommonListAdapter<T> extends BaseAdapter {

    /* renamed from: a */
    public List<T> f11337a = new ArrayList();

    /* renamed from: b */
    private int f11338b;

    /* renamed from: c */
    private AbstractC3060a f11339c;

    /* renamed from: com.gbcom.gwifi.functions.redbag.a.b$a */
    /* compiled from: CommonListAdapter */
    public interface AbstractC3060a<T> {
        /* renamed from: a */
        void mo24662a(C3061b bVar, T t, int i);
    }

    public CommonListAdapter(List<T> list, int i, AbstractC3060a aVar) {
        this.f11337a = list;
        this.f11338b = i;
        this.f11339c = aVar;
    }

    public int getCount() {
        if (this.f11337a == null) {
            return 0;
        }
        return this.f11337a.size();
    }

    public Object getItem(int i) {
        return this.f11337a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = View.inflate(viewGroup.getContext(), this.f11338b, null);
        C3061b bVar = new C3061b(inflate);
        inflate.setTag(bVar);
        m12570a(bVar, this.f11337a.get(i), i);
        return inflate;
    }

    /* renamed from: a */
    private void m12570a(C3061b bVar, T t, int i) {
        this.f11339c.mo24662a(bVar, t, i);
    }

    /* renamed from: com.gbcom.gwifi.functions.redbag.a.b$b */
    /* compiled from: CommonListAdapter */
    public static class C3061b {

        /* renamed from: a */
        public static DisplayImageOptions f11340a;

        /* renamed from: b */
        SparseArray<View> f11341b = new SparseArray<>();

        /* renamed from: c */
        private View f11342c;

        public C3061b(View view) {
            this.f11342c = view;
            f11340a = new DisplayImageOptions.Builder().showImageForEmptyUri(C2425R.C2426drawable.user).showImageOnFail(C2425R.C2426drawable.user).cacheInMemory(false).cacheOnDisk(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();
        }

        /* renamed from: a */
        public View mo26111a(int i) {
            View view = this.f11341b.get(i);
            if (view != null) {
                return view;
            }
            View findViewById = this.f11342c.findViewById(i);
            this.f11341b.put(i, findViewById);
            return findViewById;
        }

        /* renamed from: a */
        public C3061b mo26112a(int i, CharSequence charSequence) {
            ((TextView) mo26111a(i)).setText(charSequence);
            return this;
        }

        /* renamed from: a */
        public C3061b mo26114a(int i, boolean z) {
            mo26111a(i).setVisibility(z ? 0 : 8);
            return this;
        }

        /* renamed from: a */
        public C3061b mo26113a(int i, String str) {
            try {
                ImageTools.m14149a(str, (ImageView) mo26111a(i), f11340a);
            } catch (Exception e) {
                Log.m14398b("loadImageView", e.toString());
            }
            return this;
        }
    }
}
