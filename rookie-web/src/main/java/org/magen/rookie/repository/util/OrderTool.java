package org.magen.rookie.repository.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

public class OrderTool {
	private List<Order> orders = new ArrayList<Order>();
	    
	public void add(Order order) {
		orders.add(order);
	}
	    
	public void build(Criteria criteria) {
		for(Order order : orders) {
			criteria.addOrder(order);
	    }
	}
}
