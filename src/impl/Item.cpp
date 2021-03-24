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
           const unsigned _masteryLevel, const set<Elements>& _elements,
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

Item& Item::operator=(const Item& src) {
    name = src.name;
    basePower = src.basePower;
    masteryLevel = src.masteryLevel;
    elements = src.elements;
    quantity = src.quantity;
    return *this;
}

bool Item::operator==(const Item& sblh) {
    return name == sblh.name && basePower == sblh.basePower &&
           masteryLevel == sblh.masteryLevel && elements == sblh.elements &&
           quantity == sblh.quantity;
}

ostream& operator<<(ostream& os, const Item& src) {
    set<Elements> els = src.getElements();
    os << "Name\t:" << src.getName() << "\n";
    os << "Base power\t: " << src.getBasePower() << "\n";
    os << "Mastery level\t: " << src.getMasteryLevel() << "\n";
    os << "Element(s)\t: ";
    size_t i = 0;
    for (set<Elements>::iterator it = els.begin(); it != els.end(); ++it) {
        os << *it << endl;
        if (i != (els.size() - 1)) {
            os << ", ";
        }
    }
    os << "\n";
    os << "Quantity\t: " << src.getQuantity() << "\n";
    return os;
}

unsigned Item::getQuantity() const { return quantity; }

void Item::setQuantity(unsigned _quantity) { quantity = _quantity; }

void Item::learn(Engimon e) {
    bool compatible = false;
    // vector<Elements> engiElements = e.getElements();

    // Mengecek mastery level item
    if (masteryLevel != 1) {
        throw ItemException(0);
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
        throw ItemException(1);
    }
    */

    // Mengecek apakah skill sudah dipelajari atau belum
    /*
    for (int i = 0; i < (int)e.getSkillsCount(); i++) {
        if (e.getSkills(i) == name) {
            throw ItemException(2);
        }
    }
    */

    // Mengecek jumlah skills yang telah dilajari
    /*
    if (e.getSkillsCount() = MAX_SKILLS) {
        string old;
        for (int i = 0; (int)MAX_SKILLS; i++) {
            cout << i+1 << ". "<< e.getSkills(i) << endl;
        }
        cout << "Pilih nama skill untuk diganti dengan skill baru: ";
        cin >> old;

        e.setSkills(old, name);
    } else {
        e.setSkills(name);
    }
    */
    quantity--;
}

ItemException::ItemException(int x) : msgID(x) {}
const char* ItemException::what() { return msg[msgID].c_str(); }
void ItemException::bruh() { cout << what() << endl; }
string ItemException::msg[] = {
    "Mastery level item bukan 1", "Skill item tidak cocok dengan Engimon",
    "Skill item sudah pernah dipelajari",
    "Input pilihan untuk mengganti skill di luar batas"};
