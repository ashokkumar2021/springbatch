package com.abclimited.springbatch.batchprocessor;

import com.abclimited.springbatch.entity.BookEntity;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class BookTitleProcessor implements ItemProcessor<BookEntity,BookEntity> {

    private static final Logger log = LoggerFactory.getLogger(BookTitleProcessor.class);

    @Override
    public BookEntity process(BookEntity item) throws Exception {
        log.info("Processing title for {}",item);
        item.setTitle(item.getTitle().toUpperCase());
        return item;
    }
}
