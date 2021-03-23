#ifndef PLAYER_HPP
#define PLAYER_HPP

#include <string>
#include <tuple>

using namespace std;

class Player {
protected:
    string name;
    bool isEngiActive;  // kyknya masuk game aja
    // Engimon activeEngi; kyknya masuk game juga
    // Inventory<Engimon>[] listEngimon;
    // Inventory<SkillItem>[] listSkill;
    int totalInvent;
    tuple<int, int> position;
    char dir;

public:
    Player(string _name, int x, int y);
    Player();
    bool isEngimonActive() const;
    // Engimon getActiveEngimon() const;
    int getTotalInventory() const;
    // void switchEngimon(Engimon engi);

    tuple<int, int> getPosition() const;
    int getPositionX() const;
    int getPositionY() const;
    void setPositionX(int x);
    void setPositionY(int y);

    const static int MAX_CAPACITY = 50;
};

#endif
