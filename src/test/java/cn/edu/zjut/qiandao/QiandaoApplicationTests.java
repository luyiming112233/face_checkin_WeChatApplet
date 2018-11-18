package cn.edu.zjut.qiandao;

import cn.edu.zjut.qiandao.conf.Configuration;
import cn.edu.zjut.qiandao.domain.Login;
import cn.edu.zjut.qiandao.domain.Student;
import cn.edu.zjut.qiandao.domain.User;
import cn.edu.zjut.qiandao.domain.UserRespository;
import cn.edu.zjut.qiandao.mapper.UserMapper;
import cn.edu.zjut.qiandao.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QiandaoApplicationTests {
	@Autowired
	Configuration conf;
	@Autowired
	UserService userService;
	@Autowired
	UserRespository userRespository;
	@Autowired
	UserMapper userMapper;
	@Test
	public void contextLoads() {
		String secret=conf.getGetfeatureurl();
		System.out.println(secret);
	}
   @Test
	public void testmybatis() {
	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 //  userMapper.addSuggest("oasdN5e88EgUWsn04BSQhG9r5kUI","我是一个建议",df.format(new Date()));
     // userMapper.updatefeature("12345","111");
	   userMapper.registerface("ffaadd","123");
   }
   @Test
	public void testjpa(){

   }
}
