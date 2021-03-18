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
INC_FLAGS = -I$(inc_dir)

src_dir = src
lib_dir = lib
inc_dir = include
out_dir = out
obj_dir = $(out_dir)/obj

src = $(wildcard $(src_dir)/*.cpp)
lib_src = $(wildcard $(lib_dir)/*.cpp)

objs = $(src:$(src_dir)/%.cpp=$(obj_dir)/%.o) $(lib_src:$(lib_dir)/%.cpp=$(obj_dir)/%.o)

target = $(out_dir)/Engimon

default: clean $(target)

$(obj_dir):
	$(MKDIR) $(obj_dir)

$(obj_dir)/%.o: $(src_dir)/%.cpp $(obj_dir)
	$(CXX) $(CXX_FLAGS) -c -o $@ $< $(INC_FLAGS)
$(obj_dir)/%.o: $(lib_dir)/%.cpp $(obj_dir)
	$(CXX) $(CXX_FLAGS) -c -o $@ $< $(INC_FLAGS)

$(target): $(objs)
	$(CXX) $(CXX_FLAGS) -o $@ $^ $(INC_FLAGS)
run: $(target)
	./$<

target: $(target)

clean:
	$(RM) $(objs) $(target)
