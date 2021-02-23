#include <pthread.h>
#include <stdio.h>

const int BUFFER_SIZE = 5;
int buffer[BUFFER_SIZE];
int count = 0;
int in = 0;
int out = 0;

void *producer(void *ignored) {
    for(int i = 0; i < 10; i++) {
        while(count == BUFFER_SIZE) {
            // do nothing
        }
        printf("Before producing: %d\n", count);
        buffer[in] = 1;
        in = (in + 1) % BUFFER_SIZE;
        count++;
        printf("After producing: %d\n", count);
    }
}

void *consumer(void *ignored) {
    for(int i = 0; i < 10; i++) {
        while(count == 0) {
            // do nothing
        }
        printf("Before consuming: %d\n", count);
        int consumed = buffer[out];
        out = (out + 1) % BUFFER_SIZE;
        count--;
        printf("After consuming: %d\n", count);
    }
}

int main(int argc, char *argv[]) {
  pthread_t p1;
  pthread_t p2;

  pthread_create(&p1, NULL, producer, NULL);
  pthread_create(&p2, NULL, consumer, NULL);

  pthread_join(p1, NULL);
  pthread_join(p2, NULL);

  return 0;
}
