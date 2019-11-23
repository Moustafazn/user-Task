package com.example.userTask.user_task.hello.impl;


import org.pcollections.PMap;
import org.pcollections.PVector;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.javadsl.persistence.AggregateEvent;
import com.lightbend.lagom.javadsl.persistence.AggregateEventShards;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTag;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTagger;
import com.lightbend.lagom.serialization.Jsonable;

import lombok.Value;

public interface BasketEvent extends Jsonable, AggregateEvent<BasketEvent> {

	AggregateEventShards<BasketEvent> TAG = AggregateEventTag.sharded(BasketEvent.class, 10);

	@SuppressWarnings("serial")
	@Value
	@JsonDeserialize
	public final class ItemAdded implements BasketEvent {

		public final String uuid;
		public final String userUuid;
		public final String subTotal;
		public final String tax;
		public final String total;
		public final PVector<PMap<String, String>> items;

		@JsonCreator
		public ItemAdded(String uuid, String userUuid, PVector<PMap<String, String>> items, String subTotal, String tax,
				String total) {
			this.userUuid = Preconditions.checkNotNull(userUuid, "userUuid");
			this.tax = Preconditions.checkNotNull(tax, "tax");
			this.subTotal = Preconditions.checkNotNull(subTotal, "subTotal");
			this.total = Preconditions.checkNotNull(total, "total");
			this.uuid = Preconditions.checkNotNull(uuid, "uuid");
			this.items = Preconditions.checkNotNull(items, "items");

		}

		public String getUuid() {
			return uuid;
		}

		public String getUserUuid() {
			return userUuid;
		}

		public String getSubTotal() {
			return subTotal;
		}

		public String getTax() {
			return tax;
		}

		public String getTotal() {
			return total;
		}

		public PVector<PMap<String, String>> getItems() {
			return items;
		}

	}

	@Override
	default AggregateEventTagger<BasketEvent> aggregateTag() {
		return TAG;
	}

}
