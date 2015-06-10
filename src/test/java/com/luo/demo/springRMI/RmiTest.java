package com.luo.demo.springRMI;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.luo.demo.springRMI.service.HelloRmiService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class RmiTest extends AbstractJUnit4SpringContextTests {
	
	@Resource(name="myClient")
	private HelloRmiService helloService;
	/**
	 * 测试
	 */
	@Test
	public void test(){
		System.out.println( helloService.getAdd(1, 1) );
	}
}
