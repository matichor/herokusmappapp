package com.herokusmallapp.services;


import com.herokusmallapp.entity.News;
import com.herokusmallapp.repositories.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;

    public void persistNews(byte[] file, News news) {
        news.setTeammatePicture(file);
        newsRepository.save(news);
    }

    public void persistNews( News news) {
        newsRepository.save(news);
    }
}
