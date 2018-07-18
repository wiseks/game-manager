package com.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author 吴德基  
	created at 2018年7月10日 下午8:52:37
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan("com.manager.dao")
public class ServerStart {

	public static void main(String[] args) {
		SpringApplication.run(ServerStart.class, args);
	}
}
