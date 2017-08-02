package cn.net.communion.datasource;

import java.util.ArrayList;
import java.util.List;

public class MultipleDataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static List<String> dataSourceNames = new ArrayList<>(10);

    public static void setDataSourceName(String dataSourceName) {
        contextHolder.set(dataSourceName);
    }

    public static String getDataSourceName() {
        return contextHolder.get();
    }

    public static void resetDataSourceName() {
        contextHolder.remove();
    }

    public static boolean containsDataSource(String dataSourceName) {
        return dataSourceNames.contains(dataSourceName);
    }
}
