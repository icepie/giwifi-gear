package com.gbcom.gwifi.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Scroller;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XListView extends ListView implements AbsListView.OnScrollListener {

    /* renamed from: r */
    private static final int f13805r = 0;

    /* renamed from: s */
    private static final int f13806s = 1;

    /* renamed from: t */
    private static final int f13807t = 400;

    /* renamed from: u */
    private static final int f13808u = 80;

    /* renamed from: v */
    private static final float f13809v = 1.8f;

    /* renamed from: a */
    private float f13810a = -1.0f;

    /* renamed from: b */
    private Scroller f13811b;

    /* renamed from: c */
    private AbsListView.OnScrollListener f13812c;

    /* renamed from: d */
    private AbstractC3496a f13813d;

    /* renamed from: e */
    private XListViewHeader f13814e;

    /* renamed from: f */
    private TextView f13815f;

    /* renamed from: g */
    private TextView f13816g;

    /* renamed from: h */
    private int f13817h;

    /* renamed from: i */
    private int f13818i;

    /* renamed from: j */
    private boolean f13819j = true;

    /* renamed from: k */
    private boolean f13820k = false;

    /* renamed from: l */
    private XListViewFooter f13821l;

    /* renamed from: m */
    private boolean f13822m = true;

    /* renamed from: n */
    private boolean f13823n;

    /* renamed from: o */
    private boolean f13824o = false;

    /* renamed from: p */
    private int f13825p;

    /* renamed from: q */
    private int f13826q;

    /* renamed from: w */
    private EnumC3498c f13827w = EnumC3498c.NORMAL;

    /* renamed from: x */
    private int f13828x = 0;

    /* renamed from: com.gbcom.gwifi.widget.XListView$a */
    public interface AbstractC3496a {
        void onLoadMore();

        void onRefresh();
    }

    /* renamed from: com.gbcom.gwifi.widget.XListView$b */
    public interface AbstractC3497b extends AbsListView.OnScrollListener {
        /* renamed from: a */
        void mo28438a(View view);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.widget.XListView$c */
    public enum EnumC3498c {
        NORMAL,
        HEAD_ERR,
        FOOTER_ERR
    }

    public XListView(Context context) {
        super(context);
        m14836a(context);
    }

    public XListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m14837a(context.obtainStyledAttributes(attributeSet, C2425R.styleable.f8403aJ));
        m14836a(context);
    }

    public XListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m14837a(context.obtainStyledAttributes(attributeSet, C2425R.styleable.f8403aJ));
        m14836a(context);
    }

    /* renamed from: a */
    private void m14837a(TypedArray typedArray) {
        this.f13828x = typedArray.getInt(0, this.f13828x);
        typedArray.recycle();
    }

    /* renamed from: a */
    private void m14836a(Context context) {
        this.f13811b = new Scroller(context, new DecelerateInterpolator());
        super.setOnScrollListener(this);
        this.f13814e = new XListViewHeader(context);
        this.f13815f = (TextView) this.f13814e.findViewById(C2425R.C2427id.xlistview_header_time);
        this.f13816g = (TextView) this.f13814e.findViewById(C2425R.C2427id.fail_tv);
        addHeaderView(this.f13814e);
        this.f13817h = this.f13814e.mo28443a();
        this.f13821l = new XListViewFooter(context);
    }

    @Override // android.widget.AbsListView, android.widget.ListView
    public void setAdapter(ListAdapter listAdapter) {
        if (!this.f13824o) {
            this.f13824o = true;
            addFooterView(this.f13821l);
            this.f13818i = this.f13821l.mo28439a();
        }
        super.setAdapter(listAdapter);
    }

    /* renamed from: a */
    private void m14838a(String str) {
        this.f13815f.setText(str);
    }

    /* renamed from: e */
    private void m14840e() {
        if (this.f13812c instanceof AbstractC3497b) {
            ((AbstractC3497b) this.f13812c).mo28438a(this);
        }
    }

    /* renamed from: a */
    private void m14835a(float f) {
        int b = ((int) f) + this.f13814e.mo28445b();
        if (this.f13819j && !this.f13820k) {
            if (this.f13827w == EnumC3498c.HEAD_ERR && b < 0) {
                b = 0;
            }
            if (b < (-this.f13817h)) {
                b = -this.f13817h;
            }
            this.f13814e.mo28446b(b);
            if (b > 0) {
                this.f13814e.mo28444a(1);
            } else if (b == 0 && this.f13827w == EnumC3498c.HEAD_ERR) {
                this.f13814e.mo28444a(3);
            } else {
                this.f13814e.mo28444a(0);
            }
        }
    }

    /* renamed from: f */
    private void m14841f() {
        this.f13826q = 0;
        int b = this.f13814e.mo28445b();
        this.f13811b.startScroll(0, b, 0, (-this.f13817h) - b, 400);
        invalidate();
    }

    /* renamed from: b */
    private void m14839b(float f) {
        int b = this.f13821l.mo28441b() - ((int) f);
        if (this.f13822m && !this.f13823n) {
            if (this.f13827w == EnumC3498c.FOOTER_ERR && b < 0) {
                b = 0;
            }
            if (b < (-this.f13818i)) {
                b = -this.f13818i;
            }
            this.f13821l.mo28442b(b);
            if (b > 0) {
                this.f13821l.mo28440a(1);
            } else if (b == 0 && this.f13827w == EnumC3498c.FOOTER_ERR) {
                this.f13821l.mo28440a(3);
            } else {
                this.f13821l.mo28440a(0);
            }
        }
    }

    /* renamed from: g */
    private void m14842g() {
        int b = this.f13821l.mo28441b();
        this.f13826q = 1;
        this.f13811b.startScroll(0, b, 0, (-this.f13818i) - b, 400);
        invalidate();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f13820k || this.f13823n) {
            return super.onTouchEvent(motionEvent);
        }
        if (this.f13810a == -1.0f) {
            this.f13810a = motionEvent.getRawY();
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.f13810a = motionEvent.getRawY();
                break;
            case 1:
            default:
                this.f13810a = -1.0f;
                if (getFirstVisiblePosition() != 0) {
                    if (getLastVisiblePosition() == this.f13825p - 1 && this.f13828x != 1) {
                        if (!this.f13822m || this.f13821l.mo28441b() <= 0) {
                            if (this.f13827w != EnumC3498c.FOOTER_ERR) {
                                m14842g();
                                break;
                            } else {
                                this.f13821l.mo28440a(3);
                                mo28428b(0);
                                break;
                            }
                        } else {
                            this.f13823n = true;
                            this.f13821l.mo28440a(2);
                            mo28428b(0);
                            if (this.f13813d != null) {
                                this.f13813d.onLoadMore();
                                break;
                            }
                        }
                    }
                } else if (!this.f13819j || this.f13814e.mo28445b() <= 0) {
                    if (this.f13827w != EnumC3498c.HEAD_ERR) {
                        m14841f();
                        break;
                    } else {
                        this.f13814e.mo28444a(3);
                        mo28425a(0);
                        break;
                    }
                } else {
                    this.f13820k = true;
                    XListViewHeader xListViewHeader = this.f13814e;
                    XListViewHeader xListViewHeader2 = this.f13814e;
                    xListViewHeader.mo28444a(2);
                    mo28425a(0);
                    if (this.f13813d != null) {
                        this.f13813d.onRefresh();
                        break;
                    }
                }
                break;
            case 2:
                float rawY = motionEvent.getRawY() - this.f13810a;
                this.f13810a = motionEvent.getRawY();
                if (getFirstVisiblePosition() != 0) {
                    if (getLastVisiblePosition() == this.f13825p - 1 && this.f13828x != 1) {
                        m14839b(rawY * f13809v);
                        break;
                    }
                } else if (this.f13827w != EnumC3498c.HEAD_ERR) {
                    if (this.f13814e.mo28445b() <= 0) {
                        if (rawY > 0.0f) {
                            m14835a(rawY / f13809v);
                            break;
                        }
                    } else {
                        m14835a(rawY / f13809v);
                        break;
                    }
                } else if (rawY > 0.0f && this.f13828x != 2) {
                    m14835a(rawY / f13809v);
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    /* renamed from: a */
    public void mo28424a() {
        this.f13820k = true;
        this.f13827w = EnumC3498c.NORMAL;
        this.f13814e.mo28444a(2);
        mo28425a(0);
        if (this.f13813d != null) {
            this.f13813d.onRefresh();
        }
    }

    /* renamed from: b */
    public void mo28427b() {
        this.f13827w = EnumC3498c.HEAD_ERR;
        this.f13814e.mo28444a(3);
        mo28425a(0);
        this.f13820k = false;
    }

    /* renamed from: c */
    public void mo28429c() {
        this.f13827w = EnumC3498c.NORMAL;
        if (this.f13814e.mo28445b() >= (-this.f13817h)) {
            m14838a(new SimpleDateFormat("MM-dd HH:mm").format(new Date(System.currentTimeMillis())));
            mo28425a(-this.f13817h);
            this.f13814e.mo28444a(0);
            if (this.f13821l.mo28441b() > (-this.f13818i)) {
                this.f13821l.mo28442b(-this.f13818i);
                this.f13821l.mo28440a(0);
            }
        } else if (this.f13821l.mo28441b() > (-this.f13818i)) {
            mo28428b(-this.f13818i);
            this.f13821l.mo28440a(0);
        }
        this.f13820k = false;
        this.f13823n = false;
    }

    /* renamed from: a */
    public void mo28425a(int i) {
        this.f13826q = 0;
        this.f13811b.startScroll(0, this.f13814e.mo28445b(), 0, i - this.f13814e.mo28445b(), 400);
        invalidate();
    }

    /* renamed from: b */
    public void mo28428b(int i) {
        this.f13826q = 1;
        this.f13811b.startScroll(0, this.f13821l.mo28441b(), 0, i - this.f13821l.mo28441b(), 400);
        invalidate();
    }

    /* renamed from: d */
    public void mo28431d() {
        this.f13827w = EnumC3498c.FOOTER_ERR;
        mo28428b(0);
        this.f13821l.mo28440a(3);
        this.f13823n = false;
    }

    public void computeScroll() {
        if (this.f13811b.computeScrollOffset()) {
            if (this.f13826q == 0) {
                this.f13814e.mo28446b(this.f13811b.getCurrY());
            } else if (this.f13826q == 1) {
                this.f13821l.mo28442b(this.f13811b.getCurrY());
            }
            postInvalidate();
            m14840e();
        }
        super.computeScroll();
    }

    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        this.f13812c = onScrollListener;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.f13812c != null) {
            this.f13812c.onScrollStateChanged(absListView, i);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.f13825p = i3;
        if (this.f13812c != null) {
            this.f13812c.onScroll(absListView, i, i2, i3);
        }
    }

    /* renamed from: a */
    public void mo28426a(AbstractC3496a aVar) {
        this.f13813d = aVar;
    }
}
