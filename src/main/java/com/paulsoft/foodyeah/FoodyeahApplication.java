package com.paulsoft.foodyeah;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodyeahApplication {

	public static void main(String[] args) {

		SpringApplication.run(FoodyeahApplication.class, args);
		String urlPage = "https://ajaflorez-coder.github.io/";

		WebDriver webDriver;
		String pathDriver = "driver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", pathDriver);

		webDriver = new ChromeDriver();
		webDriver.get(urlPage);

		String titulo = webDriver.getTitle();
		System.out.println(titulo);
	}

}
