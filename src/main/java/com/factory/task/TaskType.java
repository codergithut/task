package com.factory.task;

/**
 * Created by tianjian on 2020/3/24.
 */
public enum TaskType {

    NODE("node"), DEPEND("depend");
    private String type;

    TaskType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Boolean checkType(String type) {
        for(TaskType e : TaskType.values()) {
            if(e.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
