package org.wy.example.action;

import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import com.iohao.game.action.skeleton.core.flow.FlowContext;
import com.iohao.game.bolt.broker.client.kit.ExternalCommunicationKit;
import lombok.extern.slf4j.Slf4j;
import org.wy.example.code.GameCode;
import org.wy.example.server.Cmd;
import org.wy.example.vo.LoginVerify;
import org.wy.example.vo.UserInfo;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ActionController(Cmd.login)
public class LoginAction {
    static Map<String, UserInfo> userMap = new HashMap<>();

    static {
        userMap.put("a", new UserInfo(1, "1号用户"));
        userMap.put("b", new UserInfo(2, "2号用户"));
        userMap.put("c", new UserInfo(3, "3号用户"));
        userMap.put("d", new UserInfo(4, "4号用户"));
        userMap.put("e", new UserInfo(5, "5号用户"));
        userMap.put("f", new UserInfo(6, "6号用户"));
    }

    @ActionMethod(Cmd.loginVerify)
    public UserInfo loginVerify(LoginVerify loginVerify, FlowContext flowContext) {
        String jwt = loginVerify.jwt;
        // 根据 jwt 获取用户信息
        UserInfo userInfo = userMap.get(jwt);
        log.info(userInfo.toString());

        // 查询用户是否在线（顶号逻辑，forcedOffline 强制断开，再 bind）
        long uid = userInfo.id;
        boolean existUser = ExternalCommunicationKit.existUser(uid);
        if (existUser) {
            log.info("当前玩家在线：{}", uid);
        }
        GameCode.accountOnline.assertTrueThrows(existUser);

        boolean success = flowContext.bindingUserId(uid);
        GameCode.loginErr.assertTrue(success);

        return userInfo;
    }
}
