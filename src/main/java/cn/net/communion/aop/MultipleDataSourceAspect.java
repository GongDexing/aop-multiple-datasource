package cn.net.communion.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.net.communion.annotation.TargetDataSource;
import cn.net.communion.datasource.MultipleDataSourceContextHolder;

@Aspect
@Component
@Order(0)
public class MultipleDataSourceAspect {
    private Logger logger = Logger.getLogger(MultipleDataSourceAspect.class);

    @Around("@annotation(ds)")
    public Object processed(ProceedingJoinPoint point, TargetDataSource ds) throws Throwable {
        try {
            logger.info("choose " + ds.name() + " datasource");
            String dataSourceName = ds.name();
            if (MultipleDataSourceContextHolder.containsDataSource(dataSourceName)) {
                MultipleDataSourceContextHolder.setDataSourceName(dataSourceName);
            }
            return point.proceed();
        } finally {
            logger.info("reset datasource");
            MultipleDataSourceContextHolder.resetDataSourceName();
        }

    }
}
