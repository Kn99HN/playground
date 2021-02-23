#include <stdlib.h>
#include <stdio.h>

int main(void) {
  printf("Current %d\n", 1);
  int rc = fork();
  if(rc == 0) {
    printf("Current %d\n", 2);
  } else if(rc > 0) {
    // wait(NULL);
    printf("Parent %d\n", 3);
  } else {
    printf("Error");
  }
  return 0;
}
