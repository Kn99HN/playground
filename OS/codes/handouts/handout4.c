#include <pthread.h>
#include <stdio.h>

pthread_mutex_t mutex;
pthread_cond_t nonempty = PTHREAD_COND_INITIALIZER;
pthread_cond_t nonfull = PTHREAD_COND_INITIALIZER;

const int BUFFER_SIZE = 5;
int buffer[BUFFER_SIZE];
int count;
int in;
int out;

void *producer(void *ignored) {
  for(int i = 0; i < 5; i++) {
    pthread_mutex_lock(&mutex);
    while(count == 0) {
        pthread_cont_wait(&nonfull, &mutex);
    }
    printf("Before produced: %d\n", count);
    buffer[in] = 1;
    in = (in + 1) % BUFFER_SIZE;
    count++;
    printf("After produced: %d\n", count);
    pthread_cond_signal(&nonempty);
    pthread_mutex_unlock(&mutex);
  }
}

void *consumer(void *ignored) {
  for(int i = 0; i < 5; i++) {
    pthread_mutex_lock(&mutex);
    while(count == 0) {
       pthread_cond_wait(&nonempty, &mutex);
    }
    printf("Before consumed: %d\n", count);
    int consumed = buffer[out];
    out = (out + 1) % BUFFER_SIZE;
    count--;
    printf("After produced: %d\n", count);
    pthread_cond_signal(&nonfull);
    pthread_mutex_unlock(&mutex);
  }
}

int main(int argc, char* argv[]) {
    pthread_mutex_init(&mutex, NULL);
  
    count, in, out = 0;
    pthread_t p1;
    pthread_t p2;
    
    pthread_create(&p1, NULL, producer, NULL);
    pthread_create(&p2, NULL, consumer, NULL);

    pthread_join(p1, NULL);
    pthread_join(p2, NULL);

    return 0;
}


