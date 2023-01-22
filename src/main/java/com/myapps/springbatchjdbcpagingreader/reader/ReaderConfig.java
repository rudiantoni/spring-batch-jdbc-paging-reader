package com.myapps.springbatchjdbcpagingreader.reader;

import com.myapps.springbatchjdbcpagingreader.domain.Customer;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.PostgresPagingQueryProvider;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ReaderConfig {


    private final DataSource dataSource;
    /*
     If you want to use the CustomerRowMapper implementation (at mapper package), uncomment the code below.
     There's more changing needed in order to achieve this throughout the code in this class.
     */
    //private final CustomerRowMapper clientRowMapper;

    public ReaderConfig(
        @Qualifier("batchAppDataSource") DataSource dataSource
        //, CustomerRowMapper clientRowMapper
    ) {
        this.dataSource = dataSource;
        //this.clientRowMapper = clientRowMapper;
    }


    @Bean
    public JdbcPagingItemReader<Customer> jdbcPagingReader(
        PagingQueryProvider queryProvider
        //PagingQueryProvider pgQueryProvider
    ) {
        return new JdbcPagingItemReaderBuilder<Customer>()
            .name("jdbcPagingReader")
            .dataSource(dataSource)
            .queryProvider(queryProvider)
            //.queryProvider(pgQueryProvider)
            .pageSize(1)
            /*
            This mapper must be used only if the domain class attributes are the same as the result set fields
            or columns from the database, or following standard naming conventions such as field_name = fieldName
            beanRowMapper(domain_class) or
            rowMapper(new BeanPropertyRowMapper<domain_class>(domain_class))
             */
            //.beanRowMapper(Customer.class)
            /*
            The mapper below can be used everytime, that's the recommended method, since it can map any result set
            field or column names from the database to any domain class attribute.
            Just need to implement the RowMapper interface.
            */
            //.rowMapper(clientRowMapper)
            .rowMapper(rowMapper())
            .build();
    }

    @Bean
    public PostgresPagingQueryProvider pgQueryProvider() throws Exception {
        Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("id", Order.ASCENDING);

        PostgresPagingQueryProvider queryProvider = new PostgresPagingQueryProvider();
        queryProvider.setSelectClause("select *");
        queryProvider.setFromClause("from clients");
        queryProvider.setSortKeys(sortKeys);
        queryProvider.init(dataSource);

        return queryProvider;
    }

    @Bean
    public SqlPagingQueryProviderFactoryBean queryProvider(
        // This annotation can be used to inject a DataSource in the function
        // Or, you can just inject in the constructor.
        //@Qualifier("batchAppDataSource") DataSource dataSource
    ) {
        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause("select *");
        queryProvider.setFromClause("from clients");
        queryProvider.setSortKey("id");

        return queryProvider;
    }

    private RowMapper<Customer> rowMapper() {
        return new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Customer customer = new Customer();
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setAge(rs.getString("age"));
                customer.setEmail(rs.getString("email"));
                return customer;
            }
        };
    }
}
