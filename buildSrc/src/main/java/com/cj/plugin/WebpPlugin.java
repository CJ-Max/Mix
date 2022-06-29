package com.cj.plugin;

import com.android.build.gradle.AppPlugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.logging.LogLevel;
import org.gradle.api.logging.Logger;

public class WebpPlugin implements Plugin<Project> {
    Logger logger;
    @Override
    public void apply(Project project) {
        logger = project.getLogger();
        project.afterEvaluate(project1 -> {
            logger.log(LogLevel.DEBUG, "test WebpPlugin apply");
        });
    }
}