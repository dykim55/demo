package com.example.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Repository;

import com.example.demo.model.News;

@Repository
public class NewsRepository {
	
    @Resource
    TransportClient client;
	
    public void save(News news) {
    }
    
	public List<HashMap<String ,Object>> findByCode(int code) {
		
		SearchResponse response = client.prepareSearch("meta")
                .setIndices("news")
                .setTypes("article")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("code", code))
                .get();

        SearchHits hits = response.getHits();		
		
        List<HashMap<String ,Object>> result = new ArrayList<HashMap<String, Object>>();
        for (SearchHit hit : hits) {
        	HashMap<String ,Object> hMap = new HashMap<String, Object>();
        	hMap.put("code", hit.getSource().get("code"));
        	hMap.put("title", hit.getSource().get("title"));
        	hMap.put("discription", hit.getSource().get("discription"));
        	result.add(hMap);
        }

        return result;		
	}
}
