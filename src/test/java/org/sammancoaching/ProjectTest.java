package org.sammancoaching;

import org.junit.jupiter.api.Test;
import org.sammancoaching.dependencies.DeploymentEnvironment;
import org.sammancoaching.dependencies.Project;
import org.sammancoaching.stage.STAGE_RESULT;
import org.sammancoaching.dependencies.TestStatus;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectTest {

    @Test
    void deployTest() {
        //Em prod e prod que da certo
        Project project = new Project.ProjectBuilder(DeploymentEnvironment.PRODUCTION).setDeploysSuccessfully(true).build();
        assertEquals(project.deploy(), STAGE_RESULT.FAILURE);
        project.runTests();
        assertEquals(project.deploy(), STAGE_RESULT.SUCCESS);

        //Em prod mas só staging que da certo
        project = new Project.ProjectBuilder(DeploymentEnvironment.PRODUCTION).setDeploysSuccessfullyToStaging(true).build();
        assertEquals(project.deploy(), STAGE_RESULT.FAILURE);
        project.runTests();
        assertEquals(project.deploy(), STAGE_RESULT.FAILURE);

        //Em staging e staging vai
        project = new Project.ProjectBuilder(DeploymentEnvironment.STAGING).setDeploysSuccessfullyToStaging(true).build();
        assertEquals(project.deploy(), STAGE_RESULT.FAILURE);
        project.runTests();
        assertEquals(project.deploy(), STAGE_RESULT.SUCCESS);

        //Em staging mas só prod vai
        project = new Project.ProjectBuilder(DeploymentEnvironment.STAGING).setDeploysSuccessfully(true).build();
        assertEquals(project.deploy(), STAGE_RESULT.FAILURE);
        project.runTests();
        assertEquals(project.deploy(), STAGE_RESULT.FAILURE);
    }

    @Test
    void testsTest() {
        Project project = new Project.ProjectBuilder(DeploymentEnvironment.PRODUCTION).setTestStatus(TestStatus.NO_TESTS).build();
        assertEquals(project.hasTests(), false);
        assertEquals(project.runTests(), STAGE_RESULT.SUCCESS);

        project = new Project.ProjectBuilder(DeploymentEnvironment.PRODUCTION).setTestStatus(TestStatus.FAILING_TESTS).build();
        assertEquals(project.hasTests(), true);
        assertEquals(project.runTests(), STAGE_RESULT.FAILURE);

        project = new Project.ProjectBuilder(DeploymentEnvironment.PRODUCTION).setTestStatus(TestStatus.PASSING_TESTS).build();
        assertEquals(project.hasTests(), true);
        assertEquals(project.runTests(), STAGE_RESULT.SUCCESS);

    }
}
