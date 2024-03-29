package com.example.learning;

import com.example.learning.carts.Cart;
import com.example.learning.orders.OrderLine;
import com.example.learning.products.Product;
import com.example.learning.users.RoleTypeEnum;
import com.google.gson.Gson;
import lombok.Getter;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class UserContextService {

    @Getter
    private Cart cart = new Cart();

    private static Gson gson = new Gson();

    public String getLoggedUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        return authentication.getName();
    }

    public boolean admin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream().anyMatch(e -> RoleTypeEnum.ADMIN.getRoleName().equalsIgnoreCase(e.getAuthority()));
    }

    public boolean user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream().anyMatch(e -> RoleTypeEnum.USER.getRoleName().equalsIgnoreCase(e.getAuthority()));
    }

    public String getCartAsJson() {
        return gson.toJson(cart);
    }


    public void addProductToCart(Product product) {
        List<OrderLine> orderLines = cart.getOrderLines();
        Optional<OrderLine> first = orderLines.stream().filter(e -> e.getProduct().getId().equals(product.getId())).findFirst();
        if (first.isPresent()) {
            first.get().setQuantity(first.get().getQuantity() + 1);
        } else {
            OrderLine orderLine = new OrderLine();
            orderLine.setProduct(product);
            orderLine.setProductPrice(product.getProductPrice());
            orderLine.setQuantity(1);
            orderLines.add(orderLine);
        }
    }

    public void clearCart() {
        cart = new Cart();
    }
}
