package com.factory.task.data.weixin.curd;

import com.factory.task.data.weixin.WeiXinUserLinkSysUser;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tianjian on 2020/4/13.
 */
public interface WeiXinUserLinkSysUserCurd extends CrudRepository<WeiXinUserLinkSysUser, String> {
    WeiXinUserLinkSysUser findByUnionId(String unionId);

    WeiXinUserLinkSysUser findByUserCode(String userCode);
}
