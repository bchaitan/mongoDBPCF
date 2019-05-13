package com.navyaa.mongoDBPCF.demo.config;

import java.util.Properties;

import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@Profile("cloud")
public class MongoConfig extends AbstractCloudConfig {

    @Bean
    public MongoDbFactory mongoDbFactory() {
    	CloudFactory cloudFactory = new CloudFactory();
        Cloud cloud = cloudFactory.getCloud();
        return cloud.getSingletonServiceConnector(MongoDbFactory.class, null);
    }
    
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }
    
    @Bean
    public Properties cloudProperties() {
    	 Properties properties = properties();
    	 System.out.println(properties.toString());
         return properties;
    }

}