package com.dzx.util;

public class StringUtil {

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
}
