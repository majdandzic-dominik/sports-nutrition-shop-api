package com.dmajd.nutritionapi.configuration;

import jakarta.annotation.PostConstruct;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfiguration
{
    @PostConstruct
    void postConstruct()
    {
        System.setProperty("webdriver.firefox.driver", "/resources/static/geckodriver.exe");
    }

    @Bean
    public FirefoxDriver driver()
    {
        // stop selenium from opening actual browser
        final FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("-headless");
        return new FirefoxDriver(firefoxOptions);
    }
}
