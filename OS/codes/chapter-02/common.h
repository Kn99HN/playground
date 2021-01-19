#define __common_h__
#include "stdlib.h"
#include <sys/time.h>
#include <sys/stat.h>
#include <assert.h>


double GetTime() {
    struct timeval t;
    int rc = gettimeofday(&t, NULL);
    assert(rc == 0); //check for return code
    return (double) t.tv_sec + (double) t.tv_usec / 1e6;
}

void Spin(int range) {
    double time = GetTime();
    while((GetTime() - time) < (double) range);
}