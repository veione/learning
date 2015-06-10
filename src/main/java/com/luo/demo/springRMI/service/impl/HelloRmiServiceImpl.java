package com.luo.demo.springRMI.service.impl;

import org.springframework.stereotype.Component;
import com.luo.demo.springRMI.service.HelloRmiService;

@Component("helloRmiServiceImpl")
public class HelloRmiServiceImpl implements HelloRmiService {

	public int getAdd(int a, int b) {
		return a + b;
	}
}