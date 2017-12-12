;The Back Row
;switching to procted mode

[bits 16]

switch_to_pm:

cli

lgdt [gdt_descriptor]   ; Load  GDT
mov eax , cr0             
or eax , 0x1              
mov cr0 , eax
jmp  CODE_SEG:init_pm    ; Make a far  jump to 32-bit code
						 
[bits  32]
						
init_pm:
mov ax, DATA_SEG         
mov ds, ax                ; Move segment  registers  to the data  selector  we  defined  in our  GDT
mov ss, ax                 
mov es, ax
mov fs, ax
mov gs, ax
mov ebp , 0x90000         
mov esp , ebp             
call  BEGIN_PM            

