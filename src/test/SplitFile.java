package test;

import java.io.*;

/**
 * 将文件按照指定行数分割成新文件
 */
public class SplitFile {
    public static void main(String[] args) {

        readFile("C:\\Users\\20313\\Desktop\\wangtong\\yueping.txt",
                "C:\\Users\\20313\\Desktop\\wangtong\\yueping\\yuegping",
                ".txt",60);
        readFile("C:\\Users\\20313\\Desktop\\wangtong\\yingping.txt",
                "C:\\Users\\20313\\Desktop\\wangtong\\yingping\\yingping",
                ".txt",60);
    }


    /**
     * 读取文件
     *
     * @param sourceFilePath 源文件路径
     * @param targetFilePath 目标文件路径
     * @param targetFileType 目标文件类型
     */
    @SuppressWarnings("SameParameterValue")
    private static void readFile(String sourceFilePath, String targetFilePath,
                                 String targetFileType,int lineLength) {
        FileReader fr = null;
        BufferedReader bf = null;
        int count = 0;
        int fileCount = 0;
        StringBuilder builder = new StringBuilder();
        try {
            fr = new FileReader(sourceFilePath);
            bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                count++;
                builder.append(str);
                builder.append("\n");
                if (count % lineLength == 0) {
                    fileCount++;
                    writeFile(targetFilePath + fileCount + targetFileType, builder.toString());
                    builder = new StringBuilder();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bf != null) {
                    bf.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("文件总行数-------" + count);
        System.out.println("分割后的文件总数-------" + fileCount);
    }

    /**
     * 写入文件
     *
     * @param targetFilePath 目标文件存储路径
     * @param content        文件内容
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void writeFile(String targetFilePath, String content) {
        FileWriter writer = null;
        BufferedWriter bw = null;
        try {
            File file = new File(targetFilePath);

            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            writer = new FileWriter(file);
            bw = new BufferedWriter(writer);
            bw.write(content);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
