package com.example.userTask.user_task.hello.api;

import org.pcollections.PMap;
import org.pcollections.PVector;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.serialization.CompressedJsonable;

import lombok.Value;

@Value
@JsonDeserialize
public final class Basket implements CompressedJsonable{
	private static final long serialVersionUID = -4562316901290289958L;
	public final String uuid;
	public final String userUuid;
    public final PVector<PMap<String, String>> items;
    public final String subTotal;
    public final String tax;
    public final String total;
    
    
    @JsonCreator
    public Basket(String uuid, String userUuid, PVector<PMap<String, String>> items, String subTotal, String tax, String total) {
        this.uuid = uuid;
        this.userUuid = userUuid;
    	this.items = Preconditions.checkNotNull(items, "items");
        this.subTotal = subTotal;
        this.tax = tax;
        this.total = total;
    }
 
    
    
}
