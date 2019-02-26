package easy.part7_bit_problem;

public class Code01_swap {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        System.out.println(a + " " + b);

        a = a ^ b; // 将a,b两者的不同信息 算出来  只需要这个不同信息就可以得到原来的两个数字！
        b = a ^ b; // 此时的 a ^ b 把最初的a值(1) 给 逼出来了，赋值给b交换
        a = a ^ b; // 此时 a相当于不同信息， b是上一步逼出来的a值(1)，相当于总信息异或上a逼出b，再赋值给a；
        System.out.println(a + " " + b);

    }
}
