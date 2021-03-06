package EngimonHunter2000;

import java.util.ArrayList;

/**
 * {@link EngimonHunter2000}
 * Breeding untuk {@link Engimon}
 * @author Alvin Wilta
 */

public class Breeding {

    private int max1;
    private int max2;
    private int max_mastery;
    private int max_mastery2;
    private Double max_adv;
    private Element[] el_p1;
    private Element[] el_p2;
    private ArrayList<SkillEngimon> skillsP1;
    private ArrayList<SkillEngimon> skillsP2;
    private int n1;
    private int n2;

    public Breeding() {
        this.max1 = 0;
        this.max2 = 0;
        this.max_adv = 0d;
        this.max_mastery = 0;
        this.max_mastery2 = 0;
        skillsP1 = new ArrayList<SkillEngimon>();
        skillsP2 = new ArrayList<SkillEngimon>();
        el_p1 = new Element[2];
        el_p2 = new Element[2];
        this.n1 = 0;
        this.n2 = 0;
    }

    public Engimon breeding(EngiDex _dex, Engimon _parent1, Engimon _parent2, SkillDex _skillDex, String _name)
            throws BreedingException, EngimonException, EngimonSpeciesException, ElementsListException {
        if (_parent1.getLvl() < 4 || _parent2.getLvl() < 4) {
            throw new BreedingException(0);
        }
        if (_parent1.equals(_parent2)) {
            throw new BreedingException(1);
        }
        _parent1.addLevel(-3);
        _parent2.addLevel(-3);
        this.n1 = _parent1.getListElement().getElementsList().size();
        this.n2 = _parent2.getListElement().getElementsList().size();
        // Mengubah ElementList menjadi Array (mengambil 2 elemen terdepan saja)
        Element[] temp = new Element[this.n1];
        Element[] temp2 = new Element[this.n2];
        _parent1.getListElement().getElementsList().toArray(temp);
        _parent2.getListElement().getElementsList().toArray(temp2);

        el_p1[0] = temp[0];
        if (this.n1 > 1) {
            el_p1[1] = temp[1];
        }

        el_p2[0] = temp2[0];
        if (this.n2 > 1) {
            el_p2[1] = temp2[1];
        }

        Engimon retVal = new Engimon(_dex, prioritySpecies(_dex), _name);

        // Mengubah HashSet -> Array -> ArrayList
        this.max1 = _parent1.getSkills().size();
        this.max2 = _parent2.getSkills().size();
        SkillEngimon[] skillP1 = new SkillEngimon[max1];
        _parent1.getSkills().toArray(skillP1);
        for (SkillEngimon skill : skillP1) {
            skillsP1.add(skill);
        }
        if (skillsP1.isEmpty()) {
            throw new BreedingException(2);
        }

        SkillEngimon[] skillP2 = new SkillEngimon[max2];
        _parent2.getSkills().toArray(skillP2);
        for (SkillEngimon skill : skillP2) {
            skillsP2.add(skill);
        }
        if (skillsP1.isEmpty()) {
            throw new BreedingException(3);
        }

        int max_addskill = _parent1.getSkillCount() + _parent2.getSkillCount();
        // Untuk menghapus skill ketika skill starter engimon sudah dimiliki oleh parentnya
        boolean dupl_skill = false;
        for (SkillEngimon skill : skillsP1) {
            if (retVal.getStarterSkill().getName() == skill.getName()) {
                dupl_skill = true;
            }
        }
        for (SkillEngimon skill2 : skillsP2) {
            if (retVal.getStarterSkill().getName() == skill2.getName()) {
                dupl_skill = true;
            }
        }
        if (max_addskill >= 4 || dupl_skill) {
            // delete starter skill buat direplace oleh parent: Spesifikasi 5.d.i
            // didelete ketika skill parent > 4 atau terdapat skill yang bisa diinherit oleh parent
            if (max_addskill > 4) {
                max_addskill = 4;
            }
            retVal.delSkill(_dex.getEntity(retVal.getSpecies()).getStarterSkill());
            for (int i = 0; i < max_addskill; i++) {
                retVal.addSkill(priorityBreeding(_skillDex));
                this.max1 = skillsP1.size();
                this.max2 = skillsP2.size();
            }
        } else {
            for (int i = 0; i < max_addskill; i++) {
                retVal.addSkill(priorityBreeding(_skillDex));
                this.max1 = skillsP1.size();
                this.max2 = skillsP2.size();

            }
        }

        retVal.changeParent(0, _parent1.getName());
        retVal.changeParent(1, _parent2.getName());

        return retVal;

    }

    private SkillEngimon priorityBreeding(SkillDex _skillDex) throws BreedingException {
        if (skillsP1.size() != 0 && skillsP2.size() != 0) {
            // Mengambil benchmark
            SkillEngimon S1 = skillsP1.get(0);
            this.max_mastery = S1.getMasteryLevel();
            SkillEngimon S2 = skillsP2.get(0);
            this.max_mastery2 = S2.getMasteryLevel();
            int idxS1 = 0;
            int idxS2 = 0;
            // Iterasi untuk mencari skill terbaik: Spesifikasi 5.d.iii.1, iterasi dari belakang spesifikasi 5.d.iii.3
            for (int i = max1 - 1; i > 0; i--) {
                if (skillsP1.get(i).getMasteryLevel() > max_mastery) {
                    S1 = skillsP1.get(i);
                    max_mastery = skillsP1.get(i).getMasteryLevel();
                    idxS1 = i;
                }
            }

            for (int i = max2 - 1; i > 0; i--) {
                if (skillsP2.get(i).getMasteryLevel() > max_mastery2) {
                    S2 = skillsP2.get(i);
                    max_mastery2 = skillsP2.get(i).getMasteryLevel();
                    idxS2 = i;
                }
            }
            // Mencari mastery level tertinggi: Spesifikasi 5.d.iii.2
            // banyak if tapi emang speknya kea gitu :')
            String namaSkill;
            if (max_mastery > max_mastery2) {
                namaSkill = new String(S1.getName());
                skillsP1.remove(idxS1);
                return new SkillEngimon(_skillDex.getEntity(namaSkill), max_mastery);
            } else if (max_mastery < max_mastery2) {
                namaSkill = new String(S1.getName());
                skillsP2.remove(idxS2);
                return new SkillEngimon(_skillDex.getEntity(namaSkill), max_mastery2);
            } else {
                if (S1.getName() == S2.getName()) {
                    // Spesifikasi 5.d.iv
                    if (this.max_mastery == this.max_mastery2 && max_mastery != 3) {
                        max_mastery += 1;
                    }
                    namaSkill = new String(S1.getName());
                    skillsP1.remove(idxS1);
                    return new SkillEngimon(_skillDex.getEntity(namaSkill), max_mastery);
                } else {
                    namaSkill = new String(S1.getName());
                    skillsP1.remove(idxS1);
                    return new SkillEngimon(_skillDex.getEntity(namaSkill), max_mastery);
                }
            }
        } else {
            if (skillsP1.size() > 0) {
                String namaSkill = new String(skillsP1.get(0).getName());
                int mastery = skillsP1.get(0).getMasteryLevel();
                skillsP1.remove(0);
                return new SkillEngimon(_skillDex.getEntity(namaSkill), mastery);
            } else if (skillsP2.size() > 0) {
                String namaSkill = new String(skillsP2.get(0).getName());
                int mastery = skillsP2.get(0).getMasteryLevel();
                skillsP2.remove(0);
                return new SkillEngimon(_skillDex.getEntity(namaSkill), mastery);
            } else {
                throw new BreedingException(4);
            }
        }
    }

    private String prioritySpecies(EngiDex _engiDex) {
        // Spesifikasi 5.e
        Element tempEl1 = el_p1[0];
        int i, j;
        for (j = 0; j < this.n2; j++) {
            for (i = 0; i < this.n1; i++) {
                if (max_adv < Element.getElementalAdvantage(el_p1[i], el_p2[j])) {
                    max_adv = Element.getElementalAdvantage(el_p1[i], el_p2[j]);
                    tempEl1 = el_p1[i];
                }
            }
        }
        max_adv = 0.0;
        Element tempEl2 = el_p2[0];

        for (j = 0; j < this.n2; j++) {
            for (i = 0; i < this.n1; i++) {
                if (max_adv < Element.getElementalAdvantage(el_p2[j], el_p1[i])) {
                    max_adv = Element.getElementalAdvantage(el_p2[j], el_p1[i]);
                    tempEl1 = el_p2[i];
                }
            }
        }

        if (Element.getElementalAdvantage(tempEl1, tempEl2) >= 1) {
            //printElement(tempEl1);
            return _engiDex.getEngimonNameFromElement(tempEl1);
        } else {
            //printElement(tempEl2);
            return _engiDex.getEngimonNameFromElement(tempEl2);
        }
    }

    // private static <T extends Number> T max(T a, T b) {
    //     return a.doubleValue() >= b.doubleValue() ? a : b;
    // }

}
