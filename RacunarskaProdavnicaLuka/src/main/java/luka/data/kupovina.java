package luka.data;

import java.io.Serializable;

public class kupovina implements Serializable{

    private int idPurchase;
    private korisnik customer;
    private proizvod product;

    public kupovina() {
    }

    public kupovina(int idPurchase, korisnik customer, proizvod product) {
        this.idPurchase = idPurchase;
        this.customer = customer;
        this.product = product;
    }

    public kupovina(korisnik customer, proizvod product) {
        this.customer = customer;
        this.product = product;
    }

    public int getIdPurchase() {
        return idPurchase;
    }

    public korisnik getCustomer() {
        return customer;
    }

    public proizvod getProduct() {
        return product;
    }

    public void setCustomer(korisnik customer) {
        this.customer = customer;
    }

    public void setProduct(proizvod product) {
        this.product = product;
    }
    
    

}
