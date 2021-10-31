package com.group.original.panopticon.file.system;

import com.group.original.panopticon.StandardPaths;
import junit.framework.TestCase;
import org.junit.Assert;

import java.nio.file.Path;

public class StamperTest extends TestCase {

    public void testGetStandardStampsPath() {
        Path path = StandardPaths.MAC_STAMPS;
//        System.out.println(System.getProperty("os.name"));
        Assert.assertEquals(path, Stamper.getStandardStampsPath());
    }
}