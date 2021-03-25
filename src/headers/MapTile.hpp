#include <ostream>

using namespace std;

class MapTile {
public:
    enum TileType {
        WATER,
        GRASSLAND,
        EDGE,
    };

    MapTile();  // default constructor, shouldn't be used
    // default value is:
    // unoccupied
    // edge
    MapTile(TileType);
    MapTile(char);

    bool isOccupied();
    TileType getType();
    char getTileChar();

    void makeOccupied();
    void makeUnOccupied();
    void setType(TileType);
    void setTileChar(char);

    friend ostream& operator<<(ostream& os, const MapTile& src);

private:
    bool occupied;  // always unoccupied on initialization
    TileType type;
    char tileChar;
};
