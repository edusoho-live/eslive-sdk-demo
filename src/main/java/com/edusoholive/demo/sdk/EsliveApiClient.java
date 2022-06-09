package com.edusoholive.demo.sdk;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.edusoholive.demo.sdk.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;

public class EsliveApiClient {

    private static final String POST = "POST";

    private static final String GET = "GET";

    private static final MediaType JSON_TYPE
            = MediaType.get("application/json; charset=utf-8");

    private final OkHttpClient client = new OkHttpClient();

    private final Gson gson = new Gson();

    private String accessKey = "flv_self_aliyun";

    private String secretKey = "testSecretKey4";

    private String server = "live-dev.edusoho.cn";

    public EsliveApiClient(ClientConfig config) {
        if (StringUtils.isNotBlank(config.getServer())) {
            server = config.getServer();
        }
        accessKey = config.getAccessKey();
        secretKey = config.getSecretKey();
    }

    public Pager<RoomMember> memberList(RoomCreateParams params) {
        var pagerType = new TypeToken<Pager<RoomMember>>(){}.getType();
        return request(GET, "member/list", params, pagerType);
    }

    public Room roomCreate(RoomCreateParams params) {
        return request( POST, "room/create", params, Room.class);
    }

//    private <T> List<T> getOfList(String uri, Object params, Class<T> responseClass) {
//
//    }
//
//    private <T> Pager<T> getOfPager(String uri, Object params, Class<T> responseClass) {
//
//    }

    public String roomGetEnterUrl(Long roomId, Long userId, String name, Role role) {
        var token = JWT.create()
                .withKeyId(accessKey)
                .withIssuer("live client api")
                .withClaim("rid", roomId)
                .withClaim("uid", userId)
                .withClaim("name",name)
                .withClaim("role", role.name().toLowerCase())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1800000))
                .sign(Algorithm.HMAC256(secretKey));

        return "https://" + server + "/h5/room/" + roomId.toString() + "/enter?token=" + token;
    }

    private <T> T request(String method, String uri, Object params, Type responseClass) {
        var urlBuilder = new HttpUrl.Builder()
                .scheme("https")
                .host(server)
                .addPathSegment("api-v2")
                .addPathSegments(uri);

        if (GET.equals(method)) {
            Map<String, Object> queries = gson.fromJson(gson.toJson(params), new TypeToken<Map<String, Object>>() {}.getType());
            for(var query : queries.entrySet()) {
                urlBuilder.addQueryParameter(query.getKey(), query.getValue().toString());
            }
        }
        var url = urlBuilder.build();

        var token = JWT.create()
                .withKeyId(accessKey)
                .withIssuer("live api")
                .withExpiresAt(new Date(System.currentTimeMillis() + 300000)) //5分钟
                .sign(Algorithm.HMAC256(secretKey));

        var req = new Request.Builder()
                .url(url)
                .header("User-Agent", "eslive-api-java-sdk v1.0.0")
                .header("Authorization","Bearer " + token);

        if (POST.equals(method)) {
            var body = gson.toJson(params);
            req.post(RequestBody.create(body, JSON_TYPE));
        }

        try {
            var response = client.newCall(req.build()).execute();
            var result = response.body().string();

            return gson.fromJson(result, responseClass);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
