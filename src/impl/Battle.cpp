#include "../headers/Battle.hpp"

#include <iostream>

using namespace std;

Battle::Battle() {}

void Battle::setEngimonWinner() {}

double Battle::getAdvantage(Elements::el A, Elements::el B) {
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
