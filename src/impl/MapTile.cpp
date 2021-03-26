#include "../headers/MapTile.hpp"

MapTile::MapTile() {
    occupied = true;
    type = EDGE;
    originalType = EDGE;
    tileChar = '*';
}

MapTile::MapTile(TileType _type) {
    type = _type;
    setTileChar();
    originalType = type;
}

MapTile::MapTile(char _tileChar) {
    tileChar = _tileChar;
    setType();
    originalType = type;
}

MapTile::MapTile(const MapTile& src) {
    occupied = src.occupied;
    type = src.type;
    tileChar = src.tileChar;
    originalType = src.originalType;
}

MapTile& MapTile::operator=(const MapTile& src) {
    occupied = src.occupied;
    type = src.type;
    tileChar = src.tileChar;
    originalType = src.originalType;

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

void MapTile::toOriginalType() {
    type = originalType;
    setTileChar();
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
    switch (type) {
        case GRASSLAND:
            tileChar = '-';
            occupied = false;
            break;
        case WATER:
            tileChar = 'o';
            occupied = false;
            break;
        case EDGE:
            tileChar = '*';
            occupied = true;
            break;
        case OCCUPIED_W:
            tileChar = 'W';
            occupied = true;
            break;
        case OCCUPIED_F:
            tileChar = 'F';
            occupied = true;
            break;
        case OCCUPIED_G:
            tileChar = 'G';
            occupied = true;
            break;
        case OCCUPIED_E:
            tileChar = 'E';
            occupied = true;
            break;
        case OCCUPIED_I:
            tileChar = 'I';
            occupied = true;
            break;
        case OCCUPIED_L:
            tileChar = 'L';
            occupied = true;
            break;
        case OCCUPIED_S:
            tileChar = 'S';
            occupied = true;
            break;
        case OCCUPIED_N:
            tileChar = 'N';
            occupied = true;
            break;
        case PLAYER:
            tileChar = 'P';
            occupied = true;
            break;
        default: // ACTIVE_ENGI
            tileChar = 'X';
            occupied = true;
    }
}

void MapTile::setType() {
    switch (tileChar) {
        case '-':
            type = GRASSLAND;
            occupied = false;
            break;
        case 'o':
            type = WATER;
            occupied = false;
            break;
        case '*':
            type = EDGE;
            occupied = true;
            break;
        case 'W':
            type = OCCUPIED_W;
            occupied = true;
            break;
        case 'F':
            type = OCCUPIED_F;
            occupied = true;
            break;
        case 'G':
            type = OCCUPIED_G;
            occupied = true;
            break;
        case 'E':
            type = OCCUPIED_E;
            occupied = true;
            break;
        case 'I':
            type = OCCUPIED_I;
            occupied = true;
            break;
        case 'L':
            type = OCCUPIED_L;
            occupied = true;
            break;
        case 'S':
            type = OCCUPIED_S;
            occupied = true;
            break;
        case 'N':
            type = OCCUPIED_N;
            occupied = true;
            break;
        case 'P':
            type = PLAYER;
            occupied = true;
            break;
        default: // ACTIVE_ENGI
            type = ACTIVE_ENGI;
            occupied = true;
    }
}

ostream& operator<<(ostream& os, const MapTile& src) {
    os << src.tileChar;
    return os;
}
