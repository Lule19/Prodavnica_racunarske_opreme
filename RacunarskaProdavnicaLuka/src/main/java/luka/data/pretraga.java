
package luka.data;


import java.io.Serializable;

public class pretraga {
    private int pretraga_id;
    private korisnik korisnik_id;
    private podesavanje_pretrage podesavanje_pretrage_id;
    
    private Integer[] donja_granica_obima_cene;
    private Integer[] gornja_granica_obima_cene;
    private String[] vrsta_opreme;
    private String[] kljucna_rec;

    public pretraga(int pretraga_id, korisnik korisnik_id, podesavanje_pretrage podesavanje_pretrage_id, Integer[] donja_granica_obima_cene, Integer[] gornja_granica_obima_cene, String[] vrsta_opreme, String[] kljucna_rec) {
        this.pretraga_id = pretraga_id;
        this.korisnik_id = korisnik_id;
        this.podesavanje_pretrage_id = podesavanje_pretrage_id;
        this.donja_granica_obima_cene = donja_granica_obima_cene;
        this.gornja_granica_obima_cene = gornja_granica_obima_cene;
        this.vrsta_opreme = vrsta_opreme;
        this.kljucna_rec = kljucna_rec;
    }

    public pretraga(korisnik korisnik_id, podesavanje_pretrage podesavanje_pretrage_id, Integer[] donja_granica_obima_cene, Integer[] gornja_granica_obima_cene, String[] vrsta_opreme, String[] kljucna_rec) {
        this.korisnik_id = korisnik_id;
        this.podesavanje_pretrage_id = podesavanje_pretrage_id;
        this.donja_granica_obima_cene = donja_granica_obima_cene;
        this.gornja_granica_obima_cene = gornja_granica_obima_cene;
        this.vrsta_opreme = vrsta_opreme;
        this.kljucna_rec = kljucna_rec;
    }

    public pretraga() {
    }
    

    public Integer[] getDonja_granica_obima_cene() {
        return donja_granica_obima_cene;
    }

    public void setDonja_granica_obima_cene(Integer[] donja_granica_obima_cene) {
        this.donja_granica_obima_cene = donja_granica_obima_cene;
    }

    public Integer[] getGornja_granica_obima_cene() {
        return gornja_granica_obima_cene;
    }

    public void setGornja_granica_obima_cene(Integer[] gornja_granica_obima_cene) {
        this.gornja_granica_obima_cene = gornja_granica_obima_cene;
    }

    public String[] getVrsta_opreme() {
        return vrsta_opreme;
    }

    public void setVrsta_opreme(String[] vrsta_opreme) {
        this.vrsta_opreme = vrsta_opreme;
    }

    public String[] getKljucna_rec() {
        return kljucna_rec;
    }

    public void setKljucna_rec(String[] kljucna_rec) {
        this.kljucna_rec = kljucna_rec;
    }

    @Override
    public String toString() {
        return "Pretraga{" + "pretraga_id=" + pretraga_id + ", korisnik_id=" + korisnik_id + ", podesavanje_pretrage_id=" + podesavanje_pretrage_id + ", donja_granica_obima_cene=" + donja_granica_obima_cene + ", gornja_granica_obima_cene=" + gornja_granica_obima_cene + ", vrsta_opreme=" + vrsta_opreme + ", kljucna_rec=" + kljucna_rec + '}';
    }
}