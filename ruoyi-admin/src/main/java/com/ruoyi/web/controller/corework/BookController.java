package com.ruoyi.web.controller.corework;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.corework.domain.Book;
import com.ruoyi.corework.service.BookService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName:BookController
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/16-16:38
 * @Version 1.0
 */
@RestController
@RequestMapping("/book")
@Api("图书管理")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/bookList")
    public AjaxResult getBookList(Book book) {
        return AjaxResult.success(bookService.selectBookList(book));
    }
}
