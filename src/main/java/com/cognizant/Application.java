package com.cognizant;

import java.util.Scanner;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.service.RelationalObjectService;

public class Application {

	static int childId;
	public static void main(String[] args) throws CustomException {

		try {
		// creating applicaiton context
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		RelationalObjectService relationalObjectService = ctx.getBean("relationalObjectService",
				RelationalObjectService.class);
		//closing the application context
		ctx.close();
		
		// Using scanner to get input from the user to find the immediate and ultimate parent
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the Child id : ");
		childId = in.nextInt();
		in.close();
		relationalObjectService.getParentsByInMemory(childId);
		relationalObjectService.getParentByQuery(childId);
		}catch (NullPointerException e) {
			throw new CustomException("Child id does not exists");
		}
	}

}
