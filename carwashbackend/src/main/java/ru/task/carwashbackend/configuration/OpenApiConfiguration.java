package ru.task.carwashbackend.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Value("${developer-server}")
    private String developerUrl;

    @Value("${production-server}")
    private String productionUrl;

    @Bean
    public OpenAPI configureOpenApi() {

        Server developerServer = new Server();
        developerServer.setUrl(developerUrl);
        developerServer.setDescription("Developer server");

        Server productionServer = new Server();
        productionServer.setUrl(productionUrl);
        productionServer.setDescription("Production Server");

        Info info = new Info()
                .title("Тестовое задание для компании AISA")
                .version("1.0")
                .description("Этот API описывает взаимодействие с приложением автомойки.");

        return new OpenAPI().info(info).servers(List.of(developerServer, productionServer));
    }
}
