package com.group.original.panopticon.file;

import com.group.original.panopticon.file.differences.Differences;
import com.group.original.panopticon.file.matcher.StampMatcher;
import com.group.original.panopticon.file.system.DirectoryStamp;
import com.group.original.panopticon.file.system.Stamper;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Researcher {
    private static final Map<Path, DirectoryStamp> stamps = new HashMap<>();
//    private static final long LIFE_TIME = 1;
//    private static final ChronoUnit LIFE_TIME_UNIT = ChronoUnit.MINUTES;
    private Map<Connection, StampMatcher> matchers;

    public Differences compareLazy(Path localPath, Path netPath) {
        Connection connection = new Connection(localPath, netPath);
        if (!isMatched(connection)) {
            DirectoryStamp localStamp = getStamp(localPath);
            DirectoryStamp netStamp = getStamp(netPath);
            return compareStamps(localStamp, netStamp);
        }
        return Differences.of(matchers.get(connection), getOrder(connection));
    }

    public Differences compare(Path localPath, Path netPath) {
        DirectoryStamp local = DirectoryStamp.stampOf(localPath);
        DirectoryStamp net = DirectoryStamp.stampOf(netPath);
        return compareStamps(local, net);
    }


    private boolean isMatched(Connection connection) {
        return matchers.get(connection) != null;
    }

    private ComparisonOrder getOrder(Connection connection) {
        if (matchers.containsKey(connection)) {
            Connection oldConnection = matchers.keySet().stream()
                    .filter(c -> c.equals(connection))
                    .findFirst()
                    .get();
            if (connection.isReverseOf(oldConnection)) {
                return ComparisonOrder.REVERSE;
            }
        }
        return ComparisonOrder.DIRECT;
    }

    private DirectoryStamp getStamp(Path path) {
        return stamps.getOrDefault(path, DirectoryStamp.stampOf(path));
    }

    private Differences compareStamps(DirectoryStamp localStamp, DirectoryStamp netStamp) {
        StampMatcher matcher = new StampMatcher(localStamp, netStamp);
        Connection connection = new Connection(localStamp.getRoot(), netStamp.getRoot());

        stamps.put(localStamp.getRoot(), localStamp);
        stamps.put(netStamp.getRoot(), netStamp);
        matchers.put(connection, matcher);

        return Differences.of(matcher, ComparisonOrder.DIRECT);
    }

    public Differences getChanges(Path dirPath) {
        DirectoryStamp directoryStamp = DirectoryStamp.stampOf(dirPath);
        DirectoryStamp saved = Stamper.readStamp(dirPath);

        return compareStamps(directoryStamp, saved);
    }

    public void makeStamp(Path dirPath) {
        DirectoryStamp directoryStamp = DirectoryStamp.stampOf(dirPath);
        Stamper.writeStamp(directoryStamp);
        stamps.put(dirPath, directoryStamp);
    }

    public boolean isSaved(Path path) {
        return Stamper.isStamped(path);
    }

    private class Connection {
        String first;
        String second;

        public Connection(Path first, Path second) {
            this.first = first.toString();
            this.second = second.toString();
        }

        public boolean isReverseOf(Connection that) {
            if (this.equals(that)) {
                return this.first.equals(that.second);
            }
            return false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Connection that = (Connection) o;
            return (first.equals(that.first) && second.equals(that.second))
                    || (first.equals(that.second) && second.equals(that.first));
        }

        @Override
        public int hashCode() {
            return Math.max(Objects.hash(first), Objects.hash(second));
        }
    }
}
