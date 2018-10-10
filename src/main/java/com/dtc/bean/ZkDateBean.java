package com.dtc.bean;

import java.util.List;

public class ZkDateBean {

    private String path;

    private String date;

    private List<ZkDateBean> childNode;

    public String getPath() {
        return path;
    }

    public ZkDateBean setPath(String path) {
        this.path = path;
        return this;
    }

    public String getDate() {
        return date;
    }

    public ZkDateBean setDate(String date) {
        this.date = date;
        return this;
    }

    public List<ZkDateBean> getChildNode() {
        return childNode;
    }

    public ZkDateBean setChildNode(List<ZkDateBean> childNode) {
        this.childNode = childNode;
        return this;
    }
}
