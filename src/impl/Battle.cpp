#include "../headers/Battle.hpp"
#include <iostream>

using namespace std;

Battle :: Battle(){

}


void Battle :: setEngimonWinner(){

} 

double Battle :: getAdvantage(Elements A, Elements B){
    if (A==FIRE){
        if (B==FIRE){
            return 1;
        }
        else if (B==WATER){
            return 0;
        }
        else if (B==ELECTRIC){
            return 1;
        }
        else if (B==GROUND){
            return 0.5;
        }
        else if (B==ICE){
            return 2;
        }
    }
    else if (A==WATER){
        if (B==FIRE){
            return 2;
        }
        else if (B==WATER){
            return 1;
        }
        else if (B==ELECTRIC){
            return 0;
        }
        else if (B==GROUND){
            return 1;
        }
        else if (B==ICE){
            return 1;
        }
    }
    else if (A==ELECTRIC){
        if (B==FIRE){
            return 1;
        }
        else if (B==WATER){
            return 2;
        }
        else if (B==ELECTRIC){
            return 1;
        }
        else if (B==GROUND){
            return 0;
        }
        else if (B==ICE){
            return 1.5;
        }
    }
    else if (A==GROUND){
        if (B==FIRE){
            return 1.5;
        }
        else if (B==WATER){
            return 1;
        }
        else if (B==ELECTRIC){
            return 2;
        }
        else if (B==GROUND){
            return 1;
        }
        else if (B==ICE){
            return 0;
        }
    }
    else if (A==ICE){
        if (B==FIRE){
            return 0;
        }
        else if (B==WATER){
            return 1;
        }
        else if (B==ELECTRIC){
            return 0.5;
        }
        else if (B==GROUND){
            return 2;
        }
        else if (B==ICE){
            return 1;
        }
    }
}
