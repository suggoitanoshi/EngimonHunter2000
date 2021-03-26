#include "../headers/Game.hpp"

#include <algorithm>
#include <cstdlib>
#include <fstream>
#include <iostream>
#include <tuple>
#include <utility>

#ifdef _WIN32
#include <Windows.h>
#else
#include <unistd.h>
#endif

#include "../headers/Battle.hpp"
#include "../headers/BreedingException.hpp"
#include "../headers/Dex.hpp"
#include "../headers/Player.hpp"

using namespace std;

Game::Game() {
    srand(time(0));

    unsigned pX = rand() % mapX, pY = rand() % mapY;
    pX = pX == 0 ? 1 : pX >= mapX - 1 ? mapX - 2 : pX;
    pY = pY == 0 ? 1 : pY >= mapY - 1 ? mapY + 2 : pY;
    int eX = pX == 1 ? 2 : pX - 1;
    int eY = pY;

    this->isExitGame = false;
    player.setPosition(make_tuple(pX, pY));
    player.getActiveEngimon().setPos(eX, eY);
    map.setTile(pX, pY, MapTile::PLAYER);       // buat player
    map.setTile(eX, eY, MapTile::ACTIVE_ENGI);  // buat active engimon

    spawnWildEngimon(wildEngimonCount);

    player.addItem(Item(dex.getSkill("Tackle"), 10));
}

void Game::printGameIntro() {
    std::string name;
    cout << "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░" << endl;
    cout << "░░░░░░░░░░░░░█████████░░░░░░░░░░░░░░░░░░" << endl;
    cout << "░░░░░░░░░░███████████████░░░░░░░░░░░░░░░" << endl;
    cout << "░░░░░░░░░██████▓▓▓▓▓▓▓▓████░░░░░░░░░░░░░" << endl;
    cout << "░░░░░░░░██░▒▒███▒▒▒▒▒▓▓▓███░░░░░░░░░░░░░" << endl;
    cout << "░░░░░░░░█░░░▒▒▒████░░▒▒▒▓███░░░░░░░░░░░░" << endl;
    cout << "░░░░░░░██░░░▒▒██░▄░██░▒▒▓███░░░░░░░░░░░░" << endl;
    cout << "░░░░░░░██░░░░▒██░▀░██▓▓▓▓███░░░░░░░░░░░░" << endl;
    cout << "░░░░░░░░██░░░▒▒███████▓▓████░░░░░░░░░░░░" << endl;
    cout << "░░░░░░░░░██░░░▒▒▒▒▒▒███████░░░░░░░░░░░░░" << endl;
    cout << "░░░░░░░░░░████░░░░░▒▒█████░░░░░░░░░░░░░░" << endl;
    cout << "░░░░░░░░░░░░░██████████░░░░░░░░░░░░░░░░░" << endl;
    cout << "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░" << endl;
    cout << "Selamat datang di Engimon Factory ^_^" << endl;
    cout << "           Siapa nama mu?" << endl << endl;
    cout << "Masukkan nama mu: ";
    cin >> name;
    player.setName(name);
    cout << "selamat bermain di Engimon Factory " << player.getName() << " !"
         << endl;
    cout << "============================================================\n\n"
         << endl;
}

void Game::printCommandHelp() {
    cout
        << "--------------------------C O M M A N D----------------------------"
        << endl;
    cout << "     W:atas  A:kiri  S:bawah  D:kanan  X:Keluar game" << endl;
    cout
        << "-------------------------------------------------------------------"
        << endl;
    cout << "1. Lihat list engimon pemain | 2. Lihat seluruh engimon" << endl;
    cout << "3. Ganti active engimon      | 4. Lihat list skill item pemain"
         << endl;
    cout << "5. Gunakan skill item        | 6. Lakukan breeding" << endl;
    cout
        << "7. Lakukan Battle            | 8. Lihat data lengkap Engimon pemain"
        << endl;
    cout
        << "9. Interaksi dengan Engimon"
        << endl;
    cout
        << "-------------------------------------------------------------------"
        << endl;
}

void Game::engimonFollowPlayer(int x, int y) {
    int oldx, oldy;
    oldx = get<0>(player.getActiveEngimon().getPosition());
    oldy = get<1>(player.getActiveEngimon().getPosition());
    // x dan y baru pasti kosong karena tempat lama player
    player.getActiveEngimon().setPos(x, y);
    if (map.getTile(oldx, oldy).getTileChar() !=
        'P') {  // biar bisa "tukeran" posisi sama player
        map.setTileToOriginal(oldx, oldy);
    }
    map.setTile(x, y, MapTile::ACTIVE_ENGI);
}

void Game::movePlayerDelta(int dx, int dy) {
    int newx, newy, oldx, oldy;
    oldx = this->player.getPositionX();
    oldy = this->player.getPositionY();
    newx = oldx + dx;
    newy = oldy + dy;
    if (!map.isFree(newx, newy)) {
        throw GameException(0);
    }
    this->player.setPositionX(newx);
    this->player.setPositionY(newy);
    // ganti tipe tile
    map.setTileToOriginal(oldx, oldy);
    map.setTile(newx, newy, MapTile::PLAYER);
    engimonFollowPlayer(oldx, oldy);
}

void Game::moveEngimonDelta(int dx, int dy, Engimon& engie) {
    int newx, newy, oldx, oldy;
    oldx = get<0>(engie.getPosition());
    oldy = get<1>(engie.getPosition());
    newx = oldx + dx;
    newy = oldy + dy;
    if (!map.isFree(newx, newy)) {
        throw GameException(0);
    }
    MapTile::TileType curType = map.getTile(oldx, oldy).getType();
    MapTile::TileType nextType = map.getTile(newx, newy).getType();

    bool canMove;
    switch (curType) {
        case MapTile::OCCUPIED_E:
        case MapTile::OCCUPIED_F:
        case MapTile::OCCUPIED_G:
        case MapTile::OCCUPIED_L:
            // do smth
            canMove = nextType == MapTile::GRASSLAND;
            break;
        case MapTile::OCCUPIED_W:
        case MapTile::OCCUPIED_I:
        case MapTile::OCCUPIED_S:
            canMove = nextType == MapTile::WATER;
            break;
        default:  // ground/water
            canMove = curType == MapTile::OCCUPIED_N;
    }

    if (canMove) {
        engie.setPos(newx, newy);
        // ganti tipe tile
        map.setTileToOriginal(oldx, oldy);
        map.setTile(newx, newy, curType);
    } else {
        throw GameException(0);
    }
}

Engimon Game::makeRandomEngimon() const {
    int idx = rand() % dex.getEngiDex().size();
    bool compat = false;
    EngimonSpecies engieSpecies;

    // dapetin spesies engimonnya
    for (pair<string, EngimonSpecies> a : dex.getEngiDex()) {
        if (idx == 0) engieSpecies = a.second;
        idx--;
    }

    Engimon engie(engieSpecies);
    engie.addExp((rand() % wildEngimonLevelBound) *
                 100);  // karena tiap level butuh 100 exp dan sementara dibikin
                        // random 1 sampe 100

    unordered_map<string, Skill> filtered;
    for (pair<string, Skill> a : dex.getSkillDex()) {
        // cek elemen
        for (Elements::el engieEl : engie.getElements()) {
            for (Elements::el skillEl : a.second.getElements()) {
                compat = engieEl == skillEl;
                if (compat) {
                    filtered.emplace(a.first, a.second);
                    break;
                }
            }
            if (compat) break;
        }
    }

    // dapetin n move acak
    for (size_t i = 0;
         i < min(filtered.size(), (size_t)wildEngimonMoveSetBound); i++) {
        idx = rand() % filtered.size();
        for (pair<string, Skill> a : filtered) {
            if (idx == 0) {
                // belom cek tipe, do it later
                engie.setSkills(i, a.second);
                break;
            }
            idx--;
        }
    }

    return engie;
}

vector<tuple<int, int>> Game::getEmptyMapTile() const {
    vector<tuple<int, int>> ret;
    for (int y = 0; y < 16; ++y) {
        for (int x = 0; x < 32; ++x) {
            if (map.isFree(x, y)) {
                ret.push_back(make_tuple(x, y));
            }
        }
    }

    return ret;
}

void Game::spawnWildEngimon(unsigned count) {
    wildEngimonLevelBound = (unsigned)player.getActiveEngimon().getLvl() + 5;
    wildEngimonCaptilizeTileCharLevelBound =
        (unsigned)player.getActiveEngimon().getLvl();
    wildEngimonMoveSetBound = (unsigned)player.getActiveEngimon().getLvl() / 25;

    unordered_map<string, EngimonSpecies> engies = dex.getEngiDex();
    // dapetin tile kosong
    vector<tuple<int, int>> freeSpaces = getEmptyMapTile();

    count =
        (count < getEmptyMapTile().size()) ? count : getEmptyMapTile().size();

    // bikin `count` jumlah engimon lalu taruh di map
    for (size_t i = 0; i < count; ++i) {
        // bikin engimon random
        Engimon engie = makeRandomEngimon();

        // taruh engimon di map
        bool isPlaced = false;
        for (unsigned j = 0; !isPlaced && j < (mapX * mapY); ++j) {
            unsigned randIdx = rand() % freeSpaces.size();
            tuple<int, int> pos = freeSpaces[randIdx];
            vector<Elements::el> engieEl = engie.getElements();
            char engieChar = 0;
            bool isCapitilized = (unsigned)engie.getLvl() >=
                                 wildEngimonCaptilizeTileCharLevelBound;

            // cari tipenya
            if (engieEl.size() == 2) {
                if (((engieEl[0] == Elements::ICE &&
                      engieEl[1] == Elements::WATER) ||
                     (engieEl[0] == Elements::WATER &&
                      engieEl[1] == Elements::ICE))) {
                    engieChar = 'S' * isCapitilized + 's' * !isCapitilized;
                } else if (((engieEl[0] == Elements::WATER &&
                             engieEl[1] == Elements::GROUND) ||
                            (engieEl[0] == Elements::GROUND &&
                             engieEl[1] == Elements::WATER))) {
                    engieChar = 'N' * isCapitilized + 'n' * !isCapitilized;
                } else {
                    engieChar = 'L' * isCapitilized + 'l' * !isCapitilized;
                }
            } else {  // ukurannya 1
                if (engieEl[0] == Elements::FIRE) {
                    engieChar = 'F' * isCapitilized + 'f' * !isCapitilized;
                } else if (engieEl[0] == Elements::ICE) {
                    engieChar = 'I' * isCapitilized + 'i' * !isCapitilized;
                } else if (engieEl[0] == Elements::WATER) {
                    engieChar = 'W' * isCapitilized + 'w' * !isCapitilized;
                } else if (engieEl[0] == Elements::GROUND) {
                    engieChar = 'G' * isCapitilized + 'g' * !isCapitilized;
                } else {
                    engieChar = 'E' * isCapitilized + 'e' * !isCapitilized;
                }
            }

            // cek tipe tyle lalu ganti tipenya kalau cocok
            if (map.getTile(get<0>(pos), get<1>(pos)).getType() ==
                MapTile::WATER) {
                if (!(engieChar == 'F' || engieChar == 'G' ||
                      engieChar == 'E' || engieChar == 'L')) {
                    map.setTile(get<0>(pos), get<1>(pos), engieChar);
                    engie.setPos(get<0>(pos), get<1>(pos));
                    freeSpaces.erase(freeSpaces.begin() + randIdx);
                    isPlaced = true;
                }
            } else {  // char-nya: '-'
                if (engieChar == 'F' || engieChar == 'G' || engieChar == 'E' ||
                    engieChar == 'L') {
                    map.setTile(get<0>(pos), get<1>(pos), engieChar);
                    engie.setPos(get<0>(pos), get<1>(pos));
                    freeSpaces.erase(freeSpaces.begin() + randIdx);
                    isPlaced = true;
                }
            }
        }

        if (!isPlaced) {
            // error
            --i;  // ga diitung langkah ini
        } else {
            wildEngimons.push_back(engie);
        }
    }
}

void Game::moveWildEngimons() {
    int possibleD[] = {-1, 0, 1};
    for (Engimon& engie : wildEngimons) {
        int randIdx = rand() % 3;
        int d = possibleD[randIdx];
        int isEven = rand() % 2 == 0;

        if (!d)
            continue;
        else
            moveEngimonDelta(d * isEven, d * !isEven, engie);
    }
}

void Game::battle() {
    vector<Engimon> surroundingEngi;
    int dx, dy;
    Engimon& engi = wildEngimons[0];
    size_t i;
    vector<Engimon>::iterator it;

    for (Engimon& e : wildEngimons) {
        dx = get<0>(e.getPosition()) - player.getPositionX();
        dx = abs(dx);
        dy = get<1>(e.getPosition()) - player.getPositionY();
        dy = abs(dy);
        if ((dx == 1 && dy == 0) || (dy == 1 && dx == 0)) {
            surroundingEngi.push_back(e);
        }
    }

    if (surroundingEngi.size() == 0)
        throw GameException(1);
    else if (surroundingEngi.size() == 1)
        engi = surroundingEngi[0];
    else {  // surroundingEngi.size() > 1
        cout << "Pilih engimon: " << endl;
        for (i = 0; i < surroundingEngi.size(); i++) {
            Engimon& e = surroundingEngi[i];
            cout << i + 1 << ". ";
            cout << e.getName() << " (" << e.getLvl() << ")" << endl;
        }
        cin >> i;
        if (i > surroundingEngi.size()) throw GameException(2);
        i--;
        engi = surroundingEngi[i];
    }

    Battle b;
    bool succ = b.runBattle(this->player.getActiveEngimon(), engi);
    if (succ) {
        this->player.addEngimon(Engimon(engi));
        for (it = wildEngimons.begin(); !(*it == engi); ++it)
            ;
        map.setTileToOriginal(get<0>(it->getPosition()),
                              get<1>(it->getPosition()));
        player.getActiveEngimon().addExp(it->getLvl() * 4);
        wildEngimons.erase(it);
    } else {
        this->player.removeEngimon(this->player.getActiveEngimon());
        try {
            this->player.switchEngimon(0);
        } catch (InventoryException& e) {
            cout << "Game Over~" << endl;
            this->isExitGame = 1;
        }
    }
}

void Game::run() {
    printGameIntro();
    int countTurn = 0;

    do {
        // spawnWildEngimon(wildEngimonCount - wildEngimons.size());
        if (countTurn % 3 == 0) {
            try {
                moveWildEngimons();
            } catch (GameException&) {
                // do nothing
            }
        }

        string input;
        int input2;
        map.printMap();
        printCommandHelp();
        cout << "Masukkan input: ";
        cin >> input;
        if (input.length() != 1) {
            cout << "Masukan salah, silakan ulangi masukan" << endl;
        } else {
            try {
                switch (tolower(input[0])) {
                    case 'W':
                    case 'w':
                        this->player.setDir('w');
                        movePlayerDelta(0, -1);
                        break;
                    case 'A':
                    case 'a':
                        this->player.setDir('a');
                        movePlayerDelta(-1, 0);
                        break;
                    case 'S':
                    case 's':
                        this->player.setDir('s');
                        movePlayerDelta(0, 1);
                        break;
                    case 'D':
                    case 'd':
                        this->player.setDir('d');
                        movePlayerDelta(1, 0);
                        break;
                    case 'X':
                    case 'x':
                        this->isExitGame = true;
                        break;
                    case '1':
                        // List engimon dimiliki
                        try {
                            this->player.showEngimon();
                            break;
                        } catch (InventoryException& e) {
                            cout << e.what() << endl;
                        }
                    case '2':
                        // List engimon dex
                        dex.showEngimons();
                        break;
                    case '3':
                        // Ganti active engi
                        this->player.showEngimon();
                        cout << "Pilih engimon: ";
                        cin >> input;
                        try {
                            this->player.switchEngimon(atoi(input.c_str()) - 1);
                        } catch (InventoryException& e) {
                            cout << e.what() << endl;
                        }
                        break;
                    case '4':
                        // Lihat skill item dimiliki
                        try {
                            this->player.itemIsEmpty();
                            this->player.showItem();
                        } catch (InventoryException& e) {
                            cout << e.what() << endl;
                        }
                        break;
                    case '5':
                        try {
                            this->player.engimonIsEmpty();
                            this->player.showEngimon();
                            cout << "Pilih engimon: ";
                            cin >> input;
                        } catch (InventoryException& e) {
                            cout << e.what() << endl;
                        }
                        // Pakai skill item
                        try {
                            this->player.itemIsEmpty();
                            try {
                                this->player.showItem();
                                cout << "Pilih item skill: ";
                                cin >> input2;
                                this->player.useItem(atoi(input.c_str()) - 1, input2 - 1, dex);
                            } catch (ItemException& e) {
                                cout << e.what() << endl;
                            }
                        } catch (InventoryException& e) {
                            cout << e.what() << endl;
                        }
                        break;
                    case '6': {
                        // Breeding
                        Engimon A, B, HasilKawin;
                        this->player.showEngimon();
                        try {
                            cout << "Pilih engimon 1: ";
                            cin >> input2;
                            A = player.getEngimonById(input2-1);
                            cout << "Pilih engimon 2: ";
                            cin >> input2;
                            B = player.getEngimonById(input2-1);
                            HasilKawin = kawin(A, B);
                            this->player.addEngimon(HasilKawin);
                        } catch (InventoryException& e) {
                            cout << e.what() << endl;
                        } catch (BreedingException& e) {
                            cout << e.what() << endl;
                        }
                        break;
                    }
                    case '7':
                        // Battle
                        battle();
                        break;
                    case '8':
                        // Lihat data lengkap Engimon
                        try {
                            this->player.engimonIsEmpty();
                            this->player.showEngimon();
                            cout << "Pilih engimon: ";
                            cin >> input;
                            this->player.showEngimon(atoi(input.c_str()) - 1);
                        } catch (InventoryException& e) {
                            cout << e.what() << endl;
                        }
                        break;
                    case '9':
                        // Interaksi
                        this->player.interact();
                        break;
                    default:
                        cout << "Masukan salah, ulangi masukan" << endl;
                        break;
                }
                countTurn++;
            } catch (GameException& e) {
                cout << e.what() << endl;
            }
        }
        cout << "==================================================================="
                "\n"
             << endl;
    } while (this->isExitGame == false);

    cout << "Terima kasih sudah bermain di Engimon Factory!" << endl << endl;
    cout << "                 C R E D I T S                " << endl;
    cout << "==============================================" << endl;
    cout << "|              y e e wangy wangy             |" << endl;
    cout << "==============================================" << endl;
    cout << "|       13519116 Jeane Mikha Erwansyah       |" << endl;
    cout << "|          13519118 Cynthia Rusadi           |" << endl;
    cout << "|       13519124 Fransiskus Febryan S.       |" << endl;
    cout << "|            13519131 Hera Shafira           |" << endl;
    cout << "|             13519163 Alvin Wilta           |" << endl;
    cout << "|           13519164 Josep Marcello          |" << endl;
    cout << "==============================================" << endl;
}

Engimon& Game::kawin(Engimon& bapak, Engimon& emak) {
    std::string name;
    EngimonSpecies spesies;
    vector<Skill> skillYangDiambil;
    Skill skillSekarang;
    int indexSkillAnak, indexSkillOrangtua, skillBapakTerambil,
        skillEmakTerambil, pembanding;
    bool bapakTerambil, emakTerambil, pernahTerambil;
    // uji level orangtua
    if ((unsigned)bapak.getLvl() < 30 + bapak.defaultLevel ||
        (unsigned)emak.getLvl() < 30 + emak.defaultLevel) {
        throw BreedingException(0);
    }

    sleep(3);

    indexSkillAnak = 0;
    skillBapakTerambil = 0;
    skillEmakTerambil = 0;
    while (indexSkillAnak < 4 &&
           (skillEmakTerambil < (int)bapak.getSkills().size() ||
            skillEmakTerambil < (int)emak.getSkills().size())) {
        skillSekarang = bapak.getSkills()[0];
        bapakTerambil = 1;
        emakTerambil = 0;
        for (indexSkillOrangtua = 1;
             indexSkillOrangtua < (int)bapak.getSkills().size();
             indexSkillOrangtua++) {
            skillSekarang = bapak.getSkills()[indexSkillOrangtua];
            pembanding = 0;
            pernahTerambil = 0;
            while (pembanding < indexSkillAnak && !pernahTerambil) {
                pernahTerambil = skillSekarang.getName() ==
                                 skillYangDiambil[pembanding].getName();
            }
            if (!pernahTerambil &&
                skillYangDiambil[indexSkillAnak].getMasteryLevel() <
                    skillSekarang.getMasteryLevel()) {
                skillYangDiambil[indexSkillAnak] = skillSekarang;
            }
        }
        for (indexSkillOrangtua = 0;
             indexSkillOrangtua < (int)emak.getSkills().size();
             indexSkillOrangtua++) {
            skillSekarang = emak.getSkills()[indexSkillOrangtua];
            pembanding = 0;
            pernahTerambil = 0;
            while (pembanding < indexSkillAnak && !pernahTerambil) {
                pernahTerambil = skillSekarang.getName() ==
                                 skillYangDiambil[pembanding].getName();
            }
            if (!pernahTerambil) {
                if (skillYangDiambil[indexSkillAnak].getName() ==
                    skillSekarang.getName()) {
                    emakTerambil = 1;
                    pembanding =
                        skillYangDiambil[indexSkillAnak].getMasteryLevel() -
                        skillSekarang.getMasteryLevel();
                    if (pembanding == 0)
                        skillYangDiambil[indexSkillAnak].setMasteryLevel(
                            skillYangDiambil[indexSkillAnak].getMasteryLevel() +
                            1);
                    else if (pembanding < 0)
                        skillYangDiambil[indexSkillAnak].setMasteryLevel(
                            skillSekarang.getMasteryLevel());
                } else if (skillYangDiambil[indexSkillAnak].getMasteryLevel() <
                           skillSekarang.getMasteryLevel()) {
                    skillYangDiambil[indexSkillAnak] = skillSekarang;
                    bapakTerambil = 0;
                    emakTerambil = 1;
                }
            }
        }
        if (bapakTerambil) skillBapakTerambil++;
        if (emakTerambil) skillEmakTerambil++;
    }
    // kurangi level orangtua
    bapak.setLevel(bapak.getLvl() - 30);
    emak.setLevel(emak.getLvl() - 30);
    // kasus sama
    Elements::el elementBapak, elementEmak;
    if (bapak.getElements().size() == 2)
        elementBapak = bapak.getElements().at(rand() % 2);
    else
        elementBapak = bapak.getElements().at(0);
    if (emak.getElements().size() == 2)
        elementEmak = emak.getElements().at(rand() % 2);
    else
        elementEmak = emak.getElements().at(0);
    if (elementBapak == elementEmak) {
        // spesies sama dengan bapak ato emak
        if (rand() % 2 == 0)
            spesies = dex.getEngi(bapak.getSpecies());
        else
            spesies = dex.getEngi(emak.getSpecies());
    } else {
        // Hitung Element Advantage

        double advBapak =
            Elements::getElementalAdvantage(elementBapak, elementEmak);
        double advEmak =
            Elements::getElementalAdvantage(elementEmak, elementBapak);
        if (advBapak > advEmak) {
            spesies = dex.getEngi(bapak.getSpecies());
        } else if (advEmak > advBapak) {
            spesies = dex.getEngi(emak.getSpecies());
        } else {
            for (pair<string, EngimonSpecies> esMap : dex.getEngiDex()) {
                vector<Elements::el> el = esMap.second.getElements();
                if (std::find(el.begin(), el.end(), elementBapak) != el.end()) {
                    if (std::find(el.begin(), el.end(), elementEmak) !=
                        el.end()) {
                        spesies = esMap.second;
                        break;
                    }
                }
            }
        }
    }
    cout << "Masukkan nama engimon: " << endl;
    cin >> name;
    tuple<string, string> parents[2] = {
        make_tuple(bapak.getName(), bapak.getSpecies()),
        make_tuple(emak.getName(), emak.getSpecies())};
    Engimon* anjay = new Engimon(spesies, name, parents, skillYangDiambil);
    return *anjay;
}

const string GameException::msg[] = {"Pergerakan tidak valid",
                                     "Tidak ada engimon di sekitar",
                                     "Pilihan tidak valid"};

GameException::GameException(int id) : exceptionID(id) {}
const char* GameException::what() { return msg[this->exceptionID].c_str(); }
