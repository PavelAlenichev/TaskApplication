package berthold.taskapplication.service.dependencies;

import berthold.taskapplication.MainActivity;
import dagger.Component;

/**
 * Внедрение зависимостей в MainActivity
 */
@CustomScope
@Component(modules = ApiModule.class, dependencies = NetworkComponent.class)
public interface ApiComponent {

    /**
     * Внедрение в activity
     *
     * @param activity
     */
    void inject(MainActivity activity);

}