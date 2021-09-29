package ch.zuehlke.fullstack.hackathon.controller;

import ch.zuehlke.fullstack.hackathon.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;


@Controller
public class SocketController {

    private GameService gameService;
    private final SimpMessagingTemplate template;
    private volatile Map<Long, Map<Timer, CountGameTime>> timerMap = new HashMap<>();

    @Autowired
    SocketController(GameService gameService, SimpMessagingTemplate template) {
        this.gameService = gameService;
        this.template = template;
    }


    @SubscribeMapping("/send/start/{id}")
    public void sendStart(@DestinationVariable long id) {
        Timer timer = new Timer(String.valueOf(id));
        ZonedDateTime now = ZonedDateTime.now();
        CountGameTime countGameTime = new CountGameTime(template, now, id);
        timer.schedule(countGameTime, 0, 100);
        Map<Timer, CountGameTime> count = new HashMap<>();
        count.put(timer, countGameTime);
        timerMap.put(id, count);

        this.template.convertAndSend("/send/start/" + id, id);
    }

    @SubscribeMapping("/send/finish/{id}")
    public void sendFinish(@DestinationVariable long id) {
        Map<Timer, CountGameTime> threads = timerMap.get(id);
        Optional<CountGameTime> countGameTimeOptional = threads.values().stream().findFirst();
        if (countGameTimeOptional.isPresent()) {
            CountGameTime countGameTime = countGameTimeOptional.get();
            long milliSecs = countGameTime.time;
            countGameTime.cancel();
            Optional<Timer> timerOptional = threads.keySet().stream().findFirst();
            if (timerOptional.isPresent()) {
                timerOptional.get().purge();
                timerOptional.get().cancel();
                timerMap.remove(id);
                String result = this.gameService.saveNewGameTime(id, milliSecs);
                this.template.convertAndSend("/send/finish/" + id, "success - " + result);
            } else {
                this.template.convertAndSend("/send/finish/" + id, "error");
            }
        } else {
            this.template.convertAndSend("/send/finish/" + id, "error");
        }
    }


    class CountGameTime extends TimerTask {

        SimpMessagingTemplate simpMessagingTemplate;
        long id;
        ZonedDateTime now;
        long time;

        public CountGameTime(SimpMessagingTemplate simpMessagingTemplate, ZonedDateTime now, long id) {
            this.simpMessagingTemplate = simpMessagingTemplate;
            this.id = id;
            this.now = now;
        }

        public void run() {
            time = now.until(ZonedDateTime.now(), ChronoUnit.MILLIS) / 100;
            this.simpMessagingTemplate.convertAndSend("/send/start/" + id, time);
        }
    }
}
