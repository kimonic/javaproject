package com.dzx.analyze;

import com.dzx.util.LUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ParseArBaikeMonkeyLog {

    public static void main(String[] args) {
        String targetFilter = "FATAL EXCEPTION".toLowerCase();
        String targetFilter1 = "lowmemorykiller: Killing '.ar.baike:unity'".toLowerCase();

        String exclude = "com.jamdeo.tv.mediacenter".toLowerCase();
        String exclude1= "com.hisense.tv.familyalbum".toLowerCase();
        String exclude2= "com.hisense.tv.familycloud".toLowerCase();
        String exclude3= "com.hisense.familymessage".toLowerCase();
        String exclude4= "monkey".toLowerCase();

        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\monkey记录");
        File[] files = file.listFiles();
        int length = files.length;
        for (int i = 0; i < length; i++) {
            File file1 = files[i];
            LUtils.i(file1.getAbsolutePath(), "\n\n\n");
            try {
                List<String> list = FileUtils.readLines(file1);
                int count = 0;
                for (String s : list) {
                    count++;
                    if (s.toLowerCase().contains(targetFilter)) {
                        String next=list.get(count).toLowerCase();
                        if (next.contains(exclude)
                        ||next.contains(exclude1)
                        ||next.contains(exclude2)
                        ||next.contains(exclude3)
                        ||next.contains(exclude4)
                        ){

                        }else {
                            LUtils.i(s);
                            for (int j = 0; j < 30; j++) {
                                LUtils.i(list.get(count + j));
                            }
                            LUtils.i("\n\n\n");
                        }

                    }
//                    else if (s.toLowerCase().contains(targetFilter1)) {
//                        LUtils.i(s);
//                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }


}
