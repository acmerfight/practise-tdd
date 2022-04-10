/**
 * @(#)args.java, Apr 09, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package practise.tdd.args;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author yaogangqiang
 */
public class Args {

    public static <T> T parse(Class<T> optionsClass, String... args) {
        try {
            Constructor<?> constructor = optionsClass.getDeclaredConstructors()[0];
            System.out.println(optionsClass.getDeclaredConstructors()[0]);
            Parameter parameter = constructor.getParameters()[0];
            System.out.println(parameter);
            Option option = parameter.getAnnotation(Option.class);
            System.out.println(option.value());
            List<String> arguments = Arrays.asList(args);

            Object value = null;
            if (parameter.getType() == boolean.class) {
                value = arguments.contains("-" + option.value());
            }
            if (parameter.getType() == int.class) {
                int index = arguments.indexOf("-" + option.value());
                value = Integer.parseInt(arguments.get(index + 1));
            }
            return (T) constructor.newInstance(value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}