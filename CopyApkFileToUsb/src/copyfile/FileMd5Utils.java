package copyfile;

import org.w3c.dom.html.HTMLOptGroupElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 2020-01-16
 * 计算文件的MD5值
 */
public class FileMd5Utils {

    protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    protected static MessageDigest messageDigest = null;

    static {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String getFileMD5String(String fileName) throws IOException {
        File f = new File(fileName);
        return getFileMD5String(f);
    }

    /**
     * 当文件大小超过1g时,而系统内存不足时,会计算失败
     */
    public static String getFileMD5String(File file) throws IOException {

        long step = 412083539;

        MappedByteBuffer byteBuffer;

        if (file.length() < step) {
            FileInputStream in = new FileInputStream(file);
            FileChannel ch = in.getChannel();
            byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            messageDigest.update(byteBuffer);

        } else {
            long before = 0;
            long position = step;
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

                FileChannel ch = randomAccessFile.getChannel();
            while (position < file.length()) {
                byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, before, step);
                messageDigest.update(byteBuffer);
                before = position;
                if (position + step > file.length()) {
                    step = file.length() - position;
                    position = file.length();
                } else {
                    position += step;
                }

            }
            byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, before, step);
            messageDigest.update(byteBuffer);

        }

        System.out.println("========================end============");

        return bufferToHex(messageDigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }


    /**
     * 适用于文件超过1g
     */
    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        String md5code = bigInt.toString(16);
        //前面为0时补零
        int lenth = md5code.length();
        for (int i = 0; i < 32 - lenth; i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
