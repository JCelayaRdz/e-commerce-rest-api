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