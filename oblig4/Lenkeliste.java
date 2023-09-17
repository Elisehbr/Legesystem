import java.util.Iterator;
abstract class Lenkeliste<E> implements Liste<E> {
    protected Node start;
    protected Node slutt;
    protected int stoerrelse = 0;

    protected class Node {
        Node neste;
        E data;

        public Node(E data){
            this.data = data;
        }
    }

    public Iterator<E> iterator() {
        return new LenkelisteIterator();
    }

    public class LenkelisteIterator implements Iterator<E>{
  
        private Node denne;

        public LenkelisteIterator(){
            denne = start;
        }
  
        @Override
        public boolean hasNext(){

            return denne != null;
        }
        @Override
        public E next(){
          if (!hasNext()) {
                  throw new UgyldigListeindeks(0);
              }
              E data = denne.data;
              denne = denne.neste;
              return data;
        }
    }
    
    @Override
    public int stoerrelse(){
        return stoerrelse;
    }

    @Override
    public void leggTil (E data){
        Node nyNode = new Node(data); //oppretter og setter inn en ny node
        stoerrelse++; //Øker størrelsen på beholderen når vi setter inn en node
        
        if (start == null){ // hvis vi ikke har noen flere er den nye noden start og slutt, slutt skal aldri være null med mindre det er en tom liste. 
            start = nyNode;
            slutt = nyNode;
            return;
        }else if(stoerrelse == 1){
            start.neste = nyNode;
            slutt = nyNode;
        }else{
            slutt.neste = nyNode; // slutt sin neste skal peke på ny node
            slutt = nyNode; // endrer sluttpeker til å nå peke på nyNode
        }

           
    }

    @Override
    public E hent(){
        return start.data;
    }
    @Override
    public E fjern(){

        if(stoerrelse() == 0){
            throw new UgyldigListeindeks(-1);
        }else{
            Node holder = start; // lager en beholder for data i start
            start = start.neste; //sier at start skal være neste som vil si at start ikke finnes typ    
            stoerrelse --;         // oppdaterer størrelse
            return holder.data;
        }
        
    }

 
    @Override
    public String toString(){
        String tekst = "";
        Node peker = start; //peker referer til start
        while(peker != null){ // så lenge peker ikke er null henter vi verdier
            tekst += "\n " + peker.data; 
            peker = peker.neste; //henter neste node
        }
        return tekst;
    }
    
}
