package com.cov.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.cov.beans.Student;
import com.cov.beans.Traingle;

public class App_01 {

	public static void main(String[] args) {
	
		ApplicationContext ctxt=new FileSystemXmlApplicationContext("spring.xml");
		Student student1=(Student)ctxt.getBean("stud");
		/*Student student2=(Student)ctxt.getBean("stud");
		if(student1==student2) {
			System.out.println("singleton");
		}
		else {
			System.out.println("multiple");
		}*/
		System.out.println("Id:"+student1.getId()+",Name:"+student1.getName());
		Traingle traingle=(Traingle)ctxt.getBean("tri");
		System.out.println(traingle.getPointA()+","+traingle.getPointB()+","+traingle.getPointC());
		System.out.println("Thank youuu");
	}

}
