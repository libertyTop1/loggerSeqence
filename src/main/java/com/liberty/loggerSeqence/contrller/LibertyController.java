package com.liberty.loggerSeqence.contrller;

import com.liberty.loggerSeqence.service.LibertyService;
import com.liberty.loggerSeqence.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class LibertyController {
    private final LogUtil log;
    public LibertyController(@Autowired LogUtil logUtil){
        logUtil.setAttachedClass(this.getClass());
        log = logUtil;
    }
    @Autowired
    private LibertyService libertyService;

    @RequestMapping("/add")
    public Object add(){
        log.info("111111");
        //log.info("1{}2{}3{}",1,2,3,new RuntimeException("test"));
        Object object =  libertyService.add();
        return System.currentTimeMillis();
    }

}
