package cn.edu.zjut.qiandao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("cn.edu.zjut.qiandao.mapper")
@EnableTransactionManagement
public class QiandaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(QiandaoApplication.class, args);
	}

}
