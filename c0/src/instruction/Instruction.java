package instruction;

import analyser.util.NumberUtil;

public class Instruction {
    public Operation operation;
    public Object value;

    public Instruction(Operation operation){
        this.operation = operation;
    }

    public Instruction(Operation operation,Object value){
        this.operation = operation;
        this.value = value;
    }

    public String generate(){
        StringBuilder stringBuilder = new StringBuilder();
        switch (operation){
            case nop:
                stringBuilder.append("00");
                break;
            case push:
                stringBuilder.append("01 ");
                stringBuilder.append(NumberUtil.int64(value));
                break;
            case pop:
                stringBuilder.append("02");
                break;
            case popn:
                stringBuilder.append("03 ");
                stringBuilder.append(NumberUtil.int32(value));
                break;
            case dup:
                stringBuilder.append("04");
                break;
            case loca:
                stringBuilder.append("0a ");
                stringBuilder.append(NumberUtil.int32(value));
                break;
            case arga:
                stringBuilder.append("0b ");
                stringBuilder.append(NumberUtil.int32(value));
                break;
            case globa:
                stringBuilder.append("0c ");
                stringBuilder.append(NumberUtil.int32(value));
                break;
            case load8:
                stringBuilder.append("10");
                break;
            case load16:
                stringBuilder.append("11");
                break;
            case load32:
                stringBuilder.append("12");
                break;
            case load64:
                stringBuilder.append("13");
                break;
            case store8:
                stringBuilder.append("14");
                break;
            case store16:
                stringBuilder.append("15");
                break;
            case store32:
                stringBuilder.append("16");
                break;
            case store64:
                stringBuilder.append("17");
                break;
            case alloc:
                stringBuilder.append("18");
                break;
            case free:
                stringBuilder.append("19");
                break;
            case stackalloc:
                stringBuilder.append("1a ");
                stringBuilder.append(NumberUtil.int32(value));
                break;
            case addi:
                stringBuilder.append("20");
                break;
            case subi:
                stringBuilder.append("21");
                break;
            case muli:
                stringBuilder.append("22");
                break;
            case divi:
                stringBuilder.append("23");
                break;
            case addf:
                stringBuilder.append("24");
                break;
            case subf:
                stringBuilder.append("25");
                break;
            case mulf:
                stringBuilder.append("26");
                break;
            case divf:
                stringBuilder.append("27");
                break;
            case divu:
                stringBuilder.append("28");
                break;
            case shl:
                stringBuilder.append("29");
                break;
            case shr:
                stringBuilder.append("2a");
                break;
            case and:
                stringBuilder.append("2b");
                break;
            case or:
                stringBuilder.append("2c");
                break;
            case xor:
                stringBuilder.append("2d");
                break;
            case not:
                stringBuilder.append("2e");
                break;
            case cmpi:
                stringBuilder.append("30");
                break;
            case cmpu:
                stringBuilder.append("31");
                break;
            case cmpf:
                stringBuilder.append("32");
                break;
            case negi:
                stringBuilder.append("34");
                break;
            case negf:
                stringBuilder.append("35");
                break;
            case itof:
                stringBuilder.append("36");
                break;
            case ftoi:
                stringBuilder.append("37");
                break;
            case shrl:
                stringBuilder.append("38");
                break;
            case setlt:
                stringBuilder.append("39");
                break;
            case setgt:
                stringBuilder.append("3a");
                break;
            case br:
                stringBuilder.append("41 ");
                stringBuilder.append(NumberUtil.int32(value));
                break;
            case brfalse:
                stringBuilder.append("42 ");
                stringBuilder.append(NumberUtil.int32(value));
                break;
            case brtrue:
                stringBuilder.append("43 ");
                stringBuilder.append(NumberUtil.int32(value));
                break;
            case call:
                stringBuilder.append("48 ");
                stringBuilder.append(NumberUtil.int32(value));
                break;
            case ret:
                stringBuilder.append("49");
                break;
            case callname:
                stringBuilder.append("4a ");
                stringBuilder.append(NumberUtil.int32(value));
                break;
            case scani:
                stringBuilder.append("50");
                break;
            case scanc:
                stringBuilder.append("51");
                break;
            case scanf:
                stringBuilder.append("52");
                break;
            case printi:
                stringBuilder.append("54");
                break;
            case printc:
                stringBuilder.append("55");
                break;
            case printf:
                stringBuilder.append("56");
                break;
            case prints:
                stringBuilder.append("57");
                break;
            case println:
                stringBuilder.append("58");
                break;
            case panic:
                stringBuilder.append("fe");
                break;
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "operation: " + operation + ", value: "+ value +"\n";
    }
}
