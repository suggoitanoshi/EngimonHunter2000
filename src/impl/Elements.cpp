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

double Elements::getElementalAdvantage(const el A, const el B){
    if (A == Elements::FIRE) {
        if (B == Elements::FIRE) {
            return 1;
        } else if (B == Elements::WATER) {
            return 0;
        } else if (B == Elements::ELECTRIC) {
            return 1;
        } else if (B == Elements::GROUND) {
            return 0.5;
        } else {  // B == Elements::ICE
            return 2;
        }
    } else if (A == Elements::WATER) {
        if (B == Elements::FIRE) {
            return 2;
        } else if (B == Elements::WATER) {
            return 1;
        } else if (B == Elements::ELECTRIC) {
            return 0;
        } else if (B == Elements::GROUND) {
            return 1;
        } else {  // B == Elements::ICE
            return 1;
        }
    } else if (A == Elements::ELECTRIC) {
        if (B == Elements::FIRE) {
            return 1;
        } else if (B == Elements::WATER) {
            return 2;
        } else if (B == Elements::ELECTRIC) {
            return 1;
        } else if (B == Elements::GROUND) {
            return 0;
        } else {  // B == Elements::ICE
            return 1.5;
        }
    } else if (A == Elements::GROUND) {
        if (B == Elements::FIRE) {
            return 1.5;
        } else if (B == Elements::WATER) {
            return 1;
        } else if (B == Elements::ELECTRIC) {
            return 2;
        } else if (B == Elements::GROUND) {
            return 1;
        } else {  // B == Elements::ICE
            return 0;
        }
    } else {  // A == Elements::ICE
        if (B == Elements::FIRE) {
            return 0;
        } else if (B == Elements::WATER) {
            return 1;
        } else if (B == Elements::ELECTRIC) {
            return 0.5;
        } else if (B == Elements::GROUND) {
            return 2;
        } else {  // B == Elements::ICE
            return 1;
        }
    }
}



ElementsException::ElementsException(int id) : msgID(id) {}
const char* ElementsException::what() { return msg[msgID].c_str(); }
void ElementsException::bruh() { cout << what() << endl; }
string ElementsException::msg[] = {"Element tidak ada."};
