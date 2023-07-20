package es.jcelayardz.ecommercerestapi.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addressid", nullable = false, unique = true)
    private Integer addressId;

    @Column(nullable = false, length = 56)
    private String country;

    @Column(name = "postalcode", nullable = false, length = 32)
    private String postalCode;

    @Column(nullable = false, length = 35)
    private String city;

    @Column(nullable = false, length = 95)
    private String street;

    @Column(nullable = false, length = 50)
    private String details;

    @Column(name = "isvisible", nullable = false)
    private boolean isVisible;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private Set<Order> orders;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    }, optional = false)
    @JoinColumn(name = "customerusername", referencedColumnName = "username")
    private Customer customer;

    public Address() {
    }

    public Address(String country, String postalCode, String city, String street, String details) {
        this.country = country;
        this.postalCode = postalCode;
        this.city = city;
        this.street = street;
        this.details = details;
        this.isVisible = true;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Address = {\n" +
                "addressId=" + addressId +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", details='" + details + '\'' +
                ", isVisible=" + isVisible +
                "}\n";
    }
}
