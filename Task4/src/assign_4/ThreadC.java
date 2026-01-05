package assign_4;

public class ThreadC extends Thread {
    private final Control ctrl;

    public  ThreadC(Control ctrl) {
        this.ctrl = ctrl;
    }

    private void doC() {
        System.out.println("C");
    }

    @Override
    public void run() {
        while(true) {
            synchronized (ctrl) {
                while(!(ctrl.states == State.B && ctrl.bSwitchToC)&& ctrl.states != Stat.C) {
                    try {
                        ctrl.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                    if (ctrl.states == State.B && ctrl.bSwitchToC) {
                        ctrl.states = State.C;
                        ctrl.cDone = 0;
                        ctrl.bSwitchToC = false;
                        ctrl.notifyAll();
                    }
                }
                synchronized (ctrl) {
                    while (ctrl.state != state.C) {
                        try {
                            ctrl.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    doC();

                    synchronized (ctrl) {
                        ctrl.cDone++;
                        if (ctrl.cDone == 2) {
                            ctrl.states = State.D;
                            ctrl.dSwitchToB = false;
                        }
                        ctrl.notifyAll();
                    }
                }
            }
        }
    }
}
