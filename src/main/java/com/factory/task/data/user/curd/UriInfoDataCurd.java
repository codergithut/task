package com.factory.task.data.user.curd;

import com.factory.task.data.user.UriInfoData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tianjian on 2020/1/15.
 */
public interface UriInfoDataCurd extends CrudRepository<UriInfoData,String> {
    List<UriInfoData> findByUriPath(String uri);
}
