package org.wy.example.action;

import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import com.iohao.game.action.skeleton.core.flow.FlowContext;
import org.wy.example.code.GameCode;
import org.wy.example.server.Cmd;
import org.wy.example.server.HelloReq;

import java.util.List;
import java.util.stream.IntStream;

@ActionController(Cmd.demoCmd)
public class DemoAction {
    @ActionMethod(Cmd.here)
    public HelloReq here(HelloReq helloReq) {
        HelloReq newHelloReq = new HelloReq();
        newHelloReq.setName(helloReq.getName() + ", I'm here ");
        return newHelloReq;
    }

    @ActionMethod(Cmd.jackson)
    public HelloReq jackson(HelloReq helloReq) {
        // 异常机制演示
        GameCode.nameChecked.assertTrue("jackson".equals(helloReq.getName()));
        helloReq.setName(helloReq.getName() + ", hello, jackson !");
        return helloReq;
    }

    @ActionMethod(Cmd.list)
    public List<HelloReq> list() {
        // 得到一个 List 列表数据，并返回给请求端
        return IntStream.range(1, 5).mapToObj(id -> {
            HelloReq helloReq = new HelloReq();
            helloReq.setName("data:" + id);
            return helloReq;
        }).toList();
    }


    // 注意，这个方法只是为了演示而写的；（ioGame21 开始支持）
    // 效果与上面的方法一样，只不过是用广播（推送）的方式将数据返回给请求方
    @ActionMethod(Cmd.hereFlow)
    public void hereFlow(HelloReq helloReq, FlowContext flowContext) {
        // 业务数据
        var newHelloReq = new HelloReq();
        newHelloReq.setName(helloReq.getName() + ", I'm here ");

        flowContext.broadcastMe(newHelloReq);
    }
}
