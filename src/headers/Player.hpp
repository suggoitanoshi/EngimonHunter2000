#ifndef PLAYER_HPP
#define PLAYER_HPP
#include <string>
#include <tuple>

#include "../impl/Inventory.cpp"
#include "Engimon.hpp"
#include "Item.hpp"

using namespace std;

class Player {
protected:
    string name;
    Engimon activeEngi;
    Inventory<Engimon> listEngimon;
    Inventory<Item> listItem;
    tuple<int, int> position;
    char dir;

public:
    // constructors
    Player();

    // getters
    string getName() const;
    Engimon getActiveEngimon() const;
    tuple<int, int> getPosition() const;
    int getPositionX() const;
    int getPositionY() const;
    char getDir() const;
    Engimon getEngimonFromString(string);
    Item getItemFromString(string);
    int getEngimonIdxFromString(string);
    int getItemIdxFromString(string);

    // setters
    void setName(string);
    void setPosition(tuple<int, int>);
    void setPositionX(int);
    void setPositionY(int);
    void setDir(char);
    void setActiveEngiPos();

    // methods
    void checkActiveEngimon();
    void switchEngimon(string);
    void showEngimon(Engimon);
    void showEngimon(string);
    void showEngimon() const;
    void showItem() const;
    void useItem(string, string);
    void interact();
};

class PlayerException : exception {
private:
    const int msgID;
    static string msg[];

public:
    PlayerException(int);
    const char* what();
    void bruh();
};

#endif
