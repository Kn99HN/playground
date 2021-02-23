#include <pthread.h>
#include <stdio.h>

int x;

void *f() {
  x = 1;
  pthread_exit(NULL);
}

void *g() {
  x = 2;
  pthread_exit(NULL);
}

int main(int argc, char* argv[]) {
  pthread_t p1;
  pthread_t p2;
  int tid1 =  pthread_create(&p1, NULL, f, NULL);
  int tid2 = pthread_create(&p2, NULL, g, NULL);

  pthread_join(p1, NULL);
  pthread_join(p2, NULL);

  printf("%d\n", x);
}
