package com.xzq.xrpc.remoting.serializer;

/**
 * 序列化接口
 * @Author xzq
 * @Version 1.0.0
 * @Date 2023/3/9 15:09
 */
public interface Serializer {

    /**
     * jdk,json,protobuf序列化方案
     */
    byte JDK=0;
    byte JSON=1;
    byte PROTOBUF=2;

    /**
     * 序列化
     */
    <T> byte[] serializer(T object);

    /**
     *   反序列化
     */
    <T> T deserializer(byte[] bytes, Class<T> clazz);

}
