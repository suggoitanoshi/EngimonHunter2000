/**
 * Item.hpp
 * y e e wangy wangy
 *
 * header berisi definisi atribut serta metode dari class Item
 */

#ifndef _ITEM_HPP_
#define _ITEM_HPP_

#include <string>
#include <vector>

#include "Elements.hpp"
#include "Skill.hpp"
#include "Engimon.hpp"

using namespace std;

/**
 * Class untuk item skill yang (dapat) dimiliki Player dan dipelajari oleh Engimon
 */
class Item : Skill {
private:
    unsigned quantity;

public:
    // constructors and destructors
    Item(const string, const unsigned, const unsigned, const vector<Elements>&,
         unsigned);
    Item(const string, const unsigned, const unsigned, const Elements,
         unsigned);
    Item(const Item&);
    // ~Item();

    // getters
    unsigned getQuantity() const;

    // setters
    void setQuantity(unsigned);

    // methods
    void learn(Engimon);
};

class ItemExp {
private:
    const int msgID;
    static std::string msg[];

public:
    ItemExp(int);
    void bruh() const;
};

#endif
