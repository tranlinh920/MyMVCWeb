package com.my.config;

import java.util.Arrays;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig {

	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		Cache productsCache = new ConcurrentMapCache("products");
//		Cache menuListCache = new ConcurrentMapCache("menuList");
//		cacheManager.setCaches(Arrays.asList(singlePostSeoUrlCache, menuListCache));
		cacheManager.setCaches(Arrays.asList(productsCache));
		return cacheManager;
	}
}
