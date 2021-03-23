#include <iostream>
#include <fstream>
#include <tuple>
#include <cctype>
#include <string>
#include "../headers/Player.hpp"

using namespace std;


//dipisah jd hpp cpp nanti kalo dah fix
class Gamestate{

private:
    char map[32][16];
    tuple<int,int> pos;
    bool isExitGame;
    char dir='a';

public:
    Gamestate(){
        pos = make_tuple(1,1);
        isExitGame = false;
    }
    void printGameIntro(){
        cout<<"░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░"<<endl;
        cout<<"░░░░░░░░░░░░░█████████░░░░░░░░░░░░░░░░░░"<<endl;
        cout<<"░░░░░░░░░░███████████████░░░░░░░░░░░░░░░"<<endl;
        cout<<"░░░░░░░░░██████▓▓▓▓▓▓▓▓████░░░░░░░░░░░░░"<<endl;
        cout<<"░░░░░░░░██░▒▒███▒▒▒▒▒▓▓▓███░░░░░░░░░░░░░"<<endl;
        cout<<"░░░░░░░░█░░░▒▒▒████░░▒▒▒▓███░░░░░░░░░░░░"<<endl;
        cout<<"░░░░░░░██░░░▒▒██░▄░██░▒▒▓███░░░░░░░░░░░░"<<endl;
        cout<<"░░░░░░░██░░░░▒██░▀░██▓▓▓▓███░░░░░░░░░░░░"<<endl;
        cout<<"░░░░░░░░██░░░▒▒███████▓▓████░░░░░░░░░░░░"<<endl;
        cout<<"░░░░░░░░░██░░░▒▒▒▒▒▒███████░░░░░░░░░░░░░"<<endl;
        cout<<"░░░░░░░░░░████░░░░░▒▒█████░░░░░░░░░░░░░░"<<endl;
        cout<<"░░░░░░░░░░░░░██████████░░░░░░░░░░░░░░░░░"<<endl;
        cout<<"Masukkan s untuk memulai permainan!"<<endl;
        char start='b';
        do {
            cin >> start;
        }
        while (start != 's');
        
    }

    void readMap(){
        int i,j;
        ifstream f;
        string linemap;
        f.open("../../file/map.txt");
        if (f.is_open()){
            j=0;
            while(getline(f,linemap)){
                i = 0;
                for(char& c : linemap) {
                    if (c != '\0' && c != '\n' && c!='\r'){
                        map[i][j] = c;
                        i++;
                    }
                    // cout<<c;
                }
                j++;
            }
        }
    }

    void printMap(){
        for(int i = 0; i<15;i++){
            for(int j=0;j<32;j++){
                if (j == get<0>(pos) && i==get<1>(pos)){
                    cout<<'P';
                }
                else if (j==get<0>(pos)+1 && i==get<1>(pos) && dir=='a' ){
                    cout<<'X';
                }
                else if (j==get<0>(pos) && i==get<1>(pos)+1 && dir=='w' ){
                    cout<<'X';
                }
                else if (j==get<0>(pos)-1 && i==get<1>(pos) && dir=='d' ){
                    cout<<'X';
                }
                else if (j==get<0>(pos) && i==get<1>(pos)-1 && dir=='s' ){
                    cout<<'X';
                }

                else{
                     cout<<map[j][i];
                }
            }
            cout<<endl;
        }
        cout<<"W:atas A:kiri S:bawah D:kanan X:Keluar game"<<endl;
        cout<<"Masukkan input : ";
    }

    bool getIsExit(){
        return isExitGame;
    }

    void setIsExit(bool x){
        isExitGame = x;
    }

    int getPosX(){
        return get<0>(pos);
    }

    int getPosY(){
        return get<1>(pos);
    }

    void setPosX (int x){
        get<0>(pos) = x;
    }
    void setPosY (int y){
        get<1>(pos) = y;
    }

    void setDir(char c){
        dir = c;
    }
};

int main(){

    Gamestate g = Gamestate();
    g.printGameIntro();
    g.readMap();
    
    do{
        char input;
        g.printMap();
        cin >> input;
        if ((char)tolower(input) == 'w'){
            try {
                if (g.getPosY()-1==0){
                    throw (g.getPosY());
                }
                g.setPosY(g.getPosY()-1);
                g.setDir('w');
            }
            catch (int num){
                cout << "Aduh nabrak xixixi"<<endl;
            }
        }
        else if ((char)tolower(input) == 'a'){
            try {
                if (g.getPosX()-1==0){
                    throw (g.getPosX());
                }
                g.setPosX(g.getPosX()-1);
                g.setDir('a');
            }
            catch (int num){
                cout << "Aduh nabrak xixixi"<<endl;
            }
        }
        else if ((char)tolower(input) == 's'){
            try {
                if (g.getPosY()+1==14){
                    throw (g.getPosY());
                }
                g.setPosY(g.getPosY()+1);
                g.setDir('s');
            }
            catch (int num){
                cout << "Aduh nabrak xixixi"<<endl;
            }
        }
        else if ((char)tolower(input) == 'd'){
            try {
                if (g.getPosX()+1==31){
                    throw (g.getPosX());
                }
                g.setPosX(g.getPosX()+1);
                g.setDir('d');
            }
            catch (int num){
                cout << "Aduh nabrak xixixi"<<endl;
            }
        }
        else if((char)tolower(input) == 'x'){
            g.setIsExit(true);
        }
        cout<<"\n===========================================\n"<<endl;
    }
    while(g.getIsExit()==false);
    cout << "makasih dah main kk"<<endl;
}