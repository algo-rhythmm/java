import java.io.*;
import java.util.StringTokenizer;

public class 붐버맨 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();

        String input = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(input);

        int rows = Integer.parseInt(tokenizer.nextToken());
        int cols = Integer.parseInt(tokenizer.nextToken());
        int time = Integer.parseInt(tokenizer.nextToken());

        char[][] currentGrid = new char[rows][cols];
        char[][] previousGrid = new char[rows][cols];

        for (int row = 0; row < rows; row++) {
            String line = reader.readLine();
            for (int col = 0; col < cols; col++) {
                currentGrid[row][col] = line.charAt(col);
                previousGrid[row][col] = line.charAt(col);
            }
        }

        for (int t = 2; t <= time; t++) {
            int[][] directions = {{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            if (t % 4 == 2 || t % 4 == 0) {
                // At 2 seconds or 4 seconds, fill all cells with bombs
                for (int row = 0; row < rows; row++) {
                    for (int col = 0; col < cols; col++) {
                        currentGrid[row][col] = 'O';
                    }
                }
            } else {
                for (int row = 0; row < rows; row++) {
                    for (int col = 0; col < cols; col++) {
                        if (previousGrid[row][col] == 'O') {
                            for (int d = 0; d < 5; d++) {
                                int newRow = row + directions[d][0];
                                int newCol = col + directions[d][1];
                                if (0 <= newRow && newRow < rows && 0 <= newCol && newCol < cols) {
                                    currentGrid[newRow][newCol] = '.';
                                }
                            }
                        }
                    }
                }

                for (int row = 0; row < rows; row++) {
                    for (int col = 0; col < cols; col++) {
                        previousGrid[row][col] = currentGrid[row][col];
                    }
                }
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                output.append(currentGrid[row][col]);
            }
            output.append("\n");
        }
        System.out.print(output);
    }
}
