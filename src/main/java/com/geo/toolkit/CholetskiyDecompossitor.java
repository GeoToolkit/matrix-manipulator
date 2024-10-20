package com.geo.toolkit;

import java.security.InvalidAlgorithmParameterException;

public class CholetskiyDecompossitor {


  public double[] forwardSubstitution(double[] lowTriangleElements, double[] vectorB)
      throws InvalidAlgorithmParameterException {
    if (findElementsAmountOfTriangleMatrixByItsOrder(vectorB.length) != lowTriangleElements.length) {
      throw new InvalidAlgorithmParameterException("size of matrices are inappropriate");
    }
    double[] result = new double[vectorB.length];

    result[0] = vectorB[0] / lowTriangleElements[0];

    for (int n = 1; n < vectorB.length; n++) {
      double sum = 0;
      for (int k = 0; k < n; k++) {
        sum += lowTriangleElements[transformTriangleMatrixIndexesToLinearArrayIndex(n, k)] * result[k];
      }
      result[n] = (vectorB[n] - sum) / lowTriangleElements[transformTriangleMatrixIndexesToLinearArrayIndex(n, n)];
    }
    return result;
  }

  public double[] backwardSubstitution(double[] lowTriangleElements, double[] vectorB)
      throws InvalidAlgorithmParameterException {
    if (findElementsAmountOfTriangleMatrixByItsOrder(vectorB.length) != lowTriangleElements.length) {
      throw new InvalidAlgorithmParameterException("size of matrices are inappropriate");
    }
    int numberOfLastElementOfVectorB = vectorB.length - 1;
    double[] result = new double[vectorB.length];

    result[numberOfLastElementOfVectorB] = vectorB[numberOfLastElementOfVectorB] / lowTriangleElements[transformTriangleMatrixIndexesToLinearArrayIndex(numberOfLastElementOfVectorB, numberOfLastElementOfVectorB)];

    for (int n = numberOfLastElementOfVectorB - 1; n >= 0; n--) {
      double sum = 0;
      for (int k = n; k <= numberOfLastElementOfVectorB; k++) {
        sum += lowTriangleElements[transformTriangleMatrixIndexesToLinearArrayIndex(k, n)] * result[k];
      }
      result[n] = (vectorB[n] - sum) / lowTriangleElements[transformTriangleMatrixIndexesToLinearArrayIndex(n, n)];
    }
    return result;
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

  public double[] solveLinearEquations(Matrix matrixA, double[] vectorY)
      throws InvalidAlgorithmParameterException {
    CholetskiyDecompossitor decompossitor = new CholetskiyDecompossitor();
    double[] lowTriangleElements = decompossitor.getDecomposedTriangleMatrixElementsInFormOfLinearArrayFromSymmetricMatrix(
        matrixA);

    double[] intermediateResult = decompossitor.forwardSubstitution(lowTriangleElements, vectorY);
    return decompossitor.backwardSubstitution(lowTriangleElements, intermediateResult);

  }

}
