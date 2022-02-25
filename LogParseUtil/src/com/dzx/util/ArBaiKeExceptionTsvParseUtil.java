package com.dzx.util;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import java.io.*;
import java.util.*;

public class ArBaiKeExceptionTsvParseUtil {

    public static void main(String[] args) {
        //[exceptionmessage, devicemsg, featurecode, deviceid, datecode, logtime, appversionname, appversioncode]
        String filePath = "C:\\Users\\dingzhixin.ex\\Desktop\\AR百科馆异常分析\\AR百科馆异常1月2月份.tsv";
        List<String[]> list = getAllTsvRows(filePath);
        LUtils.i(Arrays.toString(list.get(0)), "\n\n\n\n");

        Set<String> set = new HashSet<>();
        int size = list.size();

        int count2 = 0;
        Map<String, StatisticsEntity> sameCountMap = new HashMap<>();

        for (int i = 1; i < size; i++) {
            String[] strings = list.get(i);
            ArBaiKeExceptionEntity exceptionEntity = new ArBaiKeExceptionEntity();
            exceptionEntity.exceptionmessage = strings[0];
            String[] exceptionLines = exceptionEntity.exceptionmessage.split("  ");
            exceptionEntity.exceptionName = exceptionLines[0].substring(0, exceptionLines[0].indexOf(":"));
            exceptionEntity.devicemsg = strings[1];
            exceptionEntity.featurecode = strings[2];
            exceptionEntity.deviceid = strings[3];
            exceptionEntity.datecode = strings[4];
            exceptionEntity.logtime = strings[5];
            exceptionEntity.appversionname = strings[6];
            exceptionEntity.appversioncode = strings[7];
            if ("java.lang.OutOfMemoryError".equals(exceptionEntity.exceptionName)) {
                count2++;
            }

            if (set.contains(exceptionEntity.exceptionmessage)) {
                StatisticsEntity statisticsEntity = sameCountMap.get(exceptionEntity.exceptionmessage);
                statisticsEntity.appearCount = statisticsEntity.appearCount + 1;

                statisticsEntity.featurecodeSet.merge(exceptionEntity.featurecode, 1, Integer::sum);

                statisticsEntity.devicemsgSet.merge(exceptionEntity.devicemsg, 1, Integer::sum);

                statisticsEntity.appversionnameSet.merge(exceptionEntity.appversionname, 1, Integer::sum);
                statisticsEntity.deviceIdSet.merge(exceptionEntity.deviceid, 1, Integer::sum);

            } else {
                set.add(exceptionEntity.exceptionmessage);

                StatisticsEntity statisticsEntity = new StatisticsEntity();
                statisticsEntity.exceptionmessage = exceptionEntity.exceptionmessage;
                statisticsEntity.appearCount = 1;
                statisticsEntity.exceptionName = exceptionEntity.exceptionName;

                statisticsEntity.appversionnameSet.put(exceptionEntity.appversionname, 1);
                statisticsEntity.deviceIdSet.put(exceptionEntity.deviceid, 1);
                statisticsEntity.devicemsgSet.put(exceptionEntity.devicemsg, 1);
                statisticsEntity.featurecodeSet.put(exceptionEntity.featurecode, 1);

                sameCountMap.put(exceptionEntity.exceptionmessage, statisticsEntity);
            }
        }

        int count1 = 0;
        for (StatisticsEntity s : sameCountMap.values()) {
            if ("java.lang.OutOfMemoryError".equals(s.exceptionName)) {
                count1 += s.appearCount;
            }
        }
//        LUtils.i("初步统计内存溢出异常  = ", count1, "   ", count2);


        Set<String> exceptionNameSet = new HashSet<>();

        Map<String, List<StatisticsEntity>> resultMap = new HashMap<>();


        //收集同名异常
        for (StatisticsEntity s : sameCountMap.values()) {
            if (exceptionNameSet.contains(s.exceptionName)) {
                resultMap.get(s.exceptionName).add(s);
            } else {
                exceptionNameSet.add(s.exceptionName);
                List<StatisticsEntity> list1 = new ArrayList<>();
                list1.add(s);
                resultMap.put(s.exceptionName, list1);
            }
        }

        List<StatisticsEntity> actualList = new ArrayList<>();
        int outOfMemoryCount = 0;
        StringBuilder builder = new StringBuilder();

        //同名异常判断相似度
        for (List<StatisticsEntity> ll : resultMap.values()) {

            List<StatisticsEntity> merge = mergeSame(ll);

            int temp = 0;
            for (StatisticsEntity statisticsEntity : ll) {
                temp += statisticsEntity.appearCount;
            }
            builder.append(ll.get(0).exceptionName).append("  异常有 ").append(merge.size()).append("  种类型 , ")
                    .append("总计 ").append(temp).append("次\n");

//            LUtils.i(ll.size(), "   ==   ", merge.size(), "  ", merge.get(0).exceptionName);
            actualList.addAll(merge);
            if ("java.lang.OutOfMemoryError".equals(ll.get(0).exceptionName)) {
                for (StatisticsEntity statisticsEntity : ll) {
                    outOfMemoryCount += statisticsEntity.appearCount;
                }
            }

        }

        int handleAfter = 0;

        LUtils.i("\n\n\nAR百科馆1月1日至2月23日累计异常次数 ", (list.size() - 1), "次,其中java.lang.OutOfMemoryError异常 ",
                outOfMemoryCount, "次,占比 ", (1.0f * outOfMemoryCount / (list.size() - 1) * 100), "%");
        LUtils.i(builder.toString());
        LUtils.i("详细异常类型如下:\n\n\n");

        for (StatisticsEntity entity : actualList) {
            handleAfter += entity.appearCountOrigin;
            LUtils.i("累计次数  ", entity.appearCountOrigin);
            LUtils.i("异常发生设备featureCode  ", entity.featurecodeSet);
            LUtils.i("异常发生设备型号devicemsg  ", entity.devicemsgSet);
            LUtils.i("异常发生版本号appversionnameSet  ", entity.appversionnameSet);
            LUtils.i("异常发生设备id deviceIdSet ", entity.deviceIdSet);
            LUtils.i(entity.exceptionmessage.replaceAll("  ", "\n"), "\n\n\n");
        }


        LUtils.i("文件总行数  = ", list.size(), "  处理后异常总个数 = ", handleAfter, " java.lang.OutOfMemoryError异常 = ", outOfMemoryCount);
    }


    private static List<StatisticsEntity> mergeSame(List<StatisticsEntity> ll) {
        if (ll.size() > 0) {
            StatisticsEntity first = ll.get(0);
            first.appearCountOrigin = first.appearCount;
        }
        if (ll.size() < 2) {
            return ll;
        }
        StatisticsEntity first = ll.get(0);
        first.appearCountOrigin = first.appearCount;
        List<StatisticsEntity> actualList = new ArrayList<>();
        actualList.add(first);
        int size = ll.size();
        List<StatisticsEntity> secondList = new ArrayList<>();
        for (int i = 1; i < size; i++) {
            if (getSimilarityRatio(first.exceptionmessage, ll.get(i).exceptionmessage) > 90) {
                first.appearCountOrigin = first.appearCountOrigin + ll.get(i).appearCount;
                mergeMap(first.featurecodeSet, ll.get(i).featurecodeSet);
                mergeMap(first.devicemsgSet, ll.get(i).devicemsgSet);
                mergeMap(first.appversionnameSet, ll.get(i).appversionnameSet);
                mergeMap(first.deviceIdSet, ll.get(i).deviceIdSet);
            } else {
                secondList.add(ll.get(i));
            }
        }
        actualList.addAll(mergeSame(secondList));
        return actualList;
    }

    private static void mergeMap(Map<String, Integer> first, Map<String, Integer> second) {
        for (String s : second.keySet()) {
            if (first.containsKey(s)) {
                first.put(s, first.get(s) + second.get(s));
            } else {
                first.put(s, second.get(s));
            }
        }
    }

    private static void testSimilarity() {
        String test1 = "java.lang.SecurityException: Permission Denial: broadcastIntent() requesting a sticky broadcast from pid=16522, uid=10061 requires android.permission.BROADCAST_STICKY\n" +
                "at android.os.Parcel.readException(Parcel.java:1599)\n";
        String test2 = "java.lang.SecurityException: Permission Denial: broadcastIntent() requesting a sticky broadcast from pid=9216, uid=10066 " +
                "requires android.permission.BROADCAST_STICKY\n";

        float similarity = getSimilarityRatio(test1, test2);
        LUtils.i(similarity);
    }


    private static List<String[]> getAllTsvRows(String filePath) {
        List<String[]> allRows = new ArrayList<>();
        try {
            TsvParserSettings settings = new TsvParserSettings();
            settings.setMaxCharsPerColumn(20000);
            settings.getFormat().setLineSeparator("\n");
            TsvParser parser = new TsvParser(settings);
            DataInputStream in = new DataInputStream(new FileInputStream(new File(filePath)));
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));//这里如果csv文件编码格式是utf-8,改成utf-8即可
            allRows = parser.parseAll(br);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allRows;

    }


    /**
     * 计算两个字符串的相似度
     */
    public static float getSimilarityRatio(String str, String target) {
        //编辑距离算法,矩阵实现
        //使用两个字符串的长度分别加1初始化一个数组,即一个长度为5,一个长度为6的字符串
        //则吃石化一个长度为6*7的矩阵,第一行从0开始用数字标记,第一列从0开始用数字标记
        //填充矩阵中的其他值,填充规则为
        //假若要填充矩阵中i,j位置的值,则计算
        //i,j位置左侧的值+1,即(i-1,j)位置的值+1
        //i,j位置上方的值+1,即(i,j-1)位置的值+1
        //i,j位置左上角的值+ij位置在两个字符串中的对应字符是否相等,如果相等则左上角的值+0
        //不相等则+1,即位置(i-1,j-1)+(该位置字符一致?+0:+1)
        //(i,j)位置的值最终为,以上计算出的三个值中的最小值
        //依此方式填充整个矩阵,最终计算出右下角的值
        //字符串的相似度即为 1-右下角的值/(两个字符串中较长的字符数)


//
//           a a a d
//         0 1 2 3 4
//       b 1 1 2 3 4
//       b 2 2 2 3 4
//       b 3 3 3 3 4
//       d 4 4 4 4 3
//       字符串aaad 与字符串bbbd 的相似度为    1-3/4=0.25




        int d[][]; // 矩阵
        int n = str.length();
        int m = target.length();
        int i; // 遍历str的
        int j; // 遍历target的
        char ch1; // str的
        char ch2; // target的
        int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1,相同0,不同1
        if (n == 0 || m == 0) {
            return 0;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++) { // 初始化第一列
            d[i][0] = i;
        }

        for (j = 0; j <= m; j++) { // 初始化第一行
            d[0][j] = j;
        }

        for (i = 1; i <= n; i++) { // 遍历str
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), d[i - 1][j - 1] + temp);
            }
        }

        float result = (1 - (float) d[n][m] / Math.max(str.length(), target.length())) * 100F;
//        LUtils.i("相似度  ", result);
        return result;
    }


    private static class ArBaiKeExceptionEntity {
        public String exceptionmessage;
        public String devicemsg;
        public String featurecode;
        public String deviceid;
        public String datecode;
        public String logtime;
        public String appversionname;
        public String appversioncode;
        public String exceptionName;
    }

    private static class StatisticsEntity {
        //[exceptionmessage, devicemsg, featurecode, deviceid, datecode, logtime, appversionname, appversioncode]
        public String exceptionmessage;
        public String exceptionName;
        public int appearCount = 0;
        public int appearCountOrigin = 0;
        //设备出现次数
        public Map<String, Integer> devicemsgSet = new HashMap<>();
        public Map<String, Integer> featurecodeSet = new HashMap<>();
        public Map<String, Integer> appversionnameSet = new HashMap<>();
        public Map<String, Integer> deviceIdSet = new HashMap<>();

    }
}
