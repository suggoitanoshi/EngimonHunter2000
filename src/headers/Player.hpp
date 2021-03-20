#ifndef PLAYER_HPP
#define PLAYER_HPP

#include <iostream>
#define MAX_CAPACITY 50;

using namespace std;

class Player {
   protected:
    string name;
    bool isEngiActive;  // kyknya masuk game aja
    // Engimon activeEngi; kyknya masuk game juga
    // Inventory<Engimon>[] listEngimon;
    // Inventory<SkillItem>[] listSkill;
    int totalInvent;
    int maxCapacity;

   public:
    Player(string _name);
    bool isEngimonActive() const;
    // Engimon getActiveEngimon() const;
    int getTotalInventory() const;
    // void switchEngimon(Engimon engi);
};

#endif
