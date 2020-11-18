package cn.codenest.redists.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan("cn.codenest.redists.mapper")
public class MybatisConfig {
    @Bean(name = "serviceDataSource")
    @ConfigurationProperties(prefix = "service.datasource")
    public DataSource businessDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 创建工厂
     *@param dataSource
     *@throws Exception
     *@return SqlSessionFactory
     */
    @Bean(name = "serviceSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("serviceDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * 创建事务
     *@param dataSource
     *@return DataSourceTransactionManager
     */
    @Bean(name = "serviceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("serviceDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 创建模板
     *@param sqlSessionFactory
     *@return SqlSessionTemplate
     */
    @Bean(name = "serviceSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("serviceSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
