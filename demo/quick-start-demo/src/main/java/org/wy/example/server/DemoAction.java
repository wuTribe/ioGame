package org.wy.example.server;

import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import org.wy.example.code.GameCode;

import java.util.List;
import java.util.stream.IntStream;

@ActionController(DemoCmd.cmd)
public class DemoAction {
    @ActionMethod(DemoCmd.here)
    public HelloReq here(HelloReq helloReq) {
        HelloReq newHelloReq = new HelloReq();
        newHelloReq.name = helloReq.name + ", I'm here ";
        return newHelloReq;
    }

    @ActionMethod(DemoCmd.jackson)
    public HelloReq jackson(HelloReq helloReq) {
        // 异常机制演示
        GameCode.nameChecked.assertTrue("jackson".equals(helloReq.name));
        helloReq.name = helloReq.name + ", hello, jackson !";
        return helloReq;
    }

    @ActionMethod(DemoCmd.list)
    public List<HelloReq> list() {
        // 得到一个 List 列表数据，并返回给请求端
        return IntStream.range(1, 5).mapToObj(id -> {
            HelloReq helloReq = new HelloReq();
            helloReq.name = "data:" + id;
            return helloReq;
        }).toList();
    }
}
