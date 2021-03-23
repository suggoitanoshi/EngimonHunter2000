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
// #include "Engimon.hpp"

/**
 * Class untuk menyimpan skill yang (dapat) dimiliki Engimon
 */
class Item : Skill {
private:
    unsigned quantity;
    std::vector<Elements> elements;  // elemen yang dapat mempelajari skill ini

public:
    // constructors and destructors
    Item(const std::string, const unsigned, const unsigned,
         const std::vector<Elements>&, unsigned);
    Item(const std::string, const unsigned, const unsigned, const Elements,
         unsigned);
    Item(const Item&);
    // ~Item();

    // getters
    unsigned getQuantity() const;

    // setters
    void setQuantity(unsigned);

    // methods
    void learn(/*Engimon*/);
};

#endif
