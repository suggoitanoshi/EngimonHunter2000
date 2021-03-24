#include "../headers/Game.hpp"

#include <iostream>

#include "../headers/Player.hpp"

using namespace std;

Game ::Game() {
    this->isExitGame = false;
    this->player = Player();
    this->playerName = "josep";
}

void Game ::printGameIntro() {
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
    cout << "Masukkan nama mu : ";
    cin >> this->playerName;
    cout << "selamat bermain di Engimon Factory "<< this->playerName <<" !"<<endl;
    cout << "============================================================\n\n" << endl;
}

void Game ::readMap() {
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

void Game :: printMap(){
    cout <<"--------------------------P E T A--------------------------"<<endl;
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
    cout <<"------------------------C O M M A N D-----------------------"<<endl;
    cout << "     W:atas  A:kiri  S:bawah  D:kanan  X:Keluar game" << endl;
    cout <<"------------------------------------------------------------"<<endl;
    cout << "1. Lihat list engimon saya | 2. Lihat seluruh engimon"<<endl;
    cout << "3. Ganti active engimon    | 4. Lihat list skill item saya" <<endl;
    cout << "5. Gunakan skill item      | 6. Lakukan breeding"<<endl;
    cout << "7. Lakukan Battle"<<endl;
    cout <<"------------------------------------------------------------"<<endl;
}

// double Game :: getAdvantage(Elements A, Elements B){
//     if (A==FIRE){
//         if (B==FIRE){
//             return 1;
//         }
//         else if (B==WATER){
//             return 0;
//         }
//         else if (B==ELECTRIC){
//             return 1;
//         }
//         else if (B==GROUND){
//             return 0.5;
//         }
//         else if (B==ICE){
//             return 2;
//         }
//     }
//     else if (A==WATER){
//         if (B==FIRE){
//             return 2;
//         }
//         else if (B==WATER){
//             return 1;
//         }
//         else if (B==ELECTRIC){
//             return 0;
//         }
//         else if (B==GROUND){
//             return 1;
//         }
//         else if (B==ICE){
//             return 1;
//         }
//     }
//     else if (A==ELECTRIC){
//         if (B==FIRE){
//             return 1;
//         }
//         else if (B==WATER){
//             return 2;
//         }
//         else if (B==ELECTRIC){
//             return 1;
//         }
//         else if (B==GROUND){
//             return 0;
//         }
//         else if (B==ICE){
//             return 1.5;
//         }
//     }
//     else if (A==GROUND){
//         if (B==FIRE){
//             return 1.5;
//         }
//         else if (B==WATER){
//             return 1;
//         }
//         else if (B==ELECTRIC){
//             return 2;
//         }
//         else if (B==GROUND){
//             return 1;
//         }
//         else if (B==ICE){
//             return 0;
//         }
//     }
//     else if (A==ICE){
//         if (B==FIRE){
//             return 0;
//         }
//         else if (B==WATER){
//             return 1;
//         }
//         else if (B==ELECTRIC){
//             return 0.5;
//         }
//         else if (B==GROUND){
//             return 2;
//         }
//         else if (B==ICE){
//             return 1;
//         }
//     }
// }

// double Game :: checkAdvantage(Engimon A, Engimon B){

// }

// void Game :: battle(Engimon A, Engimon B){

// }

// void Game :: spawnWildEngimon(){

// }

bool Game ::getIsExit() { return this->isExitGame; }

Player Game ::getPlayer() { return this->player; }

// list<Engimon> Game :: getWildEngimon(){
//     return this->wildEngimons;
// }

void Game ::setIsExit(bool x) { this->isExitGame = x; }

// void Game :: addWildEngimon(Engimon X){
//     this->wildEngimons.push_back(X);
// }

void Game ::run() {
    this->printGameIntro();
    this->player.setName(this->playerName);
    this->readMap();

    do {
        string input;
        this->printMap();
        cout << "Masukkan input : ";
        cin >> input;
        if (input.length()!=1){
            cout << "Masukan salah, silakan ulangi masukan"<<endl;
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
            this->setIsExit(true);
        }
        else{
            cout << "Masukan salah, silakan ulangi masukan"<<endl;
        }
        cout << "\n============================================================\n" << endl;
    } while (this->getIsExit() == false);
    cout << "makasih dah main kk" << endl;
}