#include <gtest/gtest.h>

#include "../include/Parser.hpp"

TEST(Parser, ParseFileNotFound) {
    Parser p1("FileIniGaAda", ',');

    EXPECT_THROW(p1.parse(), ParserException);
}

TEST(Parser, ParseFileExists) {
    Parser p2("data/Engimons.csv", ',');
    std::vector<std::vector<std::string>> res;
    ASSERT_NO_THROW(p2.parse());
    res = p2.parse();
    ASSERT_EQ(res.size(), 1);
    ASSERT_EQ(res[0].size(), 4);
    EXPECT_EQ(res[0][0], "Picakhu");
    EXPECT_EQ(res[0][1], "Pica Pica khu!");
    EXPECT_EQ(res[0][2], "THUNDAAA");
    EXPECT_EQ(res[0][3], "ELECTRIC");
}
