#ifndef _BREEDING_EXCEPTION_H
#define _BREEDING_EXCEPTION_H

#include<exception>
#include<string>

class BreedingException: std::exception{
  private:
    const int msgID;
    static std::string msg[];
  public:
    BreedingException(int);
    const char* what();
    void bruh();
};

#endif // _BREEDING_EXCEPTION_H