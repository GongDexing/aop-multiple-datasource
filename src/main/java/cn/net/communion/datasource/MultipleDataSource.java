package cn.net.communion.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return MultipleDataSourceContextHolder.getDataSourceName();
    }

}
