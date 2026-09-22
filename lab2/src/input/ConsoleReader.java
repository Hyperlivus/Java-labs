package input;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleReader<T> {
    public T read(Class<T> clazz) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> args = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(ConsoleSource.class)) {
                String description = field.getAnnotation(ConsoleSource.class).description();
                System.out.println(description);
                String line = scanner.nextLine();
                args.add(line);
            }
        }


        Class<?>[] paramTypes = args.stream()
                .map(Object::getClass)
                .toArray(Class<?>[]::new);

        Object[] paramValues = args.toArray();

        return clazz.getDeclaredConstructor(paramTypes)
                .newInstance(paramValues);
    }
}
