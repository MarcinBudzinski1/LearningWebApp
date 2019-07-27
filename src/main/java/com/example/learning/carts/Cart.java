package com.example.learning.carts;

import com.example.learning.orders.OrderLine;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Cart {

    private List<OrderLine> orderLines = Lists.newArrayList();
}
