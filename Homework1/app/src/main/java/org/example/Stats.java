package org.example;


public class Stats {
    public int count = 0;
    public double sum = 0;
    public double min = Double.MAX_VALUE;
    public double max = Double.MIN_VALUE;

    public void update(int value) {
        count++;
        sum += value;
        min = Math.min(min, value);
        max = Math.max(max, value);
    }

    public void update(float value) {
        count++;
        sum += value;
        min = Math.min(min, value);
        max = Math.max(max, value);
    }

    public void printFullStats() {
        System.out.println("Количество: " + count);
        System.out.println("Минимум: " + min);
        System.out.println("Максимум: " + max);
        System.out.println("Сумма: " + sum);
        System.out.println("Среднее: " + (count > 0 ? sum / count : 0) + "\n");
    }
}
