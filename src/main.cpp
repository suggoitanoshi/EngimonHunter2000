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
    } catch (std::exception& e) {
        std::cout << e.what() << std::endl;
        std::cout << "jia hahaha rusak" << std::endl;
        return 1;
    } catch (...) {
        std::cout << "jia hahaha rusak" << std::endl;
        std::cout << "tapi ga tau kenapa :(" << std::endl;
        return 1;
    }
}
