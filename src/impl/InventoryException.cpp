#include "../headers/InventoryException.hpp"

#include "string"

const std::string InventoryException::msg[] = {
    "Inventory penuh", "Inventory kosong",
    "Item tersebut tidak ada di inventory",
    "Tidak ada item di indeks tersebut"};