package com.kaloyantodorov.spring.boot.tutorial.util;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TutorialUtils {
	
	private Logger log = LoggerFactory.getLogger(TutorialUtils.class);

	@PostConstruct
	private void init() {
		log.debug("Initializing TutorialUtils");
	}
	
	// Every 15 minutes
	@Scheduled(cron = "0 0/15 * * * ?")
	@Async
	// For Async the method needs to be public or protected
	protected void someAsyncMethod() {
		log.debug("Scheduled Async method called");
	}
}
