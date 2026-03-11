package org.sammancoaching.stage;

import lombok.Getter;
import org.sammancoaching.dependencies.DeploymentEnvironment;

import java.util.List;

public abstract class Stage {

    @Getter
    protected STAGE_RESULT result = STAGE_RESULT.FAILURE;
    protected StageDependencies dependencies;


    protected Stage(StageDependencies dependencies) {
        this.dependencies = dependencies;
    }

    protected boolean allDependenciesSuccessful() {
        return dependencies.areSuccessful();
    }

    protected abstract STAGE_RESULT runOverride();

    public void run() {
        this.result = runOverride();
    }

}