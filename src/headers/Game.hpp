#ifndef _GAME_HPP_
#define _GAME_HPP_

#include <cctype>
#include <fstream>
#include <iostream>
#include <string>
#include <tuple>

#include "./Dex.hpp"
#include "./Engimon.hpp"
#include "./Player.hpp"
#include "./MapTile.hpp"

using namespace std;

// dipisah jd hpp cpp nanti kalo dah fix
class Game {
private:
    static const unsigned wildEngimonCount = 10;
    static const unsigned mapX = 32;
    static const unsigned mapY = 16;
    MapTile map[mapX][mapY];
    //char map[32][16];
    bool isExitGame;
    Player player;
    Dex dex;

    // map & input handling
    void printGameIntro();
    void printMap();
    void readMap();

    // print command manual
    void printCommandHelp();

    // spawn wild engimon
    /// dapetin list tempat kosong di map
    vector<tuple<int, int>> getEmptyMapTile();
    /// bikin engimon random, belom dimasukin ke game
    Engimon makeRandomEngimon() const;
    /// taro n engimon-engimon liar di map, kalo tempat kosong < n maka
    /// hanya sebanyak n engimon yang ditaro
    void spawnWildEngimon(unsigned);

public:
    Game();

    // main
    void run();

    Engimon& kawin(Engimon&, Engimon&);
};

#endif
