#include <stdio.h>
#include <sys/wait.h>

int main() {
  for(int i = 0; i < 2; i++) {
    printf("Current idx is %d\n", i);
    if(i == 1) {
      char *args[] = {"ls", "./", NULL};
      execvp("ls", args);
      printf("Hello this executes after execp\n");
    }
  }
  return 0;
}
