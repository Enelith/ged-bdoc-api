package fr.entoria.ged.bdoc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@EnableCaching
public class CachingConfig extends CachingConfigurerSupport {
    public static final String PROPERTY_RESOLVING_CACHE_RESOLVER_BEAN_NAME = "propertyResolvingCacheResolver";

    @Value("${spring.cache.cacheName.token}")
    private String cachePartitionName;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private Environment springEnv;

    @Bean
    @Override
    public CacheManager cacheManager() {
	SimpleCacheManager cacheManager = new SimpleCacheManager();
	Cache cache = new ConcurrentMapCache(cachePartitionName);
	cacheManager.setCaches(Arrays.asList(cache));
	return cacheManager;
    }

    @Bean(PROPERTY_RESOLVING_CACHE_RESOLVER_BEAN_NAME)
    @Override
    public CacheResolver cacheResolver() {
	return new PropertyResolvingCacheResolver(cacheManager, springEnv);
    }
}