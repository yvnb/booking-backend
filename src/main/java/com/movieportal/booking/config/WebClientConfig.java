package com.movieportal.booking.config;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

@Configuration
public class WebClientConfig {

	private static final Logger log = LoggerFactory.getLogger(WebClientConfig.class);

	// configuring empty base url as absolute url will be used at time of invocation
	@Value("${tmdb.base.url}")
	private String tmdbBaseUrl;
	public static final int TIMEOUT = 50000;

	@Bean
	public WebClient webClient() throws SSLException {

		SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
				.build();
		final TcpClient tcpClient = TcpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
				.secure(sslContextSpec -> sslContextSpec.sslContext(sslContext)).doOnConnected(connection -> {
					connection.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
					connection.addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
				});
		return WebClient.builder().baseUrl(tmdbBaseUrl)
				.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient))).build();

	}

	// This method returns filter function which will log request data
	@SuppressWarnings("unused")
	private static ExchangeFilterFunction logRequest() {
		return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
			log.info("Request: {} {}", clientRequest.body().toString(), clientRequest.url());
			clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
			return Mono.just(clientRequest);
		});
	}
}
