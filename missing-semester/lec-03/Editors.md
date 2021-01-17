# Text Editor (VIM)

## Philosophy of Vim
Vim is a *modal* editor: it has different modes for inserting vs manipulating text. Vim is programmable (with Vimscript and also other languages like Python), and Vim's interface itself is a programming language: keystrokes(with mnemonic names) are commands, and these commands are composable. Vim avoids the use of mouse, because it's too slow; Vim even avoids using arrow keys because it requires too much movement.

## Modal Editing
Vim's design is based on the idea that a lot of programmer time is spent reading, navigating, and making small edits, as opposed to writing long streams of text. For this reason, Vim has multiple operating modes.

- **Normal**: for moving around a file and making edits
- **Insert**: for inserting text
- **Replace**: for replacing text
- **Visual**(plain, line, or block): for selecting blocks of text
- **Command-line**: for running a command 

For instance, `x` in Insert mode will just insert a literal character 'x', but in Normal mode, it will delete the character under the cursor, and in Visual mode, it will delete the selection.

You change mode by pressing `ESC` to switch to Normal mode. From Normal mode, enter Insert mode with `i`, Replace mode with `R`, Visual mode with `v`, Visual Line mode with `V`, Visual Block mode with `Ctrl-V`.

# Basics
## Inserting text
From Normal mode, press `i` to enter Insert mode. Now Vim behaves like any other text editor, until you press `ESC` to return to Normal mode.

## Buffers, tabs and windows
Vim matains a set of oopen files, called "buffers". A Vim session has a number of tabs, each of which has number of windows(split panes). Each window shows a single buffer. Unlike other programs, like web browser, there is not a 1-to-1 correspondence between buffers and windows; windows are merely views. A given buffer may be open in *multiple* windows, even within the same tab.

## Command-line
Command mode can be entered by typing in `:` in Normal mode. Your cursor will jump to the command line at the bottom of screen upon pressing `:`. This mode has many functionalities, including opening, saving, closing files, and quitting Vim.

- `:q`: quit(close window)
- `:w` save("write")
- `:wq` save and quit
- `:e {name of file}` open file for editing
- `:ls` show open buffers
- `:help {topic}` open help

## Vim's interface is a programming language
The most important idea in Vim is that Vim's interface itself is a programming language. Keystrokes are commands, and these commands **compose**. This enable efficient movement and edits, especially once the commands become muscle memory

### Movement
You should spend your time in Normal mode, using movement commands to navigate the buffer. Movements in Vim are also called "nouns", because they refer to chunks of text.
- Basic movement: `hjkl` (left, down, up, right)
- Words: `w`(next word), `b`(beginning of world), `e`(end of world)
- Lines: `0`(beginning of line), `^`(first non-blank character), `$`(end of line)
- Screen: `H`(top of screen), `M`(middle of screen), `L` (bottom of screen)
- Scroll: `Ctrl-u`(up), `Ctril-d`(down)
- File: `gg`(beginning of file), `G`(end of file)
- Line numbers: `:{number}<CR>` or `{number}G`(line {number})
- Misc: `%`(corresponding item)
- Find: `f{character}`, `t{character}`,`F{character}`,`T{character}`
    - find/to forward/backward {character} on the current line
    - `,`/`;` for navigating matches
- Search: `/{regex}`,`n`/`N` for navigating matches

## Selection
Visual modes:
- Visual
- Visual Line
- Visual Block

## Edits
Vim's editing commands are also called "verbs", because verbs act on nouns.
- `i` enter Insert mode
    - but for manipulating/deleting text, want to use something more than backspace
-  `o`/`O` insert line below/above
- `d{motion}` delete {motion}
    - e.g: `dw` is delete word, `d$` is delete to end of line
- `c{motion}` change{motion}
    - e.g: `cw` is change word
- `x` delete character
- `s` substitute character
- Visual mode + manipulation
    - select text, `d` to delete it or `c` to change it
- `u` to undo, `Ctril-r` to redo
- `y` to copy
- `p` to paste

## Counts
You can combine nouns and verbs with a count, which will perform a given action a number of times.

- `3w` move 3 words forward
- `5j` move 5 lines down
