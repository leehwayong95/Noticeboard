package com.emgram.noticeboard.Configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(basePackages="com.emgram.noticeboard.Dao")
@EnableTransactionManagement

public class Config {
	

	@Bean
    public  SqlSessionFactory sqlSessionFactory(DataSource dataSource)throws Exception {
        final SqlSessionFactoryBean sessionFactory =new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver =new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:Mapper/*.xml"));
        return sessionFactory.getObject();
    }
    
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory)throws Exception {
      final SqlSessionTemplate sqlSessionTemplate =new SqlSessionTemplate(sqlSessionFactory);
      return sqlSessionTemplate;
    }
}
	
	/*
	public class DatabaseConfiguration {
		@Bean
		@ConfigurationProperties(prefix = "spring.datasource.hikari")
		public HikariConfig hikariConfig() {
			return new HikariConfig();
		}

		@Bean
		public DataSource dataSource() {
			DataSource dataSource = new HikariDataSource(hikariConfig());
			log.info("datasource : {}", dataSource);
			return dataSource;
		}
	}
	
	
	
	@Autowired
	private ApplicationContext applicationContext;
	
	
	 @Bean
	    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
	        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
	        sessionFactory.setDataSource(dataSource);
	       PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	       // sessionFactory.setMapperLocations(applicationContext.getResource("classpath:Mapper/*.xml"));
	       sessionFactory.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*.xml"));
	        return sessionFactory.getObject();
	        //applcationContext==resolver.getResources
	    }
	    @Bean
	    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
	     // final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
	      return sqlSessionTemplate(sqlSessionFactory);
	    }
	 	*/
	
