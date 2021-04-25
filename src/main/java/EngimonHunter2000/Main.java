package EngimonHunter2000;

/**
 * Program utama {@link EngimonHunter2000}
 * @author y e e wangy wangy
 */
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, world!");
        String[] a = {};
        try {
            ElementsList e = new ElementsList(a);
            System.out.println(e.getElementsList());
        } catch (ElementsListException f) {
            f.bruh();
            System.out.println(f);
        }

        SkillDex skillDex = new SkillDex();
        skillDex.getDexDataFromFile("data/Skills.csv");
        // System.out.println(skillDex.toString());

        EngiDex engiDex = new EngiDex(skillDex);
        engiDex.getDexDataFromFile("data/Engimons.csv");
        System.out.println(engiDex.toString());

        GameState gs = new GameState();
        gs.spawnWildEngimons(10);
        gs.save("data/game.obj");

        GameState gsLoad = GameState.load("data/game.obj");
    }
}
