package com.dog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

//@Configuration
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableJpaRepositories
//@EnableAutoConfiguration
@EnableTransactionManagement
@EntityScan(basePackages = "com.dog.beans")
@ComponentScan(basePackages = {"com.dog.service","com.dog.domain"})

/***
 * 该 ServiceApplication 保留为后续引入 dubbo 扣子
 * 单个项目暂时可以使用web下的 DogWebApplication 来启动springboot
 * 【注意】DogServiceApplication类一定要在扫描包的外面，否则spring-boot只会扫到启动类当前目录下所有类文件
 */
@ImportResource({"classpath:dubbo/spring-dubbo.xml"})
public class DogServiceApplication  {

	public static void main(String[] args) {
		SpringApplication.run(DogServiceApplication.class, args);

		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
