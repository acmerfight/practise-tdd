package practise.tdd.args;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArgsTest {

    @Test
    public void should_example_1() {
        Options options = Args.parse(Options.class, "-l", "-p", "8080", "-d", "/usr/logs");

        assertTrue(options.logging());
        assertEquals(options.port(), 8080);
        assertEquals(options.directory(), "/usr/logs");
    }

    @Test
    public void should_example_2() {
        ListOptions listOptions = Args.parse(ListOptions.class, "-g", "this", "is", "a", "list", "-d", "1", "2", "-3", "5");
        assertEquals(listOptions.group(), new String[]{"this", "is", "a", "list"});
        assertEquals(listOptions.decimals(), new int[]{1, 2, -3, 5});
    }


    static record Options(@Option("l") boolean logging, @Option("p") int port,
                          @Option("d") String directory) {
    }

    static record ListOptions(@Option("g") String[] group, @Option("d") int[] decimals) {
    }
}