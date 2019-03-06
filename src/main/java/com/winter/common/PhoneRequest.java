package com.winter.common;

public class PhoneRequest {
    String mobilePhoneNumber;
    String template;

    public PhoneRequest(String phoneNum, String template) {
        this.mobilePhoneNumber = phoneNum;
        this.template = template;
    }

    public String getPhoneNum() {
        return mobilePhoneNumber;
    }

    public void setPhoneNum(String phoneNum) {
        this.mobilePhoneNumber = phoneNum;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
