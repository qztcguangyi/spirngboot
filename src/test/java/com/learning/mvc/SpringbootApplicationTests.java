package com.learning.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/** 
 * @Description:
 * @Param: 可以看到我们使用的是org.junit.jupiter.api.Test,
 * 而这个模块是Junit5才有的，并且Junit官网对这个模块使用有一定的要求，也就是IDEA的版本必须要是2017.3及以后的
 * @return:
 * @Author: Gavin Zhang
 * @Date: 2020/11/5 
*/ 
@SpringBootTest
class SpringbootApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void test() {
		System.out.println("a");
	}

}
