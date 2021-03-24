#include <gtest/gtest.h>

#include "../include/Parser.hpp"

TEST(Parser, ParseNoFile) {
    Parser p1("FileIniGaAda", ',');

    EXPECT_THROW(p1.parse(), ParserException);
}

TEST(Parser, ParseFile) {
    Parser p2("data/Engimons.csv", ',');
    std::vector<std::vector<std::string>> res;
    ASSERT_NO_THROW(p2.parse());
    res = p2.parse();
    EXPECT_EQ(res[0][0], "Picakhu");
    EXPECT_EQ(res[0][1], "Pica Pica khu!");
    EXPECT_EQ(res[0][2], "THUNDAA");
    EXPECT_EQ(res[0][3], "ELECTRIC");
}
