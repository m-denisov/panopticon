package com.group.original.panopticon.io;

import com.group.original.panopticon.App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Flow;

public class ConsoleInputManager implements InputManager, Flow.Publisher<String> {
    private Flow.Subscriber<? super String> subscriber;

    @Override
    public void subscribe(Flow.Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
        subscriber.onSubscribe(new Subscription());
        startMonitoringUserInput();
    }

    private void startMonitoringUserInput() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = null;
            if (subscriber != null) {
                while ((line = reader.readLine()) != null) {
                    subscriber.onNext(line);
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
