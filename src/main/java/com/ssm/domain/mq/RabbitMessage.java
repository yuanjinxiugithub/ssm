package com.ssm.domain.mq;

import java.io.Serializable;
import java.util.Map;

public class RabbitMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String exchange;// 交换器

	private Object params;//传递的消息

	private String routeKey;// 路由key

	private Map<String, Object> attribute;//属性

	public RabbitMessage() {

	}

	@Override
	public String toString() {
		return "RabbitMessage [exchange=" + exchange + ", params=" + params + ", routeKey=" + routeKey + ", attribute="
				+ attribute + "]";
	}

	public RabbitMessage(String exchange, String routeKey, Map<String, Object> attribute, Object params) {
		this.params = params;
		this.exchange = exchange;
		this.routeKey = routeKey;
		this.attribute = attribute;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}

	public String getRouteKey() {
		return routeKey;
	}

	public void setRouteKey(String routeKey) {
		this.routeKey = routeKey;
	}

	public Map<String, Object> getAttribute() {
		return attribute;
	}

	public void setAttribute(Map<String, Object> attribute) {
		this.attribute = attribute;
	}

}
