package com.group.original.panopticon.file.system;

import java.io.Serializable;
import java.nio.file.Path;

public interface Stamp extends Serializable {
    Path getPath();
}
