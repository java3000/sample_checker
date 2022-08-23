package ru.vk.competition.minchecker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vk.competition.minchecker.service.SingleQueryService;
import ru.vk.competition.minchecker.service.StartService;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StartController {

    private final StartService startService;
    private final SingleQueryService singleQueryService;

    @GetMapping("/start")
    public void onStartMission() {
        startService.onStartMission();
        startService.onStartMission2();
        startService.onStartMission3();
        startService.onStartMission4();
        startService.onStartMission6();
        startService.onStartMission7();
        startService.onStartMission8();
    }
}
