package EngimonHunter2000;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class EngiDex implements Dex<EngimonSpecies> {
    private HashMap<String, EngimonSpecies> dex;
    private SkillDex skillDex;

    // constructors
    public EngiDex(SkillDex _skillDex) {
        this.dex = new HashMap<String, EngimonSpecies>();
        this.skillDex = _skillDex;
    }

    // getters
    public EngimonSpecies getEntity(String name) {
        return dex.get(name);
    }

    public void getDexDataFromFile(String pathToFile) throws DexException {
        Reader in = null;
        try {
            String[] headers = { "SpesiesName", "Slogan", "FirstMove", "Element1", "Element2", "Element3", "Element4",
                    "Element5" };

            in = new FileReader(pathToFile);
            Iterable<CSVRecord> rows = CSVFormat.DEFAULT.withHeader(headers).withFirstRecordAsHeader().parse(in);

            for (CSVRecord row : rows) {
                int i = 0;
                String name = null;
                String slogan = null;
                String firstMove = null;
                HashSet<Element> elsSet = new HashSet<Element>();

                for (String rowData : row) {
                    switch (i) {
                    case 0:
                        name = rowData;
                        break;
                    case 1:
                        slogan = rowData;
                        break;
                    case 2:
                        firstMove = rowData;
                        break;
                    default:
                        try {
                            elsSet.add(Element.getElementFromString(rowData));
                        } catch (ElementException e) {
                            System.err.println(e.what());
                        }
                    }
                    i++;
                }

                if (name == null || slogan == null || firstMove == null || elsSet.size() == 0) {
                    throw new DexException(2);
                }

                Skill skill = this.skillDex.getEntity(firstMove);
                SkillEngimon firstSkill = new SkillEngimon(skill);
                try {
                    Element[] tempEls = new Element[elsSet.size()];
                    Iterator<Element> it = elsSet.iterator();
                    for (int j = 0; it.hasNext() && j < tempEls.length; ++j) {
                        tempEls[j] = it.next();
                    }
                    dex.put(name, new EngimonSpecies(name, slogan, firstSkill, tempEls));
                } catch (ElementsListException e) {
                    // error message?
                }
            }

            if (this.dex.size() == 0) {
                throw new DexException(1);
            }
        } catch (IOException e) {
            throw new DexException(0);
        } finally {
            try {
                in.close();
            } catch (Exception e) {

            }
        }
    }

    public Map<String, EngimonSpecies> getDex() {
        return dex;
    }

    // setters
    public void addEngi(String name, EngimonSpecies engi) {
        this.dex.put(name, engi);
    }

    // methods
    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder();
        int i = 1;
        SB.append(String.format("No. %-20sSlogan\t\t\tDefault Skill\t\tElement\n", "Nama"));
        for (Map.Entry<String, EngimonSpecies> engi : dex.entrySet()) {
            SB.append(String.format("%d%-3s%-20s", i, ".", engi.getKey()));
            SB.append(engi.getValue().getSlogan());
            SB.append("\t\t");
            SB.append(engi.getValue().getStarterSkill().getName());
            SB.append("\t\t[");
            int j = 0;
            int elsLen = engi.getValue().getListElement().getElementsList().size();
            for (Element el : engi.getValue().getListElement().getElementsList()) {
                SB.append(el.name());
                if (j != elsLen - 1) {
                    SB.append(", ");
                }
                j++;
            }
            SB.append("]\n");
            i++;
        }
        return SB.toString();
    }
    
    private int randomGenerator(ArrayList<String> al) {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(al.size());
    }
    
    private ArrayList<String> getEngimonNamesFromElement(Element el) {
        ArrayList<String> engiList = new ArrayList<String>();
        for (Map.Entry<String, EngimonSpecies> engi : dex.entrySet()) {
            if (engi.getValue().getListElement().getElementsList().size() == 1
            && engi.getValue().getListElement().getElementsList().contains(el)) {
                engiList.add(engi.getValue().getSpecies());
            }
        }
        return engiList;
    }

    public String getEngimonNameFromElement(Element el) {
        ArrayList<String> engiList = getEngimonNamesFromElement(el);
        return engiList.get(randomGenerator(engiList));
    }
        
    public String getEngimonNameFromElement(Element el1, Element el2) {
        ArrayList<String> engiList = new ArrayList<String>();
        for (Map.Entry<String, EngimonSpecies> engi : dex.entrySet()) {
            if (engi.getValue().getListElement().getElementsList().size() == 2
            && engi.getValue().getListElement().getElementsList().contains(el1)
            && engi.getValue().getListElement().getElementsList().contains(el2)) {
                engiList.add(engi.getValue().getSpecies());
            }
        }

        if (engiList.size() == 0) {
            engiList.addAll(getEngimonNamesFromElement(el1));
            engiList.addAll(getEngimonNamesFromElement(el2));
        }
        return engiList.get(randomGenerator(engiList));
    }
}
