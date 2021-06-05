package com.elasticsearch.study.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @Description book 实体
 * @Auther wanghao
 * @Date 2021/6/5 15:25
 */
@Data
@Document(indexName = "books")
public class Book {

    @Id
    private Long id;

    @Field
    private String title;

    @Field
    private String label;

    @Field
    private String createDate;

    @Field
    private Integer readNum;
}
