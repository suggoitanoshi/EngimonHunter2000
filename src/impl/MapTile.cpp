#include "../headers/MapTile.hpp"

MapTile::MapTile() {
    occupied = false;
    type = EDGE;
    tileChar = '*';
}

MapTile::MapTile(TileType _type) {
    occupied = false;
    type = _type;
    setTileChar();
}

MapTile::MapTile(char _tileChar) {
    occupied = false;
    tileChar = _tileChar;
    setType();
}

MapTile::MapTile(const MapTile& src) {
    occupied = src.occupied;
    type = src.type;
    tileChar = src.tileChar;
}

MapTile& MapTile::operator=(const MapTile& src) {
    occupied = src.occupied;
    type = src.type;
    tileChar = src.tileChar;

    return *this;
}

bool MapTile::isOccupied() const {
    return occupied;
}

MapTile::TileType MapTile::getType() const {
    return type;
}

char MapTile::getTileChar() const {
    return tileChar;
}

MapTile& MapTile::operator=(char _tileChar) {
    tileChar = _tileChar;
    this->setType();

    return *this;
}

void MapTile::makeOccupied() {
    occupied = true;
}

void MapTile::makeUnOccupied() {
    occupied = false;
}

void MapTile::setType(TileType _type) {
    type = _type;
    setTileChar();
}

void MapTile::setTileChar(char _tileChar) {
    tileChar = _tileChar;
    setType();
}

void MapTile::setTileChar() {
    if (type == GRASSLAND) {
        tileChar = '-';
        occupied = false;
    } else if (type == WATER) {
        tileChar = 'o';
        occupied = false;
    } else if (type == EDGE) {
        tileChar = '*';
        occupied = true;
    } else if (type == OCCUPIED_W) {
        tileChar = 'W';
        occupied = true;
    } else if (type == OCCUPIED_F) {
        tileChar = 'F';
        occupied = true;
    } else if (type == OCCUPIED_G) {
        tileChar = 'G';
        occupied = true;
    } else if (type == OCCUPIED_E) {
        tileChar = 'E';
        occupied = true;
    } else if (type == OCCUPIED_I) {
        tileChar = 'I';
        occupied = true;
    } else if (type == OCCUPIED_L) {
        tileChar = 'L';
        occupied = true;
    } else if (type == OCCUPIED_S) {
        tileChar = 'S';
        occupied = true;
    } else if (type == OCCUPIED_N) {
        tileChar = 'N';
        occupied = true;
    } else {
        // error
    }
}

void MapTile::setType() {
    if (tileChar == 'o') {
        type = WATER;
        occupied = false;
    } else if (tileChar == '-') {
        type = GRASSLAND;
        occupied = false;
    } else if (tileChar == '*') {
        type = EDGE;
        occupied = true;
    } else if (tileChar == 'W') {
        type = OCCUPIED_W;
        occupied = true;
    } else if (tileChar == 'F') {
        type = OCCUPIED_F;
        occupied = true;
    } else if (tileChar == 'G') {
        type = OCCUPIED_G;
        occupied = true;
    } else if (tileChar == 'E') {
        type = OCCUPIED_E;
        occupied = true;
    } else if (tileChar == 'I') {
        type = OCCUPIED_I;
        occupied = true;
    } else if (tileChar == 'L') {
        type = OCCUPIED_L;
        occupied = true;
    } else if (tileChar == 'S') {
        type = OCCUPIED_S;
        occupied = true;
    } else if (tileChar == 'N') {
        type = OCCUPIED_N;
        occupied = true;
    } else {
        // error
    }
}

ostream& operator<<(ostream& os, const MapTile& src) {
    os << src.tileChar;
    return os;
}
