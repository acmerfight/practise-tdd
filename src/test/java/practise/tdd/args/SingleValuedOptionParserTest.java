package practise.tdd.args;

import org.junit.jupiter.api.Test;

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

    @Test
    void should_not_accept_insufficient_argument_for_single_valued_option() {
        InsufficientArgumentsException e = assertThrows(InsufficientArgumentsException.class,
                () -> new SingleValuedOptionParser<>(Integer::parseInt).parse(List.of("-p"), option("p")));
        assertEquals("p", e.getOption());

    }
}