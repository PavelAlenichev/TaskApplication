package berthold.taskapplication.service.dependencies;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by User on 27.03.2017.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomScope {
}
