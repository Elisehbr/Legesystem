public class PResept extends HvitResept {
    private static int rabatt = 108;
    
    public PResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
        super(legemiddel, lege, pasient, reit);
    }

    @Override
    public int prisAaBetale() {
        int pPris;
        if (legemiddel.hentPris() > 108) {
            pPris = legemiddel.hentPris() - rabatt;
        } else {
            pPris = 0;
        }
        return pPris;
    }

    @Override
    public String toString() {
        return super.toString() + "\nType: P-resept" + ", Rabatt: 108 kr" + ", Pris aa betale: " + prisAaBetale() + "kr";
    }
}
