package com.ruoyi.corework.service;

import com.ruoyi.corework.domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:BookService
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/16-16:34
 * @Version 1.0
 */
public interface BookService {

    // 查询图书列表
    List<Book> selectBookList(Book book);
}
