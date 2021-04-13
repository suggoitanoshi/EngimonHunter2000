package EngimonHunter2000;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

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
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(headers)
                .withFirstRecordAsHeader()
                .parse(in);

            for (CSVRecord record : records) {
                System.out.println(record.get("NamaSkill"));
            }

            in.close();
        } catch (IOException e) {
            // TODO: Finish dex exception
            System.out.println(e.getMessage());
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