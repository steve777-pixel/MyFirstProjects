package app;

public class Index{
    
    static{
        System.loadLibrary("MathEngine");
    }

    public static native double getPopulationVariance(double[] data);
    public static native double getPopulationSD(double[] data);

}