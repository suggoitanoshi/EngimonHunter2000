/**
 * Skill.hpp
 * y e e wangy wangy
 *
 * header berisi definisi atribut serta metode dari class Skill
 */

#ifndef _SKILL_HPP_
#define _SKILL_HPP_

#include <string>
#include <vector>

#include "Elements.hpp"

/**
 * Class untuk menyimpan skill yang (dapat) dimiliki Engimon
 */
class Skill {
  protected:
    std::string name;
    unsigned basePower;
    unsigned masteryLevel;
    std::vector<Elements> elements;  // elemen yang dapat mempelajari skill ini

  public:
    // constructors
    Skill(const std::string, const unsigned _basePower,
          const unsigned _masteryLevel, const std::vector<Elements>&);
    Skill(const std::string, const unsigned _basePower,
          const unsigned _masteryLevel, const Elements);
    Skill(const Skill&);

    // getters
    std::string getName() const;
    unsigned getBasePower() const;
    unsigned getMasteryLevel() const;
    std::vector<Elements> getElements() const;

    // setters
    void setMasteryLevel(unsigned);
};

class SkillExp {
   private:
    const int msgID;
    static std::string msg[];

   public:
    SkillExp(int);
    void what() const;
};

#endif
