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
    tuple<int, int> position;
    char dir;

public:
    Player(string _name, int x, int y);
    Player();

    string getName() const;
    void setName(string _name);

    bool isEngimonActive() const;
    // Engimon getActiveEngimon() const;
    // void switchEngimon(Engimon engi);

    tuple<int, int> getPosition() const;
    int getPositionX() const;
    int getPositionY() const;
    void setPositionX(int x);
    void setPositionY(int y);

    char getDir() const;
    void setDir(char _dir);

};

#endif
