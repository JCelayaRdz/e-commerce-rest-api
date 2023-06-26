package es.jcelayardz.ecommercerestapi.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid", nullable = false, unique = true)
    private Integer orderId;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private Date date;

    private Date deliveredAt;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    }, optional = false)
    @JoinColumn(name = "customerUsername", referencedColumnName = "username")
    private Customer customer;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    }, optional = false)
    @JoinColumn(name = "addressid", referencedColumnName = "addressid")
    private Address address;

    public Order() {
    }

    public Order(OrderStatus status, Date date, Date deliveredAt) {
        this.status = status;
        this.date = date;
        this.deliveredAt = deliveredAt;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(Date deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order = {\n" +
                "orderId=" + orderId +
                ", status=" + status +
                ", date=" + date +
                ", deliveredAt=" + deliveredAt +
                "}\n";
    }
}
