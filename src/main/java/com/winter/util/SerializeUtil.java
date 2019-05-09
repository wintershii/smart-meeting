package com.winter.util;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化工具类
 */
public class SerializeUtil {

    public static String serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;

        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            System.out.println(object);
            byte[] bytes = baos.toByteArray();
            oos.close();
            baos.close();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                if ((bytes[i] & 0xff) < 0x10)//0~F前面不零
                    sb.append("0");
                sb.append(Integer.toHexString(0xFF & bytes[i]));
            }
            return sb.toString().toLowerCase();
        } catch (Exception e) {
            System.out.println("序列化失败!");
        }
        return null;
    }


    public static Object unserialize(String string) {
        ByteArrayInputStream bais = null;
        try {

            string = string.toLowerCase();
            byte[] bytes = new byte[string.length() / 2];
            int k = 0;
            for (int i = 0; i < bytes.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
                byte high = (byte) (Character.digit(string.charAt(k), 16) & 0xff);
                byte low = (byte) (Character.digit(string.charAt(k + 1), 16) & 0xff);
                bytes[i] = (byte) (high << 4 | low);
                k += 2;
            }
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("反序列化失败!");
        }
        return null;
    }
}