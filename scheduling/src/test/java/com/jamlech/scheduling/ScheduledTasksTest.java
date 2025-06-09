package com.jamlech.scheduling;

import org.awaitility.Awaitility;
import org.awaitility.Durations;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.TestPropertySource;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;

@SpringBootTest
@TestPropertySource(
        properties = {
                "scheduled.tasks.enabled=true",
                "logging.level.com.example.ScheduledTasks=DEBUG"
        }
)
public class ScheduledTasksTest {

   @TestConfiguration
   static class TestConfig{
       @Bean
       @Primary
       public ScheduledTasks scheduledTasks(){
           return Mockito.spy(new ScheduledTasks());
       }
   }
   @Autowired
   private ScheduledTasks tasks;

    @Test
    public void reportCurrentTime() {
        Awaitility.await()
                .atMost(Durations.TEN_SECONDS)
                .pollInterval(Durations.ONE_HUNDRED_MILLISECONDS)
                .untilAsserted(()->{
                    Mockito.verify(tasks,Mockito.atLeast(2)).reportCurrentTime();
                });
    }
}