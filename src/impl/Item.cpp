/**
 * Item.cpp
 * y e e wangy wangy
 *
 * Implementasi class Item
 */

#include "../headers/Item.hpp"

#include <iostream>
#include <string>
#include <vector>

#include "../headers/Elements.hpp"
#include "../headers/Engimon.hpp"
#include "../headers/Skill.hpp"

using namespace std;

Item::Item(const string _name, const unsigned _basePower,
           const unsigned _masteryLevel, const vector<Elements>& _elements,
           unsigned _quantity)
    : Skill(_name, _basePower, _masteryLevel, _elements) {
    quantity = _quantity;
}

Item::Item(const string _name, const unsigned _basePower,
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

void Item::learn(Engimon e) {
    bool compatible = false;
    vector<Elements> engiElements = e.getElements();

    // Mengecek mastery level item
    if (masteryLevel != 1) {
        throw ItemExp(0);
    }

    // Mengecek kecocokan skill item dengan engimon
    /*
    for (int i = 0; i < (int)e.getElementCount(); i++) {
        for (int j = 0; j < (int)elements.size(); j++) {
            if (engiElements[i].getName == elements[j]) {
                compatible = true;
                break;
            }
        }
    }

    if (!compatible) {
        throw ItemExp(1);
    }
    */

    // Mengecek apakah skill sudah dipelajari atau belum
    /*
    for (int i = 0; i < (int)e.getSkillsCount(); i++) {
        if (e.getSkills(i) == name) {
            throw ItemExp(2);
        }
    }
    */

    // Mengecek jumlah skills yang telah dilajari
    /*
    if (e.getSkillsCount() = MAX_SKILLS) {
        for (int i = 0; (int)MAX_SKILLS; i++) {
            cout << i+1 << ". "<< e.getSkills(i) << endl;
        }
        cout << "Pilih nomor skill untuk diganti dengan skill baru: ";
        cin >> j;

        if (j < 1 || j > e.skills.size()) {
            throw ItemExp(3);
        }

        e.setSkills(j-1, name);
    } else {
        e.setSkills(e.getSkillsCount() name);
    }
    */
    quantity--;
}

ItemExp::ItemExp(int x) : msgID(x) {}
void ItemExp::bruh() const { cout << msg[msgID] << endl; }
string ItemExp::msg[] = {"Mastery level item bukan 1",
                         "Skill item tidak cocok dengan Engimon",
                         "Skill item sudah pernah dipelajari",
                         "Input pilihan untuk mengganti skill di luar batas"};
