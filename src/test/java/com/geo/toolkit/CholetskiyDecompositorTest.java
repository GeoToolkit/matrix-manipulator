package com.geo.toolkit;

import java.security.InvalidAlgorithmParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CholetskiyDecompositorTest {

  @Test
  void forwardSubstitution_whenTriangleElementsAndVectorBAreReceived_thenResultIsReturned()
      throws InvalidAlgorithmParameterException {
    CholetskiyDecompossitor decompossitor = new CholetskiyDecompossitor();
    double[] triangleElements = {1.0, 2.0, 4.0, 1.0, 3.0, 1.0};
    double[] vectorB = {3.0, 14.0, 13.0};

    double[] actual = decompossitor.forwardSubstitution(triangleElements, vectorB);
    double[] expected = {3.0, 2.0, 4.0};

    Assertions.assertArrayEquals(expected, actual);
  }

  @Test
  void backwardSubstitution_whenTriangleElementsAndVectorBAreReceived_thenResultIsReturned()
      throws InvalidAlgorithmParameterException {
    CholetskiyDecompossitor decompossitor = new CholetskiyDecompossitor();
    double[] lowTriangleElements = {1.0, 2.0, 4.0, 1.0, 3.0, 1.0};
    double[] vectorB = {11.0, 19.0, 1.0};

    double[] actual = decompossitor.backwardSubstitution(lowTriangleElements, vectorB);
    double[] expected = {2.0, 4.0, 1.0};

    Assertions.assertArrayEquals(expected, actual);
  }

  @Test
  void findElementsAmountOfTriangleMatrixByItsOrder_whenTriangleMatrixOrderIsReceived_thenAmountOfElementsIsReturned() {
    CholetskiyDecompossitor decompossitor = new CholetskiyDecompossitor();
    int actual = decompossitor.findElementsAmountOfTriangleMatrixByItsOrder(4);
    Assertions.assertEquals(10, actual);
  }

  @Test
  void transformTriangleMatrixIndexesToLinearArrayIndex_whenIndexesOfLowTriangleMatrixAreObtained_thenSingleArrayIndexIsReturned() {
    CholetskiyDecompossitor decompossitor = new CholetskiyDecompossitor();
    int actual = decompossitor.transformTriangleMatrixIndexesToLinearArrayIndex(4, 3);
    Assertions.assertEquals(13, actual);
  }

  @Test
  void getDecomposedTriangleMatrixElementsInFormOfLinearArrayFromSymmetricMatrix() {
    double[][] elementsOfMatrix = {{1.0, 2.0, 1.0}, {2.0, 20.0, 14.0}, {1.0, 14.0, 11.0}};
    Matrix matrix = new Matrix(elementsOfMatrix);
    CholetskiyDecompossitor decompossitor = new CholetskiyDecompossitor();

    double[] actual = decompossitor.getDecomposedTriangleMatrixElementsInFormOfLinearArrayFromSymmetricMatrix(matrix);
    double[] expected = {1.0, 2.0, 4.0, 1.0, 3.0, 1.0};
    Assertions.assertArrayEquals(expected, actual);
  }

  @Test
  void findVectorX_whenMatrixAAndVectorYAreKnown() throws InvalidAlgorithmParameterException {
    double[][] elementsOfMatrixA = {{1.0, 2.0, 1.0}, {2.0, 20.0, 14.0}, {1.0, 14.0, 11.0}};
    Matrix matrixA = new Matrix(elementsOfMatrixA);
    double[] vectorY = {21.5, 193.4, 137.1};

    CholetskiyDecompossitor decompossitor = new CholetskiyDecompossitor();
    double[] lowTriangleElements = decompossitor.getDecomposedTriangleMatrixElementsInFormOfLinearArrayFromSymmetricMatrix(
        matrixA);

    double[] intermediateResult = decompossitor.forwardSubstitution(lowTriangleElements, vectorY);
    double[] result = decompossitor.backwardSubstitution(lowTriangleElements, intermediateResult);

    double[] expectedVectorX = {4.1, 7.3, 2.8};
    Assertions.assertArrayEquals(expectedVectorX, result, 1e-13);
  }

}
