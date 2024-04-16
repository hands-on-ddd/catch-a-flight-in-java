package io.hands.on.ddd.common.persistence;

public record Version(int value) {
    public static Version zero() {
        return new Version(0);
    }
}
