package com.winter.util;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化工具类
 */
public class SerializeUtil {

    public static byte[] serialize(Object object) {
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
            return bytes;
        } catch (Exception e) {
            System.out.println("序列化失败!");
        }
        return null;
    }

    public static Object unserialize( byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            System.out.println("反序列化失败!");
        }
        return null;
    }
}