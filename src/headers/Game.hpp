#ifndef _GAME_HPP_
#define _GAME_HPP_

#include <cctype>
#include <fstream>
#include <iostream>
#include <list>
#include <string>
#include <tuple>

#include "./Engimon.hpp"
#include "./Player.hpp"
#include "./Dex.hpp"

using namespace std;

// dipisah jd hpp cpp nanti kalo dah fix
class Game {
private:
    char map[32][16];
    bool isExitGame;
    Player player;
    Dex dex;
    // list<Engimon> wildEngimons;

    // map & input handling
    void printGameIntro();
    void printMap();
    void readMap();

    // battle
    // double getAdvantage(Elements A, Elements B);
    // double checkAdvantage(Engimon A, Engimon B);
    // void battle(Engimon A, Engimon B);

    // spawn wild engimon
    // void spawnWildEngimon();

public:
    Game();

    // main
    void run();

    Engimon& kawin(Engimon&, Engimon&);
};

#endif
