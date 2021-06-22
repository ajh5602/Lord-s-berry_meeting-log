package com.mdm.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackageClasses = SofaceServerApplication.class)
@SpringBootApplication
public class SofaceServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SofaceServerApplication.class, args);
	}

}
