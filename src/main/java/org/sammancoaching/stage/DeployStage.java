package org.sammancoaching.stage;

import org.sammancoaching.dependencies.DeploymentEnvironment;
import org.sammancoaching.dependencies.TestStatus;

public class DeployStage extends Stage {

    public static class Builder {
        boolean deploysToStaging = false;
        boolean deploysToProd = false;
        DeploymentEnvironment environment = DeploymentEnvironment.PRODUCTION;

        private final TestStage testStage;

        public Builder(TestStage testStage) {
            this.testStage = testStage;
        }

        public Builder setDeploysToProd(boolean deploysToProd) {
            this.deploysToProd = deploysToProd;
            return this;
        }

        public Builder setDeploysToStaging(boolean deploysToStaging) {
            this.deploysToStaging = deploysToStaging;
            return this;
        }

        public Builder setEnviroment(DeploymentEnvironment environment) {
            this.environment = environment;
            return this;
        }

        public DeployStage build() {
            return new DeployStage(deploysToProd, deploysToStaging, environment, testStage);
        }
    }

    private final boolean deploysToStaging;
    private final boolean deploysToProd;
    private final DeploymentEnvironment environment;

    private DeployStage(boolean deploysToProd, boolean deploysToStaging, DeploymentEnvironment environment, TestStage testStage) {
        super(new StageDependencies(testStage));
        this.environment = environment;
        this.deploysToStaging = deploysToStaging;
        this.deploysToProd = deploysToProd;
    }


    @Override
    protected STAGE_RESULT runOverride() {
        if (!allDependenciesSuccessful())
            return STAGE_RESULT.FAILURE;

        switch (environment) {
            case STAGING:
                return deploysToStaging ? STAGE_RESULT.SUCCESS : STAGE_RESULT.FAILURE;
            case PRODUCTION:
                return deploysToProd ? STAGE_RESULT.SUCCESS : STAGE_RESULT.FAILURE;
            default:
                return STAGE_RESULT.FAILURE;
        }
    }
}
