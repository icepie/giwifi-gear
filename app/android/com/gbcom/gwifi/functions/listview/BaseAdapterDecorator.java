package com.gbcom.gwifi.functions.listview;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import com.gbcom.gwifi.functions.listview.DynamicListView;

/* renamed from: com.gbcom.gwifi.functions.listview.a */
public abstract class BaseAdapterDecorator extends BaseAdapter implements SectionIndexer, DynamicListView.AbstractC2645b {

    /* renamed from: a */
    protected final BaseAdapter f9411a;

    /* renamed from: b */
    private AbsListView f9412b;

    /* renamed from: c */
    private boolean f9413c;

    /* renamed from: d */
    private int f9414d;

    public BaseAdapterDecorator(BaseAdapter baseAdapter) {
        this.f9411a = baseAdapter;
    }

    /* renamed from: a */
    public void mo24867a(AbsListView absListView) {
        this.f9412b = absListView;
        if (this.f9411a instanceof BaseAdapterDecorator) {
            ((BaseAdapterDecorator) this.f9411a).mo24867a(absListView);
        }
        if (this.f9412b instanceof DynamicListView) {
            DynamicListView dynamicListView = (DynamicListView) this.f9412b;
            dynamicListView.mo24843a(this.f9413c);
            dynamicListView.mo24840a(this.f9414d);
        }
    }

    /* renamed from: a */
    public AbsListView mo24865a() {
        return this.f9412b;
    }

    public int getCount() {
        return this.f9411a.getCount();
    }

    public Object getItem(int i) {
        return this.f9411a.getItem(i);
    }

    public long getItemId(int i) {
        return this.f9411a.getItemId(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return this.f9411a.getView(i, view, viewGroup);
    }

    public boolean areAllItemsEnabled() {
        return this.f9411a.areAllItemsEnabled();
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return this.f9411a.getDropDownView(i, view, viewGroup);
    }

    public int getItemViewType(int i) {
        return this.f9411a.getItemViewType(i);
    }

    public int getViewTypeCount() {
        return this.f9411a.getViewTypeCount();
    }

    public boolean hasStableIds() {
        return this.f9411a.hasStableIds();
    }

    public boolean isEmpty() {
        return this.f9411a.isEmpty();
    }

    public boolean isEnabled(int i) {
        return this.f9411a.isEnabled(i);
    }

    public void notifyDataSetChanged() {
        if (!(this.f9411a instanceof ArrayAdapter)) {
            this.f9411a.notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public void mo24868a(Boolean bool) {
        if (bool.booleanValue() || !(this.f9411a instanceof ArrayAdapter)) {
            this.f9411a.notifyDataSetChanged();
        }
    }

    public void notifyDataSetInvalidated() {
        this.f9411a.notifyDataSetInvalidated();
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.f9411a.registerDataSetObserver(dataSetObserver);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.f9411a.unregisterDataSetObserver(dataSetObserver);
    }

    public int getPositionForSection(int i) {
        if (this.f9411a instanceof SectionIndexer) {
            return ((SectionIndexer) this.f9411a).getPositionForSection(i);
        }
        return 0;
    }

    public int getSectionForPosition(int i) {
        if (this.f9411a instanceof SectionIndexer) {
            return ((SectionIndexer) this.f9411a).getSectionForPosition(i);
        }
        return 0;
    }

    public Object[] getSections() {
        if (this.f9411a instanceof SectionIndexer) {
            return ((SectionIndexer) this.f9411a).getSections();
        }
        return null;
    }

    /* renamed from: b */
    public BaseAdapter mo24871b() {
        return this.f9411a;
    }

    @Override // com.gbcom.gwifi.functions.listview.DynamicListView.AbstractC2645b
    /* renamed from: a */
    public void mo24863a(int i, int i2) {
        if (this.f9411a instanceof DynamicListView.AbstractC2645b) {
            ((DynamicListView.AbstractC2645b) this.f9411a).mo24863a(i, i2);
        }
    }

    /* renamed from: a */
    public void mo24869a(boolean z) {
        this.f9413c = z;
        if (this.f9412b instanceof DynamicListView) {
            ((DynamicListView) this.f9412b).mo24843a(this.f9413c);
        }
    }

    /* renamed from: c */
    public boolean mo24872c() {
        return this.f9413c;
    }

    /* renamed from: a */
    public void mo24866a(int i) {
        this.f9414d = i;
        if (this.f9412b instanceof DynamicListView) {
            ((DynamicListView) this.f9412b).mo24840a(this.f9414d);
        }
    }

    /* renamed from: d */
    public int mo24873d() {
        return this.f9414d;
    }
}
