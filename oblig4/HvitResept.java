public class HvitResept extends Resept {
    public HvitResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
        super(legemiddel, lege, pasient, reit);
    }

    public String farge() {
        return "Hvit";
    }

    public int prisAaBetale() {
        return legemiddel.hentPris();
    }

    @Override
    public String toString() {
        return super.toString() +"\nResept: Hvit" + ", Pris aa betale: " + prisAaBetale() + "kr";
    }
}
