package EngimonHunter2000;
import java.util.Set;

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

    public Boolean runBattle(Engimon A, Engimon B) {
        checkAdvantage(A, B);
        this.pwrA = A.getLvl() * this.advA;
        for (SkillEngimon skillA : A.getSkills()) {
            this.pwrA = this.pwrA + skillA.getBasePower() * skillA.getMasteryLevel();
        }

        this.pwrB = B.getLvl() * this.advB;
        for (SkillEngimon skillB : B.getSkills()) {
            this.pwrB = this.pwrB + skillB.getBasePower() * skillB.getMasteryLevel();
        }

        if (this.pwrA >= this.pwrB) {
            System.out.println("Hore menang!");
        }
        else {
            System.out.println("Yah kalah :(");
        }
        return pwrA >= pwrB;
    }
}
