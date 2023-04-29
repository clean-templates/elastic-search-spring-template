package com.rolandsall.elastic.search.spring.template;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(
        packages = ".",
        importOptions = {
                ImportOption.DoNotIncludeJars.class,
                ImportOption.DoNotIncludeTests.class,
        }
)
public class ElasticSearchSpringTemplateServiceArchitectureTest {
    private static final String CORE_PACKAGE = "..elastic.search.spring.template.core..";
    private static final String API_PACKAGE = "..elastic.search.spring.template.api..";
    private static final String CONFIG_PACKAGE = "..elastic.search.spring.template.config..";
    private static final String INFRA_PACKAGE = "..elastic.search.spring.template.infra..";
    private static final String JAVA_PACKAGE = "..java..";


    @ArchTest
    void classes_that_resides_in_core_should_not_depend_on_the_api_layer(JavaClasses javaClasses) {
        noClasses()
                .that().resideInAnyPackage(CORE_PACKAGE)
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage(API_PACKAGE)
                .because("core domain does not depend on api layer")
                .check(javaClasses);
    }

    @ArchTest
    void classes_that_resides_in_core_should_only_depend_on_classes_within_the_core_package(JavaClasses javaClasses){
        classes()
                .that().resideInAPackage(CORE_PACKAGE)
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage(CORE_PACKAGE, JAVA_PACKAGE)
                .because("core domain cannot depend on other layers")
                .check(javaClasses);
    }


    @ArchTest
    void classes_that_resides_in_core_should_not_depend_on_the_config_layer(JavaClasses javaClasses) {
        noClasses()
                .that().resideInAnyPackage(CORE_PACKAGE)
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage(CONFIG_PACKAGE)
                .because("core domain does not depend on config layer")
                .check(javaClasses);
    }

    @ArchTest
    void classes_that_resides_in_core_should_not_depend_on_infra_layer(JavaClasses javaClasses) {
        noClasses()
                .that().resideInAnyPackage(CORE_PACKAGE)
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage(INFRA_PACKAGE)
                .because("core domain does not depend on infra layer")
                .check(javaClasses);
    }
}
