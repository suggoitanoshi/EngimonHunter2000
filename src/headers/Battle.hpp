#ifndef _BATTLE_HPP_
#define _BATTLE_HPP_

#include <iostream>
#include <string>

#include "./Elements.hpp"
#include "./Engimon.hpp"

using namespace std;

class Battle {
private:
    double advA;
    double advB;
    double pwrA;
    double pwrB;

public:
    Battle();
    // battle
    void checkAdvantage(Engimon A, Engimon B);
    void runBattle(Engimon A, Engimon B);
};

#endif
