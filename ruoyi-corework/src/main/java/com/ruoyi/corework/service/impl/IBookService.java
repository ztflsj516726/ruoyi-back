package com.ruoyi.corework.service.impl;

import com.github.pagehelper.PageHelper;
import com.ruoyi.corework.domain.Book;
import com.ruoyi.corework.domain.dto.BookDto;
import com.ruoyi.corework.mapper.BookMapper;
import com.ruoyi.corework.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:IBookService
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/16-16:34
 * @Version 1.0
 */
@Service
public class IBookService implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> selectBookList(BookDto bookDto) {
        return bookMapper.selectBookList(bookDto);
    }
}
