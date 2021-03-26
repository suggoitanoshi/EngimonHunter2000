#include "../headers/Battle.hpp"

#ifdef _WIN32
#include <Windows.h>
#else
#include <unistd.h>
#endif

#include <cstdlib>
#include <iostream>

using namespace std;

Battle::Battle() {
    advA = 0;
    advB = 0;
    pwrA = 0;
    pwrB = 0;
}

void Battle::checkAdvantage(Engimon A, Engimon B) {
    vector<Elements::el> elA = A.getElements();
    vector<Elements::el> elB = B.getElements();

    for (Elements::el elemA : elA) {
        for (Elements::el elemB : elB) {
            if (Elements::getElementalAdvantage(elemA, elemB) >= advA) {
                advA = Elements::getElementalAdvantage(elemA, elemB);
            }
        }
    }

    for (Elements::el elemB2 : elB) {
        for (Elements::el elemA2 : elA) {
            if (Elements::getElementalAdvantage(elemB2, elemA2) >= advB) {
                advB = Elements::getElementalAdvantage(elemB2, elemA2);
            }
        }
    }
}

bool Battle ::runBattle(Engimon& A, Engimon& B) {
    checkAdvantage(A, B);
    pwrA = A.getLvl() * advA;
    for (auto& skillA : A.getSkills()) {
        pwrA = pwrA + skillA.getBasePower() * skillA.getMasteryLevel();
    }

    pwrB = B.getLvl() * advB;
    for (auto& skillB : B.getSkills()) {
        pwrB = pwrB + skillB.getBasePower() * skillB.getMasteryLevel();
    }

    cout << "Engimon pemain: " << '\n';
    A.showEngimon();
    cout << '\n' << "Engimon liar: " << '\n';
    B.showEngimon();

    sleep(3);

    if (pwrA >= pwrB) {
        cout << "-------------------S E L A M A T-----------------" << endl;
        cout << "                                   .''.          " << endl;
        cout << "       .''.      .        *''*    :_\\/_:     .   " << endl;
        cout << "      :_\\/_:   _\\(/_  .:.*_\\/_*   : /\\ :  .'.:.'." << endl;
        cout << "  .''.: /\\ :    /)\\   ':'* /\\ *  : '..'.  -=:o:=-" << endl;
        cout << " :_\\/_:'.:::.    ' *''*    * '.\'/.'_\\(/_'.':'.' " << endl;
        cout << " : /\\ : :::::     *_\\/_*     -= o =- /)\\    '  * " << endl;
        cout << "  '..'  ':::'     * /\\ *     .'/.\'.  ' .        " << endl;
        cout << "-------------------------------------------------" << endl;
        cout << A.getName() << " berhasil menang melawan " << B.getName()
             << endl;
        cout << "Sekarang " << B.getName() << " ada di inventory mu!" << endl;
    } else {
        cout << "--------------------- R.I.P. ---------------------" << endl;
        cout << "                             .                    " << endl;
        cout << "                            -|-                   " << endl;
        cout << "                             |                    " << endl;
        cout << "                         .-'~~~`-.                " << endl;
        cout << "                       .'         `.              " << endl;
        cout << "                       |  R  I  P  |              " << endl;
        cout << "                       |           |              " << endl;
        cout << "                       |           |              " << endl;
        cout << "                     \\|           |//            " << endl;
        cout << "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" << endl;
        cout << "-------------------------------------------------" << endl;
        cout << A.getName() << " kalah melawan " << B.getName() << endl;
        cout << "Sekarang " << A.getName() << " dihapus dari inventory mu"
             << endl;
        cout << "Semoga amal ibadah " << A.getName()
             << " diterima oleh Yang Maha Kuasa" << endl;
    }
    return pwrA >= pwrB;
}
