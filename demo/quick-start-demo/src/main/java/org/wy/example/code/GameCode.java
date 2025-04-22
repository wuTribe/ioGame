package org.wy.example.code;

import com.iohao.game.action.skeleton.core.exception.MsgExceptionInfo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum GameCode implements MsgExceptionInfo {
    nameChecked(100, "异常机制测试，name 必须是 jackson !"),
    accountOnline(101, "用户在线"),
    loginErr(102, "登录失败"),

    ;

    /** 消息码 */
    final int code;
    /** 消息模板 */
    final String msg;

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
