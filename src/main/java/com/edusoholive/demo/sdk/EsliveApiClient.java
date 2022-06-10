package com.edusoholive.demo.sdk;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.edusoholive.demo.sdk.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class EsliveApiClient {

    private static final String POST = "POST";

    private static final String GET = "GET";

    private static final MediaType JSON_TYPE
            = MediaType.get("application/json; charset=utf-8");

    private final OkHttpClient client = new OkHttpClient();

    private final Gson gson = new Gson();

    private String accessKey = "";

    private String secretKey = "";

    private String server = "";

    public EsliveApiClient(ClientConfig config) {
        if (StringUtils.isNotBlank(config.getServer())) {
            server = config.getServer();
        }
        accessKey = config.getAccessKey();
        secretKey = config.getSecretKey();
    }

    public Room roomGet(Long id) {
        var params = Map.of("id", id.toString());
        return request(GET, "room/get", params, Room.class);
    }

    public RoomSimple roomCreate(RoomCreateParams params) {
        return request( POST, "room/create", params, RoomSimple.class);
    }

    public RoomSimple roomUpdate(RoomUpdateParams params) {
        return request( POST, "room/update", params, RoomSimple.class);
    }

    public BooleanResponse roomClose(Long id) {
        var params = Map.of("id", id.toString());
        return request(POST, "room/close", params, BooleanResponse.class);
    }

    public BooleanResponse roomDelete(Long id) {
        var params = Map.of("id", id.toString());
        return request(POST, "room/delete", params, BooleanResponse.class);
    }

    public Pager<Member> memberList(MemberListParams params) {
        var pagerType = new TypeToken<Pager<Member>>(){}.getType();
        return request(GET, "member/list", params, pagerType);
    }

    public Pager<MemberVisit> memberListVisits(MemberListVisitsParams params) {
        var pagerType = new TypeToken<Pager<MemberVisit>>(){}.getType();
        return request(GET, "member/listVisits", params, pagerType);
    }

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

    public Replay replayGet(Long roomId) {
        var params = Map.of("roomId", roomId.toString());
        return request(GET, "replay/get", params, Replay.class);
    }

    public List<Replay> replayGets(List<Long> roomIds) {
        var roomIdsStr = roomIds.stream().map(Object::toString).collect(Collectors.joining(","));

        log.info("room id str: {}", roomIdsStr);

        var params = Map.of("roomIds", roomIdsStr);

        var listType = new TypeToken<List<MemberVisit>>(){}.getType();
        return request(GET, "replay/gets", params, listType);
    }

    public BooleanResponse replayDelete(Long roomId) {
        var params = Map.of("roomId", roomId.toString());
        return request(POST, "replay/delete", params, BooleanResponse.class);
    }

    private <T> T request(String method, String uri, Object params, Type responseClass) {
        var urlBuilder = new HttpUrl.Builder()
                .scheme("https")
                .host(server)
                .addPathSegment("api-v2")
                .addPathSegments(uri);

        if (GET.equals(method)) {
            Map<String, Object> queries;
            if (params instanceof QueryParams) {
                queries = ((QueryParams) params).toQueryParams();
            } else {
                queries = gson.fromJson(gson.toJson(params), new TypeToken<Map<String, Object>>() {}.getType());
            }

            for(var query : queries.entrySet()) {
                urlBuilder.addQueryParameter(query.getKey(), query.getValue().toString());
            }
        }
        var url = urlBuilder.build();

        log.info(" request: {} {}", method, url.toString());

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

            log.info("request body: {}", body);

            req.post(RequestBody.create(body, JSON_TYPE));
        }

        try {
            var response = client.newCall(req.build()).execute();
            var result = response.body().string();

            log.info("response: {}", result);

            return gson.fromJson(result, responseClass);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
