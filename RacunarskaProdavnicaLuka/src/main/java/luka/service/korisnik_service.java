package luka.service;

import java.sql.Connection;
import java.sql.SQLException;
import luka.dao.korisnik_dao;
import luka.data.korisnik;
import luka.exception.prodavnica_exception;
import java.util.List;
import luka.dao.rm;

public class korisnik_service {

    private static final korisnik_service instance = new korisnik_service();
    private final korisnik_dao korisnikDAO = korisnik_dao.getInstance(); 

    private korisnik_service() {}

    public static korisnik_service getInstance() {
        return instance;
    }

    public List<korisnik> getAllKorisnici() throws prodavnica_exception {
        return korisnikDAO.getAllKorisnici();  
    }
    
    public korisnik findCustomer(String username) throws prodavnica_exception {
        Connection con = null;
        try {
            con = rm.getConnection();

            return korisnik_dao.getInstance().find(username, con);

        } catch (SQLException ex) {
            throw new prodavnica_exception("Failed to find customer with username " + username, ex);
        } finally {
            rm.closeConnection(con);
        }
    }
    
    /*
    public void addNewCustomer(korisnik k) throws prodavnica_exception {
        Connection con = null;
        try {
            con = rm.getConnection();

            
            con.setAutoCommit(false);

            korisnik_dao.getInstance().insert(k, con);

            con.commit();
        } catch (SQLException ex) {
            rm.rollbackTransactions(con);
            throw new prodavnica_exception("Failed to add new customer " + k, ex);
        } finally {
            rm.closeConnection(con);
        }
    }
*/
}

