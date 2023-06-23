package com.project.gateway.config;

import com.project.gateway.filter.CustomFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {

    private final CustomFilter customFilter;

    public FilterConfig(CustomFilter customFilter) {
        this.customFilter = customFilter;
    }

//    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder){
        CustomFilter.Config customConfig = new CustomFilter.Config();

        return routeLocatorBuilder.routes()
                .route("member-service",
                        r -> r.path("/page/**")
                        .filters(f -> f.filter(customFilter.apply(customConfig))
                        )
                        .uri("http://localhost:8101"))
                .build();
    }
}
