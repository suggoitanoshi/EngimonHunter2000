RM = rm -rf
MKDIR = mkdir -p
CXX = g++
CXX_FLAGS = -std=c++14\
			-Wshadow\
			-Wall\
			-g\
			-fsanitize=address\
			-fsanitize=undefined\
			-D_GLIBCXX_DEBUG\
			-O2
INC_FLAGS = -I$(inc_dir) -I$(header_dir)

src_dir = src
lib_dir = lib
inc_dir = include
out_dir = out
obj_dir = $(out_dir)/obj
header_dir = $(src_dir)/headers
impl_dir = $(src_dir)/impl

src = $(wildcard $(src_dir)/*.cpp)
lib_src = $(wildcard $(lib_dir)/*.cpp)
impl_src = $(wildcard $(impl_dir)/*.cpp)

objs = $(src:$(src_dir)/%.cpp=$(obj_dir)/%.o)\
	$(lib_src:$(lib_dir)/%.cpp=$(obj_dir)/%.o)\
	$(impl_src:$(impl_dir)/%.cpp=$(obj_dir)/%.o)

target = $(out_dir)/Engimon.out

default: $(target)

$(obj_dir):
	$(MKDIR) $(obj_dir)

$(obj_dir)/%.o: $(src_dir)/%.cpp
	$(CXX) $(CXX_FLAGS) -c -o $@ $< $(INC_FLAGS)
$(obj_dir)/%.o: $(lib_dir)/%.cpp
	$(CXX) $(CXX_FLAGS) -c -o $@ $< $(INC_FLAGS)
$(obj_dir)/%.o: $(impl_dir)/%.cpp
	$(CXX) $(CXX_FLAGS) -c -o $@ $< $(INC_FLAGS)

$(target): $(objs)
	$(CXX) $(CXX_FLAGS) -o $@ $^ $(INC_FLAGS)
run: $(target)
	./$<

all: $(target)

clean:
	$(RM) $(target) $(obj_dir)/*
