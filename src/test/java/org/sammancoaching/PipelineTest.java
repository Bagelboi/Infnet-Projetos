package org.sammancoaching;

import org.junit.jupiter.api.Test;
import org.sammancoaching.dependencies.Project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PipelineTest {

    Pipeline pipeline = new Pipeline(new TestConfig(), new TestEmailer(), new CapturingLogger() );

    @Test
    void run() {
        pipeline.run(new Project.ProjectBuilder().build());
    }

}
