package cn.net.communion.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cn.net.communion.annotation.TargetDataSource;

@Service
public class Test {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void defaultTest(String sql, String arg) {
        System.out.println(jdbcTemplate.queryForObject(sql, String.class, arg));
    }

    @TargetDataSource(name = "ds1")
    public void ds1Test(String sql, String arg) {
        System.out.println(jdbcTemplate.queryForObject(sql, String.class, arg));
    }

    @TargetDataSource(name = "ds2")
    public void ds2Test(String sql, String arg) {
        System.out.println(jdbcTemplate.queryForObject(sql, String.class, arg));
    }
}
