package EngimonHunter2000;
import java.util.ArrayList;

public class GameState {
  Player player;
  SkillDex skillDex;
  EngiDex engiDex;
  MapTile maptile;
  Tile[][] map;
  ArrayList<Engimon> wildEngimon;

  public GameState(){
    try{
    	skillDex = new SkillDex();
    	skillDex.getDexDataFromFile("data/Skills.csv");
    	engiDex = new EngiDex(skillDex);
    	engiDex.getDexDataFromFile("data/Engimons.csv");
    	maptile = new MapTile();
    	map = maptile.getMap();
		player = new Player(engiDex);

		//set entities
		setEntities();
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
    return map;
  }

  public void setEntities(){
	
	//set player
	switch(map[player.getPositionY()][player.getPositionX()].getType()) {
		case EDGE1_MOUNTAIN:
			Tile tile1 = new Tile("data/resource/char_edge5.png", TileType.PLAYER, player.getPositionX(),player.getPositionY(),true);
			map[player.getPositionY()][player.getPositionX()] = tile1;
			break;
		case EDGE2_MOUNTAIN:
			Tile tile2 = new Tile("data/resource/char_edge4.png", TileType.PLAYER, player.getPositionX(),player.getPositionY(),true);
			map[player.getPositionY()][player.getPositionX()] = tile2;
			break;
		case EDGE3_MOUNTAIN:
			Tile tile3 = new Tile("data/resource/char_edge6.png", TileType.PLAYER, player.getPositionX(),player.getPositionY(),true);
			map[player.getPositionY()][player.getPositionX()] = tile3;
			break;
		case EDGE_GRASS:
			Tile tile4 = new Tile("data/resource/char_edge1.png", TileType.PLAYER, player.getPositionX(),player.getPositionY(),true);
			map[player.getPositionY()][player.getPositionX()] = tile4;
			break;
		case EDGE_TUNDRA:
			Tile tile5 = new Tile("data/resource/char_edge3.png", TileType.PLAYER, player.getPositionX(),player.getPositionY(),true);
			map[player.getPositionY()][player.getPositionX()] = tile5;
			break;
		case GRASS:
			Tile tile6 = new Tile("data/resource/char_grass.png", TileType.PLAYER, player.getPositionX(),player.getPositionY(),true);
			map[player.getPositionY()][player.getPositionX()] = tile6;
			break;
		case MOUNTAIN:
			Tile tile7 = new Tile("data/resource/char_mountain.png", TileType.PLAYER, player.getPositionX(),player.getPositionY(),true);
			map[player.getPositionY()][player.getPositionX()] = tile7;
			break;
		case TUNDRA:
			Tile tile8 = new Tile("data/resource/char_tundra.png", TileType.PLAYER, player.getPositionX(),player.getPositionY(),true);
			map[player.getPositionY()][player.getPositionX()] = tile8;
			break;
		case WATER:
			Tile tile_p = new Tile("data/resource/char_watergif.gif", TileType.PLAYER, player.getPositionX(),player.getPositionY(),true);
			map[player.getPositionY()][player.getPositionX()] = tile_p;
			break;
		default:
			break;
	}	
  }
  public void updateGameState(){
	  maptile = new MapTile();
	  map = maptile.getMap();
	  setEntities();
  }
}
