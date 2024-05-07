package com.dmajd.nutritionapi.service;

import jakarta.annotation.PostConstruct;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Service;

@Service
public class ScraperService
{
    private static final String URL = "https://gymbeam.hr/whey-protein-sirutke";

    private final FirefoxDriver driver;

    @PostConstruct
    void postConstruct()
    {
        scrape();
    }

    public ScraperService(final FirefoxDriver driver)
    {
        this.driver = driver;
    }

    public void scrape()
    {
        driver.get(URL);
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(driver.findElement(By.className("product-item-click")).getAttribute("title"));
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        driver.quit();
    }
}
