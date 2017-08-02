package cn.net.communion.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.net.communion.datasource.MultipleDataSource;
import cn.net.communion.datasource.MultipleDataSourceContextHolder;

@Configuration
@ConfigurationProperties(prefix = "aop.multiple")
public class MultipleDataSourceConfiguration {
    private Logger logger = Logger.getLogger(MultipleDataSourceConfiguration.class);
    private Map<String, Map<String, String>> datasources;

    @SuppressWarnings("unchecked")
    @Bean
    public DataSource dataSource() {
        Object defaultDataSource = null;
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        for (String name : datasources.keySet()) {
            try {
                Map<String, String> config = datasources.get(name);
                Class<DataSource> dataSourceType =
                        (Class<DataSource>) Class.forName(config.get("type"));
                DataSource datasource =
                        DataSourceBuilder.create().driverClassName(config.get("driver-class-name"))
                                .url(config.get("url")).username(config.get("username"))
                                .password(config.get("password")).type(dataSourceType).build();
                if (name.equals("default")) {
                    defaultDataSource = datasource;
                } else {
                    targetDataSources.put(name, datasource);
                    MultipleDataSourceContextHolder.dataSourceNames.add(name);
                }
            } catch (ClassNotFoundException e) {
                logger.error("please check datasources config!!!");
                System.exit(1);
            }
        }
        MultipleDataSource multipleDataSource = new MultipleDataSource();
        multipleDataSource.setTargetDataSources(targetDataSources);
        multipleDataSource.setDefaultTargetDataSource(defaultDataSource);
        return multipleDataSource;

    }

    // @Bean
    // public MultipleDataSource multipleDataSource() {
    // return new MultipleDataSource();
    // }

    public Map<String, Map<String, String>> getDatasources() {
        return datasources;
    }

    public void setDatasources(Map<String, Map<String, String>> datasources) {
        this.datasources = datasources;
    }

}
