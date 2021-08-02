package com.epam.springcashmachine;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringCashMachineApplication {

	@Bean
	public PropertyUtilsBean getPUB(){
		return new PropertyUtilsBean();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringCashMachineApplication.class, args);
	}

}
