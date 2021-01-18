# The Abstraction: The Process

OS provides the *process*. The definition of a process, information, is a 
**running program**. The program itself is lifeless thing: it just sits there
on the disk, a bunch of instructions, waiting to spring into action. It's the 
OS that takes these bytes and gets them running, transforming the prorgram into
something useful.

The OS creates the illusion by **virtualizing** the CPU. By running one process,
then stopping it and running another, and so forth, the OS can promote the 
illusion that many virtual CPUs exist when there is only 1 physical CPU (or a few).
This technique is called **time sharing** of the CPU, allows users to run many
concurrent processes as they would like; the potential cost is performance, each
will run more slowly if the CPU(s) must be shared.

The OS need both some low-level machinery **mechanism**; mechanisms are low-level
methords or protocols that implement needed a piece of funtionality. On top of 
these mechanisms resides some of the intelligence in the OS, in the form of 
**policies**. Policies are algorithms for making some kind of decision in the
within the OS. For example, given a number of possible programs to run on a CPU,
which program should the OS run? A **scheduling policy** in the OS will make
this decision.

**Time sharing** is a basic technique used by an OS to share a resource. By allowing
the resource to be used for a little while by one entity and then by another,
the resource in question can be shared by many. The counterpart is **space sharing**
where a resource is divided (in space) among those who wish to use it.

## The Abstraction: A Process

A running program abstraction by the OS is a **process**. To understand this,
we must understand **machine state**: what a program can read or update when 
it is running. At any given time, what parts of the machine are important
to the execution of this program?

A component of machine state that comprises a process is its *memory*. Instructions
lie in memory; the data that the running program reads and writes sits in memory
as well. Thus the memory that the process can address (called **address space**)
is part of the process.

Parts of the process's machine state are *register*; many instructions explicitly
read or update registers. There are some special registers that form part of this
machine state. For example, the **program counter** tells us which instruction 
of the process is currently executed, a **stack pointer** and associated **frame
pointer** are used to manage the stack for function parameters, local variables
and return addresses.

### Separate Policy and Mechanism
In many OS, a common design paradigm is to separate high-level policies from
their low-level mechianisms. Mechanism provides the answer to a *how* question
about a system; for instance, *how* does an OS perform a context switch? The
policy provides the answer to a *which* question; for example, *which* process
should the OS run right now?

## Process API
These APIs, in some form, are available on any modern operating system.

### Create
An OS must include some method to create new processes. When you type a command
into the shell, or double-click on an application icon, the OS is invoked
to create a new process to run the program you have indicated.

### Destroy
As there is an interface for process creation, systems also provide an interface
to destroy processes forcefully. Many processes will run and exit by themselves
when complete. When they don't, the user may wish to kill them, and thus an interface
to halt a runaway process is quite useful

### Wait
Sometimes it's useful to wait for a process to stop running; thus some kind of
waiting interface is often provided.

### Miscellaneous Control
Other than killing or waiting for a process, there are sometimes other controls
that are possible. For example, most OS provide some kind of method to suspend
a process (stop it from running for a while) and resume.

### Status
There are usually interfaces to get some status information about a process,
such as how long it has run for, or what state it's in.
