package vodTest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import org.junit.Test;

public class TestVo3 {
    //账号AK信息请填写(必选)
    private static final String accessKeyId ="LTAI5tGhXGUXiz5pgjeQMqud";
    //账号AK信息请填写(必选)
    private static final String accessKeySecret ="RtKiAi97ZMlC55TQhjSzKoX0F4Uxgx";
    /**
     * 视频上传
     */
    @Test
    public void testUploadVideo() {
//1.音视频上传-本地文件上传
//视频标题(必选)
        String title = "3 - How Does Project Submission Work - upload by sdk";
//本地文件上传和文件流上传时，文件名称为上传文件绝对路径，如:/User/sample/文件名称.mp4(必选)
//文件名必须包含扩展名
        String fileName = "d:\\Users\\西北小老汉\\Desktop\\视频\\奇异系列\\第一个 2 - 副本_AME\\合理（高级色）_AME\\合理（高级色）_AME\\WeChat_20220422124712_2.mp4";
//本地文件上传
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId,accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为1M字节 */
//        request.setPartSize(1 * 1024 * 1024L);
/* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务
器情况指定）*/
//        request.setTaskNum(1);
/* 是否开启断点续传, 默认断点续传功能关闭。当网络不稳定或者程序崩溃时，再次发起相同上
传请求，可以继续未完成的上传任务，适用于超时3000秒仍不能上传完成的大文件。
注意: 断点续传开启后，会在上传过程中将上传位置写入本地磁盘文件，影响文件上传速
度，请您根据实际情况选择是否开启*/
        request.setEnableCheckpoint(false);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");
        //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
/* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情
况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }
}
