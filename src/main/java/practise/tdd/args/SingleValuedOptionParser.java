/**
 * @(#)IntOptionParser.java, Apr 12, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package practise.tdd.args;

import java.util.List;
import java.util.Optional;
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

    public static OptionParser<Boolean> bool() {
        return (arguments, option) -> values(arguments, option, 0).isPresent();
    }

    @Override
    public T parse(List<String> arguments, Option option) {
        Optional<List<String>> argumentList = values(arguments, option, 1);
        return argumentList.map(it -> parseValue(option, it.get(0))).orElse(defaultValue);
    }

    static Optional<List<String>> values(List<String> arguments, Option option, int expectedSize) {
        int index = arguments.indexOf("-" + option.value());
        if (index == -1) {
            return Optional.empty();
        }
        List<String> values = getValues(arguments, index);
        if (values.size() < expectedSize) {
            throw new InsufficientArgumentsException(option.value());
        }
        if (values.size() > expectedSize) {
            throw new TooManyArgumentsException(option.value());
        }
        return Optional.of(values);
    }

    private T parseValue(Option option, String value) {
        try {
            return valueParser.apply(value);
        } catch (Exception e) {
            throw new IllegalValueException(option.value(), value);
        }
    }

    static List<String> getValues(List<String> arguments, int index) {
        int followingFlag = IntStream.range(index + 1, arguments.size())
                .filter(it -> arguments.get(it).startsWith("-"))
                .findFirst().orElse(arguments.size());

        return arguments.subList(index + 1, followingFlag);
    }

}