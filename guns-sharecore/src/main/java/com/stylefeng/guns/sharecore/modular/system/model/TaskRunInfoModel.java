package com.stylefeng.guns.sharecore.modular.system.model;

import java.util.Date;

public class TaskRunInfoModel {
    private String taskName;

    private Date successLastRunTime;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public Date getSuccessLastRunTime() {
        return successLastRunTime;
    }

    public void setSuccessLastRunTime(Date successLastRunTime) {
        this.successLastRunTime = successLastRunTime;
    }
}