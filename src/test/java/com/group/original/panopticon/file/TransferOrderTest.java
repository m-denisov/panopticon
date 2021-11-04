package com.group.original.panopticon.file;

import junit.framework.TestCase;

public class TransferOrderTest extends TestCase {

    public void testGetOpposite() {
        TransferOrder direct = TransferOrder.DIRECT;
        TransferOrder reverse = TransferOrder.REVERS;
        assertFalse(direct.getOpposite().isDirect());
        assertTrue(reverse.getOpposite().isDirect());
    }
}