package com.myapps.springbatchjdbcpagingreader.step;

import com.myapps.springbatchjdbcpagingreader.domain.Customer;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    public StepConfig(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Step jdbcPagingStep(
        ItemReader<Customer> jdbcPagingReader,
        ItemWriter<Customer> jdbcPagingWriter
    ) {
        return stepBuilderFactory
            .get("jdbcPagingStep")
            .<Customer, Customer>chunk(1)
            .reader(jdbcPagingReader)
            .writer(jdbcPagingWriter)
            .build();
    }
}
