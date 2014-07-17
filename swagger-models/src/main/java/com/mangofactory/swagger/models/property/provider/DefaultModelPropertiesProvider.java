package com.mangofactory.swagger.models.property.provider;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangofactory.swagger.models.property.ModelProperty;
import com.mangofactory.swagger.models.property.bean.BeanModelPropertyProvider;
import com.mangofactory.swagger.models.property.constructor.ConstructorModelPropertyProvider;
import com.mangofactory.swagger.models.property.field.FieldModelPropertyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.google.common.collect.Lists.*;

@Component
public class DefaultModelPropertiesProvider implements ModelPropertiesProvider {

  private final FieldModelPropertyProvider fieldModelPropertyProvider;
  private final BeanModelPropertyProvider beanModelPropertyProvider;
  private final ConstructorModelPropertyProvider constructorModelPropertyProvider;

  @Autowired
  public DefaultModelPropertiesProvider(BeanModelPropertyProvider beanModelPropertyProvider,
                                        FieldModelPropertyProvider fieldModelPropertyProvider,
                                        ConstructorModelPropertyProvider constructorModelPropertyProvider) {
    this.beanModelPropertyProvider = beanModelPropertyProvider;
    this.fieldModelPropertyProvider = fieldModelPropertyProvider;
    this.constructorModelPropertyProvider = constructorModelPropertyProvider;
  }

  @Override
  public Iterable<? extends ModelProperty> propertiesForSerialization(ResolvedType type) {
    ArrayList<ModelProperty> modelProperties = newArrayList(fieldModelPropertyProvider.provideSerializableProperties
            (type));
    modelProperties.addAll(beanModelPropertyProvider.provideSerializableProperties(type));
    modelProperties.addAll(constructorModelPropertyProvider.provideSerializableProperties(type));
    return modelProperties;
  }

  @Override
  public Iterable<? extends ModelProperty> propertiesForDeserialization(ResolvedType type) {
    ArrayList<ModelProperty> modelProperties = newArrayList(fieldModelPropertyProvider
            .provideDeserializableProperties(type));


    modelProperties.addAll(beanModelPropertyProvider.provideDeserializableProperties(type));
    modelProperties.addAll(constructorModelPropertyProvider.provideDeserializableProperties(type));
    
    return modelProperties;
  }

  /**
   * TODO: This setter should not exist. Why is it used?
   *
   * @param objectMapper
   */
  public void setObjectMapper(ObjectMapper objectMapper) {
    this.beanModelPropertyProvider.setObjectMapper(objectMapper);
    this.fieldModelPropertyProvider.setObjectMapper(objectMapper);
  }
}

