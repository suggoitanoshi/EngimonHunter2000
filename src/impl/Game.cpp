#include "../headers/Game.hpp"

#include <algorithm>
#include <cstdlib>
#include <fstream>
#include <iostream>
#include <tuple>
#include <utility>

#include "../headers/Battle.hpp"
#include "../headers/BreedingException.hpp"
#include "../headers/Dex.hpp"
#include "../headers/Player.hpp"

using namespace std;

Game::Game() {
    srand(time(0));
    this->isExitGame = false;
    map.setTile(1, 1, MapTile::PLAYER);       // buat player
    map.setTile(2, 1, MapTile::ACTIVE_ENGI);  // buat active engimon
    // player = Player();
    // dex = Dex();
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
    cout << "------------------------C O M M A N D-----------------------"
         << endl;
    cout << "     W:atas  A:kiri  S:bawah  D:kanan  X:Keluar game" << endl;
    cout << "------------------------------------------------------------"
         << endl;
    cout << "1. Lihat list engimon saya | 2. Lihat seluruh engimon" << endl;
    cout << "3. Ganti active engimon    | 4. Lihat list skill item saya"
         << endl;
    cout << "5. Gunakan skill item      | 6. Lakukan breeding" << endl;
    cout << "7. Lakukan Battle" << endl;
    cout << "------------------------------------------------------------"
         << endl;
}

void Game::movePlayerDelta(int dx, int dy) {
    int newx, newy;
    newx = this->player.getPositionX() + dx;
    newy = this->player.getPositionY() + dy;
    if (newx < 1 || newx > 30 || newy < 1 || newy > 14) {
        throw GameException(0);
    }
    this->player.setPositionX(newx);
    this->player.setPositionY(newy);
}

Engimon Game::makeRandomEngimon() const {
    int idx = rand() % dex.getEngiDex().size();
    bool compat;
    EngimonSpecies engieSpecies;

    // dapetin spesies engimonnya
    for (pair<string, EngimonSpecies> a : dex.getEngiDex()) {
        if (idx == 0) engieSpecies = a.second;
        idx--;
    }

    Engimon engie(engieSpecies);
    engie.addExp(((rand() % 20) + 1) *
                 100);  // karena tiap level butuh 100 exp dan sementara dibikin
                        // random 1 sampe 20

    // dapetin 4 move acak
    unordered_map<string, Skill> filtered;
    for (pair<string, Skill> a : dex.getSkillDex()) {
        // cek elemen
        compat = 0;
        for (Elements::el engieEl : engie.getElements()) {
            for (Elements::el skillEl : a.second.getElements()) {
                compat |= engieEl == skillEl;
                if (compat) {
                    filtered.emplace(a.first, a.second);
                    break;
                }
            }
            if (compat) break;
        }
    }
    for (size_t i = 0; i < min(filtered.size(), (size_t)4); i++) {
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
            if (!map.getTile(x, y).isOccupied()) {
                ret.push_back(make_tuple(x, y));
            }
        }
    }

    return ret;
}

void Game::spawnWildEngimon(unsigned count) {
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
        for (unsigned j = 0; !isPlaced && j < (Map::mapX * Map::mapY); ++j) {
            unsigned randIdx = rand() % freeSpaces.size();
            tuple<int, int> pos = freeSpaces[randIdx];
            vector<Elements::el> engieEl = engie.getElements();
            char engieChar = 0;

            // cari tipenya
            if (engieEl.size() == 2) {
                engieChar = 'S' * ((engieEl[0] == Elements::ICE &&
                                    engieEl[1] == Elements::WATER) ||
                                   (engieEl[0] == Elements::WATER &&
                                    engieEl[1] == Elements::ICE)) |
                            'N' * ((engieEl[0] == Elements::WATER &&
                                    engieEl[1] == Elements::GROUND) ||
                                   (engieEl[0] == Elements::GROUND &&
                                    engieEl[1] == Elements::WATER)) |
                            'L' * ((engieEl[0] == Elements::FIRE &&
                                    engieEl[1] == Elements::ELECTRIC) ||
                                   (engieEl[0] == Elements::ELECTRIC &&
                                    engieEl[1] == Elements::FIRE));
            } else {  // ukurannya 1
                engieChar = 'F' * (engieEl[0] == Elements::FIRE) |
                            'I' * (engieEl[0] == Elements::ICE) |
                            'W' * (engieEl[0] == Elements::WATER) |
                            'G' * (engieEl[0] == Elements::GROUND) |
                            'E' * (engieEl[0] == Elements::ELECTRIC);
            }

            // cek tipe tyle lalu ganti tipenya kalau cocok
            if (map.getTile(get<0>(pos), get<1>(pos)).getType() ==
                MapTile::WATER) {
                if (!(engieChar == 'F' || engieChar == 'G' ||
                      engieChar == 'E' || engieChar == 'L')) {
                    map.setTile(get<0>(pos), get<1>(pos), engieChar);
                    freeSpaces.erase(freeSpaces.begin() + randIdx);
                    isPlaced = true;
                }
            } else {  // char-nya: '-'
                if (engieChar == 'F' || engieChar == 'G' || engieChar == 'E' ||
                    engieChar == 'L') {
                    map.setTile(get<0>(pos), get<1>(pos), engieChar);
                    freeSpaces.erase(freeSpaces.begin() + randIdx);
                    isPlaced = true;
                }
            }
        }

        if (!isPlaced) {
            // error
            --i;  // ga diitung langkah ini
        }
    }
}

void Game::run() {
    printGameIntro();
    this->spawnWildEngimon(wildEngimonCount);

    do {
        string input;
        map.printMap(player);
        cout << "Masukkan input: ";
        cin >> input;
        if (input.length() != 1) {
            cout << "Masukan salah, silakan ulangi masukan" << endl;
        } else {
            try {
                switch (tolower(input[0])) {
                    case 'w':
                        this->player.setDir('w');
                        movePlayerDelta(0, -1);
                        break;
                    case 'a':
                        this->player.setDir('a');
                        movePlayerDelta(-1, 0);
                        break;
                    case 's':
                        this->player.setDir('s');
                        movePlayerDelta(0, 1);
                        break;
                    case 'd':
                        this->player.setDir('d');
                        movePlayerDelta(1, 0);
                        break;
                    case 'x':
                        this->isExitGame = true;
                        break;
                    case '1':
                        // List engimon dimiliki
                        this->player.showEngimon();
                        break;
                    case '2':
                        // List engimon dex
                        break;
                    case '3':
                        this->player.showEngimon();
                        cout << "Masukkan nama engimon: ";
                        cin >> input;
                        try{
                            this->player.switchEngimon(input);
                        }
                        catch(InventoryException e){
                            cout << e.what() << endl;
                        }
                        // Ganti active engi
                        break;
                    case '4':
                        // Lihat skill item dimiliki
                        break;
                    case '5':
                        // Pakai skill item
                        break;
                    case '6': {
                        // Breeding
                        Engimon A, B, HasilKawin;
                        this->player.showEngimon();
                        try {
                            cout << "Pilih engimon 1: ";
                            cin >> input;
                            A = player.getEngimonFromName(input);
                            cout << "Pilih engimon 2: ";
                            cin >> input;
                            B = player.getEngimonFromName(input);
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
                        break;
                    default:
                        cout << "Masukan salah, ulangi masukan" << endl;
                        break;
                }
            } catch (GameException& e) {
                cout << "Kamu tidak bisa bergerak ke situ" << endl;
            }
        }
        cout << "\n============================================================"
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

const string GameException::msg[] = {"Pergerakan tidak valid"};

GameException::GameException(int id) : exceptionID(id) {}
const char* GameException::what() { return msg[this->exceptionID].c_str(); }
