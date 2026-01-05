package assign_4;

public class ThreadB extends Thread {
    private final Control ctrl;

    public ThreadB(Control ctrl) {
        this.ctrl = ctrl;
    }

    private void doB() {
        System.out.println("B");
    }

    @Override
    public void run() {
        while (true) {
            synchronized (ctrl) {
                while (ctrl.states != State.B) {
                    try {
                        ctrl.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                doB();
                synchronized (ctrl) {
                    ctrl.bSwitchToC = true;
                    ctrl.notifyAll();
                }
                Thread.yield();
            }
        }
    }
}
