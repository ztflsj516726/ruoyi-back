package com.ruoyi.corework.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.corework.domain.Book;
import com.ruoyi.corework.domain.dto.BookQueryDto;
import com.ruoyi.corework.domain.dto.BookSaveDto;
import com.ruoyi.corework.mapper.BookMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:BookServiceImpl
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/16-16:34
 * @Version 1.0
 */
@Service
public class BookServiceImpl implements com.ruoyi.corework.service.IBookService {

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
        if (book.getId() == null) {
            book.setCreateBy(SecurityUtils.getUsername());
            book.setCreateTime(DateUtils.getNowDate());
            // 修改图书
            return bookMapper.insertBook(book);
        } else {
            book.setUpdateBy(SecurityUtils.getUsername());
            book.setUpdateTime(DateUtils.getNowDate());
            return bookMapper.updateBook(book);
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
