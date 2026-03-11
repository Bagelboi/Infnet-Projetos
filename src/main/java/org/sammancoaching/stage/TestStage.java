package org.sammancoaching.stage;

import org.sammancoaching.dependencies.DeploymentEnvironment;
import org.sammancoaching.dependencies.TestStatus;

public class TestStage extends Stage {
    public static class Builder {

        TestStatus testStatus = TestStatus.NO_TESTS;
        TestStatus smokeTestStatus = TestStatus.NO_TESTS;

        public Builder() {}

        public Builder setTestStatus(TestStatus testStatus) {
            this.testStatus = testStatus;
            return this;
        }

        public Builder setSmokeTestStatus(TestStatus smokeTestStatus) {
            this.smokeTestStatus = smokeTestStatus;
            return this;
        }

        public TestStage build() {
            return new TestStage(testStatus, smokeTestStatus);
        }
    }

    private final TestStatus testStatus;
    private final TestStatus smokeTestsStatus;
    private TestStage(TestStatus testStatus, TestStatus smokeTests) {
        super(new StageDependencies());
        this.testStatus = testStatus;
        this.smokeTestsStatus = smokeTests;
    }

    public boolean hasTests() {
        return testStatus != TestStatus.NO_TESTS || smokeTestsStatus != TestStatus.NO_TESTS;
    }


    @Override
    protected STAGE_RESULT runOverride() {
        if (testStatus == TestStatus.FAILING_TESTS || smokeTestsStatus == TestStatus.FAILING_TESTS)
            return STAGE_RESULT.FAILURE;
        return STAGE_RESULT.SUCCESS;
    }
}
