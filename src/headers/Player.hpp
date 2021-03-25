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
    Engimon& getEngimonFromName(string);
    Item& getItemFromName(string);
    int getEngimonIdxFromName(string);
    int getItemIdxFromName(string);

    // setters
    void setName(string);
    void setPosition(tuple<int, int>);
    void setPositionX(int);
    void setPositionY(int);
    void setDir(char);

    // methods
    void checkActiveEngimon();
    void switchEngimon(string);
    void showEngimon(Engimon);
    void showEngimon(string);
    void showEngimon() const;
    void removeEngimon(Engimon);
    void removeEngimon(string);
    void showItem() const;
    void useItem(string, string, const Dex&);
    void removeItem(Item);
    void removeItem(string);
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
