package org.wy.example.server;

public interface Cmd {
    // =================== 主路由 ==============
    /** 测试方法*/
    int demoCmd = 1;
    // =================== 主路由 ==============
    /** 子路由 here */
    int here = 0;
    /** 子路由 jackson */
    int jackson = 1;
    /** 子路由 list */
    int list = 2;
    /** 通信*/
    int hereFlow = 3;



    // =================== 主路由 ==============
    /** 登录*/
    int login = 2;
    // =================== 主路由 ==============

    int loginVerify = 1;
    int loginAfter = 2;
}
