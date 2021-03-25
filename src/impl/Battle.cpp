#include "../headers/Battle.hpp"

#include <iostream>

using namespace std;

Battle :: Battle(){
    advA = 0;
    advB = 0;
    pwrA = 0;
    pwrB = 0;
}

double Battle::getAdvantage(Elements::el A, Elements::el B) {
    if (A == Elements::FIRE) {
        if (B == Elements::FIRE) {
            return 1;
        } else if (B == Elements::WATER) {
            return 0;
        } else if (B == Elements::ELECTRIC) {
            return 1;
        } else if (B == Elements::GROUND) {
            return 0.5;
        } else {  // B == Elements::ICE
            return 2;
        }
    } else if (A == Elements::WATER) {
        if (B == Elements::FIRE) {
            return 2;
        } else if (B == Elements::WATER) {
            return 1;
        } else if (B == Elements::ELECTRIC) {
            return 0;
        } else if (B == Elements::GROUND) {
            return 1;
        } else {  // B == Elements::ICE
            return 1;
        }
    } else if (A == Elements::ELECTRIC) {
        if (B == Elements::FIRE) {
            return 1;
        } else if (B == Elements::WATER) {
            return 2;
        } else if (B == Elements::ELECTRIC) {
            return 1;
        } else if (B == Elements::GROUND) {
            return 0;
        } else {  // B == Elements::ICE
            return 1.5;
        }
    } else if (A == Elements::GROUND) {
        if (B == Elements::FIRE) {
            return 1.5;
        } else if (B == Elements::WATER) {
            return 1;
        } else if (B == Elements::ELECTRIC) {
            return 2;
        } else if (B == Elements::GROUND) {
            return 1;
        } else {  // B == Elements::ICE
            return 0;
        }
    } else {  // A == Elements::ICE
        if (B == Elements::FIRE) {
            return 0;
        } else if (B == Elements::WATER) {
            return 1;
        } else if (B == Elements::ELECTRIC) {
            return 0.5;
        } else if (B == Elements::GROUND) {
            return 2;
        } else {  // B == Elements::ICE
            return 1;
        }
    }
}

void Battle :: checkAdvantage(Engimon A, Engimon B){
    vector<Elements::el> elA = A.getElements();
    vector<Elements::el> elB = B.getElements();

    for (Elements::el elemA : elA){
        for (Elements::el elemB : elB){
            if (getAdvantage(elemA,elemB)>=advA){
                advA = getAdvantage(elemA, elemB);
            }
        }
    }

    for (Elements::el elemB2 : elB){
        for (Elements::el elemA2 : elA){
            if (getAdvantage(elemB2,elemA2)>=advB){
                advB = getAdvantage(elemB2, elemA2);
            }
        }
    }
}

void Battle :: runBattle(Engimon A, Engimon B){
    checkAdvantage(A,B);
    pwrA = A.getLvl()*advA;
    for (auto& skillA : A.getSkills()){
        pwrA = pwrA + skillA.getBasePower()*skillA.getMasteryLevel();
    }

    pwrB = B.getLvl()*advB;
    for (auto& skillB : B.getSkills()){
        pwrB = pwrB + skillB.getBasePower()*skillB.getMasteryLevel();
    }

    if (pwrA >= pwrB){
        cout << "-------------------S E L A M A T-----------------"<<endl;
        cout << "                                   .''.          "<<endl;
        cout << "       .''.      .        *''*    :_\\/_:     .   "<<endl;
        cout << "      :_\\/_:   _\\(/_  .:.*_\\/_*   : /\\ :  .'.:.'."<<endl;
        cout << "  .''.: /\\ :    /)\\   ':'* /\\ *  : '..'.  -=:o:=-"<<endl;
        cout << " :_\\/_:'.:::.    ' *''*    * '.\'/.'_\\(/_'.':'.' " <<endl;
        cout << " : /\\ : :::::     *_\\/_*     -= o =- /)\\    '  * " << endl;
        cout << "  '..'  ':::'     * /\\ *     .'/.\'.  ' .        "<< endl;
        cout << "-------------------------------------------------"<<endl;
        cout <<A.getName()<<" berhasil menang melawan "<< B.getName()<< endl;
        cout << "Sekarang "<< B.getName()<<" ada di inventory mu!"<<endl;
    }
    else{
        cout << "--------------------- R.I.P. ---------------------"<<endl;
        cout << "                             .                    " <<endl;
        cout << "                            -|-                   " <<endl;
        cout << "                             |                    " <<endl;
        cout << "                         .-'~~~`-.                " <<endl;
        cout << "                       .'         `.              " <<endl;
        cout << "                       |  R  I  P  |              " <<endl;
        cout << "                       |           |              " <<endl;
        cout << "                       |           |              " <<endl;
        cout << "                     \\|           |//            " <<endl;
        cout << "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" <<endl;
        cout << "-------------------------------------------------"<<endl;
        cout <<A.getName()<<" kalah melawan "<< B.getName()<< endl;
        cout << "Sekarang "<< A.getName()<<" dihapus dari inventory mu"<<endl;
        cout << "Semoga amal ibadah "<<A.getName()<<" diterima oleh Yang Maha Kuasa"<<endl;
    }
}