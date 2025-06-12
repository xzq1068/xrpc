package com.xzq.xrpc.remoting.protocol;

import com.xzq.xrpc.remoting.message.Message;
import com.xzq.xrpc.remoting.serializer.Serializer;

import java.util.HashSet;
import java.util.Set;

/**
 * xrpc v1 协议版本
 *
 * @Author xzq
 * @Version 1.0.0
 * @Date 2023/3/9 16:28
 */
public class XrpcProtocolV1 extends XrpcProtocol {

    /**
     * xrpc协议 魔数
     */
    private static final int MAGIC_NUM = 0x63;

    /**
     * xrpc协议 版本
     */
    private static final byte VERSION = 1;

    /**
     * xrpc 协议默认序列化方案
     */
    private static final byte SERIALIZER = Serializer.PROTOBUF;

    /**
     * xrpc协议支持的版本
     */
    private static final Set<Byte> supportVersions = new HashSet<>();

    /**
     * xrpc协议支持的序列化方案
     */
    private static final Set<Byte> supportSerializers = new HashSet<>();

    /**
     * xrpc协议支持的消息类型
     */
    private static final Set<Byte> supportMessages = new HashSet<>();

    static {
        supportVersions.add(VERSION);

        supportSerializers.add(Serializer.JDK);
        supportSerializers.add(Serializer.PROTOBUF);
        supportSerializers.add(Serializer.JSON);

        supportMessages.add(Message.PingMessage);
        supportMessages.add(Message.PongMessage);
        supportMessages.add(Message.XrpcRequestMessage);
        supportMessages.add(Message.XrpcResponseMessage);
    }

    @Override
    public int getMagicNum() {
        return MAGIC_NUM;
    }

    @Override
    public int getVersion() {
        return VERSION;
    }

    @Override
    public int getSerializer() {
        return SERIALIZER;
    }

    @Override
    public boolean isSupport(int magicNum, byte version, byte serialize, byte clazz) {


        if (magicNum == MAGIC_NUM &&
                supportVersions.contains(version)
                 && supportSerializers.contains(serialize)
                 && supportMessages.contains(clazz)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;

    }
}
