#ifndef _BREEDING_EXCEPTION_H
#define _BREEDING_EXCEPTION_H

#include <exception>
#include <string>

using namespace std;

class BreedingException : exception {
private:
    const int msgID;
    static string msg[];

public:
    BreedingException(int);
    const char* what();
    void bruh();
};

#endif  // _BREEDING_EXCEPTION_H
