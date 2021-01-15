# The compiler
CC = gcc

# Compiler flags:
# -g adds debugging information to the executable file
# -Wall turns on most, but not all, compiler warnings
CFLAGS = -g -Wall

# The build target executable

cpu: cpu common.h
	$(CC) $(FLAGS) -o cpu CPU.c

mem: mem common.h
	$(CC) $(FLAGS) -o mem mem.c

thread: threads common_threads.h
	$(CC) $(FLAGS) -o threads threads.c -pthread

clean:
	$(RM) -f cpu mem threads
