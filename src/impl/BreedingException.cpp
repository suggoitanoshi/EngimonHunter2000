#include "../headers/BreedingException.hpp"

#include "iostream"

BreedingException::BreedingException(int id) : msgID(id) {}
const char* BreedingException::what() { return msg[msgID].c_str(); }
void BreedingException::bruh() { cout << what() << endl; }
string BreedingException::msg[] = {"Level orangtua tidak cukup"};
