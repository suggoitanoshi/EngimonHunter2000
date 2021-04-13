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
    HashMap<String, Skill> dex;

    public SkillDex() {
        dex = new HashMap<String, Skill>();
    }

    public Skill getEntity(String name) {
        return dex.get(name);
    }

    public void getDexDataFromFile(String pathToFile) throws DexException {
        Reader in;
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
                                System.err.println(e.what());
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
        }
    }

    public Map<String, Skill> getDex() {
        return dex;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}