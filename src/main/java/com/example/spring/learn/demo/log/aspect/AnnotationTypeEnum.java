package com.example.spring.learn.demo.log.aspect;

/**
 * @author clark
 */

public enum AnnotationTypeEnum {
    SERVICE("service")
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
