package com.example.LeeGamja.time;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
public class TimeGet {
    LocalTime now = LocalTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
    String time = now.format(formatter);
}
