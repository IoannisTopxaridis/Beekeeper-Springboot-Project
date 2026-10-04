package com.Beehive.beekeeper;


import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;

@Configuration
@EnableCaching
@EnableScheduling

public class CacheConfiguration {

    private final static int FIVE_MINUTES = 300_000;

    @Bean
    public CacheManager cacheManager(){
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        cacheManager.setAllowNullValues(false);
        cacheManager.setCacheNames(Arrays.asList("locationCache", "beehiveCache"));
        System.out.println("All the locations" + cacheManager.getCacheNames());
        return cacheManager;
    }

    @CacheEvict(value = "locationCache", allEntries = true)
    @Scheduled(fixedDelay= FIVE_MINUTES, initialDelay = 0)
    public void evictProductCache(){
        System.out.println("Evicting Location Cache");
    }


}

