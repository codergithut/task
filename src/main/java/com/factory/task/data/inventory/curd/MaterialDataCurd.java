package com.factory.task.data.inventory.curd;

import com.factory.task.data.inventory.MaterialData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tianjian on 2020/2/16.
 */
public interface MaterialDataCurd extends CrudRepository<MaterialData,String> {
    List<MaterialData> findByMaterialName(String materialName);

    List<MaterialData> findByMaterialNameAndMaterialColour(String materialName, String materialColour);
}
