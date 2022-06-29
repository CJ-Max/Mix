package com.cjj.mylibrary

import com.android.build.gradle.AppPlugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project



/**
 * Created by SpiderG on 6/24/22.
 * About :
 * 调试 MyPlugin 均在引入plugin的地方去处理
 *  1.创建一个remote configuration 如
 *      -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005
 *      选择module为已实现的Plugin
 *  2.在app module gradle 右侧tasks list 找到build 点击 create 一个 task如 Mix:app【hello】，并生成 Run Configurations 目录
 *  3.选中 Mix:app[build] 点击 Debug按钮, 打赏断点即可
 *  tips:
 *  重复请用端口可能会被占用，可以杀掉进程重新再来
 *  sudo lsof -i tcp:5005
 *  sudo kill -9 PID
 *  处理 webp 图片转换
 */

public class MyPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.afterEvaluate {}
        project.tasks.register("hello") {
            doLast {
                println("test MyPlugin apply")
            }
        }
    }
}