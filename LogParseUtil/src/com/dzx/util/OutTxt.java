package com.dzx.util;

import com.dzx.watch.dir.DirWatcher;
import com.dzx.watch.dir.WatcherResultHandler;
import org.apache.commons.io.input.TailerListener;
import org.apache.commons.io.input.TailerListenerAdapter;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class OutTxt {


    public static void main(String args[]) throws IOException {
        //过滤异常及下载数据
//        List<String> list = new ArrayList<>(Arrays.asList("HXJ","DemoCrashHandler","RequestBean"));
        //过滤是否已经配置下载连接
//        List<String> list = new ArrayList<>(Arrays.asList("getAppUpgradeInfoByPackageName"));

//        List<String> list = new ArrayList<>(Arrays.asList("android.widget.ImageView.","android.view.View.","android.graphics"));
//        List<String> list = new ArrayList<>(Arrays.asList("anr ","ANR ","HXJ","HomeActivity","am_"));
//        List<String> list = new ArrayList<>(Arrays.asList("打开香薰机 ","关闭香薰机 ","onReceive, deviceStatusChanged","HomeActivity: |HXJ showStatus",
//                "getHomeId"));
//        List<String> list = new ArrayList<>(Arrays.asList("IotHomeCardViewStrategy","main"));
//        List<String> list = new ArrayList<>(Arrays.asList("java.lang.","java.util","android."));
//        根据条件过滤内容
//        filerFileAndOut("C:\\Users\\dingzhixin.ex\\Desktop\\002.log", list);
        //过滤异常统计信息
//        List<String> list = new ArrayList<>(Arrays.asList("8610030000010100000007120c9c6a3b"));
//        List<String> list = new ArrayList<>(Arrays.asList("speech_CoreService: sort:chat;title","HXJ onReceive, deviceStatusChanged, msgContent"));
//        filerFileAndOut("C:\\Users\\dingzhixin.ex\\Desktop\\c4a89af25b9fcbdc673a0ffdfe497d05\\log.txt", list);

        //分析内存泄漏
//        analysisMemInfo();

//        monitor("C:\\Users\\dingzhixin.ex\\Desktop\\1.txt", 500);

//        Utils.runtimeCommand("arp -a 192.168.137.172");
//        Utils.runtimeCommand("nbtstat -a  192.168.137.172");
//        handleJavaFile();
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\testbyteencode.txt");
        FileUtil.readFileByBytes(file);
    }


    /**
     * 将Java类的方法全部添加日志输出,自动添加方法名
     */
    private static void handleJavaFile() throws IOException {
        String result = FileUtil.getFileContent(new File("C:\\Users\\dingzhixin.ex\\Desktop\\12315.txt"));
        int size = result.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            String s = result.substring(i, i + 1);
            builder.append(s);
            if ("{".equals(s)) {
                builder.append("\n").append("    LUtils.e(TAG, \" = \" );\n");
            }
        }
        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\55555.java");
        file.createNewFile();
        FileUtil.outFileContent(file, builder.toString());


    }


    /**
     * 根据条件过滤文件并输出内容到文件
     */
    public static void filerFileAndOut(String targetFilePath, String outFilePath, List<String> conditions) {
        ParseLogUtil.parseLog(targetFilePath, outFilePath, conditions);
    }

    public static void filerFileAndOut(String targetFilePath, List<String> conditions) {
        filerFileAndOut(targetFilePath, "C:\\Users\\dingzhixin.ex\\Desktop\\解析结果1.log", conditions);
    }

    /**
     * 持续输出内存使用情况内容,用于分析内存泄漏
     */
    public static void analysisMemInfo() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    Utils.runtimeCommand("adb -s 192.168.137.172 shell \"dumpsys meminfo com.hisense.aiot |grep -E \'Views|Activities\'  |grep -v  WebViews\"");
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }

    public static void watchFileChange() {
        final DirWatcher dw;
        try {
            dw = new DirWatcher(true);
            dw.registerAllEvents(Paths.get("C:\\Users\\dingzhixin.ex\\Desktop")).addHandler(new WatcherResultHandler() {
                @Override
                public void handleResult(Path path, WatchEvent.Kind<?> event) {
                    System.out.println(path.toString() + ": " + event.toString());
                }
            }).processEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 2  * @param inputFile  监控文件
     * 3  * @param sleepInterval  当文件没有日志时sleep间隔
     * 监控文件改变并输出最新内容
     */
    private static void monitor(String inputFile, int sleepInterval) {
//        List<String> list = Arrays.asList("InflateException", "OutOfMemoryError", "IllegalArgumentException", "FATAL", "DemoCrashHandler","manager error","has detached");
        List<String> list = Arrays.asList("InflateException", "OutOfMemoryError", "IllegalArgumentException", "FATAL", "DemoCrashHandler", "manager error", "HXJ");
//        List<String> list = Arrays.asList("SingleCardDeviceManger", "BluetoothVoiceTipFragment","IotManagerService","RequestBean");
//        List<String> list1 = Arrays.asList("HXJ", "homeId");
        TailerListener listener = new TailerListenerAdapter() {
            @Override
            public void handle(String line) {
                boolean needOut = false;
                boolean needOut1 = false;
                int count = 0;
                for (String s : list) {
                    if (line.contains(s)) {
//                        count++;
                        needOut = true;
                    }
//                    if (line.contains("com.hisense.hitv.hicloud.a.y")) {
//                        needOut = false;
//                    }
                }
                if (needOut) {

                    if (!line.contains("HXJ") || line.contains("has detached")) {
                        System.out.println(line + "\n");
                    }
//                if (count == list.size()) {
                    FileUtil.outFileContentAppend(new File("C:\\Users\\dingzhixin.ex\\Desktop\\异常结果.txt"), line + "\n\n");
                }

            }
        };
        MyTailer tailer = new MyTailer(new File(inputFile), listener, sleepInterval, true);
        tailer.run();
    }

    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是GB2312
                String s = encode;
                return s; //是的话，返回“GB2312“，以下代码同理
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是ISO-8859-1
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是UTF-8
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是GBK
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "不确定"; //如果都不是，说明输入的内容不属于常见的编码格式。
    }

    /**
     * 重命名文件名
     */
    public static void renameFile(String filePath) {
        File file = new File(filePath);

        String[] list = file.list();

        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
//            System.out.println(list[i].replace("01_",""));
//            File file1 = new File(filePath, list[i]);
//            System.out.println("==========   " + file1.renameTo(new File(filePath, list[i].replace("01_", ""))));
        }
    }


    private static long mBeforeTime;
    private static int INTERVAL = 500;
    private static int count = 0;
    private static CountDownLatch start = new CountDownLatch(1);
    private static CountDownLatch end = new CountDownLatch(1000);

    private static void testRequest() {

        for (int i = 0; i < 1000; i++) {
            start = new CountDownLatch(1);
            end = new CountDownLatch(2);
            new Thread() {
                @Override
                public void run() {
                    try {
                        start.await();
                        request();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        end.countDown();
                    }
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    try {
                        start.await();
                        request();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        end.countDown();
                    }
                }
            }.start();


            start.countDown();
            try {
                Thread.sleep(600);
                end.await();
                System.out.println("结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }


    private static int mRunCount = 0;

    private static void request() {
        System.out.println("执行时间 = " + System.currentTimeMillis());
        if (System.currentTimeMillis() - mBeforeTime < INTERVAL) {
            count++;
            System.out.println("请求被拦截     " + count);
            return;
        } else {
            mBeforeTime = System.currentTimeMillis();
        }
        mRunCount++;
        System.out.println("发起了请求    =  " + mRunCount);
    }


    private static void test() {
        //        Desktop.getDesktop().open(new File("E:\\work\\resource\\aiot资料\\慧享家\\aiot第四期\\虚拟体验中心\\产品需求书_语音助手需求列表_V7.2.2_社交二代.xlsm"));
//        OutputStream inputStream = Runtime.getRuntime().exec("explorer E:\\work\\resource\\aiot资料\\慧享家\\aiot第四期\\虚拟体验中心\\产品需求书_语音助手需求列表_V7.2.2_社交二代.xlsm").getOutputStream();
//
//        ArrayList<String> columnList = new ArrayList<String>();
////        File file = new File("E:\\work\\resource\\aiot资料\\慧享家\\aiot第四期\\虚拟体验中心\\产品需求书_语音助手需求列表_V7.2.2_社交二代.xlsm");
////        File file = new File("E:\\work\\resource\\aiot资料\\慧享家\\aiot第四期\\虚拟体验中心\\IOT平台指令协议-20200514(addvirtualcenter) (1).xlsx");
//        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\AIOT3.0.5总的冒烟用例.xlsx");
////        FileWriter filewriter = new FileWriter("E:\\work\\resource\\aiot资料\\慧享家\\aiot第四期\\虚拟体验中心\\产品需求书_语音助手需求列表_V7.2.2_社交二代.xlsm");
//        try {
//            FileInputStream in = new FileInputStream(file);
//
//            XSSFWorkbook wb = new XSSFWorkbook(in);
//
//            Sheet sheet = wb.getSheetAt(0); //取得“测试.xlsx”中的第一个表单
//            int firstRowNum = sheet.getFirstRowNum();
//            int lastRowNum = sheet.getLastRowNum();
//
//            Row row = null;
//            Cell cell_a = null;
//            for (int i = firstRowNum + 1; i <= lastRowNum; i++) {
//                row = sheet.getRow(i); //取得第i行 （从第二行开始取，因为第一行是表头）
//                cell_a = row.getCell(1); //取得i行的第一列
//                String cellValue = cell_a.getStringCellValue().trim();
//                //System.out.println(cellValue);
////                filewriter.write(cellValue + "\r\n");   //将取出的.xlsx表中的数据写入到txt文件中
//                System.out.println(cellValue);
//                columnList.add(cellValue);
//            }
////            filewriter.flush();
////            filewriter.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        renameFile("C:\\Users\\dingzhixin.ex\\Desktop\\01_ok_button");
//        System.out.println("dsds".toString());
//        monitor("C:\\Users\\dingzhixin.ex\\Desktop\\1.txt", 1000);
//        FileUtil.getFileContent(new File("C:\\Users\\dingzhixin.ex\\Desktop\\统计异常.txt"));


    }

    public static void getDeskTopPath() {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com = fsv.getHomeDirectory();    //这便是读取桌面路径的方法了
        System.out.println("desktop path = " + com.getPath());
    }
}