package org.wy.example.client;

import com.iohao.game.external.client.AbstractInputCommandRegion;
import lombok.extern.slf4j.Slf4j;
import org.wy.example.server.Cmd;
import org.wy.example.server.HelloReq;

import java.util.List;

@Slf4j
public class DemoRegion extends AbstractInputCommandRegion {
    @Override
    public void initInputCommand() {
        // 模拟请求的主路由
        inputCommandCreate.cmd = Cmd.demoCmd;

        // ---------------- 模拟请求 1-0 ----------------
        ofCommand(Cmd.here).setTitle("here").setRequestData(() -> {
            HelloReq helloReq = new HelloReq();
            helloReq.setName("1");
            return helloReq;
        }).callback(result -> {
            HelloReq value = result.getValue(HelloReq.class);
            log.info("value : {}", value);
        });

        // ---------------- 模拟请求 1-1 ----------------
        ofCommand(Cmd.jackson).setTitle("jackson").setRequestData(() -> {
            HelloReq helloReq = new HelloReq();
            helloReq.setName("1");
            return helloReq;
        }).callback(result -> {
            // 不会进入到这里，因为发生了异常。 1-1 action 的逻辑要求 name 必须是 jackson。
            HelloReq value = result.getValue(HelloReq.class);
            log.info("jackson : {}", value);
        });

        // ---------------- 模拟请求 1-2 ----------------
        ofCommand(Cmd.list).setTitle("list").callback(result -> {
            // 得到 list 数据，因为服务器返回的是 List
            List<HelloReq> list = result.listValue(HelloReq.class);
            log.info("list : {}", list);
        });


        // ---------------- 模拟请求 1-3 ----------------
        ofCommand(Cmd.hereFlow).setTitle("hereFlow").callback(result -> {
            // 得到 list 数据，因为服务器返回的是 List
            log.info("hereFlow : {}", result);
        });
    }

    @Override
    public void loginSuccessCallback() {
        super.loginSuccessCallback();
    }
}
