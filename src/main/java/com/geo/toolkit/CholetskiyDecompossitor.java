package com.geo.toolkit;

public class CholetskiyDecompossitor {

  public Matrix decomposeSymetricMatrix(Matrix symetricMatrix) {
    return null;
  }

  public int transformTriangleMatrixIndexesToLinearArrayIndex(int n, int m) {
    return findElementsAmountOfTriangleMatrixByItsOrder(n) + m;
  }

  public int findElementsAmountOfTriangleMatrixByItsOrder(int order) {
    return (order * order + order) / 2;
  }

  public double[] getDecomposedTriangleMatrixElementsInFormOfLinearArrayFromSymmetricMatrix(Matrix symetricMatrix) {
    int amountTriangleMatrixElements = findElementsAmountOfTriangleMatrixByItsOrder(symetricMatrix.getOrder());
    double[] linearDecomposedTriangleMatrixArray = new double[amountTriangleMatrixElements];

    linearDecomposedTriangleMatrixArray[0] = Math.sqrt(symetricMatrix.getElements()[0][0]);
    linearDecomposedTriangleMatrixArray[1] = symetricMatrix.getElements()[1][0] / linearDecomposedTriangleMatrixArray[0];
    linearDecomposedTriangleMatrixArray[2] = Math.sqrt(symetricMatrix.getElements()[1][1] -Math.pow(linearDecomposedTriangleMatrixArray[1], 2));

    for (int i = 2; i < symetricMatrix.getOrder(); i++) {
      linearDecomposedTriangleMatrixArray[transformTriangleMatrixIndexesToLinearArrayIndex(i, 0)] = symetricMatrix.getElements()[i][0] / linearDecomposedTriangleMatrixArray[0];

      for (int j = 1; j < i; j++) {
        double sum2 = 0;
        for ( int k = 0; k < j; k++) {
          sum2 += linearDecomposedTriangleMatrixArray[transformTriangleMatrixIndexesToLinearArrayIndex(i, k)] * linearDecomposedTriangleMatrixArray[transformTriangleMatrixIndexesToLinearArrayIndex(j, k)];
        }
        linearDecomposedTriangleMatrixArray[transformTriangleMatrixIndexesToLinearArrayIndex(i, j)] = (symetricMatrix.getElements()[i][j] - sum2) / linearDecomposedTriangleMatrixArray[transformTriangleMatrixIndexesToLinearArrayIndex(j, j)];
      }

      double sum = 0;
      for (int k = 0; k < i; k++) {
        sum += Math.pow(linearDecomposedTriangleMatrixArray[transformTriangleMatrixIndexesToLinearArrayIndex(i, k)], 2);
      }
      linearDecomposedTriangleMatrixArray[transformTriangleMatrixIndexesToLinearArrayIndex(i, i)] = Math.sqrt(symetricMatrix.getElements()[i][i] - sum);

    }
    return linearDecomposedTriangleMatrixArray;
  }

}
