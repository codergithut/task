package com.factory.task.controller;

import com.factory.task.data.user.UriInfoData;
import com.factory.task.data.user.curd.UriInfoDataCurd;
import com.factory.task.model.RestModelTemplate;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by tianjian on 2020/3/25.
 */
@RestController
@RequestMapping("/util")
@CrossOrigin
public class UtilController {
    @Autowired
    private UriInfoDataCurd uriInfoDataCurd;

    @GetMapping("/saveUris")
    public RestModelTemplate<Boolean> saveUris() throws IOException {
        List<String> value = Files.readLines(new File("/Users/tianjian/IdeaProjects/task/src/main/resources/PROTECTURI"), Charsets.UTF_8);
        value.forEach(e -> {
            if(StringUtils.isEmpty(e)) {
                return;
            }
            String[] uris = e.split("\\|");
            List<UriInfoData> datas = uriInfoDataCurd.findByUriPath(uris[0]);
            if(CollectionUtils.isEmpty(datas)) {
                UriInfoData uriInfoData = new UriInfoData();
                uriInfoData.setUriCode(UUID.randomUUID().toString());
                uriInfoData.setUriPath(uris[0]);
                uriInfoData.setUriName(uris[1]);
                uriInfoDataCurd.save(uriInfoData);
            }
        });

        return new RestModelTemplate<Boolean>().Success(true);
    }

}
