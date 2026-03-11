package org.sammancoaching;

import org.junit.jupiter.api.Test;
import org.sammancoaching.dependencies.DeploymentEnvironment;
import org.sammancoaching.dependencies.Project;
import org.sammancoaching.dependencies.TestStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

public class PipelineTest {

    PipelineV2 pipeline = new PipelineV2(new TestConfig(), new TestEmailer(), new CapturingLogger() );

    @Test
    void run() {
        pipeline.run(new Project.ProjectBuilder(DeploymentEnvironment.PRODUCTION).build());
    }

    @Test
    void testsTest() {
        Project project = new Project.ProjectBuilder(DeploymentEnvironment.PRODUCTION).setTestStatus(TestStatus.PASSING_TESTS)
                .setDeploysSuccessfully(true).build();
        pipeline.run(project);
        assertTrue(pipeline.isTestsPassed());

         project = new Project.ProjectBuilder(DeploymentEnvironment.PRODUCTION).setTestStatus(TestStatus.PASSING_TESTS)
                .setDeploysSuccessfully(true).build();
        pipeline.run(project);
        assertTrue(pipeline.isTestsPassed());

         project = new Project.ProjectBuilder(DeploymentEnvironment.PRODUCTION).setTestStatus(TestStatus.FAILING_TESTS).build();
        pipeline.run(project);
        assertFalse(pipeline.isTestsPassed());
    }

    @Test
    void deployTest() {
        //Deploy passa
        Project project = new Project.ProjectBuilder(DeploymentEnvironment.PRODUCTION).setTestStatus(TestStatus.PASSING_TESTS)
                .setDeploysSuccessfully(true).build();
        pipeline.run(project);
        assertTrue(pipeline.isDeploySuccessful());
        //Deploy falha
         project = new Project.ProjectBuilder(DeploymentEnvironment.PRODUCTION).setTestStatus(TestStatus.PASSING_TESTS)
                 .setDeploysSuccessfully(false).build();
        pipeline.run(project);
        assertFalse(pipeline.isDeploySuccessful());
        //Testes falharam mas deploy é pra suceder
        project = new Project.ProjectBuilder(DeploymentEnvironment.PRODUCTION).
                setTestStatus(TestStatus.FAILING_TESTS).setDeploysSuccessfully(true).build();
        pipeline.run(project);
        assertFalse(pipeline.isDeploySuccessful());
    }
}
