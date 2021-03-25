/**
 * Elements.hpp
 * y e e wangy wangy
 *
 * Header berisi enum elements yang terdapat di gim Engimon
 */

#ifndef _ELEMENTS_HPP_
#define _ELEMENTS_HPP_

#include <string>

using namespace std;

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

struct Elements {
    enum el {
        ELECTRIC,
        FIRE,
        GROUND,
        ICE,
        WATER,
    };

    static el getElement(const string&);
    static string getName(const el);
};

#endif
