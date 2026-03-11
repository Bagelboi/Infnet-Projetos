package org.sammancoaching;

import lombok.NoArgsConstructor;
import org.sammancoaching.dependencies.Emailer;

@NoArgsConstructor
public class TestEmailer implements Emailer {
    @Override
    public void send(String message) {
        System.out.println("EMAIL SYSTEM: " + message);
    }
}
