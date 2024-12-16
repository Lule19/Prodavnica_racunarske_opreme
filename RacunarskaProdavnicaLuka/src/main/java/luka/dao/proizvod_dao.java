package luka.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import luka.data.korisnik;
import luka.data.pretraga;
import luka.data.proizvod;
import luka.exception.prodavnica_exception;

public class proizvod_dao {

    private static final proizvod_dao instance = new proizvod_dao();

    private proizvod_dao() {}

    public static proizvod_dao getInstance() {
        return instance;
    }

    public List<proizvod> pretraga(pretraga p) throws prodavnica_exception {
    List<proizvod> filtriraniProizvodi = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM proizvod WHERE 1=1");

    if (p.getDonja_granica_obima_cene() != null && p.getDonja_granica_obima_cene().length > 0) {
        sql.append(" AND cena >= ?");
    }
    if (p.getGornja_granica_obima_cene() != null && p.getGornja_granica_obima_cene().length > 0) {
        sql.append(" AND cena <= ?");
    }
    if (p.getVrsta_opreme() != null && p.getVrsta_opreme().length > 0) {
        sql.append(" AND vrsta_opreme = ?");
    }
    if (p.getKljucna_rec() != null && p.getKljucna_rec().length > 0) {
        sql.append(" AND naziv LIKE ?");
    }

    try (Connection con = rm.getConnection();
         PreparedStatement ps = con.prepareStatement(sql.toString())) {

        int paramIndex = 1;
        if (p.getDonja_granica_obima_cene() != null && p.getDonja_granica_obima_cene().length > 0) {
            ps.setInt(paramIndex++, p.getDonja_granica_obima_cene()[0]);  
        }
        if (p.getGornja_granica_obima_cene() != null && p.getGornja_granica_obima_cene().length > 0) {
            ps.setInt(paramIndex++, p.getGornja_granica_obima_cene()[0]); 
        }
        if (p.getVrsta_opreme() != null && p.getVrsta_opreme().length > 0) {
            ps.setString(paramIndex++, p.getVrsta_opreme()[0]);  
        }
        if (p.getKljucna_rec() != null && p.getKljucna_rec().length > 0) {
            ps.setString(paramIndex++, "%" + p.getKljucna_rec()[0] + "%");  
        }

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                proizvod pro = new proizvod();
                pro.setproizvod_id(rs.getInt("proizvod_id"));
                pro.setNaziv(rs.getString("naziv"));
                pro.setCena(rs.getInt("cena"));
                pro.setVrsta_opreme(rs.getString("vrsta_opreme"));
                pro.setStanje_na_lageru(rs.getInt("stanje_na_lageru"));
                filtriraniProizvodi.add(pro);
            }
        }
    } catch (SQLException ex) {
        throw new prodavnica_exception("Error while fetching filtered products", ex);
    }
    return filtriraniProizvodi;
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
    
    public void update(proizvod product, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {

            ps = con.prepareStatement("UPDATE proizvod SET stanje_na_lageru=? WHERE proizvod_id=?");
            ps.setInt(1, product.getStanje_na_lageru());
            ps.setInt(2, product.getproizvod_id()); // Ovdje postavljate ID proizvoda
            ps.executeUpdate();

            

        } finally {
            rm.closeResources(null, ps);
        }
    }
    
    
    
}