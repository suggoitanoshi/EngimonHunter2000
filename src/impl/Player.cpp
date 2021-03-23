#include "../headers/Player.hpp"

Player::Player(string _name, int x, int y)
    : name(_name), isEngiActive(false), dir('a') {
    position = make_tuple(x, y);
}

Player::Player() : Player("josep", 1, 1) {}

string Player::getName() const { return name; }

void Player::setName(string _name) { name = _name; }

bool Player::isEngimonActive() const { return isEngiActive; }

// Engimon Player::getActiveEngimon() const {
//     return activeEngi;
// }

// void Player::switchEngimon(Engimon engi) {
//     if (isEngiActive) {

//     }
//     else {

//     }
// }

tuple<int, int> Player::getPosition() const { return position; }

int Player::getPositionX() const { return get<0>(position); }
int Player::getPositionY() const { return get<1>(position); }
void Player::setPositionX(int x) { get<0>(position) = x; }
void Player::setPositionY(int y) { get<1>(position) = y; }

char Player::getDir() const { return dir; }

void Player::setDir(char _dir) { dir = _dir; }