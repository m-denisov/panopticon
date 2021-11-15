package com.group.original.panopticon.parser;

import com.group.original.panopticon.parser.command.Commands;
import junit.framework.TestCase;

public class ArgsParserTest extends TestCase {

    public void testParse() {

        String a_m_u = "a p \"/Users/westtochka/Documents/panopticonTest/first\"";
        String a_w_u = "a p \"C:\\Users\\m.denisov\\Documents\\Оборудование\"";

        String c_m_u = "c p \"/Users/westtochka/Documents/panopticonTest/first\"";
        String cn_m_u = "c n p \"/Users/westtochka/Documents/panopticonTest/first\"";
        String cm_m_u = "c m p \"/Users/westtochka/Documents/panopticonTest/first\"";

        String ms_m_u = "ms p \"/Users/westtochka/Documents/panopticonTest/first\"";
        String is_m_u = "is p \"/Users/westtochka/Documents/panopticonTest/first\"";

        String a_m_b = "a p1 \"/Users/westtochka/Documents/panopticonTest/first\" p2 \"/Users/westtochka/Documents/panopticonTest/second\"";
        String a_w_b = "a p1 \"C:\\Users\\m.denisov\\Documents\\Оборудование\" p2 \"/Users/westtochka/Documents/panopticonTest/second\"";

        String c_m_b = "c p1 \"/Users/westtochka/Documents/panopticonTest/first\" p2 \"/Users/westtochka/Documents/panopticonTest/second\"";
        String cn_m_b = "c n p1 \"/Users/westtochka/Documents/panopticonTest/first\" p2 \"/Users/westtochka/Documents/panopticonTest/second\"";
        String cm_m_b = "c m p1 \"/Users/westtochka/Documents/panopticonTest/first\" p2 \"/Users/westtochka/Documents/panopticonTest/second\"";

        String s = "kkk";

        assertTrue(ArgsParser.parse(a_m_u).equals(Commands.COMPARE_LAZY));
        assertTrue(ArgsParser.parse(a_w_u).equals(Commands.COMPARE_LAZY));

        assertTrue(ArgsParser.parse(c_m_u).equals(Commands.SWAP_ALL));
        assertTrue(ArgsParser.parse(cn_m_u).equals(Commands.TRANSFER_NEW_FILES_DIRECT));
        assertTrue(ArgsParser.parse(cm_m_u).equals(Commands.SWAP_MODIFIED_FILES_DIRECT));

        assertTrue(ArgsParser.parse(ms_m_u).equals(Commands.MAKE_STAMP));
        assertTrue(ArgsParser.parse(is_m_u).equals(Commands.IS_SAVE));

        assertTrue(ArgsParser.parse(a_m_b).equals(Commands.COMPARE_LAZY));
        assertTrue(ArgsParser.parse(a_w_b).equals(Commands.COMPARE_LAZY));

        assertTrue(ArgsParser.parse(c_m_b).equals(Commands.SWAP_ALL));
        assertTrue(ArgsParser.parse(cn_m_b).equals(Commands.TRANSFER_NEW_FILES_DIRECT));
        assertTrue(ArgsParser.parse(cm_m_b).equals(Commands.SWAP_MODIFIED_FILES_DIRECT));

        assertTrue(ArgsParser.parse(s).equals(Commands.REPORT));

    }
}