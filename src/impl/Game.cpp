#include "../headers/Game.hpp"

#include <algorithm>
#include <cstdlib>
#include <ctime>
#include <iostream>
#include <tuple>
#include <unordered_map>
#include <utility>

#include "../headers/Battle.hpp"
#include "../headers/BreedingException.hpp"
#include "../headers/Dex.hpp"
#include "../headers/Player.hpp"

using namespace std;

Game::Game() {
    srand(time(0));
    this->isExitGame = false;
    player = Player();
    dex = Dex();
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

void Game::readMap() {
    int x, y;
    ifstream f;
    string linemap;
    f.open("data/map.txt");
    if (f.is_open()) {
        y = 0;
        while (getline(f, linemap)) {
            x = 0;
            for (char& c : linemap) {
                if (c != '\0' && c != '\n' && c != '\r') {
                    map[x][y] = c;
                    x++;
                }
            }
            y++;
        }
    }
}

void Game::printMap() {
    cout << "--------------------------P E T A--------------------------"
         << endl;
    for (int i = 0; i < 15; i++) {
        for (int j = 0; j < 32; j++) {
            if (j == this->player.getPositionX() &&
                i == this->player.getPositionY()) {
                cout << 'P';
            } else if (j == this->player.getPositionX() + 1 &&
                       i == this->player.getPositionY() &&
                       this->player.getDir() == 'a') {
                cout << 'X';
            } else if (j == this->player.getPositionX() &&
                       i == this->player.getPositionY() + 1 &&
                       this->player.getDir() == 'w') {
                cout << 'X';
            } else if (j == this->player.getPositionX() - 1 &&
                       i == this->player.getPositionY() &&
                       this->player.getDir() == 'd') {
                cout << 'X';
            } else if (j == this->player.getPositionX() &&
                       i == this->player.getPositionY() - 1 &&
                       this->player.getDir() == 's') {
                cout << 'X';
            }

            else {
                cout << map[j][i];
            }
        }
        cout << endl;
    }
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

Engimon Game::makeRandomEngimon() const {
    int idx = rand() % wildEngimonCount;
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
    for (size_t i = 0; i < min(dex.getEngiDex().size(), (size_t)4); ++i) {
        idx = rand() % dex.getEngiDex().size();
        for (pair<string, Skill> a : dex.getSkillDex()) {
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

vector<tuple<int, int>> Game::getEmptyMapTile() {
    vector<tuple<int, int>> ret;

    for (int y = 0; y < 16; ++y) {
        for (int x = 0; x < 32; ++x) {
            char tmp = map[x][y].getTileChar();
            if (tmp == 'o' || tmp == '-') {
                ret.push_back(make_tuple(x, y));
            }
        }
    }

    return ret;
}

void Game::spawnWildEngimon(unsigned count) {
    unordered_map<string, EngimonSpecies> engies = dex.getEngiDex();
    vector<tuple<int, int>> freeSpaces = getEmptyMapTile();

    count =
        (count < getEmptyMapTile().size()) ? count : getEmptyMapTile().size();

    for (size_t i = 0; i < count; ++i) {
        Engimon engie = makeRandomEngimon();

        for (vector<tuple<int, int>>::iterator it = freeSpaces.begin();
             it != freeSpaces.end(); ++it) {
            tuple<int, int> pos = *it;
            vector<Elements::el> engieEl = engie.getElements();
            char engieChar = 0;

            if (engieEl.size() == 2) {
                engieChar = 'S' * ((engieEl[0] == Elements::ICE && engieEl[1] == Elements::WATER) ||
                               (engieEl[0] == Elements::WATER && engieEl[1] == Elements::ICE)) |
                            'N' * ((engieEl[0] == Elements::WATER && engieEl[1] == Elements::GROUND) ||
                                   (engieEl[0] == Elements::GROUND && engieEl[1] == Elements::WATER)) |
                            'L' * ((engieEl[0] == Elements::FIRE && engieEl[1] == Elements::ELECTRIC) ||
                                   (engieEl[0] == Elements::ELECTRIC && engieEl[1] == Elements::FIRE));
            } else { // ukurannya 1
                engieChar = 'F' * (engieEl[0] == Elements::FIRE) |
                            'I' * (engieEl[0] == Elements::ICE) |
                            'W' * (engieEl[0] == Elements::WATER) |
                            'G' * (engieEl[0] == Elements::GROUND) |
                            'E' * (engieEl[0] == Elements::ELECTRIC);
            }

            if (map[get<0>(pos)][get<1>(pos)].getTileChar() == 'o') {
                if (engieChar == 'F' || engieChar == 'G' || engieChar == 'E' ||
                    engieChar == 'L') {
                    continue;
                }

                map[get<0>(pos)][get<1>(pos)] = engieChar;
                freeSpaces.erase(it);
                break;
            } else {  // char-nya: '-'
                if (engieChar == 'F' || engieChar == 'G' || engieChar == 'E' ||
                    engieChar == 'L') {

                    map[get<0>(pos)][get<1>(pos)] = engieChar;
                    freeSpaces.erase(it);
                    break;
                }
            }
        }
    }
}

void Game::run() {
    printGameIntro();
    this->readMap();
    this->spawnWildEngimon(wildEngimonCount);

    do {
        string input;
        this->printMap();
        cout << "Masukkan input: ";
        cin >> input;
        if (input.length() != 1) {
            cout << "Masukan salah, silakan ulangi masukan" << endl;
        } else {
            if ((char)tolower(input[0]) == 'w') {
                try {
                    if (this->player.getPositionY() - 1 == 0) {
                        throw(this->player.getPositionY());
                    }
                    this->player.setPositionY(this->player.getPositionY() - 1);
                    this->player.setDir('w');
                } catch (int num) {
                    cout << "Kamu tidak bisa bergerak ke situ" << endl;
                }
            } else if ((char)tolower(input[0]) == 'a') {
                try {
                    if (this->player.getPositionX() - 1 == 0) {
                        throw(this->player.getPositionX());
                    }
                    this->player.setPositionX(this->player.getPositionX() - 1);
                    this->player.setDir('a');
                } catch (int num) {
                    cout << "Kamu tidak bisa bergerak ke situ" << endl;
                }
            } else if ((char)tolower(input[0]) == 's') {
                try {
                    if (this->player.getPositionY() + 1 == 14) {
                        throw(this->player.getPositionY());
                    }
                    this->player.setPositionY(this->player.getPositionY() + 1);
                    this->player.setDir('s');
                } catch (int num) {
                    cout << "Kamu tidak bisa bergerak ke situ" << endl;
                }
            } else if ((char)tolower(input[0]) == 'd') {
                try {
                    if (this->player.getPositionX() + 1 == 31) {
                        throw(this->player.getPositionX());
                    }
                    this->player.setPositionX(this->player.getPositionX() + 1);
                    this->player.setDir('d');
                } catch (int num) {
                    cout << "Kamu tidak bisa bergerak ke situ" << endl;
                }
            } else if ((char)tolower(input[0]) == 'x') {
                this->isExitGame = true;
            } else if ((char)tolower(input[0]) == '1') {
            } else if ((char)tolower(input[0]) == '2') {
            } else if ((char)tolower(input[0]) == '3') {
            } else if ((char)tolower(input[0]) == '4') {
            } else if ((char)tolower(input[0]) == '5') {
            } else if ((char)tolower(input[0]) == '6') {
            } else if ((char)tolower(input[0]) == '7') {
            } else {
                cout << "Masukan salah, silakan ulangi masukan" << endl;
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
    std::cin >> name;
    tuple<string, string> parents[2] = {
        make_tuple(bapak.getName(), bapak.getSpecies()),
        make_tuple(emak.getName(), emak.getSpecies())};
    Engimon* anjay = new Engimon(spesies, name, parents, skillYangDiambil);
    return *anjay;
}
