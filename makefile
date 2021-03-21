RM = rm -rf
MKDIR = mkdir -p
CXX = g++
CXX_FLAGS = -std=c++14\
			-Os\
			-Wall\
			-Wextra\
			-Wshadow\
			-fsanitize=address\
			-fsanitize=undefined\
			-g\
			-D_GLIBCXX_DEBUG
INC_FLAGS = -I$(inc_dir) -I$(header_dir)
LD_FLAGS = -lgtest

src_dir = src
lib_dir = lib
inc_dir = include
out_dir = out
obj_dir = $(out_dir)/obj
header_dir = $(src_dir)/headers
impl_dir = $(src_dir)/impl
test_dir = test

src = $(wildcard $(src_dir)/*.cpp)
lib_src = $(wildcard $(lib_dir)/*.cpp)
impl_src = $(wildcard $(impl_dir)/*.cpp)
test_src = $(wildcard $(test_dir)/*.cpp)

src_obj = $(src:$(src_dir)/%.cpp=$(obj_dir)/%.o)
lib_obj = $(lib_src:$(lib_dir)/%.cpp=$(obj_dir)/%.o)
impl_obj = $(impl_src:$(impl_dir)/%.cpp=$(obj_dir)/%.o)
test_obj= $(test_src:$(test_dir)/%.cpp=$(obj_dir)/%.o)

target = $(out_dir)/Engimon.out
test_target = $(out_dir)/Engimon_test.out

default: $(test) $(target)

$(obj_dir):
	$(MKDIR) $(obj_dir)

$(obj_dir)/%.o: $(src_dir)/%.cpp
	$(CXX) $(INC_FLAGS) $(CXX_FLAGS) -c -o $@ $<

$(obj_dir)/%.o: $(lib_dir)/%.cpp
	$(CXX) $(INC_FLAGS) $(CXX_FLAGS) -c -o $@ $<

$(obj_dir)/%.o: $(impl_dir)/%.cpp
	$(CXX) $(INC_FLAGS) $(CXX_FLAGS) -c -o $@ $<

$(obj_dir)/%.o: $(test_dir)/%.cpp
	$(CXX) $(INC_FLAGS) $(CXX_FLAGS) -c -o $@ $< $(LD_FLAGS)

$(target): $(src_obj) $(impl_obj) $(lib_obj)
	$(CXX) $(INC_FLAGS) $(CXX_FLAGS) -o $@ $^

$(test_target): $(test_obj) $(impl_obj) $(lib_obj)
	$(CXX) $(INC_FLAGS) $(CXX_FLAGS) -o $@ $^ $(LD_FLAGS)

all: $(test_target) $(target)
target: $(target)
test_target: $(test_target)

build: $(target)
	./$<
run: $(target)
	./$<

test: $(test_target)
	./$<

clean:
	$(RM) $(target) $(obj_dir)/*
