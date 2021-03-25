#ifndef _PARSER_HPP_
#define _PARSER_HPP_

#include <exception>
#include <string>
#include <vector>

class Parser {
private:
    char delim;
    std::string filePath;
    std::vector<std::string> parseLine(std::string) const;

public:
    Parser(std::string, char);
    std::vector<std::vector<std::string>> parse() const;
};

class ParserException : std::exception {
private:
    const int msgID;
    static std::string msg[];

public:
    ParserException(int);
    const char* what();
};

#endif
