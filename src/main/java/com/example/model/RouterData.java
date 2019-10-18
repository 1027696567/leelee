package com.example.model;

import java.io.Serializable;
import java.util.List;

public class RouterData implements Serializable {
    private List<Children> children;
    private String component;
    private String name;
    private String path;

    public static class Children implements Serializable{
        private String path;
        private String component;
        private String name;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getComponent() {
            return component;
        }

        public void setComponent(String component) {
            this.component = component;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Children{" +
                    "path='" + path + '\'' +
                    ", component='" + component + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
