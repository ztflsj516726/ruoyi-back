package com.ruoyi.corework.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.corework.domain.Book;
import com.ruoyi.corework.domain.dto.BookQueryDto;
import com.ruoyi.corework.domain.dto.BookSaveDto;
import com.ruoyi.corework.mapper.BookMapper;
import com.ruoyi.corework.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public List<Book> selectBookList(BookQueryDto bookDto) {
        return bookMapper.selectBookList(bookDto);
    }

    @Override
    public Integer saveBook(BookSaveDto bookSaveDto) {
        Book book = new Book();
        BeanUtils.copyProperties(bookSaveDto, book);
        if (book.getStatus() == null) {
            book.setStatus("0");
        }
        if (book.getId() != null) {
            book.setCreateBy(SecurityUtils.getUsername());
            book.setCreateTime(LocalDateTime.now());
            // 修改图书
            return bookMapper.updateBook(book);
        } else {
            book.setUpdateBy(SecurityUtils.getUsername());
            book.setUpdateTime(LocalDateTime.now());
            return bookMapper.insertBook(book);
        }
    }

    @Override
    public Book detail(Long id) {
        return bookMapper.detail(id);
    }

    @Override
    public Integer deleteBookByIds(List<Integer> ids) {
        return bookMapper.deleteBookByIds(ids);
    }
}
