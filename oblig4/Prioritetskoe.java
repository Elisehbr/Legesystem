
class Prioritetskoe<E extends Comparable<E>> extends Lenkeliste<E>{
    @Override
    public void leggTil(E x){
        Node node = new Node(x);
        Node forrige = null;
        Node holder = start;

        while(holder != null && holder.data.compareTo(x) < 0){
            forrige = holder;
            holder = holder.neste;
        }
        if(forrige == null){
            start = node;
        }else{
            forrige.neste = node;
        }
        node.neste = holder;
        if(holder == null){
            slutt = node;
        }
        stoerrelse ++;
    }
    

    @Override
    public E fjern(){
        if(start == null){
            throw new UgyldigListeindeks(0);
        }else{
            Node holder = start; // lager en beholder for data i start
            start = start.neste; //sier at start skal være neste som vil si at start ikke finnes typ    
            stoerrelse --;         // oppdaterer størrelse
            return holder.data;
        }
    }

    }
    