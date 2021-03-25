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

#include "../headers/Dex.hpp"
#include "../headers/Elements.hpp"
#include "../headers/Engimon.hpp"
#include "../headers/Skill.hpp"

using namespace std;

Item::Item(const string _name, const unsigned _basePower,
           const unsigned _masteryLevel, const vector<Elements::el>& _elements,
           unsigned _quantity)
    : Skill(_name, _basePower, _masteryLevel, _elements) {
    quantity = _quantity;
}

Item::Item(const string _name, const unsigned _basePower,
           const unsigned _masteryLevel, const Elements::el _elements,
           unsigned _quantity)
    : Skill(_name, _basePower, _masteryLevel, _elements) {
    quantity = _quantity;
}

Item::Item(const Skill& s, unsigned _quantity) : Skill(s) {
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

bool Item::operator==(const Item& sblh) const {
    return name == sblh.name && basePower == sblh.basePower &&
           masteryLevel == sblh.masteryLevel && elements == sblh.elements &&
           quantity == sblh.quantity;
}

ostream& operator<<(ostream& os, const Item& src) {
    os << "Name\t:" << src.getName() << "\n";
    return os;
}

unsigned Item::getQuantity() const { return quantity; }

void Item::setQuantity(unsigned _quantity) { quantity = _quantity; }

void Item::learn(Engimon& e, const Dex& dex) {
    bool compatible = false;

    // Mengecek mastery level item
    if (masteryLevel != 1) {
        throw ItemException(0);
    }

    // Mengecek kecocokan skill item dengan engimon
    for (int i = 0; i < (int)e.getElementCount(); i++) {
        for (int j = 0; j < (int)elements.size(); j++) {
            if (e.getElements()[i] == elements[j]) {
                compatible = true;
                break;
            }
        }
    }

    if (!compatible) {
        throw ItemException(1);
    }

    // Mengecek apakah skill sudah dipelajari atau belum
    for (int i = 0; i < e.getSkillsCount(); i++) {
        if (e.getSkillByIndex(i).getName() == name) {
            throw ItemException(2);
        }
    }

    // Mengecek jumlah skills yang telah dilajari
    if (e.getSkillsCount() == e.maxSkills) {
        int old;
        for (int i = 0; e.maxSkills; i++) {
            cout << i + 1 << ". " << e.getSkillByIndex(i).getName() << endl;
        }
        cout << "Pilih nomor skill untuk diganti dengan skill baru: ";
        cin >> old;

        if (old > (int)e.maxSkills || old < 1) {
            throw ItemException(3);
        }

        // e.setSkills(old + 1, e.getSkillByIndex(i));
    } else {
        e.setSkills(e.getSkillsCount(), dex.getSkill(name));
    }
    quantity--;
}

void Item::showItem() const {
    vector<Elements::el> els = getElements();
    cout << "Name\t: " << getName() << "\n";
    cout << "Base power\t: " << getBasePower() << "\n";
    cout << "Mastery level\t: " << getMasteryLevel() << "\n";
    cout << "Element(s)\t: ";
    size_t i = 0;
    for (vector<Elements::el>::iterator it = els.begin(); it != els.end();
         ++it) {
        cout << Elements::getName(*it);
        if (i != (els.size() - 1)) {
            cout << ", ";
            ++i;
        }
    }
    cout << "\n";
    cout << "Quantity\t: " << getQuantity() << "\n";
}

ItemException::ItemException(int x) : msgID(x) {}
const char* ItemException::what() { return msg[msgID].c_str(); }
void ItemException::bruh() { cout << what() << endl; }
string ItemException::msg[] = {
    "Mastery level item bukan 1", "Skill item tidak cocok dengan Engimon",
    "Skill item sudah pernah dipelajari",
    "Input pilihan untuk mengganti skill di luar batas"};
