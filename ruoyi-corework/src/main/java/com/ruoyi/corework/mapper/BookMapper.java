package com.ruoyi.corework.mapper;

import com.ruoyi.corework.domain.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName:BookMapper
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/16-16:20
 * @Version 1.0
 */
@Mapper
public interface BookMapper {
    // 查询所有图书
    List<Book> selectBookList(Book book);

//    // 根据ID查询图书
//    Book selectBookById(Long id);
//
//    // 新增图书
//    int insertBook(Book book);
//
//    // 修改图书
//    int updateBook(Book book);
//
//    // 删除图书
//    int deleteBookById(Long id);
//
//    // 批量删除图书
//    int deleteBookByIds(Long[] ids);
}
