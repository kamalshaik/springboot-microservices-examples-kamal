package com.skh;

import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

	/*
	 * @Bean public GlobalFilter customFilter() { return (exchange, chain) -> {
	 * System.out.println("Pre Filter: " + exchange.getRequest().getPath()); return
	 * chain.filter(exchange).then(Mono.fromRunnable(() -> {
	 * System.out.println("Post Filter: " + exchange.getResponse().getStatusCode());
	 * })); }; }
	 */
}
