package com.example.learning.orders;

import com.example.learning.UserContextService;
import com.example.learning.carts.Cart;
import com.example.learning.carts.CartService;
import com.example.learning.products.ProductRepository;
import com.example.learning.users.Customer;
import com.example.learning.users.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class OrdersService {

    private final UserContextService userContextService;
    private final OrderRepository ordersRepository;
    private final UserRepository<Customer> userRepository;
    private final CartService cartService;
    private final ProductRepository productRepository;

    public OrdersService(UserContextService userContextService,
                         OrderRepository ordersRepository,
                         UserRepository<Customer> userRepository,
                         CartService cartService,
                         ProductRepository productRepository) {
        this.userContextService = userContextService;
        this.ordersRepository = ordersRepository;
        this.userRepository = userRepository;
        this.cartService = cartService;
        this.productRepository = productRepository;
    }

    Order placeOrder() {
        Cart cart = userContextService.getCart();
        String loggedUserEmail = userContextService.getLoggedUserEmail();
        Customer customer = userRepository.findByUsername(loggedUserEmail).orElse(null);

        cart.getOrderLines()
                .stream()
                .peek(p -> p.getProduct().setStockAmount(p.getProduct().getStockAmount() - p.getQuantity()))
                .map(OrderLine::getProduct).forEach(productRepository::save);

        Order order = ordersRepository.save(new Order(Objects.requireNonNull(customer).getUsername(), cartService.calculateTotalCartPrice(cart), customer.getUserAddress(), customer.getUserAddress(), LocalDateTime.now(), cart.getOrderLines(), customer, OrderStatus.NEW));
        userContextService.clearCart();
        return order;
    }
}
