# Chapter 2: Introduction to Operating Systems

The Operating System is in charge of making sure the system operates correctly and efficiently in an easy-to-use manner.
The general way this is done is through **virtualization**. The OS takes a **physical** resource (such as the processor, or memory, or a disk) and transforms it into a more general and powerful **virtual** form of itself. This is often referred to as **virtual machine**.

Virtualization allows many programs to run(thus sharing the CPU) and many programs to concurrently access their own instructions and data (thus sharing memory), and many programs to access devices(thus sharing disks and so forth), the OS is also known as a **resource manager**. Each of the CPU, memory, and disk is a **resource** that the OS manages.

## Virtualizing the CPU

The operating system, with help from the hardware, is in charge of the **illusion**, i.e the illusion that the system has a very large number of virtual CPUs. Turning a single CPU(or small set of them) into a seemingly infinite number of CPUs and thus allowing may programs to seemingly run at once is called **virtualizing the CPU**.

To determine which program to run at a particular time, OS has a policy, which we will look into later on.

## Virtualizing Memory
The model of **physical memory** presented by modern machines is very simple. Memory is an array of bytes, to **read** memory, one must specify an address to access the data stored there. To **write**(or **update**) memory, one must also specify the data to be written to the given address.

PID: Process identifier, which is unique per running process.

 Each process accesses its own private **virtual address space**(or address space), which the OS somehow maps onto the physical memory of the machine. A memory reference within one running program does not affect the address space of other processes (or the OS itself); as far as the running program is concerned, it has physical memory all to itself.

 ## Concurrency
 When we run `./threads LOOPS` with different values for loops, we expect the counter to be 2 * `LOOPS`. However, life is not so simple. It turns out that an incrementation takes three instructions: one to load the value from memory into register, one to increment it, and one to store it back into memory. Because they don't execute **atomically**, strange things happen. We end up with lossy result. 
