#include <ostream>

using namespace std;

class MapTile {
public:
    enum TileType {
        WATER,
        GRASSLAND,
        EDGE,
        OCCUPIED_W, // water
        OCCUPIED_F, // fire
        OCCUPIED_G, // ground
        OCCUPIED_E, // electric
        OCCUPIED_I, // ice
        OCCUPIED_L, // fire/electric
        OCCUPIED_S, // water/ice
        OCCUPIED_N, // ground/water
        PLAYER,
        ACTIVE_ENGI,
    };

    /**
     * default constructor with default values:
     * unoccupied
     * edge
     */
    MapTile();  // default constructor, shouldn't be used
    MapTile(TileType);
    MapTile(char);
    MapTile(const MapTile&);
    MapTile& operator=(const MapTile&);

    // getter
    bool isOccupied() const;
    TileType getType() const;
    char getTileChar() const;

    // setter
    MapTile& operator=(char);
    void makeOccupied();
    void makeUnOccupied();
    void setType(TileType);
    void setTileChar(char);

    friend ostream& operator<<(ostream& os, const MapTile& src);

private:
    bool occupied;  // always unoccupied on initialization
    TileType type;
    char tileChar;

    void setTileChar();
    void setType();
};
