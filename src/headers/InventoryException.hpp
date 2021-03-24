#ifndef _INVENTORY_EXCEPTION_HPP
#define _INVENTORY_EXCEPTION_HPP
#include "string"

class InventoryException : std::exception {
private:
    const int exceptionID;
    static const std::string msg[];

public:
    InventoryException(int);
    const char* what();
};

#endif  // _BASE_INVENTORY_HPP
