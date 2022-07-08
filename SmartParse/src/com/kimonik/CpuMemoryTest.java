package com.kimonik;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * adb shell "busybox top  -d 1 |grep 31416" > C:\Users\dingzhixin.ex\Desktop\cpudata.txt
 * adb shell "busybox top  -d 1 |grep (实际线程号)" > C:\Users\dingzhixin.ex\Desktop\cpudata.txt
 * 测试并保存cpu使用情况
 */
public class CpuMemoryTest {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss", Locale.getDefault());
    private static String mFileDirectoryPath = "C:\\Users\\dingzhixin.ex\\Desktop\\性能测试";
    private static File mMemoryDataOutFile;
    private static File mCpuParseDataFile;
    private static String mTvIpAddress = "192.168.137.172";
    private static boolean mDebugToggle = false;
    private static boolean mSaveToggle = true;
    public static boolean mMemRunningToggle = true;
    private static long mSleepTime = 500;

    public static void main(String[] args) {
        getCpuInfo();
    }

    public static void getCpuInfo() {
        String result = runtimeCommand("adb -s " + mTvIpAddress + " shell \"ps -A |grep  com.hisense.smartimages\" ");
        String[] temp = result.split("\n");
        String processId = null;
        for (String s : temp) {
            if (!s.contains(":")) {
                processId = s.split("\\s+")[1];
            }
        }
        if (TextUtils.isEmpty(processId)) {
            LUtils.i("未获取到小聚识图进程号");
            return;
        }
        String command = "adb -s " + mTvIpAddress + " shell \"busybox top  -d 1 |grep " + processId + "\"";
        LUtils.i("获取cpu信息指令: ", command);
        runtimeCommand(command);
    }

    public static void init(SettingsEntity entity, MemoryCpuTestAndParseUi ui) {
        if (entity == null) {
            LUtils.i("配置文件解析异常");
            return;
        }
        if (TextUtils.isEmpty(entity.settingsFilePath)) {
            LUtils.i("配置文件,输出文件,解析文件路径异常");
            return;
        }
        mFileDirectoryPath = entity.settingsFilePath;
        if (TextUtils.isEmpty(entity.memoryOutDataFileName)) {
            LUtils.i("内存数据输出文件名异常");
            return;
        }
        String suffix = new SimpleDateFormat("_yyyyMMdd_HHmm").format(new Date()) + ".txt";
        mMemoryDataOutFile = new File(mFileDirectoryPath, entity.memoryOutDataFileName + suffix);
        ui.setMemoryFileFullPath(mMemoryDataOutFile.getAbsolutePath());
        mSleepTime = entity.sleepTime;
        if (!TextUtils.isEmpty(entity.cpuParseDataFileName)) {
            mCpuParseDataFile = new File(mFileDirectoryPath, entity.cpuParseDataFileName);
        }
        mDebugToggle = entity.debugToggle;
        mSaveToggle = entity.saveToggle;
        if (mDebugToggle) {
            LUtils.i(new Gson().toJson(entity));
        }
    }


    public static String parseMemory(String path) {
        mMemoryDataOutFile = new File(path);
        if (!mMemoryDataOutFile.exists()) {
            return "内存数据解析文件为空或者不存在";
        }
        try {
            List<String> list = FileUtils.readLines(mMemoryDataOutFile);
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            int sum = 0;
            int size = list.size();
            for (String s : list) {
                try {
                    String[] target = s.split("\\s+");
                    if (mDebugToggle) {
                        LUtils.i(Arrays.toString(target));
                    }
                    int temp = parseIntString(target[3]);
                    if (temp == 0) {
                        size -= 1;
                        continue;
                    }
                    if (temp > max) {
                        max = temp;
                    }
                    if (temp < min) {
                        min = temp;
                    }
                    sum += temp;
                } catch (Throwable e) {
                    //e.printStackTrace();
                }
            }
            String result = "内存数据解析结果: 最大值 = " + max + " , 最小值 = " + min + " , 平均值 = " + (1.0f * sum / size) + " , 有效次数 = " + size;
            LUtils.i(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "解析异常";
    }

    /**
     * "C:\\Users\\dingzhixin.ex\\Desktop\\cpudata.txt"
     *
     * @param path
     * @return
     */
    public static String parseCpuInfo(String path) {
        if (mCpuParseDataFile == null || !mCpuParseDataFile.exists()) {
            return "cpu 数据解析文件为空";
        }
        try {
            List<String> list = FileUtils.readLines(mCpuParseDataFile);
            float max = Float.MIN_VALUE;
            float min = Float.MAX_VALUE;
            float sum = 0;
            int size = list.size();
            for (String s : list) {
                float temp = parseFloatString(s.split("\\s+")[6]);
                if (temp == 0) {
                    size -= 1;
                    continue;
                }
                if (temp > max) {
                    max = temp;
                }
                if (temp < min) {
                    min = temp;
                }
                sum += temp;
            }
            String result = "CPU数据解析结果: 最大值 = " + max + " + 最小值 = " + min + " + 平均值 = " + (1.0f * sum / size) + " + 有效次数 = " + size;
            LUtils.i(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "解析异常";
    }


    private static float parseFloatString(String target) {
        if (TextUtils.isEmpty(target)) {
            return 0;
        }
        try {
            return Float.parseFloat(target);
        } catch (Throwable e) {
            //e.printStackTrace();
        }
        return 0;
    }

    private static int parseIntString(String target) {
        if (TextUtils.isEmpty(target)) {
            return 0;
        }
        try {
            return Integer.parseInt(target);
        } catch (Throwable e) {
            //e.printStackTrace();
        }
        return 0;
    }

    private static List<String> mResultList = new ArrayList<>();

    /**
     * 持续输出内存使用情况内容,用于分析内存泄漏
     */
    public static void analysisMemInfo(MemoryCpuTestAndParseUi ui) {
        mResultList.clear();
        mResultList.add("开始获取cpu信息<br>");
        new Thread() {
            @Override
            public void run() {
                while (mMemRunningToggle) {
                    String cmdResult = runtimeCommand("adb -s " + mTvIpAddress + " shell \"dumpsys meminfo com.hisense.smartimages " +
                            "|grep -iE \'TOTAL:\' \"");
                    String result = simpleDateFormat.format(new Date()) + "   " + cmdResult;
                    LUtils.i(result);
                    if (mResultList.size() > 100) {
                        mResultList.remove(0);
                    } else {
                        mResultList.add(result);
                    }
                    ui.updateResult(mResultList);
                    if (mSaveToggle) {
                        try {
                            FileUtils.write(mMemoryDataOutFile, result, true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (mSleepTime > 0) {
                        try {
                            sleep(mSleepTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                mResultList.add("获取内存信息结束");
                ui.updateResult(mResultList);
            }
        }.start();
    }


    /**
     * Java执行cmd命令
     * <p>
     * |grep -E  并列条件,满足任意一个则满足
     * |grep -v  排除条件,满足则排除,优先级高于-E
     * |grep 条件1 |grep  条件2   同时满足两个条件
     * "adb -s 192.168.137.172 shell \"dumpsys meminfo com.hisense.aiot |grep -E \'Views|Activities\'  |grep -v  WebViews\""
     */
    public static String runtimeCommand(String command) {
        Process process;
        try {//  '/c'执行完后关闭cmd窗口,最后的cmd命令有异常时会出现问题
            //  '/k'执行完命令后不关闭窗口,手动exit则不会出现异常引发的问题
            process = Runtime.getRuntime().exec("cmd.exe /c " + command);

            InputStream in = process.getInputStream();
            InputStream error = process.getErrorStream();
            String normalMessage = getInputStreamResult(in);
            String errorMessage = getInputStreamResult(error);
            int status = process.waitFor();
            process.destroy();
            if (mDebugToggle) {
                LUtils.i("正常输出 = ", normalMessage);
                if (!TextUtils.isEmpty(errorMessage)) {
                    LUtils.i("异常输出 = ", errorMessage);
                }
            }
            return normalMessage;
        } catch (Exception e) {
            System.out.println("执行cmd命令出错!");
            return "<br><br>执行cmd命令出错<br>" + getExceptionInfo(e);
        }

    }


    /**
     * 获取流的结果
     */
    public static String getInputStreamResult(InputStream inputStream) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "GB2312"));
            String line = null;
            while ((line = br.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    /**
     * 获取异常输出信息
     */
    public static String getExceptionInfo(Exception ex) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        ex.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }
}
