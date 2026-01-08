package com.example.apigateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class QlinhFilter extends AbstractGatewayFilterFactory<QlinhFilter.Config> {
    public QlinhFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            var response = exchange.getResponse();

            String key = exchange.getRequest().getHeaders().get("key").get(0);

            if (key == null || !key.equals("qlinh")) {
                exchange.getResponse().setStatusCode(HttpStatusCode.valueOf(403));
                exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
                String res = """
                        {
                                stt: 403,
                                err: has some errs,
                                mess: key is invalid
                        }
                        """;
                return response.writeWith(Mono.just(response.bufferFactory().wrap(res.getBytes())));
            }

            return chain.filter(exchange);
        };
    }

    public static class Config {

    }
}
