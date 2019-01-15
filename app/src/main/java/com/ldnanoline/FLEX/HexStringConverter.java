package com.ldnanoline.FLEX;

public class HexStringConverter {
    public static byte[] toByteArray(String HexString)
    {
        int NumberChars = HexString.length();
        byte[] bytes = new byte[NumberChars / 2];
        for (int i = 0; i < NumberChars; i += 2)
        {
            bytes[i / 2] = (byte) Byte.valueOf(HexString.substring(i, 2), 16);
        }
        return bytes;
    }
}
