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

public class EngiDex implements Dex<Engimon> {
    private HashMap<String, EngimonSpecies> dex;

    // constructors
    public EngiDex() {
        this.dex = new HashMap<String, EngimonSpecies>();
    }

    // getters
    public EngimonSpecies getEntity(String name) {
        if (dex.get(name) == null) {
            throw new DexException(4);
        } else {
            return dex.get(name);
        }

    }

    public void getDexDataFromFile(String pathToFile) {
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
                Set<Element> elsSet = new HashSet<Element>();

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

                try {
                    Element[] els = new Element[elsSet.size()];
                    elsSet.toArray(els);
                    dex.put(name, new EngimonSpecies(name, slogan, els));
                } catch (EngimonSpeciesException e) {
                    throw new DexException(2);
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
        SB.append(String.format("No. %-20sSpecies\tSlogan\telement\n", "Nama"));
        for (Map.Entry<String, EngimonSpecies> engi : dex.entrySet()) {
            SB.append(String.format("%d%-3s%-20s", i, ".", engi.getKey()));
            SB.append("\t");
            SB.append(engi.getValue().getSpecies());
            SB.append("\t[");
            int j = 0;
            int elsLen = engi.getValue().getElements().size();
            for (Element el : engi.getValue().getElements()) {
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

}
