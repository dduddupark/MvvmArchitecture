package com.example.suyeon.mvvmarchitecture.data.model;

public class LoginData {

    private String strCode;
    private String strName;

    public LoginData(String strCode, String strName) {
        this.strCode = strCode;
        this.strName = strName;
    }

    public String getStrCode() {
        return strCode;
    }

    public void setStrCode(String strCode) {
        this.strCode = strCode;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }
}
