package com.dmajd.nutritionapi.service;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Service;

@Service
public class PolleoScraperService implements ScraperService
{
    private static final String URL = "https://polleosport.hr/proteini/whey-protein/";

    private final FirefoxDriver driver;

    PolleoScraperService(FirefoxDriver driver)
    {
        this.driver = driver;
    }

    @Override
    public void scrape()
    {

    }

    @Override
    public void scrapeProductInfo(String url)
    {

    }
}
