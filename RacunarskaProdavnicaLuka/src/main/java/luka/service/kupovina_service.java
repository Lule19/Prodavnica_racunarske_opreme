package luka.service;

import java.sql.Connection;
import java.sql.SQLException;
import luka.dao.rm;
import luka.dao.korisnik_dao;
import luka.dao.proizvod_dao;
import luka.dao.kupovina_dao;
import luka.data.korisnik;
import luka.data.proizvod;
import luka.data.kupovina;
import luka.exception.prodavnica_exception;

public class kupovina_service {

    private static final kupovina_service instance = new kupovina_service();

    private kupovina_service() {
    }

    public static kupovina_service getInstance() {
        return instance;
    }

    public void makeKupovina(korisnik customer, proizvod product) throws prodavnica_exception {
        Connection con = null;
        try {
            con = rm.getConnection();
            con.setAutoCommit(false);

            if (product.getStanje_na_lageru() == 0) {
                throw new prodavnica_exception("There are no more products " + product.getNaziv() + " in the store.");
            }

            if (customer.getStanjeRacuna() < product.getCena()) {
                throw new prodavnica_exception("Customer doesn't have enough credit to make a purchase. Customer's credit is " + customer.getStanjeRacuna() + ", price of the product is " + product.getCena());
            }

            //decrease customers credit 
            int newCredit = customer.getStanjeRacuna() - product.getCena();
            customer.setStanjeRacuna(newCredit);
            korisnik_dao.getInstance().update(customer, con);
            
            
            
            //potroseno
            int newPotroseno = customer.getKolicinaPotrosenogNovca() + product.getCena();
            customer.setKolicinaPotrosenogNovca(newPotroseno);
            korisnik_dao.getInstance().updatePotroseno(customer, con);


            //decrease number of this product in store
            product.setStanje_na_lageru(product.getStanje_na_lageru() - 1);
            proizvod_dao.getInstance().update(product, con);

            //track of purchase in database
            kupovina purchase = new kupovina(customer, product);
            kupovina_dao.getInstance().insert(purchase, con);

            con.commit();

            System.out.println("Customer " + customer.getUsername() + " purchased a product " + product.getNaziv() + " for price " + product.getCena());
        } catch (SQLException ex) {
    rm.rollbackTransactions(con);
    System.err.println("SQL Error: " + ex.getMessage());
    throw new prodavnica_exception("Failed to make a purchase.", ex);
        } finally {
            rm.closeConnection(con);
        }
    }
}
