package com.example.demo.command.saga;

import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.command.order.events.OrderCreatedEvent;
import com.example.demo.command.product.commands.ReduceProductStockCommand;
import com.example.demo.command.product.events.ProductStockReducedEvent;

@Saga
public class OrderProductSaga {

	@Autowired
	private transient CommandGateway commandGateway;

	@StartSaga
	@SagaEventHandler(associationProperty = "orderId")
	public void on(OrderCreatedEvent event) {
		event.getProducts().forEach((productId, number) -> {
			SagaLifecycle.associateWith("productId", productId);
			this.commandGateway.sendAndWait(GenericCommandMessage.asCommandMessage(
					new ReduceProductStockCommand(productId, number))
													.withMetaData(MetaData.with("ownerId", event.getOrderId())));
		});
	}

	@EndSaga
	@SagaEventHandler(associationProperty = "productId")
	public void on(ProductStockReducedEvent event) {}

}
