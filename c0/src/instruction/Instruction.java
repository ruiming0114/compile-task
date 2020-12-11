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

    public byte[] byteAdd(byte one,byte[] bytes){
        byte[] res = new byte[bytes.length+1];
        res[0] = one;
        System.arraycopy(bytes, 0, res, 1, bytes.length);
        return res;
    }

    public byte[] generate(){
        switch (operation){
            case nop:
                return new byte[]{0x00};
            case push:
                return byteAdd((byte) 0x01,NumberUtil.int64(value));
            case pop:
                return new byte[]{0x02};
            case popn:
                return byteAdd((byte) 0x03,NumberUtil.int32(value));
            case dup:
                return new byte[]{0x04};
            case loca:
                return byteAdd((byte) 0x0a,NumberUtil.int32(value));
            case arga:
                return byteAdd((byte) 0x0b,NumberUtil.int32(value));
            case globa:
                return byteAdd((byte) 0x0c,NumberUtil.int32(value));
            case load8:
                return new byte[]{0x10};
            case load16:
                return new byte[]{0x11};
            case load32:
                return new byte[]{0x12};
            case load64:
                return new byte[]{0x13};
            case store8:
                return new byte[]{0x14};
            case store16:
                return new byte[]{0x15};
            case store32:
                return new byte[]{0x16};
            case store64:
                return new byte[]{0x17};
            case alloc:
                return new byte[]{0x18};
            case free:
                return new byte[]{0x19};
            case stackalloc:
                return byteAdd((byte) 0x1a,NumberUtil.int32(value));
            case addi:
                return new byte[]{0x20};
            case subi:
                return new byte[]{0x21};
            case muli:
                return new byte[]{0x22};
            case divi:
                return new byte[]{0x23};
            case addf:
                return new byte[]{0x24};
            case subf:
                return new byte[]{0x25};
            case mulf:
                return new byte[]{0x26};
            case divf:
                return new byte[]{0x27};
            case divu:
                return new byte[]{0x28};
            case shl:
                return new byte[]{0x29};
            case shr:
                return new byte[]{0x2a};
            case and:
                return new byte[]{0x2b};
            case or:
                return new byte[]{0x2c};
            case xor:
                return new byte[]{0x2d};
            case not:
                return new byte[]{0x2e};
            case cmpi:
                return new byte[]{0x30};
            case cmpu:
                return new byte[]{0x31};
            case cmpf:
                return new byte[]{0x32};
            case negi:
                return new byte[]{0x34};
            case negf:
                return new byte[]{0x35};
            case itof:
                return new byte[]{0x36};
            case ftoi:
                return new byte[]{0x37};
            case shrl:
                return new byte[]{0x38};
            case setlt:
                return new byte[]{0x39};
            case setgt:
                return new byte[]{0x3a};
            case br:
                return byteAdd((byte) 0x41,NumberUtil.int32(value));
            case brfalse:
                return byteAdd((byte) 0x42,NumberUtil.int32(value));
            case brtrue:
                return byteAdd((byte) 0x43,NumberUtil.int32(value));
            case call:
                return byteAdd((byte) 0x48,NumberUtil.int32(value));
            case ret:
                return new byte[]{0x49};
            case callname:
                return byteAdd((byte) 0x4a,NumberUtil.int32(value));
            case scani:
                return new byte[]{0x50};
            case scanc:
                return new byte[]{0x51};
            case scanf:
                return new byte[]{0x52};
            case printi:
                return new byte[]{0x54};
            case printc:
                return new byte[]{0x55};
            case printf:
                return new byte[]{0x56};
            case prints:
                return new byte[]{0x57};
            case println:
                return new byte[]{0x58};
            case panic:
                return new byte[]{(byte) 0xfe};
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "operation: " + operation + ", value: "+ value +"\n";
    }
}
