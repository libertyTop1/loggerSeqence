package com.liberty.loggerSeqence.dao;

import com.liberty.loggerSeqence.util.LogUtil;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LibertyDao {
    private final LogUtil log;
    public LibertyDao(@Autowired LogUtil logUtil){
        logUtil.setAttachedClass(this.getClass());
        log = logUtil;
    }
    @Select("select * from liberty")
    public Object query() {
        log.info("放大放大{}fdafadsf{}fdasfdasfdas{}",2,4,6,new Object());
        return null;
    }
}
