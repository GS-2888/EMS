package com.ems.bootloader;


import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import io.jaegertracing.Configuration;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.jaegertracing.internal.samplers.ProbabilisticSampler;
import io.opentracing.Tracer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
@ComponentScan(basePackages="com.ems")
@EntityScan("com.ems.infrastructure.persistance.jpa.entity")
//@EnableJpaRepositories("com.ems.infrastructure.persistance.jpa.repository")
public class EMSApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EMSApplication.class, args);
	}
	
	@Bean
	public Docket swaggerConfiguration() {
		
		
		return  new Docket(DocumentationType.SWAGGER_2)				
				.select()
				.paths(PathSelectors.ant("/api/*"))
				.apis(RequestHandlerSelectors.any())
				.build()
				.apiInfo(apiDetails());
		
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo(
			"EMS API information",
			"EMS provides api to employee details",
			"1.0",
			"Free to use",
			new Contact("GS","http://localhost:8080/api","ems@gs.com"),
			"API License",
			"http://localhost:8080/api",
			Collections.emptyList());
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	  @Bean 
	  public Tracer jaegerTracer() { 
	  Configuration.SamplerConfiguration sampler = Configuration.SamplerConfiguration.fromEnv().withType("const").withParam(1);
	  Configuration.ReporterConfiguration repoter =  Configuration.ReporterConfiguration.fromEnv().withLogSpans(true);
	  Configuration config = new Configuration("emsService").withSampler(sampler).withReporter(repoter);
	  return config.getTracer();
	  
	  }
	 

}
