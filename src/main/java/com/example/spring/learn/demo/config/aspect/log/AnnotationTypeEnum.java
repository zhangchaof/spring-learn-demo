package com.example.spring.learn.demo.config.aspect.log;

/**
 * @author clark
 */

public enum AnnotationTypeEnum {
    CONTROLLER("controller"),
    SERVICE("service"),
    ;
    /**
     * 描述
     */
    private String name;

    private AnnotationTypeEnum(String name) {
        this.name = name;
    }


    public String getName() {
        return this.name;
    }
}
