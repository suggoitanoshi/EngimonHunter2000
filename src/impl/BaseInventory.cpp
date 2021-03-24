#include "../headers/BaseInventory.hpp"

#include "../headers/InventoryException.hpp"

int BaseInventory::getTotalItemCount() { return items; };
int BaseInventory::getMaxCapacity() { return maxCap; }
void BaseInventory::incrementItem() {
    if (items == maxCap) throw InventoryException(0);
    items++;
};
void BaseInventory::subtractItem() {
    if (items == 0) throw InventoryException(1);
    items--;
};

int BaseInventory::items = 0;
const int BaseInventory::maxCap = 500;
