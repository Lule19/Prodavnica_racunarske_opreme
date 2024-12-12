package luka.data;

import java.io.Serializable;

public class kupovina implements Serializable {
    private int kupovina_id = -1;
    private int korisnik_id;
    private int proizvod_id;

 
    public kupovina() {
    }

    
    public kupovina(int kupovina_id, int korisnik_id, int proizvod_id) {
        this.kupovina_id = kupovina_id;
        this.korisnik_id = korisnik_id;
        this.proizvod_id = proizvod_id;
    }

   
    public kupovina(int korisnik_id, int proizvod_id) {
        this.korisnik_id = korisnik_id;
        this.proizvod_id = proizvod_id;
    }

    
    public int getkupovina_id() {
        return kupovina_id;
    }

    public void setkupovina_id(int kupovina_id) {
        this.kupovina_id = kupovina_id;
    }

    public int getKorisnik_id() {
        return korisnik_id;
    }

    public void setKorisnik_id(int korisnik_id) {
        this.korisnik_id = korisnik_id;
    }

    public int getProizvod_id() {
        return proizvod_id;
    }

    public void setProizvod_id(int proizvod_id) {
        this.proizvod_id = proizvod_id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("kupovina{")
          .append("kupovina_id=").append(kupovina_id)
          .append(", korisnik_id=").append(korisnik_id)
          .append(", proizvod_id=").append(proizvod_id)
          .append('}');
        return sb.toString();
    }
}

