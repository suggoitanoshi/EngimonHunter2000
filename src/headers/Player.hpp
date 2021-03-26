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
    Inventory<Engimon> listEngimon;
    Inventory<Item> listItem;
    int activeEngi;
    tuple<int, int> position;
    char dir;

public:
    // constructors
    Player();

    // getters
    string getName() const;
    Engimon& getActiveEngimon();
    tuple<int, int> getPosition() const;
    int getPositionX() const;
    int getPositionY() const;
    char getDir() const;
    Engimon& getEngimonById(int);
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
    void switchEngimon(int);
    void showEngimon(int);
    void showEngimon();
    void addEngimon(Engimon);
    void addEngimon(string);
    void removeEngimon(Engimon&);
    void removeEngimon(string);
    void showItem(string);
    void showItem(int);
    void showItem() const;
    void useItem(int, int, const Dex&);
    void addItem(Item);
    void addItem(string);
    void removeItem(Item);
    void removeItem(string);
    void interact();
    void engimonIsEmpty();
    void itemIsEmpty();
    void inventoryIsFull();
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
