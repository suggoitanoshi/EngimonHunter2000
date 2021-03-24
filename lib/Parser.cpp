#include "../include/Parser.hpp"

#include <fstream>
#include "iostream"

Parser::Parser(std::string _filePath, char _delim)
    : delim(_delim), filePath(_filePath) {}

std::vector<std::vector<std::string>> Parser::parse() const {
    std::vector<std::vector<std::string>> parseResult;
    std::fstream input;
    input.open(filePath);

    if (!input.fail()) {
        if (input.is_open()) {
            /// menyimpan baris dari file yang lagi mau diparse
            std::string line;
            std::string currentSubstr = "";
            std::vector<std::string> tmpRes;

            getline(input, line);

            while (getline(input, line)) {
                for (std::string::iterator c = line.begin(); c != line.end();
                     ++c) {
                    if (*c == delim) {
                        tmpRes.push_back(currentSubstr);
                        currentSubstr = "";
                    } else {
                        currentSubstr += *c;
                    }
                }
                // push element terakhir ke tmpRes
                tmpRes.push_back(currentSubstr);
                // tambah ke result
                parseResult.push_back(tmpRes);
                // clear tmpRes
                tmpRes.clear();
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
