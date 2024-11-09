package com.abclimited.springbatch.config;


import com.abclimited.springbatch.batchprocessor.BatchAuthorProcessor;
import com.abclimited.springbatch.batchprocessor.BookTitleProcessor;
import com.abclimited.springbatch.batchprocessor.BookWriter;
import com.abclimited.springbatch.entity.BookEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;


@Configuration
public class BatchConfig {

    @Bean
    public Job bookReaderJob(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new JobBuilder("bookReadJob" , jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(chunkStep(jobRepository,transactionManager))
                .build();

    }

    @Bean
    public Step chunkStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("bookReaderStep", jobRepository).<BookEntity,BookEntity>
                chunk(10,transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }


    @StepScope
    @Bean
    public ItemWriter<BookEntity> writer(){
        return new BookWriter();
    }

    @Bean
    public ItemProcessor<BookEntity,BookEntity> processor() {
        CompositeItemProcessor<BookEntity,BookEntity> processors = new CompositeItemProcessor<>();
        processors.setDelegates(List.of(new BookTitleProcessor(),new BatchAuthorProcessor()));
        return processors;
    }

    @Bean
    @StepScope
    public FlatFileItemReader<BookEntity> reader(){
        return new FlatFileItemReaderBuilder<BookEntity>()
                .name("bookReader")
                .resource(new ClassPathResource("book_data.csv"))
                .delimited()
                .names(new String[]{"title","author","year_of_publish"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>(){{
                    setTargetType(BookEntity.class);
                }})
                .build();
    }
}

