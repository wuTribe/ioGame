package org.wy.example;

import com.iohao.game.external.core.netty.simple.NettySimpleHelper;

import java.util.List;

public class DemoApplication {
    public static void main(String[] args) {
        int port = 10100;
        // 游戏逻辑服
        var demoLogicServer = new DemoLogicServer();

        // 启动 对外服、网关服、逻辑服; 并生成游戏业务文档
        // 这三部分在一个进程中相互使用内存通信
        NettySimpleHelper.run(port, List.of(demoLogicServer));
    }
}
