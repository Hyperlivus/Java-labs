package scheme;

import java.lang.reflect.Field;
import java.util.regex.Pattern;



public class SchemeValidator {

    public void validate(Object object) throws InvalidSchemeField {
        Class<?> clazz = object.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.isAnnotationPresent(SchemeField.class)) {
                    Pattern pattern = Pattern.compile(field.getAnnotation(SchemeField.class).regexp());
                    String value = (String) field.get(object);


                    if (!pattern.matcher(value).matches()) {
                        throw new InvalidSchemeField(field.getName(), value);
                    }
                }
            }
            catch (IllegalAccessException _){}
        }
    }
}
