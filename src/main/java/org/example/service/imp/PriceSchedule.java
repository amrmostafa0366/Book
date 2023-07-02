package org.example.service.imp;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PriceSchedule {

    Logger logger = LoggerFactory.getLogger(PriceSchedule.class);

    @Scheduled(fixedRateString = "${price.interval}")
    @SchedulerLock(name = "bookComputePrice")
    @Async
    public void computePrice() throws InterruptedException {
        Thread.sleep(4000);
        logger.info("compute price >>" + LocalDateTime.now());
    }
}
