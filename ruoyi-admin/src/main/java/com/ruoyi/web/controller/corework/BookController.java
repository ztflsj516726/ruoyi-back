package com.ruoyi.web.controller.corework;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultVo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.corework.domain.Book;
import com.ruoyi.corework.domain.dto.BookDeleteDto;
import com.ruoyi.corework.domain.dto.BookQueryDto;
import com.ruoyi.corework.domain.dto.BookSaveDto;
import com.ruoyi.corework.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "图书管理")
public class BookController extends BaseController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "图书列表")
    @GetMapping(value = "/bookList")
    public TableDataInfo<Book> getBookList(BookQueryDto bookDto) {
        startPage();
        List<Book> list = bookService.selectBookList(bookDto);
        return getDataTable(list);
    }

    @ApiOperation(value = "新增/修改图书")
    @PostMapping(value = "/saveBook")
    public AjaxResultVo<Void> saveBook(@RequestBody BookSaveDto bookSaveDto) {
        Integer num = bookService.saveBook(bookSaveDto);
        if (num > 0) {
            return AjaxResultVo.success();
        }
        return AjaxResultVo.error();
    }

    @ApiOperation("图书详情")
    @GetMapping("/detail/{id}")
    public AjaxResultVo<Book> detail(@ApiParam(value = "图书id", required = true) @PathVariable Long id) {
        Book book = bookService.detail(id);
        return AjaxResultVo.success(book);
    }

    @ApiOperation("图书删除")
    @PostMapping("/delete")
    public AjaxResultVo<Void> detail(@ApiParam(value = "图书id列表", required = true) @RequestBody BookDeleteDto bookDeleteDto) {
        System.out.println("bookDeleteDto"+bookDeleteDto);
        Integer num = bookService.deleteBookByIds(bookDeleteDto.getIds());
        if (num > 0) {
            return AjaxResultVo.success();
        }
        return AjaxResultVo.error();
    }
}
