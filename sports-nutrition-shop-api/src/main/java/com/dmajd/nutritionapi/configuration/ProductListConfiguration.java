package com.dmajd.nutritionapi.configuration;

import com.dmajd.nutritionapi.entity.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ProductListConfiguration
{
    @Bean
    public HashMap<String, ArrayList<Product>> products()
    {
        return new HashMap<>();
    }
}
