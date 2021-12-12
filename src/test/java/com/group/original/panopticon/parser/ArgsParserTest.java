package com.group.original.panopticon.parser;

import com.group.original.panopticon.parser.command.Commands;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ArgsParserTest extends TestCase {

    public void testParse() {

        String analyse_mac_un = "a p \"/Users/westtochka/Documents/panopticonTest/first\"";
        String analyse_win_un = "a p \"C:\\Users\\m.denisov\\Documents\\Оборудование\"";

//        String copy_mac_un = "c p \"/Users/westtochka/Documents/panopticonTest/first\"";
//        String copy_new_mac_un = "c n p \"/Users/westtochka/Documents/panopticonTest/first\"";
//        String copy_mod_mac_un = "c m p \"/Users/westtochka/Documents/panopticonTest/first\"";

        String make_stamp_mac_un = "ms p \"/Users/westtochka/Documents/panopticonTest/first\"";
        String is_stamped_mac_un = "is p \"/Users/westtochka/Documents/panopticonTest/first\"";

        String analyse_mac_bi = "a p1 \"/Users/westtochka/Documents/panopticonTest/first\" p2 \"/Users/westtochka/Documents/panopticonTest/second\"";
        String analyse_wac_bi = "a p1 \"C:\\Users\\m.denisov\\Documents\\Оборудование\" p2 \"/Users/westtochka/Documents/panopticonTest/second\"";

        String copy_mac_bi = "c dir p1 \"/Users/westtochka/Documents/panopticonTest/first\" p2 \"/Users/westtochka/Documents/panopticonTest/second\"";
        String copy_new_mac_bi = "c dir n p1 \"/Users/westtochka/Documents/panopticonTest/first\" p2 \"/Users/westtochka/Documents/panopticonTest/second\"";
        String copy_mod_mac_bi = "c dir m p1 \"/Users/westtochka/Documents/panopticonTest/first\" p2 \"/Users/westtochka/Documents/panopticonTest/second\"";

        assertTrue(ArgsParser.parse(analyse_mac_un).equals(Commands.COMPARE_LAZY));
        assertTrue(ArgsParser.parse(analyse_win_un).equals(Commands.COMPARE_LAZY));

//        assertTrue(ArgsParser.parse(copy_mac_un).equals(Commands.SWAP_ALL));
//        assertTrue(ArgsParser.parse(copy_new_mac_un).equals(Commands.TRANSFER_NEW_FILES_DIRECT));
//        assertTrue(ArgsParser.parse(copy_mod_mac_un).equals(Commands.SWAP_MODIFIED_FILES_DIRECT));

        assertTrue(ArgsParser.parse(make_stamp_mac_un).equals(Commands.MAKE_STAMP));
        assertTrue(ArgsParser.parse(is_stamped_mac_un).equals(Commands.IS_SAVE));

        assertTrue(ArgsParser.parse(analyse_mac_bi).equals(Commands.COMPARE_LAZY));
        assertTrue(ArgsParser.parse(analyse_wac_bi).equals(Commands.COMPARE_LAZY));

        assertTrue(ArgsParser.parse(copy_mac_bi).equals(Commands.SWAP_ALL));
        assertTrue(ArgsParser.parse(copy_new_mac_bi).equals(Commands.TRANSFER_NEW_FILES_DIRECT));
        assertTrue(ArgsParser.parse(copy_mod_mac_bi).equals(Commands.SWAP_MODIFIED_FILES_DIRECT));

    }

    public void testException() {
        String s = "kkk";
        try {
            ArgsParser.parse(s);
        } catch (RuntimeException e) {
            assertTrue(Commands.REPORT.toString().equals(e.getMessage()));
        }
    }
}