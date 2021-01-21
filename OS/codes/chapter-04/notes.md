# Homeworks

1. The CPU will always be in use
2. Total time taken is 10
3. The order does matter because in previous question, when one process is running
the IO, the other process has to wait for the IO to finish and the CPU is blocked.
However, in this scenario, when IO is first initiated, the CPU is blocked so the 
threads cannot do anything but other processes can still use the CPU so it can happen
concurrently.
4. With `SWITCH_ON_END`, the 2nd process needs to wait for the 1st process CPU
to be done first before being able to run so the total time spent is more than
switch on waiting
5. With `IO_RUN_LATER`, the current process will run later while other process
is consuming the CPU so the process that was runnin the IO needs to wait to initiate
the command.
6. However, with `IO_RUN_IMMEDIATE`, we know that the process was running I/O anyway,
so it's better to keep running I/O on that process so that other processes can consume
the CPU while the I/O process is in `WAITING` mode.
