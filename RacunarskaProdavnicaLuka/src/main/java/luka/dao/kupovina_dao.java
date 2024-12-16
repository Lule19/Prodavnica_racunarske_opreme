/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package luka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import luka.data.kupovina;
import luka.exception.prodavnica_exception;
import java.sql.Statement;

/**
 *
 * @author Korisnik
 */
public class kupovina_dao {
    
    
    private static final kupovina_dao instance = new kupovina_dao();

    private kupovina_dao() {
    }

    public static kupovina_dao getInstance() {
        return instance;
    }

    
    public int insert(kupovina k, Connection con) throws prodavnica_exception, SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO kupovina(korisnik_id, proizvod_id) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, k.getCustomer().getkorisnikId());
            ps.setInt(2, k.getProduct().getproizvod_id());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            rm.closeResources(rs, ps);
        }
        return id;
    }
}
