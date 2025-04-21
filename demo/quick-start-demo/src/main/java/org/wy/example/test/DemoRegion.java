package org.wy.example.test;

import com.iohao.game.external.client.AbstractInputCommandRegion;
import org.wy.example.DemoCmd;

public class DemoRegion extends AbstractInputCommandRegion {
    @Override
    public void initInputCommand() {
        // 模拟请求的主路由
        inputCommandCreate.cmd = DemoCmd.cmd;
    }

    @Override
    public void loginSuccessCallback() {
        super.loginSuccessCallback();
    }
}
