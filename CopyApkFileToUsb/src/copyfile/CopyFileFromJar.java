package copyfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 导报资源文件需要在file-->project structure-->artifacts-->相应的jar包-->outputlayot-->+号
 * --->directory  content-->选中相应的资源文件夹-->然后打包才能将资源文件打进jar包
 */
@SuppressWarnings({"SameParameterValue"})
class CopyFileFromJar {
    /**
     * 将jar包中的文件复制都指定目录
     */
    void loadRecourseFromJar(String path) {
        InputStream is = null;
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File("E:/USB_Disk_Eject.exe");
            System.out.println("======" + file.exists());
            if (file.exists()) {
                return;
            }
            //fileOutputStream new的时候如果文件不存在,则直接回创建文件
            //如果要判断文件是否存在,需要在该方法前执行
            fileOutputStream = new FileOutputStream(file);
            is = this.getClass().getResourceAsStream(path);
//            System.out.println("--------------?"+is.available());
            //如果直接使用byte[] bytes = new byte[is.available()];
            //                fileOutputStream.write(bytes);
//            byte[] bytes = new byte[is.available()];
//            fileOutputStream.write(bytes,0,bytes.length);
            //方法写文件会导致exe文件不可用,-1073741819异常,可能数据损坏
            byte[] bytes = new byte[1024];
            int len;
            while ((len = is.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, len);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}