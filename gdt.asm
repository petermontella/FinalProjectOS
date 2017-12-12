
;Global Descriptor Table
;The Back Row

gdt_start:
gdt_null: 
dd 0x0   
dd 0x0
gdt_code: 	; the  code  segment  descriptor
			; base=0x0, limit=0xfffff ,
			
dw 0xffff     
dw 0x0         ;flags
db 0x0         
db  10011010b 
db  11001111b 
db 0x0         
gdt_data: 		;the  data  segment  descriptor
				
dw 0xffff       ; flags
dw 0x0         
db 0x0         
db  10010010b 
db  11001111b 
db 0x0         

gdt_end:         ; for calculating size of GDT

gdt_descriptor:
dw  gdt_end  - gdt_start  - 1   ; Size of our GDT
dd  gdt_start                   ; Start address of GDT
							
CODE_SEG  equ  gdt_code  - gdt_start
DATA_SEG  equ  gdt_data  - gdt_start