public class Pasient {
    public String navn;
    public String fodselsnummer;
    public final int id;
    public static int unikId;
    public IndeksertListe<Resept> reseptListe;
 

    public Pasient(String navn, String fodselsnummer){
        this.navn = navn;
        this.fodselsnummer = fodselsnummer;
        id = unikId;
        unikId ++;
        reseptListe = new IndeksertListe<Resept>();
    }

    public String hentNavn(){
        return navn;
    }

    public String hentFodselsnummer(){
        return fodselsnummer;
    }

    public int hentPId(){
        return id;
    }

    public void leggTilRes(Resept resept){
        reseptListe.leggTil(resept);
    }

    public IndeksertListe<Resept> hentResepter(){
        return reseptListe;
    }

    public String toString() {
        return "\n" + hentPId()+ ": " + hentNavn() + " (fnr " + hentFodselsnummer() + ")";
    }


}
