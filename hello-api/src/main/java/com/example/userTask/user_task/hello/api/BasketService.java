package com.example.userTask.user_task.hello.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.restCall;

import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.Method;

import akka.Done;
import akka.NotUsed;

public interface BasketService extends Service {

	ServiceCall<NotUsed, Basket> getBasketByUUID(String uuid);

	ServiceCall<Basket, Done> addItem(String uuid);

	default Descriptor descriptor() {
		return named("basket").withCalls(restCall(Method.POST, "/api/basket/{uuid}", this::addItem),
				restCall(Method.GET, "/api/basket/{uuid}", this::getBasketByUUID));
	}

}