//package com.epam.brest.testing.dao;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//@Configuration
//public class JavaConfig {
//    @Bean
//    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
//        return new NamedParameterJdbcTemplate(getDriverManagerDataSource());
//    }
//    @Bean
//    protected DataSourceTransactionManager dataSourceTransactionManager(){
//        return new DataSourceTransactionManager(getDriverManagerDataSource());
//    }
//    @Bean
//    protected SubjectDaoJpaImpl subjectDaoJpa(){
//        return new SubjectDaoJpaImpl(namedParameterJdbcTemplate());
//    }
//    @Bean
//    protected StudetDaoImpl studetDao(){
//        return new StudetDaoImpl(namedParameterJdbcTemplate());
//    }
//    @Value("org.h2.Driver")
//    private String driverClass;
//    @Value("jdbc:h2:mem:test_db;MODE=MYSQL;DB_CLOSE_DELAY=-1")
//    private String jdbcUrl;
//    @Value("pa")
//    private String jdbcUserName;
//    @Value("1")
//    private String jdbcPassword;
//
////    @Value("classpath:dbschema.sql")
////    private Resource dbschemaSqlScript;
////    @Value("classpath:test-data.sql")
////    private Resource testDataSqlScript;
//
//    /**
//     * <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
//     */
//    @Bean(name = "dataSource")
//    public DriverManagerDataSource getDriverManagerDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(driverClass);
//        dataSource.setUrl(jdbcUrl);
//        dataSource.setUsername(jdbcUserName);
//        dataSource.setPassword(jdbcPassword);
//        return dataSource;
//    }
//
//
////    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
////        <property name="driverClassName" value="org.h2.Driver"/>
////        <property name="url" value="jdbc:h2:mem:test_db;MODE=MYSQL;DB_CLOSE_DELAY=-1"/>
////        <property name="username" value="sa"/>
////        <property name="password" value=""/>
////    </bean>
//// <bean id="namedParameterJdbcTemplate"
////    class="org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate">
////        <constructor-arg name="dataSource" ref="dataSource"/>
////    </bean>
////
////    <bean id="subjectDao" class="SubjectDaoJpaImpl">
////        <constructor-arg ref="namedParameterJdbcTemplate"/>
////    </bean>
////
////    <bean id="studentDao" class="StudetDaoImpl">
////        <constructor-arg ref="namedParameterJdbcTemplate"/>
////    </bean>
////    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
////        <property name="dataSource" ref="dataSource"/>
////    </bean>
//}
