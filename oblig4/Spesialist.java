public class Spesialist extends Lege implements Godkjenningsfritak {
    private String kontrollkode;

    public Spesialist(String navn, String kontrollkode) {
        super(navn);
        this.kontrollkode = kontrollkode;
    }

    @Override
    public String hentKontrollkode() {
        return kontrollkode;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\nType: Spesialist\nKontrollkode: " + kontrollkode;
    }
    
    @Override
    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) {
        BlaaResept blaa = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevendeResepter.leggTil(blaa);
        return blaa;
    }
}
