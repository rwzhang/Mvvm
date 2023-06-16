package com.example.mvvm.bean;

import java.util.List;

public class PostBean {
    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<DatasBean> datas;

    public int getCurPage() {
        return curPage;
    }

    public int getOffset() {
        return offset;
    }

    public boolean isOver() {
        return over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getSize() {
        return size;
    }

    public int getTotal() {
        return total;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public static class DatasBean {

        private String apkLink;
        private int audit;
        private String author;
        private int chapterId;
        private String chapterName;
        private boolean collect;
        private int courseId;
        private String desc;
        private String envelopePic;
        private boolean fresh;
        private int id;
        private String link;
        private String niceDate;
        private String niceShareDate;
        private String origin;
        private String prefix;
        private String projectLink;
        private long publishTime;
        private int selfVisible;
        private long shareDate;
        private String shareUser;
        private int superChapterId;
        private String superChapterName;
        private String title;
        private int type;
        private int userId;
        private int visible;
        private int zan;

        public String getApkLink() {
            return apkLink;
        }

        public int getAudit() {
            return audit;
        }

        public String getAuthor() {
            return author;
        }

        public int getChapterId() {
            return chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public boolean isCollect() {
            return collect;
        }

        public int getCourseId() {
            return courseId;
        }

        public String getDesc() {
            return desc;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public boolean isFresh() {
            return fresh;
        }

        public int getId() {
            return id;
        }

        public String getLink() {
            return link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public String getNiceShareDate() {
            return niceShareDate;
        }

        public String getOrigin() {
            return origin;
        }

        public String getPrefix() {
            return prefix;
        }

        public String getProjectLink() {
            return projectLink;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public int getSelfVisible() {
            return selfVisible;
        }

        public long getShareDate() {
            return shareDate;
        }

        public String getShareUser() {
            return shareUser;
        }

        public int getSuperChapterId() {
            return superChapterId;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public String getTitle() {
            return title;
        }

        public int getType() {
            return type;
        }

        public int getUserId() {
            return userId;
        }

        public int getVisible() {
            return visible;
        }

        public int getZan() {
            return zan;
        }
    }
}
