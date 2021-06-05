package com.elasticsearch.study.repository;

import com.elasticsearch.study.po.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;



@Component
public interface BookRepository extends ElasticsearchRepository<Book, Long> {
}
