#include "../headers/Game.hpp"

#include <stdlib.h>

#include <iostream>

#include "../headers/BreedingException.hpp"
#include "../headers/Dex.hpp"
#include "../headers/Player.hpp"

using namespace std;

Game::Game() {
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
    int i, j;
    ifstream f;
    string linemap;
    f.open("data/map.txt");
    if (f.is_open()) {
        j = 0;
        while (getline(f, linemap)) {
            i = 0;
            for (char& c : linemap) {
                if (c != '\0' && c != '\n' && c != '\r') {
                    map[i][j] = c;
                    i++;
                }
            }
            j++;
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

// double Game :: checkAdvantage(Engimon A, Engimon B){

// }

// void Game :: battle(Engimon A, Engimon B){

// }

// void Game :: spawnWildEngimon(){

// }

// list<Engimon> Game :: getWildEngimon(){
//     return this->wildEngimons;
// }

// void Game :: addWildEngimon(Engimon X){
//     this->wildEngimons.push_back(X);
// }

void Game::run() {
    printGameIntro();
    this->readMap();

    do {
        string input;
        this->printMap();
        cout << "Masukkan input: ";
        cin >> input;
        if (input.length() != 1) {
            cout << "Masukan salah, silakan ulangi masukan" << endl;
        }

        if ((char)tolower(input[0]) == 'w') {
            try {
                if (this->player.getPositionY() - 1 == 0) {
                    throw(this->player.getPositionY());
                }
                this->player.setPositionY(this->player.getPositionY() - 1);
                this->player.setDir('w');
            } catch (int num) {
                cout << "Aduh nabrak xixixi" << endl;
            }
        } else if ((char)tolower(input[0]) == 'a') {
            try {
                if (this->player.getPositionX() - 1 == 0) {
                    throw(this->player.getPositionX());
                }
                this->player.setPositionX(this->player.getPositionX() - 1);
                this->player.setDir('a');
            } catch (int num) {
                cout << "Aduh nabrak xixixi" << endl;
            }
        } else if ((char)tolower(input[0]) == 's') {
            try {
                if (this->player.getPositionY() + 1 == 14) {
                    throw(this->player.getPositionY());
                }
                this->player.setPositionY(this->player.getPositionY() + 1);
                this->player.setDir('s');
            } catch (int num) {
                cout << "Aduh nabrak xixixi" << endl;
            }
        } else if ((char)tolower(input[0]) == 'd') {
            try {
                if (this->player.getPositionX() + 1 == 31) {
                    throw(this->player.getPositionX());
                }
                this->player.setPositionX(this->player.getPositionX() + 1);
                this->player.setDir('d');
            } catch (int num) {
                cout << "Aduh nabrak xixixi" << endl;
            }
        } else if ((char)tolower(input[0]) == 'x') {
            this->isExitGame = true;
        } else {
            cout << "Masukan salah, silakan ulangi masukan" << endl;
        }
        cout << "\n============================================================"
                "\n"
             << endl;
    } while (!this->isExitGame);
    cout << "makasih dah main kk" << endl;
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
           (skillEmakTerambil < (int) bapak.getSkills().size() ||
            skillEmakTerambil < (int) emak.getSkills().size())) {
        skillSekarang = bapak.getSkills()[0];
        bapakTerambil = 1;
        emakTerambil = 0;
        for (indexSkillOrangtua = 1;
             indexSkillOrangtua < (int) bapak.getSkills().size();
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
             indexSkillOrangtua < (int) emak.getSkills().size();
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
    Elements elementBapak, elementEmak;
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
        // STUB
        spesies = dex.getEngi(bapak.getSpecies());
    }
    std::cin >> name;
    tuple<string, string> parents[2] = {
        make_tuple(bapak.getName(), bapak.getSpecies()),
        make_tuple(emak.getName(), emak.getSpecies())};
    Engimon* anjay = new Engimon(spesies, name, parents, skillYangDiambil);
    return *anjay;
}
