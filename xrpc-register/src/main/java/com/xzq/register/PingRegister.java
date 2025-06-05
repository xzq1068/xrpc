package com.xzq.register;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.xzq.common.model.Result;
import com.xzq.common.model.ServiceInstance;
import com.xzq.common.service.NamingService;
import com.xzq.register.model.ProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ping-注册
 * @author xiongzq
 * @since 2025-06-04 13:45
 **/
public class PingRegister implements Register{

    private Logger logger = LoggerFactory.getLogger(PingRegister.class);


    private NamingService namingService;

    @Override
    public void init() {

    }


    public PingRegister() {
    }

    public PingRegister(NamingService namingService) {
        this.namingService = namingService;
    }

    @Override
    public void register(ProviderService providerServer) {
        try {
            ServiceInstance serviceInstance = new ServiceInstance();
            serviceInstance.setServiceName(providerServer.getServiceName());
            serviceInstance.setPort(providerServer.getPort());
            serviceInstance.setHost(providerServer.getHost());
            namingService.registry(serviceInstance);
        } catch (Exception e) {
            logger.error("Xrpc ping 服务注册失败，providerService:{}", JSONObject.toJSONString(providerServer));
        }
    }

    @Override
    public void register(List<ProviderService> providerServer) {
        providerServer.forEach(this::register);
    }

    @Override
    public List<ProviderService> findServices(String serviceName) {
        try {
            Result<List<ServiceInstance>> result = namingService.getInstance(serviceName);

            if (!result.isSuccess()) {
                throw new RuntimeException("获取服务失败");
            }

            List<ServiceInstance> instances = result.getData();

            return instances.stream().map(obj -> {
                ProviderService providerService = new ProviderService();
                providerService.setServiceName(obj.getServiceName());
                providerService.setHost(obj.getHost());
                providerService.setPort(obj.getPort());
                return providerService;
            }).collect(Collectors.toList());

        } catch (Exception e) {
            logger.error("Xrpc 拉取服务失败");
            throw new RuntimeException(e);
        }
    }
}
