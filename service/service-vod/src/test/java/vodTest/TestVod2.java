package vodTest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import static vodTest.InitVod.initVodClient;

public class TestVod2 {
    /*获取播放凭证函数*/
    public static GetVideoPlayAuthResponse getVideoPlayAuth(DefaultAcsClient client) throws Exception {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId("99a55c44b0c241f18fb082329af4b408");
        return client.getAcsResponse(request);
    }

    /*以下为调用示例*/
    public static void main(String[] argv) {
        DefaultAcsClient client = initVodClient("LTAI5tGhXGUXiz5pgjeQMqud", "RtKiAi97ZMlC55TQhjSzKoX0F4Uxgx");
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
            response = getVideoPlayAuth(client);
            //播放凭证
            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            //VideoMeta信息
            System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }

}
