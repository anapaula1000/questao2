package br.com.sociotorcedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Classe para inicializar a aplicação
 * @author : Ana Paula anapaulasilva1000@gmail.com
 */

@SpringBootApplication
@EnableAutoConfiguration
@EnableFeignClients
@EnableHystrix
@EnableMongoRepositories(basePackages = "br.com.sociotorcedor.repository")
public class ApplicationStarter {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationStarter.class, args);
    }

}