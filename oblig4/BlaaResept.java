import java.lang.Math;

public class BlaaResept extends Resept {
    public BlaaResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
        super(legemiddel, lege, pasient, reit);
    }
    

    public String farge() {
        return "Blaa";
    }

    public int prisAaBetale() {
        return (int)Math.round(this.legemiddel.hentPris() * 0.25);
    }

    @Override
    public String toString() {
        return super.toString() + "\nResept: " + farge() + ", Rabatt: 75%" + ", Pris aa betale: " + prisAaBetale() + "kr";
    }
}
