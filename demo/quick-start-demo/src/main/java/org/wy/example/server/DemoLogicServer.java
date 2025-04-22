package org.wy.example.server;

import com.iohao.game.action.skeleton.core.BarSkeleton;
import com.iohao.game.action.skeleton.core.BarSkeletonBuilderParamConfig;
import com.iohao.game.action.skeleton.core.flow.internal.DebugInOut;
import com.iohao.game.bolt.broker.client.AbstractBrokerClientStartup;
import com.iohao.game.bolt.broker.core.client.BrokerAddress;
import com.iohao.game.bolt.broker.core.client.BrokerClient;
import com.iohao.game.bolt.broker.core.client.BrokerClientBuilder;
import com.iohao.game.common.kit.NetworkKit;
import com.iohao.game.external.core.config.ExternalGlobalConfig;
import org.wy.example.action.DemoAction;
import org.wy.example.action.LoginAction;

public class DemoLogicServer extends AbstractBrokerClientStartup {
    @Override
    public BarSkeleton createBarSkeleton() {
        var accessAuthenticationHook = ExternalGlobalConfig.accessAuthenticationHook;
        accessAuthenticationHook.setVerifyIdentity(true);
        // 添加不需要登录（身份验证）也能访问的业务方法 (action)
        accessAuthenticationHook.addIgnoreAuthCmd(Cmd.demoCmd);
        accessAuthenticationHook.addIgnoreAuthCmd(Cmd.login, Cmd.loginVerify);
        return new BarSkeletonBuilderParamConfig()
                .scanActionPackage(DemoAction.class)
                .scanActionPackage(LoginAction.class)
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
