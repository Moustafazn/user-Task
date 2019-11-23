package com.example.userTask.user_task.hello.impl;

import com.example.userTask.user_task.hello.api.BasketService;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

public class BasketModule extends AbstractModule implements ServiceGuiceSupport {
	@Override
	protected void configure() {
		bindService(BasketService.class, BasketServiceImpl.class);
	}
}
