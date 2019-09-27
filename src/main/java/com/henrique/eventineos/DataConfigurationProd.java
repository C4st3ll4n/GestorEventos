package com.henrique.eventineos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;


@Configuration
public class DataConfigurationProd {

    @Profile("prod")
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://g5xq0etbbo0i9sqx:cmgv1y9kcj2rf4th@s9xpbd61ok2i7drv.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/quyzobj2ull6ovod");
        dataSource.setUsername("g5xq0etbbo0i9sqx");
        dataSource.setPassword("cmgv1y9kcj2rf4th");
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MYSQLDialect");
        adapter.setPrepareConnection(true);
        return adapter;
    }

}