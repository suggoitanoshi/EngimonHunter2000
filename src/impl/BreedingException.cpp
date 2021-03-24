#include "../headers/BreedingException.hpp"
#include "iostream"

BreedingException::BreedingException(int id): msgID(id){}
const char* BreedingException::what(){ return msg[msgID].c_str(); }
void BreedingException::bruh(){ std::cout << what() << std::endl;}
std::string BreedingException::msg[] = {"Level orangtua tidak cukup"};