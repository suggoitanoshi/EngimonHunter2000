#ifndef PLAYER_HPP
#define PLAYER_HPP

#include <string>

using namespace std;

class Player {
protected:
    string name;
    bool isEngiActive;  // kyknya masuk game aja
    // Engimon activeEngi; kyknya masuk game juga
    // Inventory<Engimon>[] listEngimon;
    // Inventory<SkillItem>[] listSkill;
    int totalInvent;

public:
    Player(string _name);
    bool isEngimonActive() const;
    // Engimon getActiveEngimon() const;
    int getTotalInventory() const;
    // void switchEngimon(Engimon engi);

    const static int MAX_CAPACITY = 50;
};

#endif
