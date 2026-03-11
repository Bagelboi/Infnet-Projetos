package org.sammancoaching.dependencies;

import org.sammancoaching.stage.DeployStage;
import org.sammancoaching.stage.STAGE_RESULT;
import org.sammancoaching.stage.TestStage;

import static org.sammancoaching.dependencies.TestStatus.NO_TESTS;
import static org.sammancoaching.dependencies.TestStatus.PASSING_TESTS;

public class Project {
    private final TestStage testStage;
    private final DeployStage deployStage;

    private Project(TestStage testStage, DeployStage deployStage) {
        this.testStage = testStage;
        this.deployStage = deployStage;
    }

    public boolean hasTests() {
        return testStage.hasTests();
    }

    public STAGE_RESULT runTests() {
        testStage.run();
        return testStage.getResult();
    }

    public STAGE_RESULT deploy() {
        deployStage.run();
        return deployStage.getResult();
    }

    public static class ProjectBuilder {
        private DeploymentEnvironment environment;
        private boolean deploysSuccessfully = false;
        private TestStatus testStatus = NO_TESTS;
        private boolean deploysSuccessfullyToStaging = false;
        private TestStatus smokeTestStatus = NO_TESTS;

        public ProjectBuilder setTestStatus(TestStatus testStatus) {
            this.testStatus = testStatus;
            return this;
        }

        public ProjectBuilder(DeploymentEnvironment environment) {
            this.environment = environment;
        }

        public ProjectBuilder setSmokeTestStatus(TestStatus smokeTestStatus) {
            this.smokeTestStatus = smokeTestStatus;
            return this;
        }

        public ProjectBuilder setDeploysSuccessfully(boolean deploysSuccessfully) {
            this.deploysSuccessfully = deploysSuccessfully;
            return this;
        }

        public ProjectBuilder setDeploysSuccessfullyToStaging(boolean deploysSuccessfully) {
            this.deploysSuccessfullyToStaging = deploysSuccessfully;
            return this;
        }

        public Project build() {
            TestStage test_stage = new TestStage.Builder().setSmokeTestStatus(smokeTestStatus).setTestStatus(testStatus).build();
            return new Project( test_stage,
                    new DeployStage.Builder(test_stage).setDeploysToStaging(deploysSuccessfullyToStaging)
                            .setDeploysToProd(deploysSuccessfully).setEnviroment(environment).build());
        }
    }
}
