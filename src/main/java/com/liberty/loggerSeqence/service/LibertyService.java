package com.liberty.loggerSeqence.service;

import com.liberty.loggerSeqence.dao.LibertyDao;
import com.liberty.loggerSeqence.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibertyService {
    private final LogUtil log;
    public LibertyService(@Autowired LogUtil logUtil){
        logUtil.setAttachedClass(this.getClass());
        log = logUtil;
    }
    @Autowired
    private LibertyDao libertyDao;

    public Object add() {
        log.info("1{}3{}5{}",2,4,6);
        return libertyDao.query();
    }
}
