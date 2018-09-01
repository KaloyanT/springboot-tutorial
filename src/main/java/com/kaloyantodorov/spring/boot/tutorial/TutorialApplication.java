package com.kaloyantodorov.spring.boot.tutorial;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableCaching
public class TutorialApplication {
	
	/* 
	 * Defining a Bean. The name of the Bean will be the same as the method name
	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	Equivalent to: 
	<beans>
		<bean name="bCryptPasswordEncoder" class="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder"/>
	</beans>
	
	With Dependencies: Simply call the dependency method
	@Bean
    public Foo foo() {
        return new Foo(bar());
    }
    
    @Bean
    public Bar bar() {
        return new Bar();
    }
    */ 
	
	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

    public static void main(String[] args) {
        SpringApplication.run(TutorialApplication.class, args);
    }
}
