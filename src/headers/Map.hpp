#include "MapTile.hpp"
#include "Player.hpp"

class Map {
public:
    static const unsigned mapX = 32;
    static const unsigned mapY = 16;

    Map();        // readMap
    Map(string);  // readMap
    MapTile getTile(unsigned x, unsigned y) const;

    void setTile(unsigned x, unsigned y, MapTile::TileType);
    void setTile(unsigned x, unsigned y, char);
    void setTile(unsigned x, unsigned y, MapTile&);

    void printMap(const Player&) const;
    void legends() const;

private:
    MapTile tiles[mapX][mapY];
};
