package assign_4;

public class ThreadC extends Thread {
    private final Control ctrl;

    public ThreadC(Control ctrl) {
        this.ctrl = ctrl;
        setName("C");
    }

    private void doC() {
        System.out.print("C");
    }

    @Override
    public void run() {
        while (true) {
            synchronized (ctrl) {
                while (ctrl.states != assign_4.State.C) {
                    try {
                        ctrl.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }

                doC();
                ctrl.cDone++;

                if (ctrl.cDone == 2) {
                    ctrl.states = assign_4.State.D; // after 2 C's go to D
                }

                ctrl.notifyAll();
            }
        }
    }
}
