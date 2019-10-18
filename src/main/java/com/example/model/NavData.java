package com.example.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class NavData implements Serializable {
    private String icon;
    private String index;
    private List<Subs> subs;
    private String title;

    public static class Subs implements Serializable{
        private String index;
        private String title;

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "Subs{" +
                    "index='" + index + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<Subs> getSubs() {
        return subs;
    }

    public void setSubs(List<Subs> subs) {
        this.subs = subs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
