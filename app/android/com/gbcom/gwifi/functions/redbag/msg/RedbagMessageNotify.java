package com.gbcom.gwifi.functions.redbag.msg;

public class RedbagMessageNotify {
    private NotifyBean notify;
    private NotifyHeaderBean notifyHeader;

    public NotifyHeaderBean getNotifyHeader() {
        return this.notifyHeader;
    }

    public void setNotifyHeader(NotifyHeaderBean notifyHeaderBean) {
        this.notifyHeader = notifyHeaderBean;
    }

    public NotifyBean getNotify() {
        return this.notify;
    }

    public void setNotify(NotifyBean notifyBean) {
        this.notify = notifyBean;
    }

    public static class NotifyHeaderBean {
        private int command;

        public int getCommand() {
            return this.command;
        }

        public void setCommand(int i) {
            this.command = i;
        }
    }

    public static class NotifyBean {
        private RedbagContentBean redbagContent;

        public RedbagContentBean getRedbagContent() {
            return this.redbagContent;
        }

        public void setRedbagContent(RedbagContentBean redbagContentBean) {
            this.redbagContent = redbagContentBean;
        }

        public static class RedbagContentBean {
            private String content;
            private int endTime;

            public String getContent() {
                return this.content;
            }

            public void setContent(String str) {
                this.content = str;
            }

            public int getEndTime() {
                return this.endTime;
            }

            public void setEndTime(int i) {
                this.endTime = i;
            }
        }
    }
}
