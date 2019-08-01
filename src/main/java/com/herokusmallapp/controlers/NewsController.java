package com.herokusmallapp.controlers;

import com.herokusmallapp.entity.News;
import com.herokusmallapp.repositories.NewsRepository;
import com.herokusmallapp.services.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NewsController {


    private final NewsRepository newsRepository;
    private final NewsService newsService;


    @DeleteMapping("/deletenews")
    void removeNews(@RequestParam Long id) {
        this.newsRepository.delete(id);
    }

    @GetMapping("/news")
    public List<News> getNews() {
        return newsRepository.findByOrderByIdDesc();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, path = "/addnews")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNews(@RequestPart("file") MultipartFile file, @RequestPart("news") News news) throws IOException {
        String contentType = file.getContentType();
        if(contentType.equals("text/xml")) {
            newsService.persistNews(news);
            return;
        }
        newsService.persistNews(file.getBytes(), news);
    }
}
