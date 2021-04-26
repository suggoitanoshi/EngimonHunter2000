package EngimonHunter2000;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * Class untuk skilldex. Mengimplementasi {@link Dex}.
 * @author Josep Marcello
 */
public class SkillDex implements Dex<Skill> {
    private HashMap<String, Skill> dex;

    public SkillDex() throws DexException {
        dex = new HashMap<String, Skill>();
        getDexDataFromFile("data/Skills.csv");
    }

    public SkillDex(String fpath) throws DexException {
        dex = new HashMap<String, Skill>();
        getDexDataFromFile(fpath);
    }

    public Skill getEntity(String name) {
        // TODO: possible null here. Maybe throw an exception when it's null?
        return dex.get(name);
    }

    public void getDexDataFromFile(String pathToFile) throws DexException {
        Reader in = null;
        try {
            // parse CSV
            String[] headers = {
                "NamaSkill",
                "BasePower",
                "Element1",
                "Element2",
                "Element3",
                "Element4",
                "Element5"
            };

            in = new FileReader(pathToFile);
            Iterable<CSVRecord> rows = CSVFormat.DEFAULT
                .withHeader(headers)
                .withFirstRecordAsHeader()
                .parse(in);


            // parse entries di CSV jadi Skill baru
            for (CSVRecord row : rows) {
                int i = 0;
                String name = null;
                Integer basePower = null;
                Set<Element> elsSet = new HashSet<Element>();

                for (String rowData : row) {
                    switch (i) {
                        case 0:
                            name = rowData;
                            break;
                        case 1:
                            basePower = Integer.parseInt(rowData);
                            break;
                        default: // baca element
                            try {
                                elsSet.add(Element.getElementFromString(rowData));
                            } catch (ElementException e) {
                                // skip element yang invalid
                                e.printStackTrace();
                            }
                    }
                    ++i;
                }

                // kalo ada entry yg gagal, throw exception
                if (name == null || basePower == null || elsSet.size() == 0) {
                    throw new DexException(2);
                }

                try {
                    Element[] els = new Element[elsSet.size()];
                    elsSet.toArray(els);
                    dex.put(name, new Skill(name, basePower, els));
                } catch (ElementsListException e) {
                    throw new DexException(2);
                }
            }

            if (dex.size() == 0) {
                throw new DexException(1);
            }
        } catch (IOException e) {
            throw new DexException(0);
        } finally {
            // close the reader
            try {
                in.close();
            } catch (Exception e) {
                // do nothing
            }
        }
    }

    public Map<String, Skill> getDex() {
        return dex;
    }

    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder();

        // cursed formatting
        // i don't understand why it works or doesn't
        int i = 1;
        SB.append(String.format("No. %-20sBase Power\tElement\n", "Nama"));
        for (Map.Entry<String, Skill> skill : dex.entrySet()) {
            SB.append(String.format("%d%-3s%-20s", i, ".", skill.getKey()));
            SB.append("\t");
            SB.append(skill.getValue().getBasePower());
            SB.append("\t[");
            int j = 0;
            int elsLen = skill.getValue().getElements().size();
            for (Element el : skill.getValue().getElements()) {
                SB.append(el.name());
                if (j != elsLen -1) {
                    SB.append(", ");
                }
                j++;
            }
            SB.append("]\n");
            i++;
        }

        return SB.toString();
    }
}
