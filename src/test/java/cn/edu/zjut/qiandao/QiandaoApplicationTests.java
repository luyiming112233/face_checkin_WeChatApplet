package cn.edu.zjut.qiandao;

import cn.edu.zjut.qiandao.conf.Configuration;

import cn.edu.zjut.qiandao.dao.*;
import cn.edu.zjut.qiandao.dto.SignInstanceDTO;
import cn.edu.zjut.qiandao.entity.Sign;
import cn.edu.zjut.qiandao.entity.SignInstance;
import cn.edu.zjut.qiandao.entity.Student;
import cn.edu.zjut.qiandao.entity.StudentSign;
import cn.edu.zjut.qiandao.mapper.SignMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QiandaoApplicationTests {
	@Autowired
	Configuration conf;
	@Autowired
	SuggestRepository suggestRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	StudentFaceRepository userFaceRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SignMapper signMapper;
	@Autowired
	StudentSignRepository  studentSignRepository;
	@Autowired
	SignInstanceRespository signInstanceRespository;
	@Value("${weixin.appid}")
	String appid;
	@Test
	public void contextLoads() {
		String secret=conf.getGetfeatureurl();
		System.out.println(secret);
	}
   @Test
	public void testmybatis() {
	  // List<Sign> signList=signMapper.listMonthSigns("oasdN5e88EgUWsn04BSQhG9r5kUI","2018-12%");
	  List<Sign> signList=signMapper.listDailySigns("oasdN5e88EgUWsn04BSQhG9r5kUI","2018-12-09");
	   System.out.println("size是"+signList.size());
	   for (int i=0;i<signList.size();i++)
          System.out.println(signList.get(i).getDate());

   }
   @Test
	public void testjpa(){
	   SignInstance signInstance=signInstanceRespository.getOneBySigninstanceId(9);
	   SignInstanceDTO signInstanceDTO=new SignInstanceDTO();
	   BeanUtils.copyProperties(signInstance,signInstanceDTO);
	   System.out.println(signInstanceDTO.getLongitude());
   }
}
