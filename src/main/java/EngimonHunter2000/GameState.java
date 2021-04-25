package EngimonHunter2000;
import java.util.ArrayList;

public class GameState {
  Player player;
  SkillDex skillDex;
  EngiDex engiDex;
  MapTile map;
  ArrayList<Engimon> wildEngimon;

  public GameState(){
    try{
      skillDex = new SkillDex();
      skillDex.getDexDataFromFile("data/Skills.csv");
      engiDex = new EngiDex(skillDex);
      engiDex.getDexDataFromFile("data/Engimons.csv");
      map = new MapTile();
      player = new Player(engiDex);
    }
    catch (EngimonHunter2000Exception e){
      System.err.println(e.getMessage());
      System.exit(-1);
    }
  }

  public Player getPlayer() {
    return player;
  }

  public Tile[][] getMap(){
    return map.getMap();
  }
}
