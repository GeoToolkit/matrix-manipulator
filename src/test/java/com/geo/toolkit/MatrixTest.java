package com.geo.toolkit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MatrixTest {

  @Test
  void add_whenOneMatrixIsAddedToOther_thenNewCorrectMatrixIsReturned() {
    int[][] elementsOfMatrix1 = {{2, 3, -3},{2, -3, 2},{2, -4, 7}};
    int[][] elementsOfMatrix2 = {{1, 4, -3},{5, -2, -2},{-3, -5, -7}};
    Matrix matrix1 = new Matrix(elementsOfMatrix1);
    Matrix matrix2 = new Matrix(elementsOfMatrix2);
    Matrix matrixSum = matrix1.add(matrix2);
    int[][] element3OfExpectedMatrix = {{3, 7, -6},{7, -5, 0},{-1, -9, 0}};
    Matrix expectedMatrix = new Matrix(element3OfExpectedMatrix);
    Assertions.assertEquals(expectedMatrix, matrixSum);
  }
}
