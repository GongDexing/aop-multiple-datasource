package cn.net.communion.datasource;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.rule.OutputCapture;

import cn.net.communion.AopMultipleDataSourceApplication;

// @RunWith(SpringRunner.class)
// @SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataSourceRegisterTest {
    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void case1_defaultTest() {
        String sn = "000001";
        String sql = "select sn from tbl_admin where sn=? limit 1";
        AopMultipleDataSourceApplication.main(new String[] {"default", sql, sn});
        String output = this.outputCapture.toString();
        assertThat(output).contains(sn);
    }

    @Test
    public void case2_ds1Test() {
        String username = "admin-test";
        String sql = "select username from tbl_admin where username=? limit 1";
        AopMultipleDataSourceApplication.main(new String[] {"ds1", sql, username});
        String output = this.outputCapture.toString();
        assertThat(output).contains(username);
    }

    @Test
    public void case3_ds2Test() {
        String realname = "gongdexing";
        String sql = "select realname from tbl_admin where realname=? limit 1";
        AopMultipleDataSourceApplication.main(new String[] {"ds2", sql, realname});
        String output = this.outputCapture.toString();
        assertThat(output).contains(realname);
    }
}
