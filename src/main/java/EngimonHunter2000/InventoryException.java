package EngimonHunter2000;

/**
 * Exception ketika terjadi kesalahan pada {@link Inventory}
 * ID  : Alasan
 * ----|--------
 * 0   : Inventory Penuh 
 * 1   : Inventory Kosong
 * 2   : Item tidak ada di inventory
 * 3   : Indeks item tidak valid
 */
public class InventoryException extends EngimonHunter2000Exception{
  private static final long serialVersionUID = 1l;
  private final int msgID;
  static private final String[] msgs = {
    "Inventory Penuh",
    "Inventory Kosong",
    "Item tidak ada di inventory",
    "Item tidak ditemukan di inventory"
  };

  public InventoryException(int id){
    this.msgID = id;
  }

  public String what(){
    return msgs[this.msgID];
  }

  public void bruh(){
    System.out.println(what());
  }
}
