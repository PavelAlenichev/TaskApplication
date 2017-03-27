package berthold.taskapplication.data.application;

import berthold.taskapplication.service.dependencies.ApiComponent;
import berthold.taskapplication.service.dependencies.DaggerApiComponent;
import berthold.taskapplication.service.dependencies.DaggerNetworkComponent;
import berthold.taskapplication.service.dependencies.NetworkComponent;
import berthold.taskapplication.service.dependencies.NetworkModule;

/**
 * Created by User on 27.03.2017.
 */

public class TaskApplication extends android.app.Application {

    private ApiComponent apiComponent;

    @Override
    public void onCreate() {

        resolveDependency();

        super.onCreate();
    }

    private void resolveDependency() {
        apiComponent = DaggerApiComponent.builder()
                .networkComponent(getNetworkComponent())
                .build();
    }

    public NetworkComponent getNetworkComponent() {

        return DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule())
                .build();
    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }
}
