#include "../headers/Player.hpp"

Player::Player()
    : name("yee"),
      // engimon pertama akan dikonstruk di sini
      listEngimon(),
      listItem(),
      position(make_tuple(1, 1)),
      dir('a') {}

string Player::getName() const { return name; }
Engimon Player::getActiveEngimon() const { return activeEngi; }

tuple<int, int> Player::getPosition() const { return position; }
int Player::getPositionX() const { return get<0>(position); }
int Player::getPositionY() const { return get<1>(position); }

char Player::getDir() const { return dir; }
Engimon& Player::getEngimonFromName(string engiName) {
    if (listEngimon.getItemCount() == 0) throw InventoryException(1);
    for (int i = 0; i < listEngimon.getItemCount(); i++) {
        if (engiName.compare(listEngimon[i].getName()) == 0) {
            return listEngimon[i];
        }
    }
    throw PlayerException(0);
}

Item& Player::getItemFromName(string _itemName) {
    if (listItem.getItemCount() == 0) throw InventoryException(1);
    for (int i = 0; i < listItem.getItemCount(); i++) {
        if (_itemName.compare(listItem[i].getName()) == 0) {
            return listItem[i];
        }
    }
    throw PlayerException(1);
}

int Player::getEngimonIdxFromName(string engiName) {
    Engimon temp = getEngimonFromName(engiName);
    return listEngimon.getFirstItemIndex(temp);
}

int Player::getItemIdxFromName(string _itemName) {
    Item temp = getItemFromName(_itemName);
    return listItem.getFirstItemIndex(temp);
}

void Player::setName(string _name) { name = _name; }

void Player::setPosition(tuple<int, int> pos) { position = pos; }
void Player::setPositionX(int x) { get<0>(position) = x; }
void Player::setPositionY(int y) { get<1>(position) = y; }

void Player::setDir(char _dir) { dir = _dir; }

void Player::checkActiveEngimon() { showEngimon(activeEngi); }

void Player::switchEngimon(string engiName) {
    int beforeEngi = listEngimon.getFirstItemIndex(activeEngi);
    listEngimon[beforeEngi].setPos(-1, -1);

    tuple<int, int> currPos = activeEngi.getPosition();
    int afterEngi = getEngimonIdxFromName(engiName);
    listEngimon[afterEngi].setPos(get<0>(currPos), get<1>(currPos));

    activeEngi = listEngimon[afterEngi];
}

void Player::showEngimon(Engimon engi) {
    int idx = listEngimon.getFirstItemIndex(engi);
    listEngimon[idx].showEngimon();
}
void Player::showEngimon(string engiName) {
    showEngimon(getEngimonFromName(engiName));
}
void Player::showEngimon() const { 
    cout << "Engimon di dalam Inventory: " << endl;
    listEngimon.showInventory();
}

void Player::addEngimon(Engimon engi) { listEngimon.addItem(engi); }
void Player::addEngimon(string engi) {
    Engimon temp = getEngimonFromName(engi);
    addEngimon(temp);
}

void Player::removeEngimon(Engimon engi) { listEngimon.removeItem(engi); }
void Player::removeEngimon(string engi) {
    Engimon temp = getEngimonFromName(engi);
    removeEngimon(temp);
}

void Player::showItem(Item _item) {
    int idx = listItem.getFirstItemIndex(_item);
    listItem[idx].showItem();
}
void Player::showItem(string _item) {
    Item temp = getItemFromName(_item);
    showItem(temp);
}
void Player::showItem() const { 
    cout << "Skill Item di dalam Inventory: " << endl;
    listItem.showInventory();
}

void Player::useItem(string engiName, string _item, const Dex& dex) {
    int idxItem = getItemIdxFromName(_item);
    listItem[idxItem].learn(getEngimonFromName(engiName), dex);
    if (listItem[idxItem].getQuantity() == 0) {
        removeItem(listItem[idxItem]);
    }
}

void Player::addItem(Item _item) { listItem.addItem(_item); }
void Player::addItem(string _item) {
    Item temp = getItemFromName(_item);
    addItem(temp);
}

void Player::removeItem(Item _item) { listItem.removeItem(_item); }
void Player::removeItem(string _item) {
    Item temp = getItemFromName(_item);
    removeItem(temp);
}

void Player::interact() const { activeEngi.interact(); }

// exception
PlayerException::PlayerException(int x) : msgID(x) {}
const char* PlayerException::what() { return msg[msgID].c_str(); }
void PlayerException::bruh() { cout << what() << endl; }
string PlayerException::msg[] = {
    "Tidak ada Engimon tersebut di dalam Inventory",
    "Tidak ada Item Skill tersebut di dalam Inventory"};
