package assign_4;

public class ThreadB extends Thread {
    private final Control ctrl;

    public ThreadB(Control ctrl) {
        this.ctrl = ctrl;
        setName("B");
    }

    private void doB() {
        System.out.print("B");
    }

    @Override
    public void run() {
        while (true) {
            synchronized (ctrl) {
                while (ctrl.states != assign_4.State.B) {
                    try {
                        ctrl.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }

                doB();

                // B always moves to C and resets C counter
                ctrl.cDone = 0;
                ctrl.states = assign_4.State.C;

                ctrl.notifyAll();
            }
        }
    }
}
