package com.dmajd.nutritionapi.service;

import jakarta.annotation.PostConstruct;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class PolleoScraperService implements ScraperService
{
    private static final String URL = "https://polleosport.hr/proteini/whey-protein/";

    private final FirefoxDriver driver;

    @PostConstruct
    void postConstruct()
    {
        scrape();
    }

    PolleoScraperService(FirefoxDriver driver)
    {
        this.driver = driver;
    }

    @Override
    public void scrape()
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get(URL);

        try
        {
            // close cookies window
            driver.findElement(By.id("gdpr-cookie-accept")).click();
            // wait some time for cookies window to close
            TimeUnit.SECONDS.sleep(2);

            // close promotion window if it exists
            if (driver.findElement(By.id("myModal")) != null)
            {
                driver.findElement(By.id("myModal")).findElement(By.className("close")).click();

                // wait some time for promotion window to close
                TimeUnit.SECONDS.sleep(2);
            }

            // select option to display all products
            Select numOfProducts = new Select(driver.findElement(By.id("input-limit")));
            numOfProducts.selectByValue("100");
            // wait some time for products to load
            TimeUnit.SECONDS.sleep(3);

            // get all products
            WebElement mainContent = driver.findElement(By.id("main"));
            List<WebElement> products = mainContent.findElements(By.className("name"));

            // get links for all products
            List<String> links = new ArrayList<>();
            for(WebElement product : products)
            {
                links.add(product.getAttribute("href"));
            }
            
        } catch (NoSuchElementException e)
        {
            System.out.println(e.getMessage());
        } catch (Exception e)
        {
            System.out.println("An error occurred");
            System.out.println(e.getMessage());
        }
        driver.quit();
    }

    @Override
    public void scrapeProductInfo(String url)
    {

    }
}
