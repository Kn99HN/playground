#include <stdio.h>
#include <sys/wait.h>
# define MAX 3  
int main() {
    char *command[MAX];
    write(1, "$ ", 2);
    fgets(command, MAX, stdin);
    int pid = (int) fork();
    if(pid == 0) {
        char *args[] = {command, "./", NULL};
        execvp(command, args);
    } else if (pid > 0) {
        wait(0);
    } else {
        perror("failed to fork");
    }
    return 0;
}
