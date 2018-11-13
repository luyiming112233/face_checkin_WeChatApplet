package cn.edu.zjut.qiandao;

import cn.edu.zjut.qiandao.conf.Configuration;
import cn.edu.zjut.qiandao.domain.Login;
import cn.edu.zjut.qiandao.domain.User;
import cn.edu.zjut.qiandao.domain.UserRespository;
import cn.edu.zjut.qiandao.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QiandaoApplicationTests {
	@Autowired
	Configuration conf;
	@Autowired
	UserService userService;
	@Autowired
	UserRespository userRespository;

	@Test
	public void contextLoads() {
		String secret=conf.getAppsecret();
		System.out.println(secret);
	}

}
