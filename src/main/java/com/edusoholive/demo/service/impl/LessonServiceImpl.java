package com.edusoholive.demo.service.impl;

import com.edusoholive.demo.dto.LessonCreateParams;
import com.edusoholive.demo.dto.LessonDto;
import com.edusoholive.demo.dto.LessonRoomEnterDto;
import com.edusoholive.demo.dto.LessonRoomEnterParams;
import com.edusoholive.demo.mapper.LessonMapper;
import com.edusoholive.demo.repository.LessonRepository;
import com.edusoho.live.sdk.ClientConfig;
import com.edusoho.live.sdk.EsliveApiClient;
import com.edusoho.live.sdk.model.Role;
import com.edusoholive.demo.service.LessonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository repository;

    private final LessonMapper mapper;

    private final EsliveApiClient apiClient;

    public LessonServiceImpl(LessonRepository repository, LessonMapper mapper, Environment env) {
        this.repository = repository;
        this.mapper = mapper;

        var config = new ClientConfig();
        config.setServer(env.getProperty("eslive.server", ""));
        config.setAccessKey(env.getProperty("eslive.accessKey", ""));
        config.setSecretKey(env.getProperty("eslive.secretKey", ""));

        log.info("api client config {}", config);

        this.apiClient = new EsliveApiClient(config);
    }

    @Override
    public List<LessonDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public LessonDto create(LessonCreateParams params) {
        // 省略：参数校验...
        // 省略：鉴权...

        // 调用直播课堂创建接口
        var room = apiClient.roomCreate(mapper.toRoomCreateParams(params));

        var lesson = mapper.toEntity(params);
        // 保存直播课堂的ID
        lesson.setRoomId(room.getId());
        lesson.setCreatedAt(System.currentTimeMillis());
        // 保存数据库
        repository.save(lesson);

        return mapper.toDto(lesson);
    }

    @Override
    public LessonRoomEnterDto getRoomEnterUrl(LessonRoomEnterParams params) {
        var lesson = repository.findById(params.getLessonId())
                .orElseThrow(() -> new RuntimeException("课时不存在"));

        // 省略： 判断当前课堂是否可以进入直播课堂...
        // 省略： 判断当前时间是否允许进入直播课堂...
        // 省略： 调用相关服务，根据用户ID获取用户昵称、角色，并校验用户是否有权限进入当前直播课堂...
        // 这里就先造个假用户，用户ID=1则为"测试老师", 用户ID=2则为"测试学员"
        String username;
        Role role;
        if (params.getUserId() == 1L) {
            username = "测试老师";
            role = Role.SPEAKER;
        } else if (params.getUserId() == 2L) {
            username = "测试学员";
            role = Role.VIEWER;
        } else {
            throw new RuntimeException("用户不存在");
        }

        var url = apiClient.roomGetEnterUrl(lesson.getRoomId(), params.getUserId(), username, role);

        var roomTokenDto = new LessonRoomEnterDto();
        roomTokenDto.setUrl(url);

        return roomTokenDto;
    }
}
