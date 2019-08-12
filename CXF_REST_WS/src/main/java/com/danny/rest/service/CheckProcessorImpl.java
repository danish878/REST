package com.danny.rest.service;

import com.danny.rest.model.ChecksList;
import org.springframework.stereotype.Service;

import javax.ws.rs.container.AsyncResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class CheckProcessorImpl implements CheckProcessor {

    @Override
    public void processChecks(AsyncResponse response, ChecksList checksList) {
        // TODO: logic
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(() -> {
            try {
                //Simulating a long running process
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            if (checksList == null || checksList.getChecks() == null || checksList.getChecks().size() == 0)
//                response.resume(new BadRequestException());
            response.resume(true);

            es.shutdown();
        });

//        new Thread(() -> {
//            if (checksList == null || checksList.getChecks() == null || checksList.getChecks().size() == 0)
//                response.resume(new BadRequestException());
//            response.resume(true);
//        }).start();
    }

    @Override
    public void getChecks(AsyncResponse response) {
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(() -> {
//            try {
//                //Simulating a long running process
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            response.resume("All checks data...");

            es.shutdown();
        });
    }
}
