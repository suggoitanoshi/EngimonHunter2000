#ifndef _BATTLE_HPP_
#define _BATTLE_HPP_

#include <iostream>
#include <string>

#include "./Elements.hpp"
#include "./Engimon.hpp"

using namespace std;

class Battle {
private:
    string winner;

public:
    Battle();
    // Setter
    void setEngimonA(Engimon other);
    void setEngimonB(Engimon other);
    void setEngimonWinner();
    // battle
    double getAdvantage(Elements::el A, Elements::el B);
    void checkAdvantage();
    void runBattle();
};

#endif
