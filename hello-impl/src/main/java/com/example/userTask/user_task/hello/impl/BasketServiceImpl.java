package com.example.userTask.user_task.hello.impl;

import javax.inject.Inject;

import com.example.userTask.user_task.hello.api.Basket;
import com.example.userTask.user_task.hello.api.BasketService;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;

import akka.Done;
import akka.NotUsed;

public class BasketServiceImpl implements BasketService {

	private final PersistentEntityRegistry persistentEntityRegistry;

	@Inject
	public BasketServiceImpl(PersistentEntityRegistry persistentEntityRegistry) {
		this.persistentEntityRegistry = persistentEntityRegistry;
		persistentEntityRegistry.register(BasketEntity.class);
	}

	private PersistentEntityRef<BasketCommand> entityRef(String id) {
		return persistentEntityRegistry.refFor(BasketEntity.class, id);
	}

	@Override
	public ServiceCall<NotUsed, Basket> getBasketByUUID(String uuid) {
		return request -> entityRef(uuid).ask(BasketCommand.Get.INSTANCE)
				.thenApply(basket -> convertBasket(uuid, basket));
	}

	private Basket convertBasket(String uuid, BasketState basketState) {
		Basket basket = new Basket(uuid, basketState.userUuid, basketState.items, basketState.subTotal, basketState.tax,
				basketState.total);
		return basket;
	}

	@Override
	public ServiceCall<Basket, Done> addItem(String uuid) {
		return basket -> entityRef(uuid).ask(new BasketCommand.UseBasketValues(basket.uuid, basket.userUuid,
				basket.items, basket.subTotal, basket.tax, basket.total));
	}
}
