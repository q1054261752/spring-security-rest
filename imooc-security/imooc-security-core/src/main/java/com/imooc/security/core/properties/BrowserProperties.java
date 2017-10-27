package com.imooc.security.core.properties;

/**
 * Created by zkr on 2017/10/27.
 */
public class BrowserProperties {

    private String loginPage = "/imooc-signIn.html";

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
