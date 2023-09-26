package com.string.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectApplicationTests {

	@Test
	void contextLoads() {
		String result = getEmailDomain("mariobd38@stringwiz.com");
		System.out.println(result);
	}

	String getEmailDomain(String email) {
		return email.substring(email.indexOf("@") + 1,email.indexOf("."));
	}

}
