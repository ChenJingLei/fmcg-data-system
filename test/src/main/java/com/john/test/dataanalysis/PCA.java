package com.john.test.dataanalysis;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

import java.util.*;

/**
 * Created by cjl20 on 2017/5/28.
 */
public class PCA {

    private static final double threshold = 0.95;

    //去除平均值
    public double[][] removeAverage(double[][] data) {
        int row = data.length;
        int col = data[0].length;
        //先列后行
        double[] average = new double[col];
        double[][] removeAverageArray = new double[row][col];
        for (int c = 0; c < col; c++) {
            double temp = 0.0;
            for (int r = 0; r < row; r++) {
                temp += data[r][c];
            }
            average[c] = temp / row;
        }
        for (int c = 0; c < col; c++) {
            for (int r = 0; r < row; r++) {
                removeAverageArray[r][c] = data[r][c] - average[c];
            }
        }
        return removeAverageArray;
    }

    //计算协方差矩阵
    public double[][] getVarianceMatrix(double[][] removeAverageArray) {
        int row = removeAverageArray.length;
        int col = removeAverageArray[0].length;
        double[][] result = new double[row][col];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < col; j++) {
                double temp = 0;
                for (int k = 0; k < row; k++) {
                    temp += removeAverageArray[k][i] * removeAverageArray[k][j];
                }
                result[i][j] = temp / (row - 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        double[][] data = {{5.1, 3.5, 1.4, 0.2},
                {4.9, 3.0, 1.4, 0.2},
                {4.7, 3.2, 1.3, 0.2},
                {4.6, 3.1, 1.5, 0.2},
                {5.0, 3.6, 1.4, 0.2},
                {5.4, 3.9, 1.7, 0.4},
                {4.6, 3.4, 1.4, 0.3},
                {5.0, 3.4, 1.5, 0.2},
                {4.4, 2.9, 1.4, 0.2},
                {4.9, 3.1, 1.5, 0.1},
                {5.4, 3.7, 1.5, 0.2},
                {4.8, 3.4, 1.6, 0.2},
                {4.8, 3.0, 1.4, 0.1},
                {4.3, 3.0, 1.1, 0.1},
                {5.8, 4.0, 1.2, 0.2},
                {5.7, 4.4, 1.5, 0.4},
                {5.4, 3.9, 1.3, 0.4},
                {5.1, 3.5, 1.4, 0.3},
                {5.7, 3.8, 1.7, 0.3},
                {5.1, 3.8, 1.5, 0.3},
                {5.4, 3.4, 1.7, 0.2},
                {5.1, 3.7, 1.5, 0.4},
                {4.6, 3.6, 1.0, 0.2},
                {5.1, 3.3, 1.7, 0.5},
                {4.8, 3.4, 1.9, 0.2},
                {5.0, 3.0, 1.6, 0.2},
                {5.0, 3.4, 1.6, 0.4},
                {5.2, 3.5, 1.5, 0.2},
                {5.2, 3.4, 1.4, 0.2},
                {4.7, 3.2, 1.6, 0.2},
                {4.8, 3.1, 1.6, 0.2},
                {5.4, 3.4, 1.5, 0.4},
                {5.2, 4.1, 1.5, 0.1},
                {5.5, 4.2, 1.4, 0.2},
                {4.9, 3.1, 1.5, 0.1},
                {5.0, 3.2, 1.2, 0.2},
                {5.5, 3.5, 1.3, 0.2},
                {4.9, 3.1, 1.5, 0.1},
                {4.4, 3.0, 1.3, 0.2},
                {5.1, 3.4, 1.5, 0.2},
                {5.0, 3.5, 1.3, 0.3},
                {4.5, 2.3, 1.3, 0.3},
                {4.4, 3.2, 1.3, 0.2},
                {5.0, 3.5, 1.6, 0.6},
                {5.1, 3.8, 1.9, 0.4},
                {4.8, 3.0, 1.4, 0.3},
                {5.1, 3.8, 1.6, 0.2},
                {4.6, 3.2, 1.4, 0.2},
                {5.3, 3.7, 1.5, 0.2},
                {5.0, 3.3, 1.4, 0.2},
                {7.0, 3.2, 4.7, 1.4},
                {6.4, 3.2, 4.5, 1.5},
                {6.9, 3.1, 4.9, 1.5},
                {5.5, 2.3, 4.0, 1.3},
                {6.5, 2.8, 4.6, 1.5},
                {5.7, 2.8, 4.5, 1.3},
                {6.3, 3.3, 4.7, 1.6},
                {4.9, 2.4, 3.3, 1.0},
                {6.6, 2.9, 4.6, 1.3},
                {5.2, 2.7, 3.9, 1.4},
                {5.0, 2.0, 3.5, 1.0},
                {5.9, 3.0, 4.2, 1.5},
                {6.0, 2.2, 4.0, 1.0},
                {6.1, 2.9, 4.7, 1.4},
                {5.6, 2.9, 3.6, 1.3},
                {6.7, 3.1, 4.4, 1.4},
                {5.6, 3.0, 4.5, 1.5},
                {5.8, 2.7, 4.1, 1.0},
                {6.2, 2.2, 4.5, 1.5},
                {5.6, 2.5, 3.9, 1.1},
                {5.9, 3.2, 4.8, 1.8},
                {6.1, 2.8, 4.0, 1.3},
                {6.3, 2.5, 4.9, 1.5},
                {6.1, 2.8, 4.7, 1.2},
                {6.4, 2.9, 4.3, 1.3},
                {6.6, 3.0, 4.4, 1.4},
                {6.8, 2.8, 4.8, 1.4},
                {6.7, 3.0, 5.0, 1.7},
                {6.0, 2.9, 4.5, 1.5},
                {5.7, 2.6, 3.5, 1.0},
                {5.5, 2.4, 3.8, 1.1},
                {5.5, 2.4, 3.7, 1.0},
                {5.8, 2.7, 3.9, 1.2},
                {6.0, 2.7, 5.1, 1.6},
                {5.4, 3.0, 4.5, 1.5},
                {6.0, 3.4, 4.5, 1.6},
                {6.7, 3.1, 4.7, 1.5},
                {6.3, 2.3, 4.4, 1.3},
                {5.6, 3.0, 4.1, 1.3},
                {5.5, 2.5, 4.0, 1.3},
                {5.5, 2.6, 4.4, 1.2},
                {6.1, 3.0, 4.6, 1.4},
                {5.8, 2.6, 4.0, 1.2},
                {5.0, 2.3, 3.3, 1.0},
                {5.6, 2.7, 4.2, 1.3},
                {5.7, 3.0, 4.2, 1.2},
                {5.7, 2.9, 4.2, 1.3},
                {6.2, 2.9, 4.3, 1.3},
                {5.1, 2.5, 3.0, 1.1},
                {5.7, 2.8, 4.1, 1.3},
                {6.3, 3.3, 6.0, 2.5},
                {5.8, 2.7, 5.1, 1.9},
                {7.1, 3.0, 5.9, 2.1},
                {6.3, 2.9, 5.6, 1.8},
                {6.5, 3.0, 5.8, 2.2},
                {7.6, 3.0, 6.6, 2.1},
                {4.9, 2.5, 4.5, 1.7},
                {7.3, 2.9, 6.3, 1.8},
                {6.7, 2.5, 5.8, 1.8},
                {7.2, 3.6, 6.1, 2.5},
                {6.5, 3.2, 5.1, 2.0},
                {6.4, 2.7, 5.3, 1.9},
                {6.8, 3.0, 5.5, 2.1},
                {5.7, 2.5, 5.0, 2.0},
                {5.8, 2.8, 5.1, 2.4},
                {6.4, 3.2, 5.3, 2.3},
                {6.5, 3.0, 5.5, 1.8},
                {7.7, 3.8, 6.7, 2.2},
                {7.7, 2.6, 6.9, 2.3},
                {6.0, 2.2, 5.0, 1.5},
                {6.9, 3.2, 5.7, 2.3},
                {5.6, 2.8, 4.9, 2.0},
                {7.7, 2.8, 6.7, 2.0},
                {6.3, 2.7, 4.9, 1.8},
                {6.7, 3.3, 5.7, 2.1},
                {7.2, 3.2, 6.0, 1.8},
                {6.2, 2.8, 4.8, 1.8},
                {6.1, 3.0, 4.9, 1.8},
                {6.4, 2.8, 5.6, 2.1},
                {7.2, 3.0, 5.8, 1.6},
                {7.4, 2.8, 6.1, 1.9},
                {7.9, 3.8, 6.4, 2.0},
                {6.4, 2.8, 5.6, 2.2},
                {6.3, 2.8, 5.1, 1.5},
                {6.1, 2.6, 5.6, 1.4},
                {7.7, 3.0, 6.1, 2.3},
                {6.3, 3.4, 5.6, 2.4},
                {6.4, 3.1, 5.5, 1.8},
                {6.0, 3.0, 4.8, 1.8},
                {6.9, 3.1, 5.4, 2.1},
                {6.7, 3.1, 5.6, 2.4},
                {6.9, 3.1, 5.1, 2.3},
                {5.8, 2.7, 5.1, 1.9},
                {6.8, 3.2, 5.9, 2.3},
                {6.7, 3.3, 5.7, 2.5},
                {6.7, 3.0, 5.2, 2.3},
                {6.3, 2.5, 5.0, 1.9},
                {6.5, 3.0, 5.2, 2.0},
                {6.2, 3.4, 5.4, 2.3},
                {5.9, 3.0, 5.1, 1.8}};
        PCA pca = new PCA();
        double[][] aa = pca.getVarianceMatrix(pca.removeAverage(data));
        for (int i = 0; i < aa.length; i++) {
            for (int j = 0; j < aa[0].length; j++) {
                System.out.print(aa[i][j] + " ");
            }
            System.out.println();
        }
    }


    /**
     * 求特征值矩阵
     *
     * @param matrix 协方差矩阵
     * @return result 向量的特征值二维数组矩阵
     */
    public double[][] getEigenValueMatrix(double[][] varianceMatrix) {
        Matrix S = new Matrix(varianceMatrix);
        // 由特征值组成的对角矩阵,eig()获取特征值
        double[][] eigenValueMatrix = S.eig().getD().getArray();
        return eigenValueMatrix;
    }

    /**
     * 标准化矩阵（特征向量矩阵）
     *
     * @param matrix 特征值矩阵
     * @return result 标准化后的二维数组矩阵
     */
    public double[][] getEigenVectorMatrix(double[][] eigenValueMatrix) {
        Matrix A = new Matrix(eigenValueMatrix);
        double[][] eigenVectorMatrix = A.eig().getV().getArray();
        return eigenVectorMatrix;
    }

    /**
     * 寻找主成分
     *
     * @param prinmaryArray 原始二维数组数组
     * @param eigenvalue    特征值二维数组
     * @param eigenVectors  特征向量二维数组
     * @return principalMatrix 主成分矩阵
     */
    public Matrix getPrincipalComponent(double[][] primaryArray,
                                        double[][] eigenvalue, double[][] eigenVectors) {
        Matrix A = new Matrix(eigenVectors);// 定义一个特征向量矩阵
        double[][] tEigenVectors = A.transpose().getArray();// 特征向量转置
        Map<Integer, double[]> principalMap = new HashMap<Integer, double[]>();// key=主成分特征值，value=该特征值对应的特征向量
        TreeMap<Double, double[]> eigenMap = new TreeMap<Double, double[]>(
                Collections.reverseOrder());// key=特征值，value=对应的特征向量；初始化为翻转排序，使map按key值降序排列
        double total = 0;// 存储特征值总和
        int index = 0, n = eigenvalue.length;
        double[] eigenvalueArray = new double[n];// 把特征值矩阵对角线上的元素放到数组eigenvalueArray里
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    eigenvalueArray[index] = eigenvalue[i][j];
            }
            index++;
        }

        for (int i = 0; i < tEigenVectors.length; i++) {
            double[] value = new double[tEigenVectors[0].length];
            value = tEigenVectors[i];
            eigenMap.put(eigenvalueArray[i], value);
        }

        // 求特征总和
        for (int i = 0; i < n; i++) {
            total += eigenvalueArray[i];
        }
        // 选出前几个主成分
        double temp = 0;
        int principalComponentNum = 0;// 主成分数
        List<Double> plist = new ArrayList<Double>();// 主成分特征值
        for (double key : eigenMap.keySet()) {
            if (temp / total <= threshold) {
                temp += key;
                plist.add(key);
                principalComponentNum++;
            }
        }
        System.out.println("\n" + "当前阈值: " + threshold);
        System.out.println("取得的主成分数: " + principalComponentNum + "\n");

        // 往主成分map里输入数据
        for (int i = 0; i < plist.size(); i++) {
            if (eigenMap.containsKey(plist.get(i))) {
                principalMap.put(i, eigenMap.get(plist.get(i)));
            }
        }

        // 把map里的值存到二维数组里
        double[][] principalArray = new double[principalMap.size()][];
        Iterator<Map.Entry<Integer, double[]>> it = principalMap.entrySet()
                .iterator();
        for (int i = 0; it.hasNext(); i++) {
            principalArray[i] = it.next().getValue();
        }

        Matrix principalMatrix = new Matrix(principalArray);

        return principalMatrix;
    }

    /**
     * 矩阵相乘
     *
     * @param primary 原始二维数组
     * @param matrix  主成分矩阵
     * @return result 结果矩阵
     */
    public Matrix getResult(double[][] primary, Matrix matrix) {
        Matrix primaryMatrix = new Matrix(primary);
        Matrix result = primaryMatrix.times(matrix.transpose());
        return result;
    }
}

