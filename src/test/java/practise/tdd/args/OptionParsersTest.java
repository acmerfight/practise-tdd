package practise.tdd.args;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OptionParsersTest {

    @Nested
    class unaryOptionParser {

        @Test
        void should_not_accept_extra_argument_for_single_valued_option() {
            TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                    () -> OptionParsers.unary(0, Integer::parseInt).parse(asList("-p", "8080", "8081"), option("p")));
            assertEquals("p", e.getOption());
        }

        @ParameterizedTest
        @ValueSource(strings = {"-p -l", "-p"})
        void should_not_accept_insufficient_argument_for_single_valued_option(String input) {
            List<String> arguments = asList(input.split(" "));
            InsufficientArgumentsException e = assertThrows(InsufficientArgumentsException.class,
                    () -> OptionParsers.unary(0, Integer::parseInt).parse(arguments, option("p")));
            assertEquals("p", e.getOption());

        }

        @Test
        void should_set_default_value_for_single_valued_option() {
            Function<String, Object> whatever = (it) -> null;
            Object defaultValue = new Object();
            assertSame(defaultValue, OptionParsers.unary(defaultValue, whatever).parse(List.of(), option("p")));
        }

        @Test
        public void should_parse_value_if_flag_present() {
            Object parsed = new Object();
            Function<String, Object> parse = (it) -> parsed;
            Object whatever = new Object();
            assertSame(parsed, OptionParsers.unary(whatever, parse).parse(asList("-p", "8080"), option("p")));
        }
    }

    /**
     * @author yaogangqiang
     */
    @Nested
    class BooleanOptionParser{

        @Test
        public void should_not_accept_extra_argument_for_boolean_option() {
            TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                    () -> OptionParsers.bool().parse(asList("-l", "t"), option("l")));
            assertEquals("l", e.getOption());
        }

        @Test
        void should_set_default_value_to_false_if_option_not_present(){
            assertFalse(OptionParsers.bool().parse(List.of(), option("l")));
        }

        @Test
        void should_set_default_value_to_true_if_option_present(){
            assertTrue(OptionParsers.bool().parse(List.of("-l"), option("l")));
        }


    }

    @Nested
    class ListOptionParser {

        @Test
        public void should_parse_list_value() {
            String[] value = OptionParsers.list(String[]::new, String::valueOf).parse(asList("-g", "this", "is"), option("g"));
            assertArrayEquals(new String[]{"this", "is"}, value);
        }

        @Test
        public void should_use_empty_array_as_default_value() {
            String[] value = OptionParsers.list(String[]::new, String::valueOf).parse(List.of(), option("g"));
            assertEquals(0, value.length);
        }
    }

    static Option option(String value) {
        return new Option() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return Option.class;
            }

            @Override
            public String value() {
                return value;
            }
        };
    }
}