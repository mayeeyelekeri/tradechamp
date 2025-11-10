package com.mine.tradechamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class TradechampApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradechampApplication.class, args);
	}

	@Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl("https://financialmodelingprep.com/stable/quote-short?apikey=sfGC7h1zUyt8eN45pldwxhEewQ9JpbhF")
                .build();
    }
}
