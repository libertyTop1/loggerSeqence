# loggerSeqence
单机串行化日志
# 实现思路：
## 1、自定义一个LogUtil实现org.sl4j.Logger
### 1）需要打印日志的类中关联 LogUtil
### 2）LogUtil的使用方式，同原本使用Logger接口一致
### 3）LogUtil中提供一个属性，记录日志所属class
```java
@Scope("prototype")
@Component
public class LogUtil implements Logger {

    private Class attachedClass;

    public void setAttachedClass(Class attachedClass){
        this.attachedClass = attachedClass;
    }

    public Class getAttachedClass(){
        return this.attachedClass;
    }
    ...
}
```
```java
@RestController
@RequestMapping
public class LibertyController {
    private final LogUtil log;
    public LibertyController(@Autowired LogUtil logUtil){
        logUtil.setAttachedClass(this.getClass());
        log = logUtil;
    }
    ...
}
```
