
package luka.data;


import java.io.Serializable;

public class pretraga implements Serializable {
    private int pretraga_id = -1;
    private int podesavanje_pretrage_id;
    private int korisnik_id;

    public pretraga() {
    }

    
    public pretraga(int pretraga_id, int podesavanje_pretrage_id, int korisnik_id) {
        this.pretraga_id = pretraga_id;
        this.podesavanje_pretrage_id = podesavanje_pretrage_id;
        this.korisnik_id = korisnik_id;
    }

    
    public pretraga(int podesavanje_pretrage_id, int korisnik_id) {
        this.podesavanje_pretrage_id = podesavanje_pretrage_id;
        this.korisnik_id = korisnik_id;
    }

    
    public int getpretraga_id() {
        return pretraga_id;
    }

    public void setpretraga_id(int pretraga_id) {
        this.pretraga_id = pretraga_id;
    }

    public int getPodesavanje_pretrage_id() {
        return podesavanje_pretrage_id;
    }

    public void setPodesavanje_pretrage_id(int podesavanje_pretrage_id) {
        this.podesavanje_pretrage_id = podesavanje_pretrage_id;
    }

    public int getKorisnik_id() {
        return korisnik_id;
    }

    public void setKorisnik_id(int korisnik_id) {
        this.korisnik_id = korisnik_id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("pretraga{")
          .append("pretraga_id=").append(pretraga_id)
          .append(", podesavanje_pretrage_id=").append(podesavanje_pretrage_id)
          .append(", korisnik_id=").append(korisnik_id)
          .append('}');
        return sb.toString();
    }
}
