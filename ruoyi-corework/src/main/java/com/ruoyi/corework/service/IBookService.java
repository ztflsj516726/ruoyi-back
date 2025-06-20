package com.ruoyi.corework.service;

import com.ruoyi.corework.domain.Book;
import com.ruoyi.corework.domain.dto.BookQueryDto;
import com.ruoyi.corework.domain.dto.BookSaveDto;

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
public interface IBookService {

    // 查询图书列表
    List<Book> selectBookList(BookQueryDto bookDto);

    // 新增图书
    Integer saveBook(BookSaveDto bookSaveDto);

    // 图书详情
    Book detail(Long id);

    // 图书删除
    Integer deleteBookByIds(List<Integer> ids);
}
