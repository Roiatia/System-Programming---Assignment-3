package assign_4;

public class ThreadA extends Thread {
    private final Control ctrl;

    public ThreadA(Control ctrl) {
        this.ctrl = ctrl;
        setName("A");
    }

    private void doA() {
        System.out.println("A");
    }

    @Override
    public void run() {
        while (true) {
            synchronized (ctrl) {
                while (ctrl.state != State.A) {
                    try {
                        ctrl.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }

                doA();
                ctrl.aDone++;

                if (ctrl.aDone == 3) {
                    ctrl.state = State.B; // after 3 A's go to B
                }

                ctrl.notifyAll();
            }
        }
    }



}
