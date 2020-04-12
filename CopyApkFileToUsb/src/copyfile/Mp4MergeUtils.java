package copyfile;



import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.FileDataSourceImpl;
import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import com.googlecode.mp4parser.authoring.container.mp4.MovieCreator;
import com.googlecode.mp4parser.authoring.tracks.AppendTrack;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * * ================================================
 * name:            Mp4MergeUtils
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2020/2/3
 * description：
 * history：
 * ===================================================
 */
public class Mp4MergeUtils {
    private final static String PREFIX_VIDEO_HANDLER = "vide";
    private final static String PREFIX_AUDIO_HANDLER = "soun";

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        File file1=new File("D:\\movie\\Screenshots-20200203-152245-1920x1080.mp4");
        File file2=new File("D:\\movie\\Screenshots-20200203-152249-1920x1080.mp4");
        File file3=new File("D:\\movie\\aaa.mp4");
        list.add(file1.getAbsolutePath());
        list.add(file2.getAbsolutePath());
        try {
            appendMp4List(list,file3.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 合并视频
     * @param inputVideos
     * @param outputPath
     * @throws IOException
     */
    public static void mergeVideos(List<String> inputVideos, String outputPath) throws IOException {
        List<Movie> inputMovies = new ArrayList<>();
        for (String input : inputVideos) {
            inputMovies.add(MovieCreator.build(input));
        }

        List<Track> videoTracks = new LinkedList<>();
        List<Track> audioTracks = new LinkedList<>();

        for (Movie m : inputMovies) {
            for (Track t : m.getTracks()) {
                if (PREFIX_AUDIO_HANDLER.equals(t.getHandler())) {
                    audioTracks.add(t);
                }
                if (PREFIX_VIDEO_HANDLER.equals(t.getHandler())) {
                    videoTracks.add(t);
                }
            }
        }

        Movie outputMovie = new Movie();
        if (audioTracks.size() > 0) {
            outputMovie.addTrack(new AppendTrack(audioTracks.toArray(new Track[audioTracks.size()])));
        }
        if (videoTracks.size() > 0) {
            outputMovie.addTrack(new AppendTrack(videoTracks.toArray(new Track[videoTracks.size()])));
        }

        Container out = new DefaultMp4Builder().build(outputMovie);

        FileChannel fc = new RandomAccessFile(outputPath, "rw").getChannel();
        out.writeContainer(fc);
        fc.close();
    }


    /**
     * 对Mp4文件集合进行追加合并(按照顺序一个一个拼接起来)
     *
     * @param mp4PathList [输入]Mp4文件路径的集合(支持m4a)(不支持wav)
     * @param outPutPath  [输出]结果文件全部名称包含后缀(比如.mp4)
     * @throws IOException 格式不支持等情况抛出异常
     */
    public static void appendMp4List(List<String> mp4PathList, String outPutPath) throws IOException {
        List<Movie> mp4MovieList = new ArrayList<>();// Movie对象集合[输入]
        for (String mp4Path : mp4PathList) {// 将每个文件路径都构建成一个Movie对象
            FileInputStream fileInputStream=new FileInputStream(new File(mp4Path));
            mp4MovieList.add(MovieCreator.build(new FileDataSourceImpl(new File(mp4Path))));
        }

        List<Track> audioTracks = new LinkedList<>();// 音频通道集合
        List<Track> videoTracks = new LinkedList<>();// 视频通道集合

        for (Movie mp4Movie : mp4MovieList) {// 对Movie对象集合进行循环
            for (Track inMovieTrack : mp4Movie.getTracks()) {
                if ("soun".equals(inMovieTrack.getHandler())) {// 从Movie对象中取出音频通道
                    audioTracks.add(inMovieTrack);
                }
                if ("vide".equals(inMovieTrack.getHandler())) {// 从Movie对象中取出视频通道
                    videoTracks.add(inMovieTrack);
                }
            }
        }

        Movie resultMovie = new Movie();// 结果Movie对象[输出]
        if (!audioTracks.isEmpty()) {// 将所有音频通道追加合并
            resultMovie.addTrack(new AppendTrack(audioTracks.toArray(new Track[audioTracks.size()])));
        }
        if (!videoTracks.isEmpty()) {// 将所有视频通道追加合并
            resultMovie.addTrack(new AppendTrack(videoTracks.toArray(new Track[videoTracks.size()])));
        }

        Container outContainer = new DefaultMp4Builder().build(resultMovie);// 将结果Movie对象封装进容器
        FileChannel fileChannel = new RandomAccessFile(String.format(outPutPath), "rw").getChannel();
        outContainer.writeContainer(fileChannel);// 将容器内容写入磁盘
        fileChannel.close();
    }



//    private String increaseAudio(long value,String path) {
//        File firstFile=new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Constants.PROJECT_FOLDER + "/Sounds/"+path+ ".mp3");
//
//        System.out.println("paths "+path);
//        System.out.println("paths2 "+firstFile.getAbsolutePath());
//        Movie movieA = null;
//        try {
//            movieA = MovieCreator.build(firstFile.getAbsolutePath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        final Movie finalMovie = new Movie();
//        List<Track> audioTracks = new ArrayList<>();
//        ArrayList<Movie> movilist=new ArrayList<>();
//        for(int i=0;i<value;i++){
//            movilist.add(movieA);
//        }
//
//        for (Movie movie : movilist) {
//            for (Track track : movie.getTracks()) {
//                if (track.getHandler().equals("soun")) {
//                    audioTracks.add(track);
//                }
//            }
//        }
//        try {
//            finalMovie.addTrack(new AppendTrack(audioTracks.toArray(new Track[audioTracks.size()])));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        final Container container = new DefaultMp4Builder().build(finalMovie);
//        File mergedFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Constants.PROJECT_FOLDER + "/Sounds/newsound123" + ".mp3");
//        try {
//            final FileOutputStream fos = new FileOutputStream(mergedFile);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        FileChannel fc = null;
//        try {
//            fc = new RandomAccessFile(mergedFile, "rw").getChannel();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            container.writeContainer(fc);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            fc.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return mergedFile.getAbsolutePath();
//    }
}
