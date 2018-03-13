package com.dog.utils.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * defined Dog Project OutResult
 * @author guoxg
 */
public class DogResult implements java.io.Serializable {

    private String sn;
    private String rtnCode;
    private String rtnMsg;
    private transient Map bean = new HashMap();
    private transient List beans = new ArrayList();
    private transient Object object;

    public static interface RTN_CODE {
        public static final String SUCCESS = "0";
        public static final String ERROR = "-9999";
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public DogResult() {
        setRtnCode("0");
        setRtnMsg("成功");
    }

    public DogResult(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public DogResult(String rtnCode, String rtnMsg) {
        this.rtnCode = rtnCode;
        this.rtnMsg = rtnMsg;
    }

    public String getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getRtnMsg() {
        return rtnMsg;
    }

    public void setRtnMsg(String rtnMsg) {
        this.rtnMsg = rtnMsg;
    }

    public Map getBean() {
        if (bean == null){
            bean = new HashMap();
        }
        return bean;
    }

    public void setBean(Map bean) {
        this.bean = bean;
    }

    public List getBeans() {
        if (beans == null){
            beans = new ArrayList();
        }
        return beans;
    }

    public void setBeans(List beans) {
        this.beans = beans;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}
