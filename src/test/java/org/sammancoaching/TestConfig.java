package org.sammancoaching;

import lombok.NoArgsConstructor;
import org.sammancoaching.dependencies.Config;

@NoArgsConstructor
public class TestConfig implements Config {
    @Override
    public boolean sendEmailSummary() {
        return true;
    }
}
