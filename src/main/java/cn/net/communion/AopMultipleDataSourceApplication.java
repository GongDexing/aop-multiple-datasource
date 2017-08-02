package cn.net.communion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import cn.net.communion.test.Test;

@Configuration
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopMultipleDataSourceApplication implements CommandLineRunner {
    @Autowired
    private Test aopTestService;

    public static void main(String[] args) {
        SpringApplication.run(AopMultipleDataSourceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length < 3) {
            return;
        }
        String ds = args[0];
        if (ds.equals("default")) {
            aopTestService.defaultTest(args[1], args[2]);
        } else if (ds.equals("ds1")) {
            aopTestService.ds1Test(args[1], args[2]);
        } else if (ds.equals("ds2")) {
            aopTestService.ds2Test(args[1], args[2]);
        }
    }
}
