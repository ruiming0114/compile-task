package analyser.util;

public class NumberUtil {

    public static byte[] int64(Object value) {
        int i = (Integer) value;
        byte[] targets = new byte[8];
        targets[0] = 0;
        targets[1] = 0;
        targets[2] = 0;
        targets[3] = 0;
        targets[7] = (byte) (i & 0xFF);
        targets[6] = (byte) (i >> 8 & 0xFF);
        targets[5] = (byte) (i >> 16 & 0xFF);
        targets[4] = (byte) (i >> 24 & 0xFF);
        return targets;
    }

    public static byte[] int32(Object value) {
        int i = (Integer) value;
        byte[] targets = new byte[4];
        targets[3] = (byte) (i & 0xFF);
        targets[2] = (byte) (i >> 8 & 0xFF);
        targets[1] = (byte) (i >> 16 & 0xFF);
        targets[0] = (byte) (i >> 24 & 0xFF);
        return targets;
    }

    public static byte[] u64(Object i){
        long value = (Long) i;
        byte[] targets = new byte[8];
        targets[7] = (byte) (value & 0xFF);
        targets[6] = (byte) (value >> 8 & 0xFF);
        targets[5] = (byte) (value >> 16 & 0xFF);
        targets[4] = (byte) (value >> 24 & 0xFF);
        targets[3] = (byte) (value >> 32 & 0xFF);
        targets[2] = (byte) (value >> 40 & 0xFF);
        targets[1] = (byte) (value >> 48 & 0xFF);
        targets[0] = (byte) (value >> 56 & 0xFF);
        return targets;
    }
}
