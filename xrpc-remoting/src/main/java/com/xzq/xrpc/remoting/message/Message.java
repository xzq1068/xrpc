package com.xzq.xrpc.remoting.message;

import com.xzq.util.ListUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xzq
 * @Version 1.0.0
 * @Date 2023/3/9 16:10
 */
public abstract class Message {
    public static final byte PingMessage = -1;
    public static final byte PongMessage = -2;

    public static final byte XrpcRequestMessage = 1;
    public static final byte XrpcResponseMessage = 2;

    private static final Map<Byte, Class<? extends Message>> messageClass = new HashMap<>();

    static {
        messageClass.put(PingMessage,PingMessage.class);
        messageClass.put(PongMessage,PongMessage.class);
        messageClass.put(XrpcRequestMessage,XrpcRequestMessage.class);
        messageClass.put(XrpcResponseMessage, XrpcResponseMessage.class);
    }

    public static Class<? extends Message> match(byte messageType) {
        return  messageClass.get(messageType);
    }

    public static List<Class<? extends Message>> getMessageClass() {
        return ListUtil.toList(messageClass.values());
    }

    public abstract int getMessageType();
}
