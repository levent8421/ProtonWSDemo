package com.test.websocket.server.util;

import java.util.Arrays;

/**
 * Create by Levent8421
 * Date: 2022/2/23 13:45
 * ClassName: ByteUtils
 * Description:
 * 字节操作工具类
 *
 * @author levent8421
 */
public class ByteUtils {
    public static final byte HEX_80 = (byte) 0x80;

    public static byte intToByte(int n) {
        return (byte) (n & 0xFF);
    }

    public static int byteToInt(byte n) {
        return n & 0xFF;
    }

    public static String byteHex(byte b) {
        int i = byteToInt(b);
        String s = "00" + Integer.toHexString(i);
        return s.substring(s.length() - 2).toUpperCase();
    }

    public static String byteHex(byte[] bytes, int offset, int len) {
        if (bytes == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = offset; i < offset + len; i++) {
            byte b = bytes[i];
            sb.append(byteHex(b))
                    .append(' ');
        }
        return sb.toString();
    }

    public static String byteHex(byte[] bytes) {
        return byteHex(bytes, 0, bytes.length);
    }

    public static int bytesToInt(byte[] src) {
        return bytesToInt(src, 0, 4);
    }

    public static int bytesToInt(byte[] src, int offset, int len) {
        int val = 0;
        int pos = 0;
        len = Math.min(len, 4);
        len = Math.min(len, src.length - offset);
        while (pos < len) {
            val |= ((src[offset + pos] & 0xFF) << (8 * pos));
            pos++;
        }
        return val;
    }

    public static void intToBytes(int val, byte[] dst, int offset) {
        intToBytes(val, dst, offset, 4);
    }

    public static void intToBytes(int val, byte[] dst, int offset, int len) {
        int pos = 0;
        len = Math.min(len, 4);
        len = Math.min(len, dst.length - offset);
        while (pos < len) {
            int byteOff = pos * 8;
            byte itemVal = (byte) ((val >> byteOff) & 0xFF);
            dst[offset + pos] = itemVal;
            pos++;
        }
    }

    public static byte[] intToBytes(int val) {
        byte[] bts = new byte[4];
        intToBytes(val, bts, 0);
        return bts;
    }

    public static byte[] floatToBytes(float Value) {
        int accum = Float.floatToRawIntBits(Value);
        byte[] byteRet = new byte[4];
        byteRet[0] = (byte) (accum & 0xFF);
        byteRet[1] = (byte) ((accum >> 8) & 0xFF);
        byteRet[2] = (byte) ((accum >> 16) & 0xFF);
        byteRet[3] = (byte) ((accum >> 24) & 0xFF);
        return byteRet;
    }

    public static float bytesToFloat(byte[] bytes, int offset, int len) {
        return Float.intBitsToFloat(bytesToInt(bytes, offset, len));
    }

    public static double bytesToDouble(byte[] bytes) {
        long l = bytesToLong(bytes);
        return Double.longBitsToDouble(l);
    }

    public static long bytesToLong(byte[] bytes) {
        return (0xffL & (long) bytes[0]) | (0xff00L & ((long) bytes[1] << 8)) | (0xff0000L & ((long) bytes[2] << 16)) | (0xff000000L & ((long) bytes[3] << 24))
                | (0xff00000000L & ((long) bytes[4] << 32)) | (0xff0000000000L & ((long) bytes[5] << 40)) | (0xff000000000000L & ((long) bytes[6] << 48)) | (0xff00000000000000L & ((long) bytes[7] << 56));
    }

    public static byte[] doubleToBytes(double data) {
        long intBits = Double.doubleToLongBits(data);
        return longToBytes(intBits);
    }

    public static byte[] longToBytes(long data) {
        byte[] bytes = new byte[8];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data >> 8) & 0xff);
        bytes[2] = (byte) ((data >> 16) & 0xff);
        bytes[3] = (byte) ((data >> 24) & 0xff);
        bytes[4] = (byte) ((data >> 32) & 0xff);
        bytes[5] = (byte) ((data >> 40) & 0xff);
        bytes[6] = (byte) ((data >> 48) & 0xff);
        bytes[7] = (byte) ((data >> 56) & 0xff);
        return bytes;
    }

    public static int hexCharsToInt(String str, int offset, int length) {
        return Integer.parseUnsignedInt(str.substring(offset, offset + length), 16);
    }

    public static byte hexCharsToByte(String str, int offset, int length) {
        return (byte) (Integer.parseUnsignedInt(str.substring(offset, offset + length), 16) & 0xFF);
    }

    public static int calcSum(byte[] data) {
        int sum = 0;
        for (byte b : data) {
            sum += b & 0xFF;
        }
        return sum;
    }

    public static byte[] subArray(byte[] data, int start, int length) {
        return Arrays.copyOfRange(data, start, start + length);
    }
}
