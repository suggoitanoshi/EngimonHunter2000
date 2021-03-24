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
#include "Engimon.hpp"
#include "Skill.hpp"

using namespace std;

/**
 * Class untuk item skill yang (dapat) dimiliki Player dan dipelajari oleh
 * Engimon
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
    
    // operator
    Item& operator=(const Item&);
    bool operator==(const Item&);
    friend ostream& operator<<(ostream& os, const Item& src);

    // getters
    unsigned getQuantity() const;

    // setters
    void setQuantity(unsigned);

    // methods
    void learn(Engimon);
};

    class ItemException : std::exception {
    private:
        const int msgID;
        static std::string msg[];

    public:
        ItemException(int);
        const char* what();
        void bruh();
};

#endif
