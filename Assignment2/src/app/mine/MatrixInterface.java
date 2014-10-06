package app.mine;

public interface MatrixInterface {
    public double[][] getMatrixB();

    public void setMatrixB(int sizeB);

    public double[][] getMatrixA();

    public void setMatrixA(int sizeA);

    public void multiply();

    public int getSizeA();

    public void setSizeA(int sizeA);

    public int getSizeB();

    public void setSizeB(int sizeB);

    public void matrixA();

    public void matrixB();

    public int getThreads();

    public void setThreads(int threads);
}
