# Process API

Question: What interfaces of OS present for process creation and control? How should
these interfaces be designed to enable powerful functionality, ease of use, and 
high performance?

## The fork() system call
The `fork()` system call is used to create new process. 

In `p1.c`, the process first started running, the process prints out a hello world message included in that message is its 
**process identifier (PID)**. The process calles the `fork()` system call, which the OS provides as a way to create a new process. The process created is *almost* an exact opy of the calling process. That means that to the OS, it looks like there are 2 copies of the program `p1` running, and both are to return from the `fork` sys call. The newly-created process(called the **child**, in contrast to the creating **parent**) doesn't start running at `main()` like you might expect. 

The child isn't an exact copy. Although it now has its own copy of the address space, its own registers, its own PC, and so forth, the value it returns to the caller of **fork()** is different. While the parents receives the PID of the newly-created child, the child receives a return code of zero.

The output of `p1.c` is not **deterministic**. When the child process
is created, there are 2 active processes in the system we care about: the parent
and the child. Assuming a single CPU system, then the child or parent might run
at that point.

The CPU **scheduler** determines which process runs at a given moment in time.
Due to its complexity, we cannot assume which process will run first. This
**non-determinism** leads to some interesting problems, particularly,
**multi-threaded programs**.
