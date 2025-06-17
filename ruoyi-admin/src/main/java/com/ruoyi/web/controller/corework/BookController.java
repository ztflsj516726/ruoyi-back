package com.ruoyi.web.controller.corework;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.corework.domain.dto.BookDto;
import com.ruoyi.corework.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class BookController extends BaseController {

    @Autowired
    private BookService bookService;

   @ApiOperation("图书列表")
    @GetMapping("/bookList")
    public TableDataInfo getBookList(BookDto bookDto) {
        startPage();
        return getDataTable(bookService.selectBookList(bookDto));
    }
}
