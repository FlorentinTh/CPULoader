public class CPULoad {

    private static final int coreNumber = 4;
    private static final int threadNumber = 8;


    public static void main(String[] args) {
        final double load = 0.8;
        final long duration = 100000;

        for (int i = 0; i < coreNumber * threadNumber; i++) {
            new WorkingThread("Thread: " + i, load, duration).start();
        }
    }

    private static class WorkingThread extends Thread {
        private double load;
        private long duration;

        public WorkingThread(String name, double load, long duration) {
            super(name);
            this.load = load;
            this.duration = duration;
        }

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            try {
                while (System.currentTimeMillis() - startTime < duration) {
                    if (System.currentTimeMillis() % 100 == 0) {
                        Thread.sleep((long) Math.floor((1 - load) * 100));
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
