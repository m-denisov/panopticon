package com.group.original.panopticon;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MainProgramPaths {
	public static final String OS_NAME = System.getProperty("os.name").toLowerCase();

	public static final Path LOCAL_PATH = Paths.get("C:\\Users\\m.denisov\\Documents");
	public static final Path NET_PATH = Paths.get("F:\\1EN-TraceWay\\2.9. Департамент качества\\Обмен\\Денисов");
	public static final String PASSPORTS_CATALOG = "Паспорта";
	public static final String PROJECTS_CATALOG = "Проекты";

	public static final Path MAC_STAMPS = Paths.get("/Users/westtochka/Documents/panopticonTest/stamps");
	public static final Path WIN_STAMPS = Paths.get("D:\\panopticon\\stamps");

	public static final Path MAC_OLD_VERSIONS = Paths.get("/Users/westtochka/Documents/panopticonTest/temps");
	public static final Path WIN_OLD_VERSIONS = Paths.get("D:\\panopticon\\temps");

	public static final Path STANDARD_PATHS_FILE = Paths.get(MainProgramPaths.class.getClassLoader().getResource("StandardPaths.xml").getPath());

	public static Path getStampsPath() {
		if (isWindows()) {
			return WIN_STAMPS;
		} else if (isMac()) {
			return MAC_STAMPS;
		} else {
			throw new RuntimeException();
		}
	}

	public static Path getOldVersionsPath() {
		if (isWindows()) {
			return WIN_OLD_VERSIONS;
		} else if (isMac()) {
			return MAC_OLD_VERSIONS;
		} else {
			throw new RuntimeException();
		}
	}

	public static boolean isWindows() {
		return OS_NAME.contains("win");
	}

	public static boolean isMac() {
		return OS_NAME.contains("mac");
	}


}
