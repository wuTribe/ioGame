package org.wy.example;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;

@Data
@ProtobufClass
public class HelloReq {
    String name;

    @Override
    public String toString() {
        return "HelloReq{name='" + name + "'}";
    }
}
