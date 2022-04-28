package com.api.ms.mongodb.config.cascade;

import com.api.ms.mongodb.config.cascade.CascadeSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.mapping.MappingException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.util.ReflectionUtils;
import java.util.List;

@Configuration
public class CascadeSaveMongoEventListener extends AbstractMongoEventListener<Object> {

        @Autowired
        private DocumentReferenceFieldCallback callback;

        @Autowired
        private MongoOperations mongoOperations;

        @Override
        public void onBeforeConvert(BeforeConvertEvent<Object> event) {

            //O Reflection, em poucas palavras, serve para determinar métodos e atributos que serão utilizados
            //de determinada classe em tempo de execução.

            Object source = event.getSource();
            ReflectionUtils.doWithFields(source.getClass(), field -> {

                ReflectionUtils.makeAccessible(field);

                if (field.isAnnotationPresent(DocumentReference.class) && field.isAnnotationPresent(CascadeSave.class)){
                    final Object fieldValue = field.get(source);

                    if(fieldValue instanceof List<?>){
                        for (Object item : (List<?>)fieldValue){
                            checkNSave(item);
                        }
                    }else{
                        checkNSave(fieldValue);
                    }
                }
            });
        }

        private void checkNSave(Object fieldValue){

            ReflectionUtils.doWithFields(fieldValue.getClass(), callback);

            if (!callback.isIdFound()){
                throw new MappingException("Oops, something went wrong. Child doesn't have @Id?");
            }

            mongoOperations.save(fieldValue);
    }



}
