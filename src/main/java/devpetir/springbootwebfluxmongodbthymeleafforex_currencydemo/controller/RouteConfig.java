package devpetir.springbootwebfluxmongodbthymeleafforex_currencydemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import devpetir.springbootwebfluxmongodbthymeleafforex_currencydemo.Handler.FixerHandler;

@Configuration
public class RouteConfig {
    @Autowired
    private FixerHandler fixerHandler;

    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions.route()
            .GET("/hello", request -> ServerResponse.ok().bodyValue("Hello, from WebFlux 2"))
            /* if startDate and endDate in hardCode */
            //.GET("/timeseries",fixerHandler::getCurrentRate)

            /* if startDate and endDate as parameter in url */
            .GET("/timeseries/{startDate}/{endDate}",fixerHandler::getCurrentRate)
            .build();
    }
    
}
