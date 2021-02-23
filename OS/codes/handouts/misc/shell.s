	.section	__TEXT,__text,regular,pure_instructions
	.build_version macos, 10, 15	sdk_version 10, 15, 4
	.globl	_main                   ## -- Begin function main
	.p2align	4, 0x90
_main:                                  ## @main
	.cfi_startproc
## %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$96, %rsp
	movq	___stack_chk_guard@GOTPCREL(%rip), %rax
	movq	(%rax), %rax
	movq	%rax, -8(%rbp)
	movl	$0, -68(%rbp)
	movl	$1, %edi
	leaq	L_.str(%rip), %rsi
	movl	$2, %edx
	movb	$0, %al
	callq	_write
	movq	___stdinp@GOTPCREL(%rip), %rcx
	leaq	-32(%rbp), %rsi
	movq	(%rcx), %rdx
	movq	%rsi, %rdi
	movl	$3, %esi
	movl	%eax, -76(%rbp)         ## 4-byte Spill
	callq	_fgets
	movq	%rax, -88(%rbp)         ## 8-byte Spill
	movb	$0, %al
	callq	_fork
	movl	%eax, -72(%rbp)
	cmpl	$0, -72(%rbp)
	jne	LBB0_2
## %bb.1:
	leaq	-64(%rbp), %rsi
	leaq	-32(%rbp), %rax
	movq	%rax, %rcx
	movq	%rcx, -64(%rbp)
	leaq	L_.str.1(%rip), %rcx
	movq	%rcx, -56(%rbp)
	movq	$0, -48(%rbp)
	movq	%rax, %rdi
	movb	$0, %al
	callq	_execvp
	jmp	LBB0_6
LBB0_2:
	cmpl	$0, -72(%rbp)
	jle	LBB0_4
## %bb.3:
	xorl	%eax, %eax
	movl	%eax, %edi
	callq	_wait
	jmp	LBB0_5
LBB0_4:
	leaq	L_.str.2(%rip), %rdi
	callq	_perror
LBB0_5:
	jmp	LBB0_6
LBB0_6:
	movq	___stack_chk_guard@GOTPCREL(%rip), %rax
	movq	(%rax), %rax
	movq	-8(%rbp), %rcx
	cmpq	%rcx, %rax
	jne	LBB0_8
## %bb.7:
	xorl	%eax, %eax
	addq	$96, %rsp
	popq	%rbp
	retq
LBB0_8:
	callq	___stack_chk_fail
	ud2
	.cfi_endproc
                                        ## -- End function
	.section	__TEXT,__cstring,cstring_literals
L_.str:                                 ## @.str
	.asciz	"$ "

L_.str.1:                               ## @.str.1
	.asciz	"./"

L_.str.2:                               ## @.str.2
	.asciz	"failed to fork"


.subsections_via_symbols
