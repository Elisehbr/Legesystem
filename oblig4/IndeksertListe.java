class IndeksertListe <E> extends Lenkeliste<E>{
    
    public void leggTil (int pos, E x){
            Node peker = start;
            Node nyNode = new Node(x);
            
            if(pos > stoerrelse() || pos < 0){
                throw new UgyldigListeindeks(pos);
            }
            
            if (start == null){ // hvis vi ikke har noen flere er den nye noden start og slutt, slutt skal aldri være null med mindre det er en tom liste. 
                start = nyNode;
                slutt = nyNode;
                return;
            }else if(pos == 0){
                nyNode.neste = start;
                start = nyNode;
                return;

            }else{
                for(int i = 0; i<pos-1; i++){  
                    peker = peker.neste;
            }
            nyNode.neste = peker.neste;
            peker.neste = nyNode;
        }
        stoerrelse++;
    }

    public void sett (int pos, E x){
        Node peker = start;
        if(pos < 0 || pos >=stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }
        for(int i = 0; i< pos; i++){
            peker = peker.neste;
        }
        peker.data = x; 
    }
    

    public E hent(int pos){
        if(pos < 0 || pos >= stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }
        Node peker = start;
        for(int i = 0; i< pos; i++){
            peker = peker.neste;
        }
        return peker.data;
    }

    public E fjern(int pos) {
        if (pos < 0 || pos >= stoerrelse()) {
            throw new UgyldigListeindeks(pos);
        }
    
        if (pos == 0) {
            return super.fjern(); // kaller en egen metode for å fjerne det første elementet
        }
    
        Node peker = start;
        for (int i = 0; i < pos - 1; i++) {
            peker = peker.neste; // peker på noden før den aktuelle posisjonen
        }
        Node fjernet = peker.neste; // peker på noden som skal fjernes
        peker.neste = fjernet.neste; // hopper over noden som skal fjernes
        stoerrelse--;
        return fjernet.data; // returnerer dataen til den fjernede noden
    }

}
