#ifndef _INVENTORY_EXCEPTION_HPP
#define _INVENTORY_EXCEPTION_HPP
#include "string"

using namespace std;

class InventoryException : exception {
private:
    const int exceptionID;
    static const string msg[];

public:
    InventoryException(int);
    const char* what();
};

#endif  // _BASE_INVENTORY_HPP
