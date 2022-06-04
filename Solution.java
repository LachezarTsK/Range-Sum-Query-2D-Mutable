
public class NumMatrix {

    int[][] prefixSum;

    public NumMatrix(int[][] matrix) {
        prefixSum = new int[matrix.length + 1][matrix[0].length + 1];
        for (int r = 0; r < matrix.length; ++r) {
            for (int c = 0; c < matrix[0].length; ++c) {
                prefixSum[r + 1][c + 1] = prefixSum[r + 1][c] + prefixSum[r][c + 1] + matrix[r][c] - prefixSum[r][c];
            }
        }
    }

    public void update(int row, int column, int newValue) {
        int previousValue = (column == 0 && row == 0)
                ? prefixSum[row + 1][column + 1]
                : prefixSum[row + 1][column + 1]
                - prefixSum[row][column + 1]
                - prefixSum[row + 1][column]
                + prefixSum[row][column];

        for (int r = row + 1; r < prefixSum.length; ++r) {
            for (int c = column + 1; c < prefixSum[0].length; ++c) {
                prefixSum[r][c] = prefixSum[r][c] - previousValue + newValue;
            }
        }
    }

    public int sumRegion(int row_01, int column_01, int row_02, int column_02) {
        return prefixSum[row_02 + 1][column_02 + 1]
                - prefixSum[row_01][column_02 + 1]
                - prefixSum[row_02 + 1][column_01]
                + prefixSum[row_01][column_01];
    }
}
