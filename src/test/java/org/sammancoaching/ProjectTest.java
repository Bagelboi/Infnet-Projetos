package org.sammancoaching;

import org.junit.jupiter.api.Test;
import org.sammancoaching.dependencies.DeploymentEnvironment;
import org.sammancoaching.dependencies.Project;
import org.sammancoaching.dependencies.TestStatus;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectTest {

    @Test
    void deployTest() {
        Project project = new Project.ProjectBuilder().setDeploysSuccessfullyToStaging(true).build();
        assertEquals(project.deploy(DeploymentEnvironment.STAGING), "success");
        project = new Project.ProjectBuilder().setDeploysSuccessfully(true).build();
        assertEquals(project.deploy(DeploymentEnvironment.PRODUCTION), "success");
        project = new Project.ProjectBuilder().build();
        assertEquals(project.deploy(DeploymentEnvironment.PRODUCTION), "failure");
        assertEquals(project.deploy(DeploymentEnvironment.STAGING), "failure");
    }

    @Test
    void testsTest() {
        Project project = new Project.ProjectBuilder().setTestStatus(TestStatus.NO_TESTS).build();
        assertEquals(project.hasTests(), false);
        assertEquals(project.runTests(), "failure");
        project = new Project.ProjectBuilder().setTestStatus(TestStatus.FAILING_TESTS).build();
        assertEquals(project.hasTests(), true);
        assertEquals(project.runTests(), "failure");
        project = new Project.ProjectBuilder().setTestStatus(TestStatus.PASSING_TESTS).build();
        assertEquals(project.hasTests(), true);
        assertEquals(project.runTests(), "success");

    }
}
