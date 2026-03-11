package org.sammancoaching;

import lombok.Getter;
import org.sammancoaching.dependencies.*;
import org.sammancoaching.stage.STAGE_RESULT;

public class PipelineV2 {
    private final Config config;
    private final Emailer emailer;
    private final Logger log;

    @Getter
    private boolean testsPassed;
    @Getter
    private boolean deploySuccessful;
    private Project project;

    public PipelineV2(Config config, Emailer emailer, Logger log) {
        this.config = config;
        this.emailer = emailer;
        this.log = log;
    }


    private void runTests() {
        testsPassed = !project.hasTests() || project.runTests() == STAGE_RESULT.SUCCESS;

        if (project.hasTests()) {
            if (testsPassed) {
                log.info("Tests passed");
            } else {
                log.error("Tests failed");
            }
        } else {
            log.info("No tests");
        }
    }

    private void deploy() {
        deploySuccessful = testsPassed && project.deploy() == STAGE_RESULT.SUCCESS;

        if (deploySuccessful) {
            log.info("Deployment successful");
        } else {
            log.error("Deployment failed");
        }

    }

    private void sendEmail() {
        if (config.sendEmailSummary()) {
            log.info("Sending email");

            String email_content = "";

            if (testsPassed) {
                if (deploySuccessful) {
                    email_content = "Deployment completed successfully";
                } else {
                    email_content = "Deployment failed";
                }
            } else {
                email_content = "Tests failed";
            }

            emailer.send(email_content);
        } else {
            log.info("Email disabled");
        }
    }

    public void run(Project _project) {
        project = _project;
        testsPassed = false;
        deploySuccessful = false;

        runTests();
        deploy();
        sendEmail();
    }
}
