#include <stdio.h>
#include <stdint.h>

uint64_t f(uint64_t* ptr);
uint64_t g(uint64_t a);
uint64_t* q;

int main(void) {
  uint64_t x = 0;
  uint64_t arg = 8;

  x = f(&arg);

  printf("x: %lu\n", x);
  printf("dereference q: %lu\n", *q);

  return 0;
}

uint64_t f(uint64_t* ptr) {
  uint64_t x = 0;
  x = g(*ptr);
  return x + 1;
}

uint64_t g(uint64_t a) {
  uint64_t x = 2 * a;
  q = &x;
  return x;
}
