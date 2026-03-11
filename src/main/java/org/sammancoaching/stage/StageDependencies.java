package org.sammancoaching.stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StageDependencies {
    private List<Stage> stages;

    public StageDependencies(Stage... stages) {
        this.stages = new ArrayList<>();
        this.stages.addAll(Arrays.asList(stages));
    }

    public boolean areSuccessful() {
        return stages.stream().noneMatch( stage -> stage.getResult() == STAGE_RESULT.FAILURE );
    }
}
