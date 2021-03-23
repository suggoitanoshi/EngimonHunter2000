#include "../headers/Player.hpp"

Player::Player(string _name)
    : name(_name), isEngiActive(false), totalInvent(0) {}

bool Player::isEngimonActive() const { return isEngiActive; }

// Engimon Player::getActiveEngimon() const {
//     return activeEngi;
// }

int Player::getTotalInventory() const { return totalInvent; }

// void Player::switchEngimon(Engimon engi) {
//     if (isEngiActive) {

//     }
//     else {

//     }
// }
