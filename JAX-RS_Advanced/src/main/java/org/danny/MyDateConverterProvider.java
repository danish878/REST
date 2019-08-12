package org.danny;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

@Provider
public class MyDateConverterProvider implements ParamConverterProvider {

    public <T> ParamConverter<T> getConverter(final Class<T> rawType, Type genericType, Annotation[] annotations) {
        if (rawType.getName().equals(MyDate.class.getName())) {
            return new ParamConverter<T>() {

                public T fromString(String value) {

                    Calendar requestedDate = Calendar.getInstance();
                    if (value.equalsIgnoreCase("tomorrow"))
                        requestedDate.add(Calendar.DATE, 1);
                    else if (value.equalsIgnoreCase("yesterday"))
                        requestedDate.add(Calendar.DATE, -1);

                    MyDate myDate = new MyDate();
                    myDate.setDay(requestedDate.get(Calendar.DATE));
                    myDate.setMonth(requestedDate.get(Calendar.MONTH));
                    myDate.setYear(requestedDate.get(Calendar.YEAR));

                    return rawType.cast(myDate);
                }

                public String toString(T myBean) {
                    if (myBean == null)
                        return null;

                    return myBean.toString();
                }

            };
        }
        return null;
    }

}
