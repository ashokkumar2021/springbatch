package com.abclimited.springbatch.repository;

import com.abclimited.springbatch.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<BookEntity,Long> {
}
