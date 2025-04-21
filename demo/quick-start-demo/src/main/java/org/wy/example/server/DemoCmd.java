package org.wy.example.server;

public interface DemoCmd {
    /** 主路由 */
    int cmd = 1;
    /** 子路由 here */
    int here = 0;
    /** 子路由 jackson */
    int jackson = 1;
    /** 子路由 list */
    int list = 2;
}
