package app.mine;

public interface MatrixInterface {
    public void setMatrixA(int sizeA);
    public void setMatrixB(int sizeB);
    public double[][] getMatrixB();
    public double[][] getMatrixA();
    public void multiply ();
    public void setSizeA(int sizeA);
    public int getSizeA();
    public void setSizeB(int sizeB);
    public int getSizeB();
    public void matrixA();
    public void matrixB();
    public void setThreads(int threads);
    public int getThreads();
}
