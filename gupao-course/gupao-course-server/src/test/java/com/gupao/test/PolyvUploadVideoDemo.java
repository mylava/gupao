/*
package com.gupao.test;
import net.polyv.bean.vo.VideoInfo;
import net.polyv.callback.UploadCallBack;
import net.polyv.entry.PolyvUploadClient;
import net.polyv.enumeration.UploadErrorMsg;

public class PolyvUploadVideoDemo {
    private static final String userId = "7da7816a7e"; //保利威账号的userId(必填)
    private static final String secretKey = "cdnxXdkk33"; //账号的secretKey(必填)

    public static void main(String[] args) {
        int partitionSize = 1*1024 * 1024; //可指定分片上传时每个分片的大小，默认为1M
        int threadNum = 1; //可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）
        String checkpoint = "/Users/TestUser/Desktop"; //断点续传的上传位置写入路径
        PolyvUploadClient client = new PolyvUploadClient(userId, secretKey, partitionSize, checkpoint, threadNum); //初始化PolyvUploadClient实例

        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setFileLocation("E:\\ploy\\test.mp4"); //视频文件在服务器上的绝对路径，必须包含拓展名（必填）
        videoInfo.setTitle("测试视频"); // 视频标题（必填）
        videoInfo.setCataId(1L); // 上传的分类目录，默认值：1，表示上传到默认分类（可选）
        videoInfo.setDescrib("视频描述"); // 视频描述（可选）
        videoInfo.setTag("视频标签"); // 视频标签（可选）
        videoInfo.setLuping(0); //是否进行课件优化处理，默认值：0（可选）
        videoInfo.setKeepSource(0); //是否保持源文件播放，默认：0（可选）
        videoInfo.setState("自定义拓展信息"); //如果提交了该字段，会在上传完成的事件回调中透传返回

        System.out.println("vid=" + client.uploadVideoParts(videoInfo, new UploadCallBack() {
            @Override
            public void start(String s) {
                System.out.println("start=" + s);
            }
            @Override
            public void process(String s, long l, long l1) {
                System.out.println("process=" + s + ",uploaded=" + l + ", total=" + l1);
            }
            @Override
            public void complete(String s) {
                System.out.println("complete=" + s);
            }
            @Override
            public void success(String s) {
                System.out.println("success=" + s);
            }

            @Override
            public void error(String s, UploadErrorMsg uploadErrorMsg) {
                System.out.println("error=" + s + ", message=" + uploadErrorMsg.getMessage());
            }
        }, true));
        // 当上传中断，重新恢复的时候，可以指定vid来恢复上传 videoInfo.setVideoPoolId("xxxxxxxxxxx");
        //     videoInfo = new VideoInfo();
        //     videoInfo.setFileLocation("C:\\Users\\Lenovo\\Desktop\\video\\test.mp4");
        //     videoInfo.setVideoPoolId("xxxxxxxxxxx");
        //     System.out.println("vid=" + client.uploadVideoParts(videoInfo), new UploadCallBack() {
        //         @Override
        //         public void start(String s) {
        //             System.out.println("start=" + s);
        //         }
        //         @Override
        //         public void process(String s, long l, long l1) {
        //             System.out.println("process=" + s + ",uploaded=" + l + ", total=" + l1);
        //         }
        //         @Override
        //         public void complete(String s) {
        //             System.out.println("complete=" + s);
        //         }
        //         @Override
        //         public void success(String s) {
        //             System.out.println("success=" + s);
        //         }
        //         @Override
        //         public void error(String s, UploadErrorMsg uploadErrorMsg) {
        //             System.out.println("error=" + s + ", message=" + uploadErrorMsg.getMessage());
        //         }
        //     }, true);
        // }
    }
}
*/
