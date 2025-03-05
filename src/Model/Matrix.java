package Model;

import java.util.Arrays;

public class Matrix {

	private final int rows_, cols_;
	private final double[][] elements_;

	public Matrix(int rows_, int cols_, double[][] elements) {
		this.rows_ = rows_;
		this.cols_ = cols_;
		this.elements_ = elements;

	}

	public Matrix(double[][] elements_) {
		this.rows_ = elements_.length;
		this.cols_ = elements_[0].length;
		this.elements_ = new double[rows_][cols_];

		for (int i = 0; i < cols_; i++) {
			if (elements_[i].length != cols_) {
				throw new IllegalArgumentException("All rows_ must have the same number of columns.");
			}
			System.arraycopy(elements_[i], 0, this.elements_[i], 0, cols_);
		}
	}

	public Matrix add(Matrix other) {
		checkDimension(other);
		double[][] result = new double[rows_][cols_];
		for (int i = 0; i < rows_; i++) {
			for (int j = 0; j < cols_; j++) {
				result[i][j] = this.elements_[i][j] + other.elements_[i][j];
			}
		}
		return new Matrix(result);
	}

	// 행렬 뺄셈
	public Matrix subtract(Matrix other) {
		checkDimension(other);
		double[][] result = new double[rows_][cols_];
		for (int i = 0; i < rows_; i++) {
			for (int j = 0; j < cols_; j++) {
				result[i][j] = this.elements_[i][j] - other.elements_[i][j];
			}
		}
		return new Matrix(result);
	}

	// 행렬 곱셈
	public Matrix multiply(Matrix other) {
		if (this.cols_ != other.rows_) {
			throw new IllegalArgumentException("Invalid matrix multiplication dimensions.");
		}
		double[][] result = new double[this.rows_][other.cols_];
		for (int i = 0; i < this.rows_; i++) {
			for (int j = 0; j < other.cols_; j++) {
				for (int k = 0; k < this.cols_; k++) {
					result[i][j] += this.elements_[i][k] * other.elements_[k][j];
				}
			}
		}
		return new Matrix(result);
	}

	// 전치 행렬
	public Matrix transpose() {
		double[][] result = new double[cols_][rows_];
		for (int i = 0; i < rows_; i++) {
			for (int j = 0; j < cols_; j++) {
				result[j][i] = this.elements_[i][j];
			}
		}
		return new Matrix(result);
	}

	// 단위 행렬 반환
	public static Matrix identity(int size) {
		double[][] identity = new double[size][size];
		for (int i = 0; i < size; i++) {
			identity[i][i] = 1.0;
		}
		return new Matrix(identity);
	}

	// 행렬을 문자열로 변환하여 출력
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (double[] row : elements_) {
			sb.append(Arrays.toString(row)).append("\n");
		}
		return sb.toString();
	}

	// 행렬 크기 체크
	private void checkDimension(Matrix other) {
		if (this.rows_ != other.rows_ || this.cols_ != other.cols_) {
			throw new IllegalArgumentException("Matrix dimensions must match.");
		}
	}


}
