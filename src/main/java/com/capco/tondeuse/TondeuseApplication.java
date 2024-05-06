package com.capco.tondeuse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TondeuseApplication {

	public static void main(String[] args) throws IOException {

		String inputFile = "input.txt";

		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
			String[] dimensions = reader.readLine().split(" ");
			int lawnWidth = Integer.parseInt(dimensions[0]);
			int lawnHeight = Integer.parseInt(dimensions[1]);
			Lawn lawn = new Lawn(lawnWidth, lawnHeight);

			List<Mower> mowers = new ArrayList<>();

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

			for (Mower mower : mowers) {
				Position position = mower.getPosition();
				Orientation orientation = mower.getOrientation();
				System.out.println(position.x() + " " + position.y() + " " + orientation.direction());
			}
		} catch (IOException e) {
			System.err.println("Error reading input file: " + e.getMessage());
		}
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