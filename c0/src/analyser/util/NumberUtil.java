package analyser.util;

import java.math.BigInteger;

public class NumberUtil {

    private static final char[] HEXES = {
            '0', '1', '2', '3',
            '4', '5', '6', '7',
            '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f'
    };

    public static String bytes2Hex(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            hex.append(HEXES[(b >> 4) & 0x0F]);
            hex.append(HEXES[b & 0x0F]);
            hex.append(" ");
        }
        hex.deleteCharAt(hex.length()-1);
        return hex.toString();
    }

    public static String int64(Object value) {
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
        return bytes2Hex(targets);
    }

    public static String int32(Object value) {
        int i = (Integer) value;
        byte[] targets = new byte[4];
        targets[3] = (byte) (i & 0xFF);
        targets[2] = (byte) (i >> 8 & 0xFF);
        targets[1] = (byte) (i >> 16 & 0xFF);
        targets[0] = (byte) (i >> 24 & 0xFF);
        return bytes2Hex(targets);
    }

    public static String u64(BigInteger i){
        byte[] targets = i.toByteArray();
        StringBuilder res = new StringBuilder();
        for (int j=8;j>targets.length;j--){
            res.append("00 ");
        }
        res.append(bytes2Hex(targets));
        return res.toString();
    }

    public static String u32(BigInteger i){
        byte[] targets = i.toByteArray();
        StringBuilder res = new StringBuilder();
        for (int j=4;j>targets.length;j--){
            res.append("00 ");
        }
        res.append(bytes2Hex(targets));
        return res.toString();
    }
}
