package org.wy.example.server;

import com.iohao.game.action.skeleton.core.BarSkeleton;
import com.iohao.game.action.skeleton.core.BarSkeletonBuilderParamConfig;
import com.iohao.game.action.skeleton.core.flow.internal.DebugInOut;
import com.iohao.game.bolt.broker.client.AbstractBrokerClientStartup;
import com.iohao.game.bolt.broker.core.client.BrokerAddress;
import com.iohao.game.bolt.broker.core.client.BrokerClient;
import com.iohao.game.bolt.broker.core.client.BrokerClientBuilder;
import com.iohao.game.common.kit.NetworkKit;

public class DemoLogicServer extends AbstractBrokerClientStartup {
    @Override
    public BarSkeleton createBarSkeleton() {
        return new BarSkeletonBuilderParamConfig()
                .scanActionPackage(DemoAction.class)
                .createBuilder()
                .addInOut(new DebugInOut())
                .build();
    }

    @Override
    public BrokerClientBuilder createBrokerClientBuilder() {
        BrokerClientBuilder client = BrokerClient.newBuilder();
        client.appName("demo游戏逻辑服");
        return client;
    }

    @Override
    public BrokerAddress createBrokerAddress() {
        // 类似 127.0.0.1 ，但这里是本机的 ip
        String localIp = NetworkKit.LOCAL_IP;
        // broker （游戏网关）默认端口
        int brokerPort = 10200;
        return new BrokerAddress(localIp, brokerPort);
    }

    @Override
    public void startupSuccess(BrokerClient brokerClient) {
        super.startupSuccess(brokerClient);
    }
}
