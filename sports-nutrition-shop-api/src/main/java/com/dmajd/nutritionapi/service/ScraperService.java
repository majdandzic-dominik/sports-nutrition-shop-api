package com.dmajd.nutritionapi.service;

import jakarta.annotation.PostConstruct;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        // set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        try
        {
            // click button to load all products and wait
            WebElement showMoreBtn = driver.findElement(By.className("amscroll-load-button"));
            showMoreBtn.click();

            // wait some time for elements to load
            TimeUnit.SECONDS.sleep(3);

            // get all products
            WebElement mainContent = driver.findElement(By.id("amasty-shopby-product-list"));
            List<WebElement> products = mainContent.findElements(By.className("product-item-click"));

            for(WebElement product : products)
            {
                System.out.println(product.getAttribute("title"));
            }

        } catch (NoSuchElementException e)
        {
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("An error occured");
            System.out.println(e.getMessage());
        }
        driver.quit();
    }
}
