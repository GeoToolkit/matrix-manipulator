package com.geo.toolkit;

public class Matrix {

  private int[][] elements;
  private int order;

  public Matrix(int order) {
    elements = new int[order][order];
    this.order = order;
  }

  public Matrix( int[][] elements) {
    this.elements = elements;
    this.order = elements.length;
  }

  public Matrix add(Matrix matrix) {
    int order = matrix.order;
    int[][] sum = new int[order][order];

    for (int i = 0; i < order; i++) {
      for (int j = 0; j < order; j++) {
        sum[i][j] = this.elements[i][j] + matrix.elements[i][j];
      }
    }
    return new Matrix(sum);
  }

  @Override
  public boolean equals(Object incomingMatrix) {
    if (this == incomingMatrix) {
      return true;
    }
    if (incomingMatrix == null || getClass() != incomingMatrix.getClass()) {
      return false;
    }

    Matrix matrix = (Matrix) incomingMatrix;
    boolean result;
    for (int i = 0; i < order; i++) {
      for (int j = 0; j < order; j++) {
        result = this.elements[i][j] == matrix.elements[i][j];
        if(!result) {
          return false;
        }
      }
    }
    return true;
  }

  public int[][] getElements() {
    return elements;
  }

  public void setElements(int[][] elements) {
    this.elements = elements;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }
}
