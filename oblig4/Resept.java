public abstract class Resept {
    private int id;
    public final Legemiddel legemiddel;
    public final Lege lege;
    public Pasient pasient;
    public int reit;
    private static int antResepter = 0;

    public Resept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.lege = lege;
        this.pasient = pasient;
        this.reit = reit;
        id = antResepter++;
    }

    public int hentId() {
        return id;
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public Lege hentLegeNavn() {
        return lege;
    }

    public Pasient hentPasient() {
        return pasient;
    }

    public int hentReit() {
        return reit;
    }

    public boolean bruk() {
        if (reit == 0) {
            return false;
        } else {
            reit -= 1;
            return true;
        }
    }
    /* 
    public String toString() {
        return "\nResept: " + hentId() + "\n" + legemiddel + "\nLege: " + lege.hentNavn() + "\nPasient: " + pasient.hentNavn() + "\nReit: " + hentReit();
    }*/
    public String toString() {
        return "\nID: " + hentId() + " " + legemiddel.hentNavn() + " (Reit: " + hentReit() + ")";
    }


    abstract public String farge();
    abstract public int prisAaBetale();
}
