public class Lege implements Comparable<Lege> {
    protected String navn;
    protected Resept resept;
    protected Lege annenLege;
    public Prioritetskoe<Lege> legeListe;
    public IndeksertListe<Resept> utskrevendeResepter;
 

    public Lege(String navn) {
        this.navn = navn;
        legeListe = new Prioritetskoe<Lege>();
        utskrevendeResepter = new IndeksertListe<Resept>();


    }

    public IndeksertListe<Resept> hentReseptListe(){
        return utskrevendeResepter;
    }

    @Override
    public int compareTo(Lege annenLege){
        return this.navn.compareTo(annenLege.navn);
        
    }

    public String hentNavn() {
        return navn;
    }

    public String toString() {
        return "\nNavn: " + navn;
    }

    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if(legemiddel instanceof Narkotisk){
            throw new UlovligUtskrift(this, legemiddel);
        }else{
            HvitResept hvit = new HvitResept(legemiddel, this, pasient, reit );
            this.utskrevendeResepter.leggTil(hvit);
            pasient.leggTilRes(hvit);
            return hvit;
        }
    }
    
    public MilResept skrivMilResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift{
        if(legemiddel instanceof Narkotisk){
            throw new UlovligUtskrift(this, legemiddel);
        }else{
            MilResept mil = new MilResept(legemiddel, this, pasient);
            this.utskrevendeResepter.leggTil(mil);
            pasient.leggTilRes(mil);
            return mil;
        }
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        
        if(legemiddel instanceof Narkotisk){
            throw new UlovligUtskrift(this, legemiddel);
        }else{
            PResept p = new PResept(legemiddel, this, pasient, reit);
            this.utskrevendeResepter.leggTil(p);
            pasient.leggTilRes(p);
            return p;
        }
    }
    
    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        if(legemiddel instanceof Narkotisk){
            throw new UlovligUtskrift(this, legemiddel);
        }else{
            BlaaResept blaa = new BlaaResept(legemiddel, this, pasient, reit);
            utskrevendeResepter.leggTil(blaa);
            pasient.leggTilRes(blaa);
            return blaa;
        }
    }
}
    

    

