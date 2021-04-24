package EngimonHunter2000;

import java.util.Set;

import com.google.common.collect.SetMultimap;

import java.util.HashSet;

/**
 * 
 * {@link EngimonHunter2000}
 * 
 * @author Alvin Wilta
 */

public class EngimonSpecies {
    private final String spesies;
    private final String slogan;
    private SkillEngimon starterSkill;
    private HashSet<Element> listElement;

    /**
     * Konstruktor ini dibuat untuk membuat {@link EngiDex}
     * 
     * @param _spesies nama dari spesies yang dibaca dari file
     * @param _slogan  slogan dari spesies yang dibaca dari file
     * @param _starter skill pertama yang dibaca dari file dan sudah di-construct
     *                 menggunakan konstruktor {@link SkillEngimon}
     */
    EngimonSpecies(String _spesies, String _slogan, SkillEngimon _starter) {
        this.spesies = _spesies;
        this.slogan = _slogan;
        this.starterSkill = _starter;
        this.listElement = new HashSet<Element>();
    }

    EngimonSpecies(EngiDex dex, String _spesies) {
        this.spesies = dex.getEntity(_spesies).getSpecies();
        this.slogan = dex.getEntity(_spesies).getSlogan();
        this.listElement = new HashSet<Element>();
        this.listElement.addAll(dex.getEntity(_spesies).getListElement());
        this.starterSkill = dex.getEntity(_spesies).getStarterSkill();

    }

    public String getSpecies() {
        return this.spesies;
    }

    public String getSlogan() {
        return this.slogan;
    }

    public SkillEngimon getStarterSkill() {
        return this.starterSkill;
    }

    public Set<Element> getListElement() {
        return this.listElement;
    }

    public void addElement(Element _element) {
        this.listElement.add(_element);
    }

}