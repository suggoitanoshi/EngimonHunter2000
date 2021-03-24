#include "../headers/InventoryException.hpp"
#include "string"

const string InventoryException::msg[] = {
    "Inventory penuh", "Inventory kosong",
    "Item tersebut tidak ada di inventory",
    "Tidak ada item di indeks tersebut"};

InventoryException::InventoryException(int id) : exceptionID(id) {}
const char* InventoryException::what() {
    return msg[this->exceptionID].c_str();
}
