package practise.tdd.args;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    @Disabled
    public void should_example_2() {
        ListOptions listOptions = Args.parse(ListOptions.class, "-g", "this", "is", "a", "list", "-d", "1", "2", "-3", "5");
        assertEquals(listOptions.group(), new String[]{"this", "is", "a", "list"});
        assertEquals(listOptions.decimals(), new int[]{1, 2, -3, 5});
    }


    record ListOptions(@Option("g") String[] group, @Option("d") int[] decimals) {
    }

    @Test
    public void should_get_string_as_option_value() {
        StringOption option = Args.parse(StringOption.class, "-d", "/usr/logs");
        assertEquals("/usr/logs", option.directory());
    }

    record StringOption(@Option("d") String directory) {
    }


}