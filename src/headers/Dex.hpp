#ifndef _DEX_HPP_
#define _DEX_HPP_

#include <unordered_map>

#include "Engimon.hpp"
#include "Skill.hpp"
#include "exception"

using namespace std;

class Dex {
private:
    unordered_map<string, Skill> skillDex;
    unordered_map<string, EngimonSpecies> engiDex;

    void readEngiDex(const string);
    void readSkillDex(const string);

public:
    // konstruktor
    Dex();
    Dex(const string engiData, const string skillData);

    // getter
    Skill getSkill(const string skillname) const;
    EngimonSpecies getEngi(const string enginame) const;

    // setter
    void addSkill(const string skillname, const Skill skill);
    void addEngi(const string enginame, const EngimonSpecies engimon);

    // kalo kons ngeganggu apus aja, tapi sebisa mungkin nambah elemen lewat
    // fungsi `add`
    const unordered_map<string, Skill> getSkillDex() const;
    const unordered_map<string, EngimonSpecies> getEngiDex() const;
    void showEngimons() const;
    void showSkills() const;
};

class DexException : exception {
private:
    const int msgID;
    static string msg[];

public:
    DexException(int);
    const char* what();
    void bruh();
};

#endif
