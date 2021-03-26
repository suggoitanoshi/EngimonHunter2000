#include "../headers/Map.hpp"

#include <fstream>
#include <iostream>
#include <tuple>

using namespace std;

Map::Map() : Map("data/map.txt", 32, 16) {}

Map::Map(string path, unsigned lenx, unsigned leny) {
    int x, y;
    ifstream f;
    string linemap;

    vector<MapTile> tmp(leny);
    tiles = vector<vector<MapTile>>(lenx, tmp);

    f.open(path);
    if (f.is_open()) {
        y = 0;
        while (getline(f, linemap)) {
            x = 0;
            for (char& c : linemap) {
                if (c != '\0' && c != '\n' && c != '\r') {
                    tiles[x][y] = MapTile(c);
                    x++;
                }
            }
            y++;
        }
    }

    mapX = lenx;
    mapY = leny;
}

void Map::setTile(unsigned x, unsigned y, MapTile::TileType type) {
    tiles[x][y].setType(type);
}
void Map::setTile(unsigned x, unsigned y, char tileChar) {
    tiles[x][y].setTileChar(tileChar);
}
void Map::setTile(unsigned x, unsigned y, MapTile& mapTile) {
    tiles[x][y] = mapTile;
}

void Map::setTileToOriginal(unsigned x, unsigned y) {
    tiles[x][y].toOriginalType();
}

void Map::printMap() const {
    cout << "--------------------------P E T A--------------------------"
         << endl;
    for (int i = 0; (unsigned)i < mapY; i++) {
        for (int j = 0; (unsigned)j < mapX; j++) {
            cout << tiles[j][i];
        }
        cout << endl;
    }
}

MapTile Map::getTile(unsigned x, unsigned y) const { return tiles[x][y]; }

void Map::legends() const {
    cout << "o: Water\n";
    cout << "-: Grassland\n";
    cout << "*: Edge\n";
    cout << "W: Water Engimon\n";
    cout << "I: Ice Engimon\n";
    cout << "F: Fire Engimon\n";
    cout << "G: Ground Engimon\n";
    cout << "E: Electric Engimon\n";
    cout << "L: Fire/Electric Engimon\n";
    cout << "S: Water/Ice Engimon\n";
    cout << "N: Water/Ground Engimon" << endl;
}

bool Map::isFree(const unsigned x, const unsigned y) const {
    return x > 0 && x < mapX - 1 && y > 0 && y < mapY - 1 &&
           !tiles[x][y].isOccupied();
}
