package com.kimonik;

public class SettingsEntity {

    /**
     * 配置文件地址,同时作为输出文件与解析文件地址
     */
    public String settingsFilePath = "C:\\Users\\dingzhixin.ex\\Desktop\\性能测试";
    /**
     * 获取内存信息输出文件全路径
     */
    public String memoryOutDataFileName = "meminfodata";
    /**
     * 要解析的cpu数据文件地址
     */
    public String cpuParseDataFileName = "cpudata";
    /**
     * 调试日志开关
     */
    public boolean debugToggle = false;
    /**
     * 是否存储文件
     */
    public boolean saveToggle = true;
    /**
     * 获取内存信息休眠间隔时间
     */
    public long sleepTime = 200;
}
