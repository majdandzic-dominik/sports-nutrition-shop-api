package com.dmajd.nutritionapi.rest;

import com.dmajd.nutritionapi.entity.Product;
import com.dmajd.nutritionapi.service.GymBeamScraperService;
import com.dmajd.nutritionapi.service.PolleoScraperService;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductRestController
{
    @Autowired
    private List<Product> productList;

    @Autowired
    private FirefoxDriver driver;
    @Autowired
    private PolleoScraperService polleoScraperService;
    @Autowired
    private GymBeamScraperService gymBeamScraperService;

    @PostConstruct
    public void postConstruct()
    {
        gymBeamScraperService.scrape();
        polleoScraperService.scrape();

        // close driver after scraping
        driver.quit();
    }

    @GetMapping("/protein")
    public List<Product> getProducts()
    {
        return productList;
    }
}
