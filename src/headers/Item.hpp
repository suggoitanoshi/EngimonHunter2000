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

#include "Dex.hpp"
#include "Elements.hpp"
#include "Engimon.hpp"
#include "Skill.hpp"

using namespace std;

/**
 * Class untuk item skill yang (dapat) dimiliki Player dan dipelajari oleh
 * Engimon
 */
class Item : public Skill {
private:
    unsigned quantity;

public:
    // constructors and destructors
    Item(const string, const unsigned, const unsigned,
         const vector<Elements::el>&, unsigned);
    Item(const string, const unsigned, const unsigned, const Elements::el,
         unsigned);
    Item(const Skill&, unsigned);
    Item(const Item&);

    // operator
    Item& operator=(const Item&);
    bool operator==(const Item&);
    friend ostream& operator<<(ostream& os, const Item& src);

    // getters
    unsigned getQuantity() const;

    // setters
    void setQuantity(unsigned);

    // methods
    void learn(Engimon, const Dex&);
};

class ItemException : exception {
private:
    const int msgID;
    static string msg[];

public:
    ItemException(int);
    const char* what();
    void bruh();
};

#endif
