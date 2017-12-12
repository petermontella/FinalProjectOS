; A boot  sector  that  enters 32-bit  protected  mode.
;The Back Row
[org 0x7c00]
mov bp, 0x9000          ; Set  the  stack.
mov sp, bp
mov si, MSG_REAL_MODE
call  print_string
call  switch_to_pm      
jmp $
%include "C:\Users\Bobby\Desktop\pString.asm"
%include "C:\Users\Bobby\Desktop\gdt.asm"
%include "C:\Users\Bobby\Desktop\32pmode.asm"
%include "C:\Users\Bobby\Desktop\switch_to_pm.asm"
[bits  32]

; 32 bit protected mode below.
BEGIN_PM:
mov ebx , MSG_PROT_MODE
call  print_string_pm    
jmp $                      
; Global  variables
MSG_REAL_MODE   db "Started  in 16-bit  Real  Mode", 0
MSG_PROT_MODE   db "Successfully  landed  in 32-bit  Protected  Mode", 0
; Bootsector  padding
times  510-($-$$) db 0
dw 0xaa55