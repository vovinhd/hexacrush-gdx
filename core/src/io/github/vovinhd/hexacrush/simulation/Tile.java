package io.github.vovinhd.hexacrush.simulation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Tile {
	RED, GREEN, PURPLE, YELLOW, BLACK, BLUE;
	private static final List<Tile> VALUES = Collections
			.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static Tile random() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
