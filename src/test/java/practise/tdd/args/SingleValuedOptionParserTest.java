package practise.tdd.args;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static practise.tdd.args.BooleanOptionParserTest.option;

class SingleValuedOptionParserTest {

    @Test
    void should_not_accept_extra_argument_for_single_valued_option() {
        TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                () -> new SingleValuedOptionParser<>(Integer::parseInt).parse(asList("-p", "8080", "8081"), option("p")));
        assertEquals("p", e.getOption());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-p -l", "-p"})
    void should_not_accept_insufficient_argument_for_single_valued_option(String input) {
        List<String> arguments = asList(input.split(" "));
        InsufficientArgumentsException e = assertThrows(InsufficientArgumentsException.class,
                () -> new SingleValuedOptionParser<>(Integer::parseInt).parse(arguments, option("p")));
        assertEquals("p", e.getOption());

    }
}