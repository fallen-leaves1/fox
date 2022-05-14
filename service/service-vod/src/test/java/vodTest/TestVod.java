package vodTest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;

import java.util.List;

import static vodTest.InitVod.initVodClient;

public class TestVod {
//    获取地址如果加密的话获取地址无效需要获取凭证才能播放
    public static void main(String[] argv) throws Exception{
        DefaultAcsClient client = initVodClient("LTAI5tGhXGUXiz5pgjeQMqud", "RtKiAi97ZMlC55TQhjSzKoX0F4Uxgx");
//        创建获取视频地址的response
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        try {
            response = getPlayInfo(client);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }

    private static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client) throws Exception{
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId("99a55c44b0c241f18fb082329af4b408");
        return client.getAcsResponse(request);
    }

}
