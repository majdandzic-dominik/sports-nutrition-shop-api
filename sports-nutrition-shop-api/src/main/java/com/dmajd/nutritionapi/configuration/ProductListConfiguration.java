package com.dmajd.nutritionapi.configuration;

import com.dmajd.nutritionapi.entity.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ProductListConfiguration
{
    @Bean
    public List<Product> productList()
    {
        return new ArrayList<Product>();
    }
}
