package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import com.example.demo.model.News;

public interface NewsService {
	void saveNews(News news);
	List<HashMap<String ,Object>> findByCode(int code);
}
