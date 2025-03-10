package org.example;

import java.util.concurrent.CountDownLatch;

public class App {

    private static int Thread_count = 5;
    private static int Bar_length = 10;
    private static CountDownLatch LATCH;

    private static StringBuilder[] progressBars = new StringBuilder[Thread_count];
    private static long[] threadIds = new long[Thread_count];
    private static boolean[] threadCompleted = new boolean[Thread_count];
    private static long[] timeTaken = new long[Thread_count];

    public static class CalculationThread implements Runnable {
        private int threadNumber;


        public CalculationThread(int threadNumber) {
            this.threadNumber = threadNumber;
        }

        @Override
        public void run() {
            try {
                progressBars[threadNumber - 1] = new StringBuilder();
                threadIds[threadNumber - 1] = Thread.currentThread().getId();

                long startTime = System.nanoTime();

                for (int i = 0; i < Bar_length; i++) {
                    progressBars[threadNumber - 1].append("■");

                    synchronized (App.class) {
                        System.out.print("\033[H\033[2J");


                        for (int j = 0; j < Thread_count; j++) {
                            if (threadCompleted[j]) {
                                System.out.println("Поток " + (j + 1) + " завершил работу за " + timeTaken[j] / 1_000_000 + " миллисекунд.");
                            } else {
                                String progress = progressBars[j].toString();
                                System.out.println("Поток " + (j + 1) + " (Id " + threadIds[j] + ") [" + progress + " ".repeat(Bar_length - progress.length()) + "]");
                            }
                        }
                    }

                    Thread.sleep(1110);
                }

                long endTime = System.nanoTime();
                timeTaken[threadNumber - 1] = endTime - startTime;
                synchronized (App.class) {
                    threadCompleted[threadNumber - 1] = true;
                }

                LATCH.countDown();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        LATCH = new CountDownLatch(Thread_count);

        for (int i = 0; i < Thread_count; i++) {
            progressBars[i] = new StringBuilder();
        }

        for (int i = 1; i <= Thread_count; i++) {
            new Thread(new CalculationThread(i)).start();
            Thread.sleep(500);
        }

        LATCH.await();

        synchronized (App.class) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            for (int i = 0; i < Thread_count; i++) {
                System.out.println("Поток " + (i + 1) + " завершил работу за " + timeTaken[i] / 1_000_000 + " миллисекунд.");
            }
        }

        System.out.println("Все потоки завершили свою работу.");
    }
}
