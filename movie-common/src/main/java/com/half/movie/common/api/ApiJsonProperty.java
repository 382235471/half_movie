package com.half.movie.common.api;

public @interface ApiJsonProperty {
    /**
     * 参数名称
     * */
    String name();

    /**
     * 参数的中文含义
     * */
    String value() default "";

    /**
     * 参数示例
     * */
    String example() default "";

    /**
     * 支持各种基础数据类型、或包装的数据类型
     * */
    String dataType() default "string";

    /**
     * 参数描述（想要在参数旁有中文解析，要给此属性赋值）
     * */
    String description() default "";
    /**
     * 是否必填
     * */
    boolean required() default false;

    //支持string 和 int
    Class type() default String.class;
}