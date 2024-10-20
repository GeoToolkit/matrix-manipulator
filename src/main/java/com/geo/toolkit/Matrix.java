package com.geo.toolkit;

import java.security.InvalidAlgorithmParameterException;

/**
 * Class for matrix operations.
 */
public class Matrix {

  private double[][] elements;
  private int order;

  private final double precision = 1e-14;

  public Matrix(int order) {
    elements = new double[order][order];
    this.order = order;
  }

  public Matrix( double[][] elements) {
    this.elements = elements;
    this.order = elements.length;
  }

  public Matrix add(Matrix matrix) {
    int order = matrix.order;
    double[][] sum = new double[order][order];

    for (int i = 0; i < order; i++) {
      for (int j = 0; j < order; j++) {
        sum[i][j] = this.elements[i][j] + matrix.elements[i][j];
      }
    }
    return new Matrix(sum);
  }

  public Matrix multiply(Matrix matrix) throws InvalidAlgorithmParameterException {
    if( order != matrix.getOrder()) {
      throw new InvalidAlgorithmParameterException();
    }
    int order = matrix.order;
    double[][] product = new double[order][order];

    for (int i = 0; i < order; i++) {
      for (int j = 0; j < order; j++) {
        for( int k = 0; k < order; k++) {
          product[i][j] += this.elements[i][k] * matrix.elements[k][j];
        }
      }
    }
    return new Matrix(product);
  }

  /**
   * Uses defined precision for comparison of double values.
   */
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
        result = Math.abs(this.elements[i][j] - matrix.elements[i][j]) < precision;
        if(!result) {
          return false;
        }
      }
    }
    return true;
  }
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < order; i++) {
      for (int j = 0; j < order; j++) {
        sb.append(String.format("%e",elements[i][j])).append(" ");
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  public double[][] getElements() {
    return elements;
  }

  public void setElements(double[][] elements) {
    this.elements = elements;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }
}
