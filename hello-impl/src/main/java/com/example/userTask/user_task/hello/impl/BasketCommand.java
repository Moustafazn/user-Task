package com.example.userTask.user_task.hello.impl;


import org.pcollections.PMap;
import org.pcollections.PVector;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import com.lightbend.lagom.serialization.CompressedJsonable;
import com.lightbend.lagom.serialization.Jsonable;

import akka.Done;
import lombok.Value;

public interface BasketCommand extends Jsonable {
	@Value
	@JsonDeserialize
	final class UseBasketValues implements BasketCommand, CompressedJsonable, PersistentEntity.ReplyType<Done> {
		/**
		 * 
		 */
		private static final long serialVersionUID = -8295797283190073315L;
		public final String uuid;
		public final String userUuid;
		public final String subTotal;
		public final PVector<PMap<String, String>> items;
		public final String tax;
		public final String total;

		@JsonCreator
		public UseBasketValues(String uuid, String userUuid, PVector<PMap<String, String>> items,String subTotal, String tax, String total) {
			this.userUuid = Preconditions.checkNotNull(userUuid, "userUuid");
			this.tax = Preconditions.checkNotNull(tax, "tax");
			this.subTotal = Preconditions.checkNotNull(subTotal, "subTotal");
			this.total = Preconditions.checkNotNull(total, "total");
			this.uuid = Preconditions.checkNotNull(uuid, "uuid");
	    	this.items = Preconditions.checkNotNull(items, "items");
		}
	}


	enum Get implements BasketCommand, PersistentEntity.ReplyType<BasketState> {
		INSTANCE
	}
}
