package EngimonHunter2000;

public class Battle {
    private double advA;
    private double advB;
    private double pwrA;
    private double pwrB;
    
    public Battle() {
        this.advA = 0;
        this.advB = 0;
        this.pwrA = 0;
        this.pwrB = 0;
    }

    public void checkAdvantage(Engimon A, Engimon B) {
        ElementsList elA = A.getListElement();
        ElementsList elB = B.getListElement();

        this.advA = elA.getElementalAdvantage(elB);
        this.advB = elB.getElementalAdvantage(elA);
    }

    public double getAdvA(){ return this.advA; }
    public double getAdvB(){ return this.advB; }

    public Boolean runBattle(Engimon A, Engimon B) {
        checkAdvantage(A, B);
        this.pwrA = A.getBattlePower(this.advA);
        this.pwrB = B.getBattlePower(this.advB);

        if (this.pwrA >= this.pwrB) {
            System.out.println("Hore menang!");
        }
        else {
            System.out.println("Yah kalah :(");
        }
        return pwrA >= pwrB;
    }
}
