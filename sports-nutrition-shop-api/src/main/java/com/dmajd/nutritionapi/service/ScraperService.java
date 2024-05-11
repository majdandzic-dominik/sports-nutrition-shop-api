package com.dmajd.nutritionapi.service;

import com.dmajd.nutritionapi.entity.Product;

public interface ScraperService
{
    void scrape();
    Product scrapeProductInfo(String url);
}
