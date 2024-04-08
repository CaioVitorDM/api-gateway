package ufrn.imd.br.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ufrn.imd.br.filter.AuthenticationFilter;
import ufrn.imd.br.filter.NotFoundFilter;

@Configuration
public class GatewayConfig {

    @Autowired
    AuthenticationFilter authenticationFilter;

    @Autowired
    NotFoundFilter notFoundFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("ms-auth-server", r -> r.path("/api/auth-server/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://ms-auth-server"))
                .route("ms-protocols", r -> r.path("/api/protocols/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://ms-protocols"))
                .route("ms-files", r -> r.path("/api/files/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://ms-files"))
                .route("ms-exams", r -> r.path("/api/exams/**")
                        .uri("lb://ms-exams"))
                .build();
    }
}
