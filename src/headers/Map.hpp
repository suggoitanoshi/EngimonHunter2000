#include <vector>

#include "MapTile.hpp"
#include "Player.hpp"

using namespace std;

class Map {
public:
    Map();        // readMap
    Map(string, unsigned leny, unsigned lenx);  // readMap
    MapTile getTile(unsigned x, unsigned y) const;

    void setTile(unsigned x, unsigned y, MapTile::TileType);
    void setTile(unsigned x, unsigned y, char);
    void setTile(unsigned x, unsigned y, MapTile&);
    void setTileToOriginal(unsigned x, unsigned y);

    void printMap() const;
    void legends() const;
    bool isFree(const unsigned x, const unsigned y) const;

private:
    vector<vector<MapTile>> tiles;

    unsigned mapX;
    unsigned mapY;
};
