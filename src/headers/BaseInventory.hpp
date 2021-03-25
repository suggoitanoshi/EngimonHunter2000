#ifndef _BASE_INVENTORY_HPP
#define _BASE_INVENTORY_HPP

class BaseInventory {
protected:
    static const int maxCap;  // kata josep 500
    static int items;

public:
    static int getTotalItemCount();
    static int getMaxCapacity();
    static void incrementItem();
    static void subtractItem();
};

#endif  // _BASE_INVENTORY_HPP
