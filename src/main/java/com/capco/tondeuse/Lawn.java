package com.capco.tondeuse;

public class Lawn {
    private final int width;
    private final int height;

    public Lawn(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean isValidPosition(Position position) {
        return position.x() >= 0 && position.x() <= width &&
                position.y() >= 0 && position.y() <= height;
    }
}


