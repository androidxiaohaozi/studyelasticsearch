package com.elasticsearch.study.controller;

import com.elasticsearch.study.po.Book;
import com.elasticsearch.study.repository.BookRepository;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description book controller
 * @Auther wanghao
 * @Date 2021/6/5 15:35
 */
@RestController
@RequestMapping("book")
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("sava")
    public void save(long id, String title) {
        Book book = new Book();
        book.setId(id);
        book.setReadNum(100);
        book.setTitle(title);
        book.setLabel("java");
        book.setCreateDate(LocalDate.now().toString());
        bookRepository.save(book);
    }

    @GetMapping("saveAll")
    public void saveAll() {
        List<Book> list = new ArrayList<>();
        for (long i = 1; i < 15; i ++) {
            Book book = new Book();
            book.setId(i);
            book.setReadNum(100);
            book.setTitle("springboot整合elasticsearch2021年" + i);
            book.setLabel("java");
            book.setCreateDate(LocalDate.now().toString());
            list.add(book);
        }
        this.bookRepository.saveAll(list);
    }

    @GetMapping("findById")
    public Book findById(Long id) {
        return bookRepository.findById(id).get();
    }

    @GetMapping("findAllById")
    public List<Book> findAllById() {
        List<Long> ids = new ArrayList<>();
        ids.add(5L);
        ids.add(6L);
        ids.add(7L);
        Iterable<Book> iterable = this.bookRepository.findAllById(ids);
        return Lists.newArrayList(iterable);
    }

    @GetMapping("count")
    public Long count() {
        return this.bookRepository.count();
    }

    @GetMapping("findAll")
    public List<Book> findALl() {
        Iterable<Book> iterable = this.bookRepository.findAll();
        return Lists.newArrayList(iterable);
    }

    @GetMapping("deleteById")
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @GetMapping("deleteAll")
    public void deleteAll() {
        this.bookRepository.deleteAll();
    }

    @GetMapping("delete")
    public void delete(Book notice){
        this.bookRepository.delete(notice);
    }

    @GetMapping("query")
    public List<Book> query(String title, Integer pageSize, Integer pageNumber) {
        MatchQueryBuilder title1 = QueryBuilders.matchQuery("title", title);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        boolQueryBuilder.should(title1);

        PageRequest of = PageRequest.of(pageNumber, pageSize);

        Page<Book> search = this.bookRepository.search(boolQueryBuilder, of);

        return Lists.newArrayList(search);
    }

}
