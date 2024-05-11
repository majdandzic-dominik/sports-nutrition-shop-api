package com.dmajd.nutritionapi.service;

import com.dmajd.nutritionapi.entity.Product;
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
    private final List<Product> productList;

    @PostConstruct
    void postConstruct()
    {
        System.out.println("Getting data from Polleo Sport...");
        scrape();
        System.out.println(productList);
        System.out.println("Finished getting data from Polleo Sport!");
    }

    PolleoScraperService(FirefoxDriver driver, List<Product> productList)
    {
        this.driver = driver;
        this.productList = productList;
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
            for (WebElement product : products)
            {
                links.add(product.findElement(By.tagName("a")).getAttribute("href"));
            }

            // scrape data for all products
            for(String link : links)
            {
                productList.add(scrapeProductInfo(link));
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
    public Product scrapeProductInfo(String url)
    {
        driver.get(url);

        String name = "";
        String price = "";
        String amount = null;
        boolean isAvailable = false;

        try
        {
            // get name, price and amount
            WebElement content = driver.findElement(By.id("product_details_section"));
            name = content.findElement(By.tagName("h1")).getText();
            price = content.findElement(By.className("final-price-product-page")).getText();
            amount = content.findElement(By.className("variant-product-text-link-selected")).getText();

            // check if product is available
            if (content.findElements(By.className("btn-notify-me")).isEmpty())
            {
                isAvailable = true;
            }

        } catch (NoSuchElementException e)
        {
            System.out.println(e.getMessage());
        } catch (Exception e)
        {
            System.out.println("An error occurred");
            System.out.println(e.getMessage());
        }

        return new Product(name, price, amount, isAvailable);
    }
}
