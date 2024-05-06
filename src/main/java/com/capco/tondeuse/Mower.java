package com.capco.tondeuse;

import java.util.List;

public class Mower {
    private Position position;
    private Orientation orientation;

    public Mower(Position position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public void move(List<Instruction> instructions, Lawn lawn) {
        for (Instruction instruction : instructions) {
            switch (instruction.movement()) {
                case RIGHT -> rotateRight();
                case LEFT -> rotateLeft();
                case FORWARD -> moveForward(lawn);
                case BACK -> moveBackward(lawn);
            }
        }
    }

    private void rotateRight() {
        orientation = switch (orientation.direction()) {
            case NORTH -> new Orientation(Direction.EAST);
            case EAST -> new Orientation(Direction.SOUTH);
            case SOUTH -> new Orientation(Direction.WEST);
            case WEST -> new Orientation(Direction.NORTH);
        };
    }

    private void rotateLeft() {
        orientation = switch (orientation.direction()) {
            case NORTH -> new Orientation(Direction.WEST);
            case EAST -> new Orientation(Direction.NORTH);
            case SOUTH -> new Orientation(Direction.EAST);
            case WEST -> new Orientation(Direction.SOUTH);
        };
    }

    private void moveForward(Lawn lawn) {
        Position newPosition = switch (orientation.direction()) {
            case NORTH -> new Position(position.x(), position.y() + 1);
            case EAST -> new Position(position.x() + 1, position.y());
            case SOUTH -> new Position(position.x(), position.y() - 1);
            case WEST -> new Position(position.x() - 1, position.y());
        };
        if (lawn.isValidPosition(newPosition)) {
            position = newPosition;
        }
    }

    private void moveBackward(Lawn lawn) {
        Position newPosition = switch (orientation.direction()) {
            case NORTH -> new Position(position.x(), position.y() - 1);
            case EAST -> new Position(position.x() - 1, position.y());
            case SOUTH -> new Position(position.x(), position.y() + 1);
            case WEST -> new Position(position.x() + 1, position.y());
        };
        if (lawn.isValidPosition(newPosition)) {
            position = newPosition;
        }
    }

    public Position getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
