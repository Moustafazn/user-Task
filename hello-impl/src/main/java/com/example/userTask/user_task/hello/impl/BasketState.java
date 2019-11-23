package com.example.userTask.user_task.hello.impl;

import org.pcollections.PMap;
import org.pcollections.PVector;
import org.pcollections.TreePVector;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.serialization.CompressedJsonable;

import lombok.Value;

@SuppressWarnings("serial")
@Value
@JsonDeserialize
public final class BasketState implements CompressedJsonable{
	public final String uuid;
	public final String userUuid;
    public final PVector<PMap<String, String>> items;
    public final String subTotal;
    public final String tax;
    public final String total;
    
    
    @JsonCreator
    BasketState(String uuid, String userUuid, PVector<PMap<String, String>> items, String subTotal, String tax, String total) {
        this.uuid = uuid;
        this.userUuid = userUuid;
    	this.items = Preconditions.checkNotNull(items, "items");
        this.subTotal = subTotal;
        this.tax = tax;
        this.total = total;
    }
 
    
    public BasketState addItem(BasketState basketState, PMap<String, String> item) {
        basketState.items.plus(item);
        return basketState;
    }


    public static final BasketState EMPTY = new BasketState("", "", TreePVector.empty(), "", "", "");

}
