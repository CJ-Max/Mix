package com.cj.plugin;

import com.android.build.gradle.AppPlugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.logging.LogLevel;
import org.gradle.api.logging.Logger;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.println;

public class WebpPlugin implements Plugin<Project> {
    Logger logger;
    @Override
    public void apply(Project project) {
        logger = project.getLogger();
        println("cjj apply ${project.getName()}");
        logger.log(LogLevel.INFO, "1 webpPlugin apply " + project.getName());
        project.afterEvaluate(project1 -> {
            logger.log(LogLevel.INFO, "2 webpplugin afterEvaluate");
        });
        logger.log(LogLevel.INFO, "3 webpplugin project" + project.getAllprojects().toString());
    }
}