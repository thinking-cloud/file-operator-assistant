package thinking.cloud.audio.app;

import thinking.cloud.audio.cutter.AudioCutter;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

//ffmpeg -i F:\wow\source_audio\xx.mp3 -ss 00:00:0 -t 00:00:40 -acodec copy F:\wow\source_audio\xx.mp3
public class 魔兽嗜血音频文件 {
    private static String sourceDir = "F:/wow/source_audio";
    private static String destDir = "F:/wow/audio";
    private static String startTime = "00:00:00";
    private static String lenSec = "00:00:40";
    public static void main(String[] args) {
        File sourceFile = new File(sourceDir);
        File[] files = sourceFile.listFiles();

        if (files==null || files.length == 0){
            System.out.println("没有可以处理的文件");
            return;
        }
        Stream<File> sourceStream = Arrays.stream(files);
        sourceStream.forEach((p)->{
            String destFile = destDir+"/f_"+p.getName();
            AudioCutter.cut(p.getAbsolutePath(),destFile,"00:00:00","00:00:40");
        });
    }
}
