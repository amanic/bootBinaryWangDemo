package com.amateur.wanbei.dao.mapper;

import com.amateur.wanbei.dao.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by chenhaitao on 2018/6/5.
 */
public interface UserMapper {

    User selectByPrimaryKey (@Param("id")Integer id);

    int testTransactionUpdate(@Param("param") String param);

    int testTransactionUpdate2(@Param("param") String param);
}
