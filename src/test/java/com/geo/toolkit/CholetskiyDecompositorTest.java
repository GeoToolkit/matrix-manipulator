package com.geo.toolkit;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CholetskiyDecompositorTest {

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

}
