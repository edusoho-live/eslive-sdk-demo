package com.edusoholive.demo.sdk;

import com.edusoholive.demo.sdk.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class EsLiveApiClientTests {

    private EsliveApiClient client;

    private RoomSimple testRoom;

    public EsLiveApiClientTests() {
        var config = new ClientConfig();
        config.setServer("live-dev.edusoho.cn");
        config.setAccessKey("flv_self_aliyun");
        config.setSecretKey("testSecretKey4");
        client = new EsliveApiClient(config);

        testRoom = createTestRoom();
    }

    @Test void roomCreate() {
        var params = new RoomCreateParams();
        params.setName("Java API SDK Unit Test - " + System.currentTimeMillis());
        params.setStartAt(System.currentTimeMillis() + 600000);
        params.setEndAt(System.currentTimeMillis() + 1200000);

        var room = client.roomCreate(params);

        assertNotNull(room.getId());
        assertEquals(params.getName(), room.getName());
        assertEquals(params.getStartAt(), room.getStartAt());
        assertEquals(params.getEndAt(), room.getEndAt());
    }

    @Test void roomGet() {
        var room = client.roomGet(testRoom.getId());
        assertEquals(testRoom.getId(), room.getId());
    }

    @Test void roomUpdate() {
        var params = new RoomUpdateParams();
        params.setId(testRoom.getId());
        params.setName(testRoom.getName() + "-" + System.currentTimeMillis());
        params.setStartAt(testRoom.getStartAt() + 600000);
        params.setEndAt(testRoom.getEndAt() + 600000);

        var updated = client.roomUpdate(params);

        assertEquals(params.getName(), updated.getName());
        assertEquals(params.getStartAt(), updated.getStartAt());
        assertEquals(params.getEndAt(), updated.getEndAt());
    }

    @Test void roomClose() {
        var room = createTestRoom();
        var closed = client.roomClose(room.getId());

        assertTrue(closed.getOk());
    }

    @Test void roomDelete() {
        var room = createTestRoom();
        var deleted = client.roomDelete(room.getId());

        assertTrue(deleted.getOk());
    }

    @Test void memberList() {
        var params = new MemberListParams();
        params.setRoomId(testRoom.getId());
        params.setOffset(0L);
        params.setLimit(10L);
        var members = client.memberList(params);

        assertEquals(0, members.getData().size());
        assertEquals(0, members.getTotal());
    }

    @Test void memberListVisits() {
        var params = new MemberListVisitsParams();
        params.setRoomId(testRoom.getId());
        params.setOffset(0L);
        params.setLimit(10L);
        var members = client.memberListVisits(params);

        assertEquals(0, members.getData().size());
        assertEquals(0, members.getTotal());
    }

    @Test void replayGet() {
        var replay = client.replayGet(testRoom.getId());

        log.info("replay {}", replay);
    }

    @Test void replayGets() {
        var roomIds = List.of(1L, 2L, 3L);
        var replays = client.replayGets(roomIds);
        log.info("replays {}", replays);
    }

    @Test void replayDelete() {
        var deleted = client.replayDelete(testRoom.getId());
        log.info("deleted {}", deleted);
    }

    private RoomSimple createTestRoom() {
        var params = new RoomCreateParams();
        params.setName("Java API SDK Unit Test - " + System.currentTimeMillis());
        params.setStartAt(System.currentTimeMillis() + 600000);
        params.setEndAt(System.currentTimeMillis() + 1200000);

        return client.roomCreate(params);
    }
}
