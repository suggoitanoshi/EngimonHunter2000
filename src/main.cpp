/**
 * main.cpp
 * y e e wangy wangy
 *
 * Program utama
 */

#include <iostream>

#include "./headers/Game.hpp"

int main() {
    Game g = Game();
    try {
        g.run();
    } catch (...) {
        std::cout << "jia hahaha rusak" << std::endl;
        return 1;
    }
}
