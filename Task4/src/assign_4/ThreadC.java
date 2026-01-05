package assign_4;

public class ThreadC extends Thread {
    private final Control ctrl;

    public ThreadC(Control ctrl) {
        this.ctrl = ctrl;
        setName("C");
    }

    private void doC() {
        System.out.println("C");
    }

    @Override
    public void run() {
        while (true) {
            synchronized (ctrl) {
                while (ctrl.state != State.C) {
                    try {
                        ctrl.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }

                doC();
                ctrl.cDone++;

                if (ctrl.cDone == 2) {
                    ctrl.state = State.D; // after 2 C's go to D
                }

                ctrl.notifyAll();
            }
        }
    }
}
