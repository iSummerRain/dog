package com.dog;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.transaction.annotation.EnableTransactionManagement;

//@SpringBootApplication
//web层不加载数据源配置
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@EnableAspectJAutoProxy
//@EnableTransactionManagement
//@EnableJpaRepositories
//@EnableAutoConfiguration
@EntityScan(basePackages = "com.dog.beans")
@ComponentScan(basePackages = {"com.dog.web.controller"})
@ImportResource({"classpath:dubbo/spring-dubbo.xml"})
public class DogWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(DogWebApplication.class, args);
	}
}
