package assign_4;

public class Main {
    public static void main(String[] args) {
        Control ctrl = new Control();
        ThreadA threadA = new ThreadA(ctrl);
        ThreadB threadB = new ThreadB(ctrl);
        ThreadC threadC = new ThreadC(ctrl);
        ThreadD threadD = new ThreadD(ctrl);


        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
    }
}
