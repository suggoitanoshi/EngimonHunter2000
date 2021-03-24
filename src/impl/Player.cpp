#include "../headers/Player.hpp"

Player::Player()
    : name("yee"),
      // engimon pertama akan dikonstruk di sini
      // inventory dikonstruk di sini
      position(make_tuple(1, 1)),
      dir('a') {}

string Player::getName() const { return name; }
Engimon Player::getActiveEngimon() const { return activeEngi; }
tuple<int, int> Player::getPosition() const { return position; }
int Player::getPositionX() const { return get<0>(position); }
int Player::getPositionY() const { return get<1>(position); }
char Player::getDir() const { return dir; }
Engimon Player::getEngimonFromString(string engi) {
    if (listEngimon.getItemCount() == 0) throw InventoryException(1);
    for (int i = 0; i < listEngimon.getItemCount(); i++) {
        if (engi.compare(listEngimon[i].getName()) == 0) {
            return listEngimon[i];
        }
    }
    throw PlayerException(0);
}

Item Player::getItemFromString(string _item) {
    // if (listItem.getItemCount() == 0) throw InventoryException(1);
    // for (int i = 0; i < listItem.getItemCount(); i++) {
    //     if (_item.compare(listItem[i].getName()) == 0) {
    //         return listItem[i];
    //     }
    // }
    // throw PlayerException(1);
}

int Player::getEngimonIdxFromString(string engi) {
    Engimon temp = getEngimonFromString(engi);
    return listEngimon.getFirstItemIndex(temp);
}

int Player::getItemIdxFromString(string _item) {
    Item temp = getItemFromString(_item);
    return listItem.getFirstItemIndex(temp);
}

void Player::setName(string _name) { name = _name; }
void Player::setPosition(tuple<int, int> pos) { position = pos; }
void Player::setPositionX(int x) { get<0>(position) = x; }
void Player::setPositionY(int y) { get<1>(position) = y; }
void Player::setDir(char _dir) { dir = _dir; }

void Player::checkActiveEngimon() { showEngimon(activeEngi); }

void Player::switchEngimon(string engi) {
    // int beforeEngi = listEngimon.getFirstItemIndex(activeEngi);
    // int afterEngi = getEngimonIdxFromString(engi);
    // listEngimon[beforeEngi].setPos(); Jadiin (-1, -1)
    // listEngimon[afterEngi].setPos(activeEngi.getPosition());
    // activeEngi = engi;
}

void Player::showEngimon(Engimon engi) {}
void Player::showEngimon(string engi) {
    showEngimon(getEngimonFromString(engi));
}
void Player::showEngimon() const { listEngimon.showInventory(); }
void Player::showItem() const { listItem.showInventory(); }

void Player::useItem(string engi, string _item) {
    int idxItem = getItemIdxFromString(_item);
    listItem[idxItem].learn(getEngimonFromString(engi));
    listItem.removeItem(getItemFromString(_item));
}

void Player::interact() {}

// exception
PlayerException::PlayerException(int x) : msgID(x) {}
const char* PlayerException::what() { return msg[msgID].c_str(); }
void PlayerException::bruh() { std::cout << what() << std::endl; }
string PlayerException::msg[] = {
    "Tidak ada Engimon tersebut di dalam Inventory",
    "Tidak ada Item Skill tersebut di dalam Inventory"};
