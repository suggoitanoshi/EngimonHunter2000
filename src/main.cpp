/**
 * main.cpp
 * y e e wangy wangy
 *
 * Program utama
 */

#include <iostream>

#include "./headers/Elements.hpp"
#include "./headers/Skill.hpp"

int main() {
    Skill a("headeh", 100, 1, WATER);
    std::cout << a.getBasePower() << std::endl;
}
