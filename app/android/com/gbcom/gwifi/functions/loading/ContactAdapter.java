package com.gbcom.gwifi.functions.loading;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.functions.loading.domain.University;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.gbcom.gwifi.functions.loading.a */
public class ContactAdapter extends ArrayAdapter<University> implements SectionIndexer {

    /* renamed from: d */
    private static final String f9867d = "ContactAdapter";

    /* renamed from: a */
    List<String> f9868a;

    /* renamed from: b */
    List<University> f9869b;

    /* renamed from: c */
    List<University> f9870c = new ArrayList();

    /* renamed from: e */
    private LayoutInflater f9871e;

    /* renamed from: f */
    private SparseIntArray f9872f;

    /* renamed from: g */
    private SparseIntArray f9873g;

    /* renamed from: h */
    private int f9874h;

    /* renamed from: i */
    private C2783a f9875i;

    /* renamed from: j */
    private boolean f9876j;

    public ContactAdapter(Context context, int i, List<University> list) {
        super(context, i, list);
        this.f9874h = i;
        this.f9869b = list;
        this.f9870c.addAll(list);
        this.f9871e = LayoutInflater.from(context);
    }

    /* renamed from: com.gbcom.gwifi.functions.loading.a$b */
    /* compiled from: ContactAdapter */
    private static class C2784b {

        /* renamed from: a */
        TextView f9879a;

        /* renamed from: b */
        TextView f9880b;

        private C2784b() {
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C2784b bVar;
        if (view == null) {
            C2784b bVar2 = new C2784b();
            view = this.f9871e.inflate(this.f9874h, (ViewGroup) null);
            bVar2.f9879a = (TextView) view.findViewById(2131755382);
            bVar2.f9880b = (TextView) view.findViewById(C2425R.C2427id.header);
            view.setTag(bVar2);
            bVar = bVar2;
        } else {
            bVar = (C2784b) view.getTag();
        }
        University a = getItem(i);
        if (a == null) {
            Log.d(f9867d, i + "");
        }
        bVar.f9879a.setText(a.getUniverName());
        String header = a.getHeader();
        if (i != 0 && (header == null || header.equals(getItem(i - 1).getHeader()))) {
            bVar.f9880b.setVisibility(8);
        } else if (TextUtils.isEmpty(header)) {
            bVar.f9880b.setVisibility(8);
        } else {
            bVar.f9880b.setVisibility(0);
            bVar.f9880b.setText(header);
        }
        return view;
    }

    /* renamed from: a */
    public University getItem(int i) {
        return (University) super.getItem(i);
    }

    public int getCount() {
        return super.getCount();
    }

    public int getPositionForSection(int i) {
        return this.f9872f.get(i);
    }

    public int getSectionForPosition(int i) {
        return this.f9873g.get(i);
    }

    public Object[] getSections() {
        int i;
        this.f9872f = new SparseIntArray();
        this.f9873g = new SparseIntArray();
        int count = getCount();
        this.f9868a = new ArrayList();
        this.f9868a.add(getContext().getString(C2425R.C2429string.search_header));
        this.f9872f.put(0, 0);
        this.f9873g.put(0, 0);
        for (int i2 = 1; i2 < count; i2++) {
            String header = getItem(i2).getHeader();
            Log.d(f9867d, "contactadapter getsection getHeader:" + header + " name:" + getItem(i2).getUniverName());
            int size = this.f9868a.size() - 1;
            if (this.f9868a.get(size) == null || this.f9868a.get(size).equals(header)) {
                i = size;
            } else {
                this.f9868a.add(header);
                i = size + 1;
                this.f9872f.put(i, i2);
            }
            this.f9873g.put(i2, i);
        }
        return this.f9868a.toArray(new String[this.f9868a.size()]);
    }

    public Filter getFilter() {
        if (this.f9875i == null) {
            this.f9875i = new C2783a(this.f9869b);
        }
        return this.f9875i;
    }

    /* renamed from: com.gbcom.gwifi.functions.loading.a$a */
    /* compiled from: ContactAdapter */
    private class C2783a extends Filter {

        /* renamed from: a */
        List<University> f9877a = null;

        public C2783a(List<University> list) {
            this.f9877a = list;
        }

        /* access modifiers changed from: protected */
        public synchronized Filter.FilterResults performFiltering(CharSequence charSequence) {
            Filter.FilterResults filterResults;
            filterResults = new Filter.FilterResults();
            if (this.f9877a == null) {
                this.f9877a = new ArrayList();
            }
            Log.d(ContactAdapter.f9867d, "contacts original size: " + this.f9877a.size());
            Log.d(ContactAdapter.f9867d, "contacts copy size: " + ContactAdapter.this.f9870c.size());
            if (charSequence == null || charSequence.length() == 0) {
                filterResults.values = ContactAdapter.this.f9870c;
                filterResults.count = ContactAdapter.this.f9870c.size();
            } else {
                String charSequence2 = charSequence.toString();
                int size = this.f9877a.size();
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < size; i++) {
                    University university = this.f9877a.get(i);
                    String univerName = university.getUniverName();
                    String header = university.getHeader();
                    if (univerName.startsWith(charSequence2) || header.equalsIgnoreCase(charSequence2)) {
                        arrayList.add(university);
                    } else {
                        String[] split = univerName.split(" ");
                        int length = split.length;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= length) {
                                break;
                            } else if (split[i2].startsWith(charSequence2)) {
                                arrayList.add(university);
                                break;
                            } else {
                                i2++;
                            }
                        }
                    }
                }
                filterResults.values = arrayList;
                filterResults.count = arrayList.size();
            }
            Log.d(ContactAdapter.f9867d, "contacts filter results size: " + filterResults.count);
            return filterResults;
        }

        /* access modifiers changed from: protected */
        public synchronized void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            ContactAdapter.this.f9869b.clear();
            ContactAdapter.this.f9869b.addAll((List) filterResults.values);
            Log.d(ContactAdapter.f9867d, "publish contacts filter results size: " + filterResults.count);
            if (filterResults.count > 0) {
                ContactAdapter.this.f9876j = true;
                ContactAdapter.this.notifyDataSetChanged();
                ContactAdapter.this.f9876j = false;
            } else {
                ContactAdapter.this.notifyDataSetInvalidated();
            }
        }
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (!this.f9876j) {
            this.f9870c.clear();
            this.f9870c.addAll(this.f9869b);
        }
    }
}
