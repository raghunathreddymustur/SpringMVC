package com.raghu.controller;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class SleepUtil {
    public static final int SLEEP_TIME_SECONDS = 5;

    @SneakyThrows
    public static void sleep() {
        Thread.sleep(TimeUnit.SECONDS.toMillis(SLEEP_TIME_SECONDS));
    }
}
