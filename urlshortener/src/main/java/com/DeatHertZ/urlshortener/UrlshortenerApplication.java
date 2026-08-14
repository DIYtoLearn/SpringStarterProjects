package com.DeatHertZ.urlshortener;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class UrlshortenerApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(UrlshortenerApplication.class);

		// Set custom banner via lambda
		application.setBanner((environment, sourceClass, out) -> {
			out.println("================================");
			out.println("        GET SHORTER URL's    ");
			out.println("================================");
		});

		application.run(args);
	}
}