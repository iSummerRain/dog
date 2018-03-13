package com.dog.beans.login;

import java.io.Serializable;

public class UserBeanPage extends UserBean {

    //分页
    private String page;
    private String size;
    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
