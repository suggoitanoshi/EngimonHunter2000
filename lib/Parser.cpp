#include "../include/Parser.hpp"

#include <fstream>

Parser::Parser(std::string _filePath, char _delim)
    : delim(_delim), filePath(_filePath) {}

std::vector<std::string> Parser::parseLine(std::string line) const {
    std::string currentSubstr;
    std::vector<std::string> parseResult;

    if (*(line.end()-1) == '\r') {
        line.pop_back();
    }

    for (std::string::iterator c = line.begin(); c != line.end(); ++c) {
        if (*c == delim) {
            parseResult.push_back(currentSubstr);
            currentSubstr = "";
        } else {
            currentSubstr += *c;
        }
    }

    parseResult.push_back(currentSubstr);
    return parseResult;
}

std::vector<std::vector<std::string>> Parser::parse() const {
    std::vector<std::vector<std::string>> parseResult;
    std::fstream input;
    input.open(filePath);

    if (!input.fail()) {
        if (input.is_open()) {
            /// menyimpan baris dari file yang lagi mau diparse
            std::string line;

            getline(input, line);  // skip first line

            while (getline(input, line)) {
                parseResult.push_back(this->parseLine(line));
            }
        } else {
            // error saat buka file
            throw ParserException(1);
        }
    } else {
        // error saat baca file
        throw ParserException(0);
    }

    return parseResult;
}

ParserException::ParserException(int id) : msgID(id) {}
const char* ParserException::what() { return msg[msgID].c_str(); }
std::string ParserException::msg[] = {"Terjadi kesalahan saat membaca file.",
                                      "Gagal membuka file."};
