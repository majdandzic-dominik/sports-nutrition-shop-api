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
public class GymBeamScraperService implements ScraperService
{
    private static final String URL = "https://gymbeam.hr/whey-protein-sirutke";

    private final FirefoxDriver driver;
    private final List<Product> productList;

    public GymBeamScraperService(FirefoxDriver driver, List<Product> productList)
    {
        this.driver = driver;
        this.productList = productList;
    }

    public void scrape()
    {
        System.out.println("Getting data from GymBeam...");

        // set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get(URL);

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

            // get links for all product pages
            List<String> links = new ArrayList<>();
            for (WebElement product : products)
            {
                links.add(product.getAttribute("href"));
            }

            // scrape data for all products
            for (String link : links)
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
        System.out.println("Finished getting data from GymBeam!");
    }

    public Product scrapeProductInfo(String url)
    {
        driver.get(url);

        String name = "";
        String price = "";
        String amount = null;
        boolean isAvailable = false;
        try
        {
            // wait some time for elements to load
            // it takes a little bit of time for the amount option to get selected on load
            TimeUnit.SECONDS.sleep(3);

            // get name and price
            WebElement content = driver.findElement(By.className("product-info-main"));
            name = content.findElement(By.className("page-title")).getText();
            price = content.findElement(By.className("price")).getText();

            // sometimes there is no options for amount
            // so we need to check if it exists before setting it
            if (!content.findElements(By.name("super_attribute[310]")).isEmpty())
            {
                amount = new Select(content.findElement(By.name("super_attribute[310]"))).getFirstSelectedOption().getText();
            }

            // check if product is available
            if (!content.findElements(By.className("available")).isEmpty())
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
