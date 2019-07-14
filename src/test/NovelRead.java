package test;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class NovelRead {
    public static void main(String[] args) {
        get();
//        getContent();
    }

    public static void getContent(List<NovelBean> list) {
        try {
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < list.size(); i++) {
                Document document = Jsoup.connect("https://www.biquke.com/bq/53/53379/" + list.get(i).getUrl()).get();
                String content = list.get(i).getTitle() + "--------\n" + realContent(document);
                builder.append(content).append("\n\n");
                if (i != 0 && i % 5 == 0) {
                    writeFile("C:\\Users\\20313\\Desktop\\wangtong\\kongbuwu3\\"
                            + (i - 4) + "--" + (i + 1)
                            + ".txt", builder.toString());
                    builder=new StringBuilder();
                }
                if (i==list.size()-1){
                    writeFile("C:\\Users\\20313\\Desktop\\wangtong\\kongbuwu3\\"
                            + (i - 4) + "--" + (i + 1)
                            + ".txt", builder.toString());
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String realContent(Document document) {
        String content = document.getElementById("content").toString()
                .replace("<br>", "")
                .replace("&nbsp;", "")
                .replace("\n", "")
                .replace(" ", "")
                .replace("</div>", "")
                .replace("<divid=\"content\">", "");
        return content;
    }

    public static void get() {
        NovelFileBean novelFileBean = new NovelFileBean();
        List<NovelBean> list = new ArrayList<>();
        try {
            Document document = Jsoup.connect("https://www.biquke.com/bq/53/53379/").get();
            Elements element = document.getElementsByTag("dd");
//            Element elements = element.getElementById("a");
            for (Element element1 : element) {
                NovelBean bean = new NovelBean();
                bean.setUrl(element1.select("a").attr("href"));
                bean.setTitle(element1.select("a").attr("title"));
                list.add(bean);
            }
            novelFileBean.setList(list);
            novelFileBean.setUrlHead("https://www.biquke.com/bq/53/53379/");
            Gson gson = new Gson();
            String content = gson.toJson(novelFileBean);
//            writeFile("C:\\Users\\20313\\Desktop\\wangtong\\我有一座恐怖屋.txt",content);

            getContent(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
