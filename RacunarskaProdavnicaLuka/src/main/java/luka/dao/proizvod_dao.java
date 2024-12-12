package luka.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import luka.data.proizvod;
import luka.exception.prodavnica_exception;

public class proizvod_dao {

    private static final proizvod_dao instance = new proizvod_dao();

    private proizvod_dao() {}

    public static proizvod_dao getInstance() {
        return instance;
    }

    public List<proizvod> getAllProizvodi() throws prodavnica_exception {
        List<proizvod> proizvodi = new ArrayList<>();
        String sql = "SELECT * FROM proizvod";  
        try (Connection con = rm.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                proizvod p = new proizvod();
                p.setproizvod_id(rs.getInt("proizvod_id"));
                p.setNaziv(rs.getString("naziv"));
                p.setCena(rs.getInt("cena"));
                p.setVrsta_opreme(rs.getString("vrsta_opreme"));
                p.setStanje_na_lageru(rs.getInt("stanje_na_lageru"));
                
                proizvodi.add(p);
            }
        } catch (SQLException ex) {
            throw new prodavnica_exception("Error while fetching proizvodi", ex);
        }
        return proizvodi;
    }
    
    public proizvod find(String naziv, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        proizvod p = null;
        try {
            ps = con.prepareStatement("SELECT * FROM proizvod where naziv=?");
            ps.setString(1, naziv);
            rs = ps.executeQuery();
            if (rs.next()) {
               
                p = new proizvod(rs.getInt("proizvod_id"), naziv, rs.getInt("cena"),rs.getString("vrsta_opreme"), rs.getInt("stanje_na_lageru"));
            }
        } finally {
            rm.closeResources(rs, ps);
        }
        return p;
    }
    
    
    
    
}