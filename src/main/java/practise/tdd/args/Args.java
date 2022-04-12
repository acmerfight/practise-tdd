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
import java.util.Map;

/**
 * @author yaogangqiang
 */
public class Args {

    public static <T> T parse(Class<T> optionsClass, String... args) {
        try {
            Constructor<?> constructor = optionsClass.getDeclaredConstructors()[0];
            List<String> arguments = Arrays.asList(args);
            Object[] values = Arrays.stream(constructor.getParameters()).map(it -> parseOption(it, arguments)).toArray();
            return (T) constructor.newInstance(values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static final Map<Class<?>, OptionParser> PARSERS = Map.of(
            boolean.class, new BooleanOptionParser(),
            int.class, new IntOptionParser(Integer::parseInt),
            String.class, new IntOptionParser(String::valueOf)
    );

    private static Object parseOption(Parameter parameter, List<String> arguments) {
        Option option = parameter.getAnnotation(Option.class);
        OptionParser parser = PARSERS.get(parameter.getType());
        return parser.parse(arguments, option);
    }

}