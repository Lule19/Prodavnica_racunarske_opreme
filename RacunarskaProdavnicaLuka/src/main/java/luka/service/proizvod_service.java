package luka.service;

import java.sql.Connection;
import java.sql.SQLException;
import luka.dao.proizvod_dao;
import luka.data.proizvod;
import luka.exception.prodavnica_exception;
import java.util.List;
import luka.dao.rm;

public class proizvod_service {
    
    
    private static final proizvod_service instance = new proizvod_service();
    private final proizvod_dao proizvodDAO = proizvod_dao.getInstance(); // Inicijalizacija DAO klase

    private proizvod_service() {}

    public static proizvod_service getInstance() {
        return instance;
    }

    // Metoda koja vraća sve korisnike
    public List<proizvod> getAllProizvodi() throws prodavnica_exception {
        return proizvodDAO.getAllProizvodi();  // Pozivanje metode iz DAO klase
    }
    
    
    public proizvod findProizvod(String naziv) throws prodavnica_exception {
        Connection con = null;
        try {
            con = rm.getConnection();

            return proizvod_dao.getInstance().find(naziv, con);

        } catch (SQLException ex) {
            throw new prodavnica_exception("Failed to find customer with username " + naziv, ex);
        } finally {
            rm.closeConnection(con);
        }
    }
    
    
    
    
    
}
