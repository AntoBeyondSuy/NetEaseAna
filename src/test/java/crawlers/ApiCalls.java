package crawlers;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ApiCalls {


    public static void searchAPI() {
        String keywords = "七里香";
        JSONObject jsonObject = HttpRequests.sendGet("http://localhost:3000/search",
                "keywords=" + keywords + "&limit=5");

        JSONArray songsArray = jsonObject.getJSONObject("result").getJSONArray("songs");
        System.out.println("搜索结果：");
        for (int i = 0; i < songsArray.size(); i++) {
            System.out.println("+-------------------" + i + "------------------+");
            JSONObject subObject = songsArray.getJSONObject(i);
            System.out.println("歌曲名 - " + subObject.getString("name"));
            System.out.println("歌曲ID - " + subObject.getIntValue("id"));
            System.out.println("歌手名 - " + subObject.getJSONArray("artists").getJSONObject(0).getString("name"));
            System.out.println("歌手ID - " + subObject.getJSONArray("artists").getJSONObject(0).getIntValue("id"));
            System.out.println("专辑名 - " + subObject.getJSONObject("album").getString("name"));
            System.out.println("专辑ID - " + subObject.getJSONObject("album").getIntValue("id"));
        }
    }

    public static void singerRankAPI() {
        JSONObject jsonObject = HttpRequests.sendGet("http://localhost:3000/toplist/artist",
                "");

        JSONArray songsArray = jsonObject.getJSONObject("list").getJSONArray("artists");
        // songsArray.size() = 100
        for (int i = 0; i < 10; i++) {
            System.out.println("+-------------------" + i + "------------------+");
            JSONObject subObject = songsArray.getJSONObject(i);
            System.out.println("歌手名 - " + subObject.getString("name"));
            System.out.println("歌手ID - " + subObject.getIntValue("id"));
            System.out.println("照片 - " + subObject.getString("picUrl"));
            System.out.println("得分 - " + subObject.getIntValue("score"));
            System.out.println("上期排名 - " + subObject.getIntValue("lastRank"));

        }
    }

    public static void main(String[] args) {
        ApiCalls.singerRankAPI();
    }
}
