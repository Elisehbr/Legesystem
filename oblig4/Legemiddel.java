public class Legemiddel {
    public final int id;
    public final String navn;
    public int pris;
    public final double virkestoff;
    public static int antLegemiddel = 0;

    public Legemiddel(String navn, int pris, double virkestoff){
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        id = antLegemiddel++;
    }

    public String hentNavn() {
        return navn;
    }

    public int hentPris() {
        return pris;
    }
    
    public void settNypris(int nyPris) {
        pris = nyPris;
    }

    public int hentId() {
        return id;
    }

    public double hentVirkestoff() {
        return virkestoff;
    }

    @Override
    public String toString() {
        return "Legemiddel ID: " + hentId() + " | Navn: " + hentNavn() + " | Pris: " + hentPris() + "kr, | " + "Virkestoff: " + hentVirkestoff() + "mg";
    }
}