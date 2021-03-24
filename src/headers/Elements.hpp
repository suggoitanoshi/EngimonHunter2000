/**
 * Elements.hpp
 * y e e wangy wangy
 *
 * Header berisi enum elements yang terdapat di gim Engimon
 */

#ifndef _ELEMENTS_HPP_
#define _ELEMENTS_HPP_

using namespace std;
#include <string>

/**
 * Elemen-elemen yang dapat dimiliki Engimon ataupun skill item
 */

class ElementsException {
private:
    const int msgID;
    static std::string msg[];

public:
    ElementsException(int);
    const char* what();
    void bruh();
};

enum Elements {
    ELECTRIC,
    FIRE,
    GROUND,
    ICE,
    WATER,
};

Elements getElement(const string);

#endif
