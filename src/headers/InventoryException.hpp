#ifndef _INVENTORY_EXCEPTION_HPP
#define _INVENTORY_EXCEPTION_HPP
#include "string"

class InventoryException {
private:
    const int exceptionID;
    static const std::string msg[];

public:
    InventoryException(int id) : exceptionID(id) {}
    std::string what() const { return msg[this->exceptionID]; }
};

#endif  // _BASE_INVENTORY_HPP