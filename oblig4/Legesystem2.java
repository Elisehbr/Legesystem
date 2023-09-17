import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;


public class Legesystem2 {


    IndeksertListe<Pasient> pasientListe = new IndeksertListe<>();
    IndeksertListe<Legemiddel> legemiddelListe = new IndeksertListe<>();
    Prioritetskoe<Lege> legeListe = new Prioritetskoe<>();
    IndeksertListe<Resept> reseptListe = new IndeksertListe<>();


    public IndeksertListe<Pasient> hentPasienter(){
        return pasientListe;
    }
    public IndeksertListe<Legemiddel> hentLegemidler(){
        return legemiddelListe;
    }
    public Prioritetskoe<Lege> hentLeger(){
        return legeListe;
    }
    public IndeksertListe<Resept> hentResepter(){
        return reseptListe;
    }

    //Henter pasienter fra liste: 
    public Pasient hentPasient(int pasientId){
        for(Pasient pasient : pasientListe){
            if(pasient.hentPId() == pasientId){
                return pasient;
            }
        } return null;
    }

    //Henter legemiddel fra lsite; 
    public Legemiddel hentLegemiddel(int id){
        for(Legemiddel legemiddel : legemiddelListe){
            if(legemiddel.hentId() == id){
                return legemiddel;
            }
        } return null;
    }

    //Henter lege fra liste: 
    public Lege hentLege(String navn){
        for(Lege lege : legeListe){
            if(lege.hentNavn().equals(navn)){
                return lege;
            }
        } return null;
    }

    // Lager pasienter, legemidler, leger og resepter 

    public Pasient lagPasient(String navn, String fodselsnummer){
        Pasient pasient = new Pasient(navn, fodselsnummer);
        pasientListe.leggTil(pasient);
        return pasient;
    }


    public Vanlig lagVanlig(String navn, int pris, double virkestoff){
        Vanlig legemiddel = new Vanlig(navn, pris, virkestoff);
        legemiddelListe.leggTil(legemiddel);
        return legemiddel;

    }
    public Vanedannende lagVanedannende(String navn, int pris, double virkestoff, int styrke){
        Vanedannende legemiddel = new Vanedannende(navn, pris, virkestoff, styrke);
        legemiddelListe.leggTil(legemiddel);
        return legemiddel;
    
    }
    public Narkotisk lagNarkotisk(String navn, int pris, double virkestoff, int styrke){
        Narkotisk legemiddel = new Narkotisk(navn, pris, virkestoff, styrke);
        legemiddelListe.leggTil(legemiddel);
        return legemiddel;
    }

    public Lege lagLege(String navn, String kontrollkode){ 
        if(kontrollkode.equals("0") ){
            Lege lege = new Lege(navn);
            legeListe.leggTil(lege);
            return lege;
        }
        if(Integer.parseInt(kontrollkode) > 0){
            Spesialist spesialist = new Spesialist(navn, kontrollkode);
            legeListe.leggTil(spesialist);
            return spesialist;
        }
        return null;
        
        
    }

    public HvitResept lagHvitResept(int legemiddelNr,String legeNavn, int pasientId, int reit){
        try{
            HvitResept hvit = hentLege(legeNavn).skrivHvitResept(hentLegemiddel(legemiddelNr), hentPasient(pasientId), reit);
            reseptListe.leggTil(hvit);
            

            return hvit;
        }catch(UlovligUtskrift e){
            System.out.println(e);
            
        }return null;
    }

    public PResept lagPResept(int legemiddelNr,String legeNavn, int pasientId, int reit){
        try{
            PResept p = hentLege(legeNavn).skrivPResept(hentLegemiddel(legemiddelNr), hentPasient(pasientId), reit);
            reseptListe.leggTil(p);
            return p;
        }catch(UlovligUtskrift e){
            System.out.println(e.getMessage());
            
        }return null;
        
    }

    public BlaaResept lagBlaaResept(int legemiddelNr, String legeNavn, int pasientId, int reit){
        try{
            BlaaResept blaa = hentLege(legeNavn).skrivBlaaResept(hentLegemiddel(legemiddelNr), hentPasient(pasientId), reit);
            reseptListe.leggTil(blaa);
            return blaa;
        }catch(UlovligUtskrift e){
            System.out.println(e);
            
        }return null;
    }
    public MilResept lagMilResept(int legemiddelNr, String legeNavn, int pasientId){
        try{
            MilResept mil = hentLege(legeNavn).skrivMilResept(hentLegemiddel(legemiddelNr), hentPasient(pasientId));
            reseptListe.leggTil(mil);
            return mil;
        }catch(UlovligUtskrift e){
            System.out.println(e);
            
        }return null;
  
    }

    // Metode som leser fra fil
    public void lesFraFil(String filnavn) throws Exception{  
    
        try{
            Scanner lesFil = new Scanner(new File(filnavn));
            int teller = 0;

            while(lesFil.hasNextLine()){
                String linje = lesFil.nextLine();
                String[] ord = linje.split(",");

                if (linje.contains("#")) {
                    teller ++;
                    continue;
                    
                } 

                //henter ut pasienter fra fil:
                if(teller == 1){
                    lagPasient(ord[0], ord[1]);
                }
                //henter ut legemiddel fra fil: 
                else if(teller == 2){
                    if(ord[1].trim().equals("vanlig")){
                        lagVanlig(ord[0], Integer.parseInt(ord[2]), Double.parseDouble(ord[3]));
                    } else if(ord[1].equals("vanedannende")){
                        lagVanedannende(ord[0], Integer.parseInt(ord[2]), Double.parseDouble(ord[3]), Integer.parseInt(ord[4]));
                    } else if (ord[1].equals("narkotisk")) {
                        lagNarkotisk(ord[0], Integer.parseInt(ord[2]), Double.parseDouble(ord[3]), Integer.parseInt(ord[4]));
                    }
                    
                }
                //henter ut leger fra fil: 
                else if(teller == 3){
                    lagLege(ord[0], ord[1]);
                }
                
                //henter ut resepter fra fil:
                if(teller == 4){
                    //Sjekker at det er riktig legemiddel, pasient og lege: 
                    int legemiddelNr = Integer.parseInt(ord[0]);
                    String legeNavn = ord[1];
                    int pasientId = Integer.parseInt(ord[2]);
                    int reit = 0;
                        
                    // oppretter hvit resept: 
                    if(ord[3].equals("hvit")){
                        reit = Integer.parseInt(ord[4]);
                        lagHvitResept(legemiddelNr, legeNavn, pasientId, reit);
                    }  
                    if(ord[3].equals("p")){
                        reit = Integer.parseInt(ord[4]);
                        lagPResept(legemiddelNr, legeNavn, pasientId, reit);
                    } 
                    if(ord[3].equals("blaa")){
                        reit = Integer.parseInt(ord[4]);
                        lagBlaaResept(legemiddelNr, legeNavn, pasientId, reit);   
                    } 
                    if(ord[3].equals("militaer")){
                        lagMilResept(legemiddelNr, legeNavn, pasientId);   
                    } 
                }   
            }       
        }catch(Exception e){
            System.out.println("Kan ikke lese " + e.getMessage() + "!");
            System.exit(-1);
            
        }
    }

    //oppgave E3: Skrive ut fra fil
    public void skrivUt(){
        System.out.println("------PASIENTER------");
        System.out.println(pasientListe);
        System.out.println("\n-----FERDIG MED PASIENTER------");
        System.out.println("\n------LEGEMIDLER------");
        System.out.println(legemiddelListe);
        System.out.println("\n-----FERDIG MED LEGEMIDLER------");
        System.out.println("\n------LEGER------");
        System.out.println(legeListe);    
        System.out.println("\n-----FERDIG MED LEGER------");
        System.out.println("\n------RESEPTER------");
        System.out.println(reseptListe);
        System.out.println("\n------FERDIG MED RESEPTER------");
        
        return;
        
    }


    Scanner scanner = new Scanner(System.in);
    
    //Oppgave E4: Legge til objekter fra input: 

    public void leggTilObjekter(){

        System.out.println("\nHva ønsker du å legge til? ");
        System.out.println("\nPasient" + "\nLegemiddel" + "\nLege" + "\nResept");
        String objekt = scanner.nextLine();

        if(objekt.toLowerCase().equals("pasient")){
            System.out.println("Navn: ");
            String navn = scanner.nextLine();

            System.out.println("Fodselsnummer: ");
            String fodselsnummer = scanner.nextLine();
            
            Pasient nyPasient = new Pasient(navn, fodselsnummer);
                
            pasientListe.leggTil(nyPasient);
            System.out.println(nyPasient);
            
        }

        if(objekt.toLowerCase().equals("legemiddel")){
            System.out.println("Navn: ");
            String navn = scanner.nextLine();

            System.out.println("Pris: ");
            int pris = Integer.parseInt(scanner.nextLine());

            System.out.println("Virkemiddel: ");
            Double virkemiddel = Double.parseDouble(scanner.nextLine());
                
            Legemiddel nyttLegemiddel = new Legemiddel(navn, pris, virkemiddel);
            legemiddelListe.leggTil(nyttLegemiddel);
            System.out.println(nyttLegemiddel);
        
        }

        if(objekt.toLowerCase().equals("lege")){
            System.out.println("Vanlig lege eller spesialist? ");
            String type = scanner.nextLine();

            if(type.toLowerCase().equals("vanlig")){
                System.out.println("Navn: ");
                String navn = scanner.nextLine();

                Lege nyLege = new Lege(navn);
                legeListe.leggTil(nyLege);
                System.out.println(nyLege);
                
            }else if(type.toLowerCase().equals("spesialist")){
                System.out.println("Navn: ");
                String navn = scanner.nextLine();

                System.out.println("Kontrollkode: ");
                String kontrollkode = scanner.nextLine();

                Spesialist nySpesialist = new Spesialist(navn, kontrollkode);

                legeListe.leggTil(nySpesialist);
                System.out.println(nySpesialist);
             }
                //else hvis det ikke var noen av delene
        } 
        
    return;           
    }

    public void brukResepter(){
        //Overskrift
        System.out.println("\nHvilken pasient vil du se resepter for? ");

        //Skriver ut oversikt over pasienter
        System.out.println(pasientListe);

        //henter ut indeks til valgt pasient
        int pasientIndeks = scanner.nextInt();
        

        Pasient valgtPasient = pasientListe.hent(pasientIndeks);
        
        if(valgtPasient.hentPId() == pasientIndeks){
            System.out.println("\nValgt pasient: " + valgtPasient.hentNavn() + " (fnr " + valgtPasient.hentFodselsnummer() + ")");
            valgtPasient.hentResepter();
            
        }

        
        
        //spør bruker om input
        System.out.println("\nHvilken resept vil du bruke?");

        System.out.println(valgtPasient.hentResepter());
        
        //lagrer innput som skal ta in reseptID
        int reseptInput = scanner.nextInt();
        
        
        // henter reseptID fra pasientens reseptliste
   
        Resept valgtResept = valgtPasient.reseptListe.hent();

        
        if(reseptInput == valgtResept.hentId() ){
            valgtResept.bruk();
            System.out.println("\nBrukte valgte resept paa: " + valgtResept.hentLegemiddel().hentNavn() + ". \nAntall gjenvaerende reit: " + valgtResept.hentReit());
            
            if (valgtResept.hentReit() == 0){
                System.out.println("Kunne ikke bruke resept paa: " + valgtResept.hentLegemiddel().hentNavn() + " Ingen gjenvarende reit.");
            }
        }

            
            
    }
     
        
        
    

  
    //Opgpave E6: skrive ut statestikk
    public void hentTotantVane(){
        int totAntallVan = 0;
        for(Resept resept : reseptListe){
            if(resept.hentLegemiddel() instanceof Vanedannende){
                totAntallVan ++;
            }
        }    
        System.out.println("\nAntall Resepter på vanedannende legemidler: "  + totAntallVan);
    }

    public void hentTotantNarko(){
        int totAntalNarko = 0;
        for(Resept resept : reseptListe){
            if(resept.hentLegemiddel() instanceof Narkotisk){
                totAntalNarko ++;
            }
        }
        System.out.println("\nTotalt antall utskrevne resepter på narkotiske legemidler: " + totAntalNarko);
    }
    
    public void sjekkeNarkotisk(){
        int legeAntNarko = 0;
        int pasientAntNarko = 0;

        for(Lege lege : legeListe){
            for(Resept resept: lege.utskrevendeResepter){ // 
                if(resept instanceof BlaaResept){
                    legeAntNarko ++;
                    System.out.println("\nLegen: " + lege.hentNavn() + " har skrevet ut: " + legeAntNarko + " narkotiske resepter. ");
                }
            }
        }
        
        for(Pasient pasient : pasientListe){
            for(Resept resept : pasient.reseptListe){
                if(resept instanceof BlaaResept){
                    pasientAntNarko ++;
                    System.out.println("\nLegen: " + pasient.hentNavn() + " har skrevet ut: " + pasientAntNarko + " narkotiske resepter. ");
                }
            }
        }
    }


    public void hentStatistikk(){
        boolean seStatistikk = true;

        while(seStatistikk){
            System.out.println("\n--- VELG EN TYPE STATISTIKK ---" + "\n1. Vanedannende legemidler: " + "\n2. Narkotiske legemidler: " + "\n3. Mulig misbruk: " + "\n--- TRYKK PAA q FOR AA GAA TIL HOVEDMENY ---");
            String statValg = scanner.nextLine();
            if(statValg.equals("1")){
                hentTotantVane();
                continue;
            }
            if(statValg.equals("2")){
                hentTotantNarko();
                continue;
            }
            if(statValg.equals("3")){
                sjekkeNarkotisk();
                continue;
            }

            if(statValg.equals("q")){
                seStatistikk = false;
            }
        }
    } 
    

    public void skrivTilFil(String filepath){
        try {
            FileWriter skrivFil = new FileWriter(filepath);

            skrivFil.write("# Pasienter (navn, fnr)\n");
            for (Pasient pasient : this.hentPasienter()) {
                skrivFil.write(pasient.hentNavn() + "," + pasient.hentFodselsnummer() + "\n");
            }
 
            skrivFil.write("# Legemidler (navn,type,pris,virkestoff,[styrke]) \n");
            for (Legemiddel legemiddel : this.hentLegemidler()) {
                if (legemiddel instanceof Vanlig) {
                    skrivFil.write(legemiddel.hentNavn() + ",vanlig," + legemiddel.hentPris() + "," + legemiddel.hentVirkestoff() + "\n");
                } else if (legemiddel instanceof Narkotisk){
                    Narkotisk narkotisk = (Narkotisk) legemiddel;
                    int styrke = narkotisk.hentNarkotiskStyrke();
                    skrivFil.write(legemiddel.hentNavn() + ",narkotisk," + legemiddel.hentPris() + "," + legemiddel.hentVirkestoff() + "," + styrke + "\n");
                }else if (legemiddel instanceof Vanedannende){
                    Vanedannende vanedannende = (Vanedannende) legemiddel;
                    int styrke = vanedannende.hentVanedannendeStyrke();
                    skrivFil.write(legemiddel.hentNavn() + ",vanedannende," + legemiddel.hentPris() + "," + legemiddel.hentVirkestoff() + "," + styrke + "\n");
                }
            }

            skrivFil.write("# Leger (navn,kontrollid / 0 hvis vanlig lege)\n");
            for (Lege lege : this.hentLeger()) {
                if (lege instanceof Spesialist) {
                    Spesialist spesialist = (Spesialist) lege;
                    skrivFil.write(lege.hentNavn() + "," + spesialist.hentKontrollkode() + "\n");
                } else {
                    skrivFil.write(lege.hentNavn() + ",0\n");
                }
            }

            skrivFil.write("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])\n");
            for (Resept resept : this.hentResepter()) {
                Legemiddel legemiddel = resept.hentLegemiddel();
                Lege lege = resept.hentLegeNavn();
                Pasient pasient = resept.hentPasient();
                int reit = resept.hentReit();

                if (resept instanceof MilResept) {
                    skrivFil.write(legemiddel.hentId() + "," + lege.hentNavn() + "," + pasient.hentPId() + ",militaer\n");
                } else if (resept instanceof HvitResept){
                    skrivFil.write(legemiddel.hentId() + "," + lege.hentNavn() + "," + pasient.hentPId()  + ",hvit,"  + reit +"\n");
                } else if (resept instanceof BlaaResept) {
                    skrivFil.write(legemiddel.hentId() + "," + lege.hentNavn() + "," + pasient.hentPId() + ",blaa," + reit +"\n");
                } else if (resept instanceof PResept) {
                    skrivFil.write(legemiddel.hentId() + "," + lege.hentNavn() + "," + pasient.hentPId() + ",p," + reit +"\n");
                }
            }
 
            skrivFil.close();

            
        } 
        
        catch (IOException e) {
            System.out.println("LOL funka ikke");
            e.printStackTrace();
        }
    }


    public void hovedmeny(){   
    
        boolean skalFortsette = true;
        
        while(skalFortsette){
            System.out.println("\n");
            System.out.println("\n--- HOVEDMENY: SKRIV INN TALLET DU ØNSKER ---" + "\n"+ "\n1. Se oversikt"+ "\n2. Legg til objekter " + "\n3. Bruke resepter " + "\n4. Se statistikk" + "\n5. Skriv data til fil");
            System.out.println("\n--- SKRIV q FOR AA AVSLUTTE ---");
          
            String tekst = scanner.nextLine();

            if(tekst.equals("1")){
                skrivUt();
                continue;
            }
            if(tekst.equals("2")){
                leggTilObjekter(); 

                continue;
            }
            if(tekst.equals("3")){
                brukResepter();
                continue;
            }
            if(tekst.equals("4")){
                hentStatistikk();
                continue;
            }if(tekst.equals("5")){
               skrivTilFil("minFil.txt");
                continue;
            }

            if(tekst.toLowerCase().equals("q")){
                skalFortsette = false;
            } 
        }
    }

    
    public static void main(String[] args){
        Legesystem2 legesystem = new Legesystem2();
        try{
            legesystem.lesFraFil("legedata.txt");
        }catch(Exception e){
            System.out.println("Vil ikke lese fra fil. " + e );
        }
        legesystem.hovedmeny();
        
    }
    
}