package com.ronysky.sevendaysofcode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test107 {

	private static int diagonalDifference(List<List<Integer>> arr) {
		int primaryDiagonal = 0;
		for (int i = 0; i < arr.size(); i++) {
			for (int j = 0; j < arr.get(i).size(); j++) {
				if (i==j) {
					primaryDiagonal += arr.get(i).get(j);
				}
			}
		}
		return Math.abs(primaryDiagonal);
	}


public static void main(String[] args) throws Exception {
	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = Integer.parseInt(bufferedReader.readLine().trim());

    List<List<Integer>> arr = new ArrayList<>();

    IntStream.range(0, n).forEach(i -> {
        try {
            arr.add(
                Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList())
            );
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    });

    int result = Test107.diagonalDifference(arr);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
}
	
}

  