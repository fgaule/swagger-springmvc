package com.mangofactory.swagger.models.property

import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
import spock.lang.Specification

class PropertyUtilsSpec extends Specification {
  def "Should return the internal bean name"() {
    given:
      BeanPropertyDefinition beanPropertyDefinition = Mock(BeanPropertyDefinition)

    when:
      def name = PropertyUtils.beanPropertyByInternalName().apply(beanPropertyDefinition)
    then:
      1 * beanPropertyDefinition.getInternalName() >> "aName"
      name == "aName"
  }

  def "Should be instantiated when needed for test coverage"() {
    when:
      def propertyUtils = new PropertyUtils()
    then:
      propertyUtils.class == PropertyUtils
  }
}