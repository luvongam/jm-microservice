package com.jamlech.consumerwebservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumerWebServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(ConsumerWebServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ConsumerWebServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Bean
//    @Profile("!test")
    public CommandLineRunner runner(RestTemplate restTemplate) throws Exception{
        return args -> {
            Quote quote =restTemplate.getForObject(
                    "http://localhost:8080/greeting?name=jamlech", Quote.class
            );
//            assert quote != null;
            log.info(quote.toString());
        };
    }
}
