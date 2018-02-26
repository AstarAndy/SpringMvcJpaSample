package com.astar.andy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringMvcJpaSampleApplicationTests {

	// Inject the spring container

	@Autowired
	private ApplicationContext springContext;

	@Test
	public void contextLoads() {
		assertThat(springContext.getBeanDefinitionCount()).isGreaterThan(0);
	}

}
