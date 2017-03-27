package berthold.taskapplication.data.application;

import berthold.taskapplication.service.dependencies.ApiComponent;
import berthold.taskapplication.service.dependencies.DaggerApiComponent;
import berthold.taskapplication.service.dependencies.DaggerNetworkComponent;
import berthold.taskapplication.service.dependencies.NetworkComponent;
import berthold.taskapplication.service.dependencies.NetworkModule;

/**
 * Класс, разрешающий зависимости между Api и Retrofit, создание и настройка компонент
 */

public class TaskApplication extends android.app.Application {

    private ApiComponent apiComponent;

    /**
     * Установка нужных зависимостей
     */
    @Override
    public void onCreate() {
        resolveDependency();
        super.onCreate();
    }

    /**
     * Создание и настройка ApiComponent
     */
    private void resolveDependency() {
        apiComponent = DaggerApiComponent.builder()
                .networkComponent(getNetworkComponent())
                .build();
    }

    /**
     * Создание и настройка NetworkComponent
     * @return networkComponent
     */
    public NetworkComponent getNetworkComponent() {

        return DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule())
                .build();
    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }
}
