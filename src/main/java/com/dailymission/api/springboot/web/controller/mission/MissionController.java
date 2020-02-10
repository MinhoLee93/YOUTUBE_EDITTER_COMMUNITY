package com.dailymission.api.springboot.web.controller.mission;

import com.dailymission.api.springboot.web.dto.mission.MissionListResponseDto;
import com.dailymission.api.springboot.web.dto.mission.MissionSaveRequestDto;
import com.dailymission.api.springboot.web.dto.mission.MissionUpdateRequestDto;
import com.dailymission.api.springboot.web.service.mission.MissionService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/api/mission")
    public Long save(@RequestPart("requestJson") String requestJson, @RequestPart("file") MultipartFile file) throws Exception {

        MissionSaveRequestDto requestDto = new Gson().fromJson(requestJson, MissionSaveRequestDto.class);

        return missionService.save(requestDto, file);
    }

    @GetMapping("/api/mission/{id}")
    public String findById(@PathVariable Long id){
        String json  = new Gson().toJson(missionService.findById(id));

        return json;
    }

    @PutMapping("/api/mission/{id}")
    public Long update(@PathVariable Long id, @RequestBody String requestJson) {

        MissionUpdateRequestDto requestDto = new Gson().fromJson(requestJson, MissionUpdateRequestDto.class);

        return missionService.update(id, requestDto);
    }

    @PutMapping("/api/mission/{id}/image")
    public Long updateImage(@PathVariable Long id, @RequestPart("file") MultipartFile file) throws IOException {

        return missionService.updateImage(id, file);
    }


    @DeleteMapping("/api/mission/{id}")
    public Long delete(@PathVariable Long id){
        missionService.delete(id);

        return id;
    }

    @GetMapping("/api/mission/all")
    public String all(){
        List<MissionListResponseDto> responseDtoList = missionService.findAllDesc();
        String json = new Gson().toJson(responseDtoList);

        return json;
    }
}
