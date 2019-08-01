package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.News;
import com.example.demo.repository.NewsRepository;

@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsRepository newsRepository;

	@Override  
	public void saveNews(News news) {
		newsRepository.save(news);
	}

	@Override
	public List<HashMap<String ,Object>> findByCode(int code) {
		return newsRepository.findByCode(code);
	}
  
}
