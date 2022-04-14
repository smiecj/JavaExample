package com.example.restservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// swagger 配置
// 地址: http://localhost:port/swagger-ui.html (springfox 2.x)
@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .apiInfo(apiInfo())
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("测试构建 swagger 文档")
            .description("api文档描述")
            .termsOfServiceUrl("服务描述")
            .version("1.0")
            .build();
    }
    
}
