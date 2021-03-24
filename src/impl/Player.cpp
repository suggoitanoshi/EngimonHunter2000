#include "../headers/Player.hpp"

Player::Player()
    : name("yee"),
      isEngiActive(false),
      // engimon pertama akan dikonstruk di sini
      // inventory dikonstruk di sini
      position(make_tuple(1, 1)),
      dir('a') {}

string Player::getName() const { return name; }
bool Player::isEngimonActive() const { return isEngiActive; }
Engimon Player::getActiveEngimon() const { return activeEngi; }
tuple<int, int> Player::getPosition() const { return position; }
int Player::getPositionX() const { return get<0>(position); }
int Player::getPositionY() const { return get<1>(position); }
char Player::getDir() const { return dir; }

void Player::setName(string _name) { name = _name; }
void Player::setPosition(tuple<int, int> pos) { position = pos; }
void Player::setPositionX(int x) { get<0>(position) = x; }
void Player::setPositionY(int y) { get<1>(position) = y; }
void Player::setDir(char _dir) { dir = _dir; }
void Player::setActiveEngiPos() {}

void Player::checkActiveEngimon() {
    if (isEngiActive) {
        showEngimon(activeEngi);
    } else {
        throw PlayerException(0);
    }
}

void Player::switchEngimon(Engimon engi) {
    int nowEngi = listEngimon.getFirstItemIndex(activeEngi);
    activeEngi = engi;

    if (isEngiActive) {
        // listEngimon[nowEngi].setPos();
        int switchedEngi = listEngimon.getFirstItemIndex(engi);
        // listEngimon[switchedEngi].setPos(activeEngi.getPosition());
    } else {
        // listEngimon[nowEngi].setPos();
        isEngiActive = true;
    }
}

void Player::showEngimon(Engimon engi) {}
void Player::showEngimon() const { listEngimon.showInventory(); }
void Player::showItem() const { listItem.showInventory(); }

void Player::useItem(Engimon engi, Item _item) {
    int idxItem = listItem.getFirstItemIndex(_item);
    listItem[idxItem].learn(engi);
    listItem.removeItem(_item);
}

void Player::interact() {
    if (isEngiActive) {
    } else {
        throw PlayerException(0);
    }
}

// exception
PlayerException::PlayerException(int x) : msgID(x) {}
const char* PlayerException::what() { return msg[msgID].c_str(); }
void PlayerException::bruh() { std::cout << what() << std::endl; }
string PlayerException::msg[] = {"Tidak ada Engimon yang sedang aktif"};
