public class MilResept extends HvitResept {
   
    public MilResept(Legemiddel legemiddel, Lege lege, Pasient pasient) {
        super(legemiddel, lege, pasient, 3);
    }

    @Override
    public int prisAaBetale() {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString() + "\nType: Militaerresept" + ", Reit: 3" + "\nRabatt: 100%" + ", Pris aa betale: " + prisAaBetale() + "kr";
    }
}
