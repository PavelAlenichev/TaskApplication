package berthold.taskapplication.service.factories;

import android.content.Context;
import android.widget.GridLayout;
import java.util.List;

import berthold.taskapplication.data.metadata.Field;

/**
 * Для стандартного (не с помощью фреймворков) внедрения зависимостей
 */
public interface Factory {


    /**
     * Создает view's на нужном layout в зависимости от значений field
     *
     * @param context
     * @param field
     * @param position
     * @param metadataLayout
     */
    void setViews(Context context, Field field, int position, GridLayout metadataLayout);

    /**
     * Получение значений полей по запросу
     *
     * @return List<String> со значениями полей
     */
    List<String> getValues();
}