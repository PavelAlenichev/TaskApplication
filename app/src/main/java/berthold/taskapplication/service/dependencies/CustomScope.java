package berthold.taskapplication.service.dependencies;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Собственное пространство имен для локальных синглтонов
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomScope {
}