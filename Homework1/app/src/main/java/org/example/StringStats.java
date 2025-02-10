package org.example;


public class StringStats {
    public int count = 0;
    public int minLength = Integer.MAX_VALUE;
    public int maxLength = Integer.MIN_VALUE;

    public void update(String value) {
        count++;
        minLength = Math.min(minLength, value.length());
        maxLength = Math.max(maxLength, value.length());
    }

    public void printFullStats() {
        System.out.println("Количество: " + count);
        System.out.println("Минимальная длина строки: " + minLength);
        System.out.println("Максимальная длина строки: " + maxLength);
    }
}
