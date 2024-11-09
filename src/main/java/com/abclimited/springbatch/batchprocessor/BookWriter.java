package com.abclimited.springbatch.batchprocessor;

import com.abclimited.springbatch.entity.BookEntity;
import com.abclimited.springbatch.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class BookWriter implements ItemWriter<BookEntity> {

    private static final Logger log = LoggerFactory.getLogger(BookWriter.class);
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void write(Chunk<? extends BookEntity> chunk) throws Exception {
        log.info("Writing: {}",chunk.getItems().size());
        bookRepository.saveAll(chunk.getItems());
    }
}
