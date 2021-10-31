package com.group.original.panopticon.investigator;

import com.group.original.panopticon.file.differences.Differences;
import com.group.original.panopticon.file.matcher.StampMatcher;
import com.group.original.panopticon.file.system.DirectoryStamp;
import com.group.original.panopticon.file.system.Stamper;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Researcher {
    private static final Map<Path, DirectoryStamp> stamps = new HashMap<>();
    private static final long LIFE_TIME = 1;
    private static final ChronoUnit LIFE_TIME_UNIT = ChronoUnit.MINUTES;
    private Map<Connection, StampMatcher> matchers;

    public Differences getDifferences(Path localPath, Path netPath) {
        Connection connection = new Connection(localPath, netPath);
        if (!isMatched(connection)) {
            DirectoryStamp local = getStamp(localPath);
            DirectoryStamp net = getStamp(netPath);
            return compareStamps(local, net);
        }
        return Differences.of(matchers.get(connection), getOrder(connection));
    }

    private boolean isMatched(Connection connection) {
        return matchers.get(connection) != null;
    }

    public Differences compare(Path localPath, Path netPath) {
        DirectoryStamp local = DirectoryStamp.stampOf(localPath);
        DirectoryStamp net = DirectoryStamp.stampOf(netPath);
        return compareStamps(local, net);
    }

    private Differences compareStamps(DirectoryStamp local, DirectoryStamp net) {
        StampMatcher matcher = new StampMatcher(local, net);
        Connection connection = new Connection(local.getRoot(), net.getRoot());

        stamps.put(local.getRoot(), local);
        stamps.put(net.getRoot(), net);
        matchers.put(connection, matcher);

        return Differences.of(matcher, Differences.Order.DIRECT);
    }

    private Differences.Order getOrder(Connection connection) {
        if (matchers.containsKey(connection)) {
            Connection oldConnection = matchers.keySet().stream()
                    .filter(c -> c.equals(connection))
                    .findFirst()
                    .get();
            if (connection.isReverseOf(oldConnection)) {
                return Differences.Order.REVERSE;
            }
        }
        return Differences.Order.DIRECT;
    }

    private DirectoryStamp getStamp(Path path) {
        DirectoryStamp stamp = stamps.get(path);
        if (stamp == null) {
            stamp = DirectoryStamp.stampOf(path);
        }
        return stamp;
    }

    public Differences getChanges(Path path) {
        return null;
    }

    public void makeStamp(Path path) {

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
