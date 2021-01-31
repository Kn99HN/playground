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

## The wait() system call
Sometimes it's useful for a parent to wait for a child process to finish what is 
has been doing. This task is done with the `wait()` sys call. 

In `p2.c`, the parent process calls `wait()` to delay its execution until the child finishes
executing. When the child is done `wait()` returns to the parent.

Adding `wait()` call to the code makes it deterministic. 

If the child runs first then it will print first then the parent. If the parent runs 
first, then `wait()` is called and it won't return until the child has run and exited.


## The `exec()` system call
A final and important piece of the process creation API is the `exec()` system call.
This system call is useful when you want to run a program that is different from 
the calling program. Often, we want to run a different program, `exec()` does just 
that.

The child process calls `execvp()` in order to run program wc, which is the word 
counting program. In fact, it runs `wc` on the source file `p3.c`, thus telling us 
how many lines, words and bytes are found in this file

The `fork()` system call is strange; its partner in crime, `exec()` is not so normal 
either. What it does; given the name of an executable (e.g wc), and some arguments 
(e.g: p3.c), it **loads** code (and static data) from that executable and overwrite
its current code segment (and current static data) with it; the heap and stack 
and other parts of the memory space of the program are re-initialized. Then the OS
simply runs that program, passing in any arguments as the `argv` of that process. 
Thus, it does **not create a new process**; rather, it transforms the currently 
running program (formerly p3) into a different running program (wc). After 
the `exec()` in the child, it's as if `p3.c` never ran; a successful call to 
`exec()` never returns

## Why? Motivating the API
Why build such an odd interface to what should be the simple act of creating a new 
process. It turns out, the separation of `fork()` and `exec()` is essential in 
building a `UNIX` shell, because it lets the shell run code after the call 
to `fork()` but *before* the call to `exec()`. This code can alter the 
environment of the about-to-be-run program, and thus enables a variety of interesting
features to be readily built.

The shell is an user program. It shows you a prompt and then waits for you 
to type something into it. You then type a command into it; in most cases, 
the shell figures out where in the file system the executable resides, calls 
`fork()` to create a new child process to run the command, calls some variant
of `exec()` to run the command, and then waits for the command to complete by
calling `wait()`. When the child completes, the shell returns from `wait()`
and prints out a prompt again, ready for your next command.

The separation of `fork()` and `exec()` allows the shell to do a whole bunch 
of useful things rather easily. For example:

```shell
wc p3.c > newfile.txt
```

wc is **redirected** into the output file `newfile.txt`. When the child is created,
before calling `exec()`, the shell closes **standard output** and opens the file
`newfile.txt`. Any output from the soon-to-be running program `wc` are sent to the 
file instead of the screen.

Redirection works due to assumption about how the OS manages the file descriptors.
Specifically, UNIX systems start looking for free file descriptors at zero. In this 
case, STDOUT_FILENO will be first available one and get assigned when `open()`
is called. Subsequent writes by child process to stdard output file descriptor,
by routines such as `printf()`, will then be routed transparently to the newly-opened
file instead of the screen.

When `p4` is run, it looks as if nothing happened; the shell just prints the 
command prompt and is immediately ready for your next command. However, this is 
not the case, the program p4 did indeed call `fork()` to create a new child,
then run the `wc` program via call to `execvp()`. You don't see output printed 
because it has been redirected to the file `p4.output`.

UNIX pipes are implemented in a similar way, but with the `pipe()` system call.
In this case, the output of one process is connected to an in-kernal **pipe**
(i.e queue), and the input of another process is connected to that same pipe;
thus, the output of one process seamlessly is used as input to the next, and long,
and useful chains of commands can be strung together. As a simple example, consider
looking for a word in the file and then couting how many times said word occurs; 
with pipes and the utilities `grep` and `wc`, it's easy:

```bash
grep -o foo file | wc -l
```

## Process control and users
Beyond `fork(), exec() and wait()` there are lot of other interfaces for interacting
with processes in UNIX systems. The `kill()` system call is used to send **signals**
to a process, including directives to pause, die, and other useful imperatives. 
For convenience, in most UNIX shells, certain keystroke combinations are configured
to deliver a specific signal to the currently running process; for example, 
control-c sends a SIGINT(interrupt) to the process (normally terminating it) and 
ctrl-z sends a SIGTSTP (stop) signal thus pausing the process in mid-execution.

The entire signals sybsystems provides a rich infrastructure to deliver external 
events to processes, and ways to send signals to individual processes, and ways to 
send signals to individual processes as well as the entire **process groups**. 
To use this form of communication, a process should use the `signal()` system 
call to "catch" various signals; doing so ensures that when a particular signal
is delivered to a process, it'll suspend its normal execution and run a 
particular piece of code in response to the signal.

Systems we use have multiple people using them at the same time; if one of these 
people can arbitrarily send signals such as SIGINT, the usability nad security
of the system will be compromised. As a result, modern systems includes a strong
conception of **user**. The user may then launch one or many processes, and exercise
full control over them. User can usually only control their own processes. It's 
the OS's job to allocate the resources.

### superuser
A system generally needs an user who can **administer** the systems, and is not 
limited in the way most users are. Such a user should be able to kill an arbitrary
process, In UNIX-based systems, these specialties are given to the **superuser**
(sometimes called **root**). It's recommend to be a normal user unless you need to.

