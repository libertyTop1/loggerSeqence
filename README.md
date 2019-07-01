## loggerSeqence
### 1.  单机串行化日志 实现思路：
#### 1、自定义一个LogUtil实现org.sl4j.Logger
  - 需要打印日志的类中关联 LogUtil
  - LogUtil的使用方式，同原本使用Logger接口一致
  - LogUtil中提供一个属性，记录日志所属class
**************************************************************
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
**************************************************************
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
#### 2、创建一个LogAspect，并交由spring管理
  -  1）2个切入点：分别是:
  --  A、controller 或 job 类型的入口类下的所有方法
  --  B、LogUtil 类下的所有方法
  -  2）针对切入点A，进行环绕通知，在业务方法执行前后，设置/清除 串行化日志编号
  --  针对切入点B，进行环绕通知，依据个LogUtil所在class获取对应的Logger实例
  -  3）重新组织调用参数，把 串行化日志编号 追加进去，统一调用Logger实例的这个方法：
  --  void info(String var1, Object... var2)
