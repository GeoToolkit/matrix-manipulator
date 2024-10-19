package com.geo.toolkit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MatrixTest {

  @Test
  void add_whenOneMatrixIsAddedToOther_thenNewCorrectMatrixIsReturned() {
    double[][] elementsOfMatrix1 = {{20_000_000_000_000_000.6, 3.7, -3.2}, {2.1, -3.7, 2.6}, {2.5, -4.3, 7.1}};
    double[][] elementsOfMatrix2 = {{20_000_000_000_000_000.7, 4.4, -3.5}, {5.6, -2.1, -2.3}, {-3.4, -5.5, -7.9}};
    Matrix matrix1 = new Matrix(elementsOfMatrix1);
    Matrix matrix2 = new Matrix(elementsOfMatrix2);
    Matrix matrixSum = matrix1.add(matrix2);
    double[][] element3OfExpectedMatrix = {{40_000_000_000_000_001.3, 8.1, -6.7},{7.7, -5.8, 0.3},{-0.9, -9.8, -0.8}};
    Matrix expectedMatrix = new Matrix(element3OfExpectedMatrix);
    System.out.println(expectedMatrix.equals(matrixSum));
    Assertions.assertEquals(expectedMatrix, matrixSum);
  }
}
