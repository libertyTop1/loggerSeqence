/** Copyright (C), 和信电子商务有限公司 */
package hexindai;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @FileName LogUtil
 *
 * @describe
 * @author shijudong
 * @create 2019/7/2 0002 上午 10:39
 */
@Scope("prototype")
@Component
public class LegoLog implements Logger {

  private Class attachedClass;

  public void setAttachedClass(Class attachedClass) {
    this.attachedClass = attachedClass;
  }

  public Class getAttachedClass() {
    return this.attachedClass;
  }

  @Override
  public String getName() {
    return attachedClass.getName();
  }

  @Override
  public boolean isTraceEnabled() {
    return false;
  }

  @Override
  public void trace(String s) {}

  @Override
  public void trace(String s, Object o) {}

  @Override
  public void trace(String s, Object o, Object o1) {}

  @Override
  public void trace(String s, Object... objects) {}

  @Override
  public void trace(String s, Throwable throwable) {}

  @Override
  public boolean isTraceEnabled(Marker marker) {
    return false;
  }

  @Override
  public void trace(Marker marker, String s) {}

  @Override
  public void trace(Marker marker, String s, Object o) {}

  @Override
  public void trace(Marker marker, String s, Object o, Object o1) {}

  @Override
  public void trace(Marker marker, String s, Object... objects) {}

  @Override
  public void trace(Marker marker, String s, Throwable throwable) {}

  @Override
  public boolean isDebugEnabled() {
    return false;
  }

  @Override
  public void debug(String s) {}

  @Override
  public void debug(String s, Object o) {}

  @Override
  public void debug(String s, Object o, Object o1) {}

  @Override
  public void debug(String s, Object... objects) {}

  @Override
  public void debug(String s, Throwable throwable) {}

  @Override
  public boolean isDebugEnabled(Marker marker) {
    return false;
  }

  @Override
  public void debug(Marker marker, String s) {}

  @Override
  public void debug(Marker marker, String s, Object o) {}

  @Override
  public void debug(Marker marker, String s, Object o, Object o1) {}

  @Override
  public void debug(Marker marker, String s, Object... objects) {}

  @Override
  public void debug(Marker marker, String s, Throwable throwable) {}

  @Override
  public boolean isInfoEnabled() {
    return false;
  }

  @Override
  public void info(String s) {}

  @Override
  public void info(String s, Object o) {}

  @Override
  public void info(String s, Object o, Object o1) {}

  @Override
  public void info(String s, Object... objects) {}

  @Override
  public void info(String s, Throwable throwable) {}

  @Override
  public boolean isInfoEnabled(Marker marker) {
    return false;
  }

  @Override
  public void info(Marker marker, String s) {}

  @Override
  public void info(Marker marker, String s, Object o) {}

  @Override
  public void info(Marker marker, String s, Object o, Object o1) {}

  @Override
  public void info(Marker marker, String s, Object... objects) {}

  @Override
  public void info(Marker marker, String s, Throwable throwable) {}

  @Override
  public boolean isWarnEnabled() {
    return false;
  }

  @Override
  public void warn(String s) {}

  @Override
  public void warn(String s, Object o) {}

  @Override
  public void warn(String s, Object... objects) {}

  @Override
  public void warn(String s, Object o, Object o1) {}

  @Override
  public void warn(String s, Throwable throwable) {}

  @Override
  public boolean isWarnEnabled(Marker marker) {
    return false;
  }

  @Override
  public void warn(Marker marker, String s) {}

  @Override
  public void warn(Marker marker, String s, Object o) {}

  @Override
  public void warn(Marker marker, String s, Object o, Object o1) {}

  @Override
  public void warn(Marker marker, String s, Object... objects) {}

  @Override
  public void warn(Marker marker, String s, Throwable throwable) {}

  @Override
  public boolean isErrorEnabled() {
    return false;
  }

  @Override
  public void error(String s) {}

  @Override
  public void error(String s, Object o) {}

  @Override
  public void error(String s, Object o, Object o1) {}

  @Override
  public void error(String s, Object... objects) {}

  @Override
  public void error(String s, Throwable throwable) {}

  @Override
  public boolean isErrorEnabled(Marker marker) {
    return false;
  }

  @Override
  public void error(Marker marker, String s) {}

  @Override
  public void error(Marker marker, String s, Object o) {}

  @Override
  public void error(Marker marker, String s, Object o, Object o1) {}

  @Override
  public void error(Marker marker, String s, Object... objects) {}

  @Override
  public void error(Marker marker, String s, Throwable throwable) {}
}
