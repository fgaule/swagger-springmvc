package com.mangofactory.swagger.models.property;

import com.fasterxml.classmate.ResolvedType;
import com.mangofactory.swagger.models.property.ModelProperty;

import java.util.List;

/**
 * @author fgaule
 * @since 17/07/2014
 */
public interface ModelPropertyProvider {
  List<? extends ModelProperty> provideSerializableProperties(ResolvedType resolvedType);

  List<? extends ModelProperty> provideDeserializableProperties(ResolvedType resolvedType);
}
