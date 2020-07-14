package com.dewen.project;


import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.dewen.project.mapper")
public class DenwenAppliction {
	public static final Logger LOGGER = LoggerFactory.getLogger(DenwenAppliction.class);

	public static void main(String[] args) {
		SpringApplication.run(DenwenAppliction.class, args);
  
	}

}
