#ifndef _BATTLE_HPP_
#define _BATTLE_HPP_

#include <iostream>
#include <string>
#include "./Engimon.hpp"
#include "./Elements.hpp"

using namespace std;

class Battle{
private:
    string winner;
public:
    Battle();
    //Setter
    void setEngimonA(Engimon other);
    void setEngimonB(Engimon other);
    void setEngimonWinner();
    //battle
    double getAdvantage(Elements A, Elements B);
    void checkAdvantage();
    void runBattle();
};

#endif