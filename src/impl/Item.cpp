/**
 * Item.cpp
 * y e e wangy wangy
 *
 * Implementasi class Item
 */

#include "../headers/Item.hpp"
// #include "../headers/Engimon.hpp"
#include <iostream>

Item::Item(const std::string _name, const unsigned _basePower,
           const unsigned _masteryLevel, const std::vector<Elements>& _elements,
           unsigned _quantity)
    : Skill(_name, _basePower, _masteryLevel, _elements) {
    quantity = _quantity;
}

Item::Item(const std::string _name, const unsigned _basePower,
           const unsigned _masteryLevel, const Elements _elements,
           unsigned _quantity)
    : Skill(_name, _basePower, _masteryLevel, _elements) {
    quantity = _quantity;
}

Item::Item(const Item& src)
    : Skill(src.name, src.basePower, src.masteryLevel, src.elements) {
    quantity = src.quantity;
}

unsigned Item::getQuantity() const { return quantity; }

void Item::setQuantity(unsigned _quantity) { quantity = _quantity; }

void Item::learn(/*Engimon e*/) {
    // Mengecek apakah skill sudah dipelajari atau belum
    /*
    for (int i = 0; i < e.getSkillsCount(); i++) {  // atau MAX_SKILLS ?
        if (e.Skills == name) {
            throw (name);
        }
    }
    */

    // Mengecek mastery level item
    if (masteryLevel != 1) {
        throw(masteryLevel);
    } else {
        quantity--;
    }

    // Mengecek jumlah skills yang telah dilajari
    /*
    if (e.getSkillsCount() >= 4) {
        for (int i = 0; e.getSkillsCount(); i++) {
            cout << i+1 << ". "<< e.skills[i],getName() << endl;
        }
        cout << "Pilih nomor skill untuk diganti dengan skill baru: ";
        cin >> j;

        if (j < 1 || j > e.getSkillsCount()) {
            throw (j);
        }

        e.Skills[j-1] = SKill(name, basePower, masteryLevel, elements);
    } else {
        e.Skills[e.getSKillsCount()] = SKill(name, basePower, masteryLevel,
    elements);
    }
    */
}
