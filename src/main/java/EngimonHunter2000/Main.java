package EngimonHunter2000;

/**
 * @author y e e wangy wangy
 * Program utama EngimonHunter2000
 */
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, world!");
        String[] a = {};
        try {
            Elements e = new Elements(a);
            System.out.println(e.getElementsList());
        } catch (ElementsException f) {
            f.bruh();
        }
    }
}
