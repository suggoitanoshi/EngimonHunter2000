#ifndef _GAME_HPP_
#define _GAME_HPP_

#include "./Dex.hpp"
#include "./Engimon.hpp"
#include "./Player.hpp"
#include "./Map.hpp"

using namespace std;

// dipisah jd hpp cpp nanti kalo dah fix
class Game {
private:
    static const unsigned mapX = 32;
    static const unsigned mapY = 16;
    static const unsigned wildEngimonCount = 10;

    unsigned wildEngimonLevelBound;
    unsigned wildEngimonMoveSetBound;
    unsigned wildEngimonCaptilizeTileCharLevelBound;
    bool isExitGame;
    Player player;
    Dex dex;
    Map map;
    vector<Engimon> wildEngimons;

    // map & input handling
    void printGameIntro();

    // print command manual
    void printCommandHelp();

    // Move player logi
    void movePlayerDelta(int dx, int dy);
    void engimonFollowPlayer(int x, int y);
    void moveEngimonDelta(int dx, int dy, Engimon&);

    // spawn wild engimon
    /// dapetin list tempat kosong di map
    vector<tuple<int, int>> getEmptyMapTile() const;
    /// bikin engimon random, belom dimasukin ke game
    Engimon makeRandomEngimon() const;
    /// taro n engimon-engimon liar di map, kalo tempat kosong < n maka
    /// hanya sebanyak n engimon yang ditaro lalu tambahin ke vector
    //  wildEngimons
    void spawnWildEngimon(unsigned);
    void moveWildEngimons();

    void battle();

public:
    Game();

    // main
    void run();

    Engimon& kawin(Engimon&, Engimon&);
};

class GameException : exception {
private:
    const int exceptionID;
    static const string msg[];

public:
    GameException(int);
    const char* what();
};

#endif
