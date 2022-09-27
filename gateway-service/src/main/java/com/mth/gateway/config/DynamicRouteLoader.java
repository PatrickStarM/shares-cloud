package com.mth.gateway.config;

import com.alibaba.cloud.nacos.NacosConfigAutoConfiguration;
import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.crypto.engines.NaccacheSternEngine;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.naming.spi.InitialContextFactory;

@Slf4j
@Component
public class DynamicRouteLoader implements InitializingBean {

    @Resource
    private NacosConfigManager configService;

    @Resource
    private NacosConfigProperties configProps;

    @Resource
    private DynamicRouterListener dynamicRouterListener;

    private static final String ROUTES_CONFIG = "routes-config.json";

    @Override
    public void afterPropertiesSet() throws Exception {
        String routes = configService.getConfigService().getConfig(ROUTES_CONFIG, configProps.getGroup(),10000);
        dynamicRouterListener.receiveConfigInfo(routes);

        configService.getConfigService().addListener(ROUTES_CONFIG, configProps.getGroup(), dynamicRouterListener);
    }
}
