package com.example.learning.orders;

import com.example.learning.BaseEntity;
import com.example.learning.LocalDateTimeConverter;
import com.example.learning.users.User;
import com.example.learning.users.UserAddress;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "Orders")
@ToString(exclude = "customer")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity implements Serializable {

    private String customerName;
    private BigDecimal totalCost;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "customer_street")),
            @AttributeOverride(name = "building", column = @Column(name = "customer_building")),
            @AttributeOverride(name = "flat", column = @Column(name = "customer_flat")),
            @AttributeOverride(name = "city", column = @Column(name = "customer_city")),
            @AttributeOverride(name = "country", column = @Column(name = "customer_country")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "customer_postal_code"))})
    private UserAddress customerAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "delivery_street")),
            @AttributeOverride(name = "building", column = @Column(name = "delivery_building")),
            @AttributeOverride(name = "flat", column = @Column(name = "delivery_flat")),
            @AttributeOverride(name = "city", column = @Column(name = "delivery_city")),
            @AttributeOverride(name = "country", column = @Column(name = "delivery_country")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "delivery_postal_code"))})
    private UserAddress deliveryAddress;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime creationDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLine> orderLines;

    @ManyToOne
    private User customer;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

}
