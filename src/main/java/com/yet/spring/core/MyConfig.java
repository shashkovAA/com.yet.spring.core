package com.yet.spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;

@Configuration
@ComponentScan("com.yet.spring.core")
public class MyConfig {
    @Bean
    @Scope("prototype")
    public Event event() {
        return new Event(new Date(),dateFormat());
    }

    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getDateTimeInstance(1,1);
        }

}
