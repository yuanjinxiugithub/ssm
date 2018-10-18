package com.ssm.qt;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component(value="demo")
public class QuartzDemo {
	 public void execute() throws Exception {
	        System.out.println("定时demo:"+new Date() );
	        System.out.println("hello world");
	    }
	 
	 public void execute2() throws Exception {
	        System.out.println("定时dem2222o:"+new Date() );
	        System.out.println("hello world");
	    }

}
