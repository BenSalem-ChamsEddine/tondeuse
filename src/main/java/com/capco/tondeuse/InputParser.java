package com.capco.tondeuse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputParser {

    public static List<Mower> parseInput(String inputFile) throws IOException {
        List<Mower> mowers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String[] dimensions = reader.readLine().split(" ");
            int lawnWidth = Integer.parseInt(dimensions[0]);
            int lawnHeight = Integer.parseInt(dimensions[1]);
            Lawn lawn = new Lawn(lawnWidth, lawnHeight);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] mowerInitialPosition = line.split(" ");
                int x = Integer.parseInt(mowerInitialPosition[0]);
                int y = Integer.parseInt(mowerInitialPosition[1]);
                Direction direction = Direction.valueOf(mowerInitialPosition[2]);

                Position position = new Position(x, y);
                Orientation orientation = new Orientation(direction);
                Mower mower = new Mower(position, orientation);

                String instructions = reader.readLine();
                List<Instruction> instructionList = parseInstructions(instructions);

                mower.move(instructionList, lawn);
                mowers.add(mower);
            }
        }
        return mowers;
    }

    private static List<Instruction> parseInstructions(String instructions) {
        List<Instruction> instructionList = new ArrayList<>();
        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case 'D' -> instructionList.add(new Instruction(Movement.RIGHT));
                case 'G' -> instructionList.add(new Instruction(Movement.LEFT));
                case 'A' -> instructionList.add(new Instruction(Movement.FORWARD));
                case 'B' -> instructionList.add(new Instruction(Movement.BACK));
            }
        }
        return instructionList;
    }
}
