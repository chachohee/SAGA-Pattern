package com.market.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductEndpoint {

	private final ProductService productService;

	// Order에서 생성한 market.product 큐에 있는 메시지를 가져옴
	@RabbitListener(queues = "${message.queue.product}")
	public void receiveMessage(DeliveryMessage deliveryMessage) {
		// 주문 생성됐으니 재고 차감
		productService.reduceProductAmount(deliveryMessage);
		log.info("PRODUCT RECEIVE:{}", deliveryMessage.toString());
	}

	@RabbitListener(queues="${message.queue.err.product}")
	public void receiveErrorMessage(DeliveryMessage deliveryMessage) {
		log.info("ERROR RECEIVE !!!");
		productService.rollbackProduct(deliveryMessage);
	}
}
