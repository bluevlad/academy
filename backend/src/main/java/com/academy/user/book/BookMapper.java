package com.academy.user.book;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BookMapper {
    List<Book> findActive(@Param("keyword") String keyword);

    Optional<Book> findById(@Param("bookId") String bookId);
}
