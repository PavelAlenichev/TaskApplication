package berthold.taskapplication.service.dependencies;

import berthold.taskapplication.MainActivity;
import dagger.Component;

/**
 * Created by berthold on 27.03.2017.
 */
@CustomScope
@Component(modules = ApiModule.class, dependencies = NetworkComponent.class)
public interface ApiComponent {

    void inject(MainActivity mainActivity);

}
