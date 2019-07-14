package test;


import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.commons.lang.CharSet;
import org.w3c.dom.html.HTMLOptGroupElement;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadCsvFile {
    public static void main(String[] args) {
//        readCSVFileData("C:\\Users\\20313\\Desktop\\网易新闻.csv");
        readCSV();
    }

    /**
     * @param srcPath csv文件路径
     */
    private static void readCSVFileData(String srcPath) {
        List cells = new LinkedList();//每行记录一个list

        BufferedReader reader = null;

        String line = null;
        try {
            FileInputStream fr = new FileInputStream(srcPath);
            InputStreamReader is = new InputStreamReader(fr, "GB2312");
            reader = new BufferedReader(is);
//            reader = new BufferedReader(new FileReader(srcPath));
        } catch (FileNotFoundException e) {
            System.out.println("-------------------[读取CSV文件，插入数据时，读取文件异常]");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] fieldsArr = null;
        int lineNum = 0;
        int insertResult = 0;
//        TableInfo tableInfo = new TableInfo();
//        tableInfo.setTableName(tableName);
        try {
            List listField = null;
            int count = 0;
            while ((line = reader.readLine()) != null) {

                if (lineNum == 0) {
                    //表头信息
                    fieldsArr = line.split(",");
                } else {
                    //数据信息
                    listField = new ArrayList<>();
                    String str;

                    line += ",";
                    Pattern pCells = Pattern
                            .compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,");
                    Matcher mCells = pCells.matcher(line);
                    //读取每个单元格
                    while (mCells.find()) {
                        str = mCells.group();
                        str = str.replaceAll(
                                "(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,", "$1");
                        str = str.replaceAll("(?sm)(\"(\"))", "$2");
                        cells.add(str);
                    }
                    //从第2行起的数据信息list
                    listField.add(cells);
                }
                lineNum++;
            }

//            System.out.println("-----------总行数--------" + lineNum++);
//            System.out.println("-----------总行数-----11---" + cells.size());
//            for (int i = 0; i < cells.size(); i++) {
//                System.out.println("-------------------" + cells.get(i));
//                if (i>100){
//                    break;
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readCSV() {
        String srcPath = "C:\\Users\\20313\\Desktop\\网易新闻.csv";
        String charset = "gb2312";
        try {
            CSVReader csvReader = new CSVReaderBuilder(
                    new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(
                                            new File(srcPath)), charset)))
                    .build();
            Iterator<String[]> iterator = csvReader.iterator();
            int count=0;
            while (iterator.hasNext()) {
                count++;
                String[] s=iterator.next();
                if (count==2){
                    for (int i = 0; i <s.length; i++) {
                        System.out.println("-----------111--------"+s[i].replace("\n", "")
                                .replace(" ", ""));


                    }
                    String ss=new String("ss".getBytes(),Charset.forName("gb2312"));
                    break;
                }

//                Arrays.stream(iterator.next()).forEach(System.out::print);
//                System.out.println();
            }
//            for (String[] strings : csvReader) {
//                Arrays.stream(strings).forEach(System.out::print);
//            }


//
//            Iterator<String[]> iterator=csvReader.iterator();
//            while (iterator.hasNext()){
//                String[] s=iterator.next();
//                System.out.println("-------------------"+s.length);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
