package com.mth.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class GatewayService {

    @Resource
    private ApplicationEventPublisher publisher;

    @Resource
    RouteDefinitionWriter routeDefinitionWriter;

    public void updateRoutes(List<RouteDefinition> routes) {
        System.out.println(routes);
        if (CollectionUtils.isEmpty(routes)) {
            log.info("NO ROUTERS FOUND");
            return;
        }
        routes.forEach(r->{
            try {
                routeDefinitionWriter.save(Mono.just(r)).subscribe();
                publisher.publishEvent(new RefreshRoutesEvent(this));
            } catch (Exception e){
                log.error("cannot update route, id={}",r.getId());
            }
        });
    }
}
