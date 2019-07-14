package test;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    public static void main(String[] args) {
//        // 要验证的字符串
//        String str = "service@xsoftlab.net";
//        // 邮箱验证规则
//        String regEx = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
//        // 编译正则表达式
//        Pattern pattern = Pattern.compile(regEx);
//        // 忽略大小写的写法
//        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(str);
//        // 字符串是否与正则表达式相匹配
//        boolean rs = matcher.matches();
//        System.out.println(rs);
//        test1();
        sort();
    }

    private static void test1(){
        String string = "时光,浓淡123333s45相宜;人心,67346远近相安;流年,长3232短皆逝去;时光,浮生,31s231往来皆客.";
        String regex="3+[a-z]{1,2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find())
        System.out.println("-------------------"+matcher.group());

    }



    private static void sort(){
        List<String> list=new ArrayList<>();
        list.add("你ddd");
        list.add("eee");
        list.add("aaa");
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        System.out.println("-------------------"+(int)(list.get(0).charAt(0)));

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                if (o1.charAt(0)>o2.charAt(0)){
                    return 1;
                }else {
                    return -1;
                }
            }
        });
        for (int i = 0; i < list.size(); i++) {
            System.out.println("-------------------"+list.get(i));

        }


    }
}
