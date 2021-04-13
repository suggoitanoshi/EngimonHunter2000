package EngimonHunter2000;

import java.util.Map;

/**
 * Sebuah interface seperti "ensiklopedia" besar yang dapat digunakan untuk
 * mengakses {@link DexEntity} di game, misalnya Skill dan Engimon, menggunakan
 * nama entity.
 *
 * Agar data dapat diakses menggunakan namanya, digunakan struktur data/class
 * yang mengimmplementasikan Map dengan string (untuk nama) sebagia key dan
 * T sebagai value. T adalah entity yang disimpan.
 * @author Josep Marcello
 */
public interface Dex<T extends DexEntity> {
    /**
     * Fungsi untuk mendapatkan {@link DexEntity} yang disimpan.
     * @param name nama entity
     * @return entity {@link DexEntity} yang memiliki nama `name`.
     */
    public T getEntity(String name);

    /**
     * Fungsi untuk membaca data dex dari sebuah file lalu disimpan ke struktur.
     * Format file dibebaskan
     * @param pathToFile path ke file yang berisi data dex
     */
    public void getDexDataFromFile(String pathToFile);

    /**
     * Fungsi untuk mendapatkan map yang menyimpan {@link DexEntity}
     * @return map yang menyimpan
     */
    public Map<String, T> getDex();

    /**
     * Fungsi yang mengubah Dex ke string yang dapat diprint. Format dibebaskan.
     */
    @Override public String toString();
}
