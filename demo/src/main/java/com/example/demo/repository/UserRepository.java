package com.example.demo.repository;

import javax.annotation.Resource;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class UserRepository {
	
    @Resource
    TransportClient client;
	
    public void save(User user) throws Exception {

        IndexRequest indexRequest = new IndexRequest("mgr", "users");

        ObjectMapper om = new ObjectMapper();
        byte []json = om.writeValueAsBytes(user);
        indexRequest.source(json);
        IndexResponse r = client.index(indexRequest).actionGet();

        if (r.status() == RestStatus.CREATED) {
            System.out.println("Insert Document : " + user.getUsername());
        }
    }
    
	public User findByUsername(String username) throws Exception {
		
		SearchResponse response = client.prepareSearch("meta")
                .setIndices("mgr")
                .setTypes("users")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("username", username))
                .get();
		
        SearchHits hits = response.getHits();		

        return new User(hits.getAt(0).getSource());
	}
	
}