package buildWeek.entities;


import buildWeek.enums.SellerType;

import javax.persistence.*;

@Entity
@Table(name = "sellers")
public class Seller {

    //attributi
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "licensed")
    private boolean licensed;

    @Column(name = "seller_type")
    @Enumerated(EnumType.STRING)
    private SellerType sellerType;

    //costruttore di default e costruttore con parametri
    public Seller() {
    }

    public Seller(boolean licensed, SellerType sellerType) {
        this.licensed = licensed;
        this.sellerType = sellerType;
    }

    // getters and setters tranne setId (generato automaticamente)
    public int getId() {
        return id;
    }


    public boolean isLicensed() {
        return licensed;
    }

    public void setLicensed(boolean licensed) {
        this.licensed = licensed;
    }

    public SellerType getSellerType(String sellerType) {
        return this.sellerType;
    }

    public void setSellerType(SellerType sellerType) {
        this.sellerType = sellerType;
    }

    @Override
    public String toString() {
        return "Seller {" +
                "id=" + id +
                ", licensed=" + licensed +
                ", sellerType=" + sellerType +
                '}';
    }
}
