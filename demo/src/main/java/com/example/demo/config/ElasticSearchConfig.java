package com.example.demo.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {

    @Value("${spring.elasticsearch.host}")
    private String host;

    @Value("${spring.elasticsearch.port}")
    private Integer port;

    @Value("${spring.data.elasticsearch.cluster-name}")
    private String clusterName;
    
    private Logger logger = LoggerFactory.getLogger(ElasticSearchConfig.class);
    
    @Bean
    public TransportClient client() {
        
    	TransportClient client = null;
        
    	try {
        	Settings settings = Settings.builder()
        	        .put("cluster.name", clusterName)
        	        .build();
        	
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
        
    	} catch (UnknownHostException e) {
    		logger.error("UnknownHostException: elasticsearch");
            e.printStackTrace();
        }
        
    	logger.debug("elasticsearch connected...");

        return client;
    }

}