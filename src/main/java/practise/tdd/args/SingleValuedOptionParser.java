/**
 * @(#)IntOptionParser.java, Apr 12, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package practise.tdd.args;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * @author yaogangqiang
 */
class SingleValuedOptionParser<T> implements OptionParser<T> {

    private final T defaultValue;
    Function<String, T> valueParser;

    public SingleValuedOptionParser(T defaultValue, Function<String, T> valueParser) {
        this.defaultValue = defaultValue;
        this.valueParser = valueParser;
    }

    @Override
    public T parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());
        if (index == -1) {
            return defaultValue;
        }

        List<String> values = getValues(arguments, index);

        if (values.size() < 1) {
            throw new InsufficientArgumentsException(option.value());
        }
        if (values.size() > 1) {
            throw new TooManyArgumentsException(option.value());
        }
        String value = values.get(0);
        return valueParser.apply(value);
    }

    static List<String> getValues(List<String> arguments, int index) {
        int followingFlag = IntStream.range(index + 1, arguments.size())
                .filter(it -> arguments.get(it).startsWith("-"))
                .findFirst().orElse(arguments.size());

        return arguments.subList(index + 1, followingFlag);
    }

}