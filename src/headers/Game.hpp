#ifndef _GAME_HPP_
#define _GAME_HPP_

#include <cctype>
#include <fstream>
#include <iostream>
#include <string>
#include <tuple>
#include <list>

#include "./Player.hpp"
#include "./Engimon.hpp"

using namespace std;

// dipisah jd hpp cpp nanti kalo dah fix
class Game {
private:
    char map[32][16];
    bool isExitGame;
    string playerName;
    Player player;
    // list<Engimon> wildEngimons;

public:
    //map & input handling
    Game();
    void printGameIntro();
    void readMap();
    void printMap();

    //battle
    // double getAdvantage(Elements A, Elements B);
    // double checkAdvantage(Engimon A, Engimon B);
    // void battle(Engimon A, Engimon B);

    //spawn wild engimon
    // void spawnWildEngimon();

    //getter
    bool getIsExit();
    Player getPlayer();
    // list<Engimon> getWildEngimon();

    //setter
    void setIsExit(bool x);
    // void addWildEngimon(Engimon X);

    //main
    void run(); 

};

#endif