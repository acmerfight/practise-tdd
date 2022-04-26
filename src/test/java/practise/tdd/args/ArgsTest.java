package practise.tdd.args;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArgsTest {

    @Test
    public void should_parse_multi_options() {
        MultiOptions multiOptions = Args.parse(MultiOptions.class, "-l", "-p", "8080", "-d", "/usr/logs");

        assertTrue(multiOptions.logging());
        assertEquals(multiOptions.port(), 8080);
        assertEquals(multiOptions.directory(), "/usr/logs");
    }

    record MultiOptions(@Option("l") boolean logging, @Option("p") int port,
                        @Option("d") String directory) {
    }


    @Test
    public void should_throw_illegal_option_exception_if_annotation_not_present() {
        IllegalOptionException e = assertThrows(IllegalOptionException.class, () -> Args.parse(OptionsWithoutAnnotation.class, "-l", "-p", "8080", "-d", "/usr/logs"));
        assertEquals("port", e.getParameter());
    }

    record OptionsWithoutAnnotation(@Option("l") boolean logging, int port,
                                    @Option("d") String directory) {
    }

    @Test
    public void should_example_2() {
        ListOptions listOptions = Args.parse(ListOptions.class, "-g", "this", "is", "a", "list");
        assertArrayEquals(listOptions.group(), new String[]{"this", "is", "a", "list"});
    }


    record ListOptions(@Option("g") String[] group) {
    }
}