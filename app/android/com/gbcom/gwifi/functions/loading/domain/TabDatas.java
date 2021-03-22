package com.gbcom.gwifi.functions.loading.domain;

import java.p456io.Serializable;
import java.util.List;

public class TabDatas implements Serializable {
    private DataBean data;
    private int resultCode;

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public static class DataBean implements Serializable {
        private List<TabListBean> tab_list;

        public List<TabListBean> getTab_list() {
            return this.tab_list;
        }

        public void setTab_list(List<TabListBean> list) {
            this.tab_list = list;
        }

        public static class TabListBean implements Serializable {
            private List<ChildTabBean> child_tab;
            private int layout_type;
            private String selected_icon;
            private String tab_name;
            private String unselected_icon;

            public int getLayout_type() {
                return this.layout_type;
            }

            public void setLayout_type(int i) {
                this.layout_type = i;
            }

            public String getSelected_icon() {
                return this.selected_icon;
            }

            public void setSelected_icon(String str) {
                this.selected_icon = str;
            }

            public String getTab_name() {
                return this.tab_name;
            }

            public void setTab_name(String str) {
                this.tab_name = str;
            }

            public String getUnselected_icon() {
                return this.unselected_icon;
            }

            public void setUnselected_icon(String str) {
                this.unselected_icon = str;
            }

            public List<ChildTabBean> getChild_tab() {
                return this.child_tab;
            }

            public void setChild_tab(List<ChildTabBean> list) {
                this.child_tab = list;
            }

            public static class ChildTabBean implements Serializable {
                private String name;
                private int type;

                public String getName() {
                    return this.name;
                }

                public void setName(String str) {
                    this.name = str;
                }

                public int getType() {
                    return this.type;
                }

                public void setType(int i) {
                    this.type = i;
                }
            }
        }
    }
}
