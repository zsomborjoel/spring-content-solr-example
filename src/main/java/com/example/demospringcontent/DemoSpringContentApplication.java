package com.example.demospringcontent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters={
		@ComponentScan.Filter(type = FilterType.REGEX,
				pattern = {
						".*MongoConfiguration",
				})
})
public class DemoSpringContentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringContentApplication.class, args);
	}

}
