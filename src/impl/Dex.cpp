#include "../headers/Dex.hpp"

#include <algorithm>
#include <cstdlib>
#include <iostream>
#include <string>

using namespace std;

#include "../../include/Parser.hpp"

Dex::Dex() {
    readEngiDex("data/Engimons.csv");
    readSkillDex("data/Skills.csv");
}

Dex::Dex(const string engiData, const string skillData) {
    readEngiDex(engiData);
    readSkillDex(skillData);
}

void Dex::readEngiDex(const string path) {
    Parser p(path, ',');
    vector<vector<string>> engimons = p.parse();

    for (vector<string> engimon : engimons) {
        int i = 0;
        string engiName;
        string engiSlogan;
        string firstSkillName;
        vector<Elements::el> elements;
        for (string metadata : engimon) {
            switch (i) {
                case 0:
                    engiName = metadata;
                    break;
                case 1:
                    engiSlogan = metadata;
                    break;
                case 2:
                    firstSkillName = metadata;
                    break;
                default:  // elements
                    try {
                        elements.push_back(Elements::getElement(metadata));
                    } catch (ElementsException) {
                        throw DexException(1);
                    }
            }
            ++i;
        }

        if (elements.size() == 0) {
            throw DexException(1);
        }

        // hapus duplikat
        sort(elements.begin(), elements.end());
        vector<Elements::el>::iterator it =
            unique(elements.begin(), elements.end());
        elements = vector<Elements::el>(elements.begin(), it);

        try {
            Skill firstSkill(skillDex[firstSkillName]);  // throws out_of_range
            engiDex.emplace(engiName, EngimonSpecies(engiName, engiSlogan,
                                                     elements, firstSkill));
            ++i;
        } catch (out_of_range&) {
            throw DexException(2);
        }
    }
}

void Dex::readSkillDex(const string path) {
    Parser p(path, ',');
    vector<vector<string>> engimons = p.parse();

    for (vector<string> engimon : engimons) {
        int i = 0;
        string skillName;
        unsigned skillBasePower;
        vector<Elements::el> elements;

        for (string metadata : engimon) {
            switch (i) {
                case 0:
                    skillName = metadata;
                    break;
                case 1:
                    skillBasePower = atoi(metadata.c_str());
                    break;
                default:
                    try {
                        elements.push_back(Elements::getElement(metadata));
                    } catch (ElementsException) {
                        throw DexException(1);
                    }
            }
            ++i;
        }
        if (elements.size() == 0 || skillBasePower == 0) {
            throw DexException(0);
        }

        // hapus duplikat
        sort(elements.begin(), elements.end());
        vector<Elements::el>::iterator it =
            unique(elements.begin(), elements.end());
        elements = vector<Elements::el>(elements.begin(), it);

        skillDex.emplace(skillName,
                         Skill(skillName, skillBasePower, 1, elements));
    }

    // return;
}

Skill Dex::getSkill(const string skillname) const {
    try {
        return skillDex.at(skillname);
    } catch (out_of_range&) {
        throw DexException(3);
    }
}

EngimonSpecies Dex::getEngi(const string enginame) const {
    try {
        return engiDex.at(enginame);
    } catch (out_of_range&) {
        throw DexException(4);
    }
}

void Dex::addSkill(string skillname, Skill skill) {
    if (skillDex.count(skillname)) {
        throw DexException(5);
    }
    skillDex.emplace(skillname, skill);
}

void Dex::addEngi(string enginame, EngimonSpecies engimon) {
    if (engiDex.count(enginame)) {
        throw DexException(6);
    }
    engiDex.emplace(enginame, engimon);
}

const unordered_map<string, Skill> Dex::getSkillDex() const { return skillDex; }
const unordered_map<string, EngimonSpecies> Dex::getEngiDex() const {
    return engiDex;
}

DexException::DexException(int id) : msgID(id) {}
const char* DexException::what() { return msg[msgID].c_str(); }
void DexException::bruh() { cout << what() << endl; }
string DexException::msg[] = {"Format file invalid.",
                              "Element invalid.",
                              "First skill Engimon invalid.",
                              "Skill tidak ada.",
                              "Engimon tidak ada.",
                              "Skill sudah ada.",
                              "Engimon sudah ada."};
