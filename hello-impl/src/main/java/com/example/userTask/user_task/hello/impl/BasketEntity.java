package com.example.userTask.user_task.hello.impl;

import java.util.Optional;

import com.example.userTask.user_task.hello.impl.BasketCommand.UseBasketValues;
import com.example.userTask.user_task.hello.impl.BasketEvent.ItemAdded;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;

import akka.Done;

public class BasketEntity extends PersistentEntity<BasketCommand, BasketEvent, BasketState> {

	@Override
	public PersistentEntity<BasketCommand, BasketEvent, BasketState>.Behavior initialBehavior(
			Optional<BasketState> snapshotState) {
		BasketState state = snapshotState.orElse(BasketState.EMPTY);
        BehaviorBuilder b = newBehaviorBuilder(state);
        b.setCommandHandler(UseBasketValues.class, (cmd, ctx) ->
        ctx.thenPersist(new ItemAdded(cmd.uuid, cmd.userUuid,cmd.items, cmd.subTotal, cmd.tax, cmd.total),
                evt -> ctx.reply(Done.getInstance()))
        		);
        b.setEventHandler(ItemAdded.class, evt -> 
         new BasketState(evt.uuid, evt.userUuid, evt.items, evt.subTotal, evt.tax, evt.total)
        		);
		return b.build();
	}

	

}
