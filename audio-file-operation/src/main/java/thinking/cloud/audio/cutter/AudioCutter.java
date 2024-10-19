package thinking.cloud.audio.cutter;

import java.io.*;
import java.util.Arrays;

/**
 * 借助ffmpeg裁剪音频文件
 * 原始命令：ffmpeg -i input.mp3 -ss 00:01:30 -t 00:00:30 -acodec copy output.mp3
 */
public class AudioCutter {

    public static void main(String[] args) {
        File file = new File("utils/ffmpeg.exe");

        System.out.println(file.getAbsolutePath() +" "+file.isFile());
    }
    /**
     * 按时间裁剪mp3文件
     *
     * @param sourcePath 源文件
     * @param destPath   裁剪完成 输出文件
     * @param startTime  起始时间
     * @param lenSec     裁剪长度
     */
    public static void cut(String sourcePath, String destPath, String startTime, String lenSec) {
        try {
            System.out.println("开始处理，输入文件" + sourcePath);
            // 构建 FFmpeg 命令
            String[] command = {
                    "utils/ffmpeg", "-i", sourcePath,
                    "-ss", startTime,
                    "-t", lenSec,
                    "-acodec", "copy",
                    "-loglevel", "quiet",
                    destPath
            };
            System.out.println("生成处理命令："+ Arrays.toString(command));
            // 使用 ProcessBuilder 执行 FFmpeg 命令
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);
            // 启动进程
            Process process = processBuilder.start();

            // 获取命令的输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            // 逐行读取命令的输出
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            // 等待进程执行完成
            int exitCode = process.waitFor();

            //执行完成，处理进程完成状态码
            if (exitCode == 0) {
                System.out.println("处理成功，输出文件：" + destPath);
            } else {
                System.err.println("处理失败，退出代码：" + exitCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
