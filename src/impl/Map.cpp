#include "../headers/Map.hpp"

#include <fstream>
#include <iostream>

using namespace std;

Map::Map() : Map("data/map.txt") {}

Map::Map(string path) {
    int x, y;
    ifstream f;
    string linemap;
    f.open(path);
    if (f.is_open()) {
        y = 0;
        while (getline(f, linemap)) {
            x = 0;
            for (char& c : linemap) {
                if (c != '\0' && c != '\n' && c != '\r') {
                    tiles[x][y] = c;
                    x++;
                }
            }
            y++;
        }
    }
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

void Map::printMap(const Player& player) const {
    cout << "--------------------------P E T A--------------------------"
         << endl;
    for (int i = 0; i < 15; i++) {
        for (int j = 0; j < 32; j++) {
            if (j == player.getPositionX() && i == player.getPositionY()) {
                cout << 'P';
            } else if (j == player.getPositionX() + 1 &&
                       i == player.getPositionY() && player.getDir() == 'a') {
                cout << 'X';
            } else if (j == player.getPositionX() &&
                       i == player.getPositionY() + 1 &&
                       player.getDir() == 'w') {
                cout << 'X';
            } else if (j == player.getPositionX() - 1 &&
                       i == player.getPositionY() && player.getDir() == 'd') {
                cout << 'X';
            } else if (j == player.getPositionX() &&
                       i == player.getPositionY() - 1 &&
                       player.getDir() == 's') {
                cout << 'X';
            } else {
                cout << tiles[j][i];
            }
        }
        cout << endl;
    }
}

MapTile Map::getTile(unsigned x, unsigned y) const { return tiles[x][y]; }
