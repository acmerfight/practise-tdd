/**
 * @(#)BooleanOptionParserTest.java, Apr 13, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package practise.tdd.args;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @author yaogangqiang
 */
public class BooleanOptionParserTest {

    @Test
    public void should_not_accept_extra_argument_for_boolean_option() {
        TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                () -> SingleValuedOptionParser.bool().parse(asList("-l", "t"), option("l")));
        assertEquals("l", e.getOption());
    }

    @Test
    void should_set_default_value_to_false_if_option_not_present(){
        assertFalse(SingleValuedOptionParser.bool().parse(List.of(), option("l")));
    }

    @Test
    void should_set_default_value_to_true_if_option_present(){
        assertTrue(SingleValuedOptionParser.bool().parse(List.of("-l"), option("l")));
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