package luka.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import luka.data.korisnik;
import luka.exception.prodavnica_exception;

public class korisnik_dao {

    private static final korisnik_dao instance = new korisnik_dao();

    private korisnik_dao() {}

    public static korisnik_dao getInstance() {
        return instance;
    }

    public List<korisnik> getAllKorisnici() throws prodavnica_exception {
        List<korisnik> korisnici = new ArrayList<>();
        String sql = "SELECT * FROM korisnik";  
        try (Connection con = rm.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                korisnik k = new korisnik();
                k.setkorisnikId(rs.getInt("korisnik_id"));
                k.setImeIPrezime(rs.getString("ime_i_prezime"));
                k.setUsername(rs.getString("username"));
                k.setPassword(rs.getString("password"));
                k.setEmail(rs.getString("e_mail"));
                k.setDatumRodjenja(rs.getString("datum_rodjenja"));
                k.setStanjeRacuna(rs.getInt("stanje_racuna"));
                k.setKolicinaPotrosenogNovca(rs.getInt("kolicina_potrosenog_novca"));
                korisnici.add(k);
            }
        } catch (SQLException ex) {
            throw new prodavnica_exception("Error while fetching korisnici", ex);
        }
        return korisnici;
    }

  
    public void registracija(korisnik k, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

           
            ps = con.prepareStatement("INSERT INTO korisnik(ime_i_prezime, username, password, e_mail, datum_rodjenja,stanje_racuna,kolicina_potrosenog_novca) VALUES(?,?,?,?,?,?,?)");
            
            ps.setString(1, k.getImeIPrezime());
            ps.setString(2, k.getUsername());
            ps.setString(3, k.getPassword());
            ps.setString(4, k.getEmail());
            ps.setString(5, k.getDatumRodjenja());
            ps.setInt(6, k.getStanjeRacuna());
            ps.setInt(7, k.getKolicinaPotrosenogNovca());
            
            ps.executeUpdate();

        } finally {
            rm.closeResources(rs, ps);
        }
    }
    
    
    
    public korisnik find(String username, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        korisnik k = null;
        try {
            ps = con.prepareStatement("SELECT * FROM korisnik where username=?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
               
                k = new korisnik(rs.getInt("korisnik_id"),rs.getString("ime_i_prezime"), username,rs.getString("password"), rs.getString("e_mail"),rs.getString("datum_rodjenja"), rs.getInt("stanje_racuna"), rs.getInt("kolicina_potrosenog_novca"));
            }
        } finally {
            rm.closeResources(rs, ps);
        }
        return k;
    }

    
}
