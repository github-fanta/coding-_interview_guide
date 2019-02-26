package easy.part9_other;

/**
 * 假设Math.random()等概论随机返回一个在[0,1]范围上的数，那么我们知道，在[0，x)区间上的数出现的概率为x(0<x<=1)
 * 给定一个大于0的整数k，并且可以使用Math.random()函数，请实现一个函数依然返回在[0,1)范围上的数，但是在[0,x)
 * 区间上的数出现的概率为x的k次方(0<x<=1)
 */
public class Code03_RandXPowerK {
    /*
    实现在区间[0,x)上的数返回的概率是x的平方，只用调用2次Math.random(),返回最大的那个数即可。即
    return Math.max( Math.random(), Math.random() );
    理解：想让我返回一个数x，但是这个x，能落在[0,x)上的概率得小，于是如果我要返回x，还不行，得再经历一次考验
    最终返回的这个数才是经历了层层筛选考验的那个数。
     */
    public static double getNumWithRandXPowerK(int k) {
        if (k < 1) return 0;
        double res = -1;
        for (int i = 0; i < k; i++) {
            res = Math.max(res, Math.random());
        }
        return res;
    }
}
