package org.wy.example.client;

import com.iohao.game.external.client.AbstractInputCommandRegion;
import lombok.extern.slf4j.Slf4j;
import org.wy.example.server.Cmd;
import org.wy.example.server.HelloReq;

import java.util.List;

@Slf4j
public class LoginRegion extends AbstractInputCommandRegion {
    @Override
    public void initInputCommand() {
        // 模拟请求的主路由
        inputCommandCreate.cmd = Cmd.login;
        // ---------------- 模拟请求 2-1 ----------------
        ofCommand(Cmd.loginVerify)
                .setTitle("loginVerify")
                .setRequestData(inputCommandCreate.nextParamString("loginVerify-aaa"))
                .callback(result -> {
                    // 得到 list 数据，因为服务器返回的是 List
                    log.info("loginVerify : {}", result);
                });

        ofCommand(Cmd.loginAfter)
                .setTitle("loginAfter")
                .callback(result -> {
                    // 得到 list 数据，因为服务器返回的是 List
                    log.info("loginAfter : {}", result);
                });
    }

    @Override
    public void loginSuccessCallback() {
        super.loginSuccessCallback();
    }
}
