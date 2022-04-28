package com.api.ms.mongodb.config.cascade;

import com.mongodb.lang.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

@Configuration
public class DocumentReferenceFieldCallback implements ReflectionUtils.FieldCallback {
    private boolean idFound;
    public boolean isIdFound() {
        return idFound;
    }



    public void doWith(@NonNull Field field) throws IllegalArgumentException {

        ReflectionUtils.makeAccessible(field);

        if (field.isAnnotationPresent(Id.class)) {
            idFound = true;
        }

    }

}