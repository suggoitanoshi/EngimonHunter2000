#include "../headers/Elements.hpp"

#include <iostream>

Elements::el Elements::getElement(const string& metadata) {
    if (metadata == "ELECTRIC") {
        return ELECTRIC;
    } else if (metadata == "FIRE") {
        return FIRE;
    } else if (metadata == "ICE") {
        return ICE;
    } else if (metadata == "GROUND") {
        return GROUND;
    } else if (metadata == "WATER") {
        return WATER;
    } else {
        throw ElementsException(0);
    }
}

string Elements::getName(const Elements::el e) {
    string ret;
    switch (e) {
        case ELECTRIC:
            ret = "Electric";
            break;
        case FIRE:
            ret = "Fire";
            break;
        case ICE:
            ret = "Ice";
            break;
        case GROUND:
            ret = "Ground";
            break;
        case WATER:
            ret = "Water";
            break;
        default:
            throw ElementsException(0);
    }

    return ret;
}

ElementsException::ElementsException(int id) : msgID(id) {}
const char* ElementsException::what() { return msg[msgID].c_str(); }
void ElementsException::bruh() { cout << what() << endl; }
string ElementsException::msg[] = {"Element tidak ada."};
