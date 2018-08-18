package com.bridgelabz.eureka;

/***************************************************************************************
 * Created By:Medini P.D 
 * Date:- 11/08/2018 
 * Purpose: UserToDoEurekaServerApplication main class
 ***************************************************************************************/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ToDoUserEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoUserEurekaClientApplication.class, args);
	}
}
