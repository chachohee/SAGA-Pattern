package com.market.order;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryMessage {
	private UUID orderId;
	private UUID paymentId;
	private String userId;
	private Integer productId;
	private Integer productQuantity;
	private Integer payAmount; // 결제할 금액
	private String errorType;
}

