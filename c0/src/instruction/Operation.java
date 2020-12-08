package instruction;

public enum Operation {
    nop,
    push,pop,popn,
    dup,
    loca,
    arga,
    globa,
    load8,load16,load32,load64,
    store8,store16,store32,store64,
    alloc,
    free,
    stackalloc,
    addi,subi,muli,divi,
    addf,subf,mulf,divf,divu,
    shl,shr,shrl,
    and,or,xor,not,
    cmpi,cmpu,cmpf,
    negi,negf,
    itof,ftoi,
    setlt,setgt,
    br,brfalse,brtrue,
    call,ret,callname,
    scani,scanf,scanc,
    printi,printf,printc,prints,println,
    panic
}