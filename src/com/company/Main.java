package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите размерность массива");
        int size = input.nextInt();

        int[] Array = new int[size];

        System.out.println("Введите элементы массива");
        for(int i =0; i< size; i++){
            Array[i] = input.nextInt();
        }

        Threadd t1 = new Threadd(Array); // создание первого потока
        Threadd t2 = new Threadd(Array); // создание второго потока

        try {
            t1.getThread().join();
            t2.getThread().join(); // проверка завершились ли потоки
        } catch (InterruptedException ex){
            System.out.println(ex);
        }

        System.out.println("Максимальное = " + t1.getMax());
        System.out.println("Минимальное = " + t1.getMin()); // вывод максимального и минимального значения
    }
}
class Threadd implements Runnable{
    private Thread thred; // инициализируем поток
    private int[] Arr; // массив
    private int max; // макс
    private int min; // мин

    public Threadd(int[] Array){ // коструктор
    Arr = Array; // присваиваем массив из конструктора в данный класс
    thred = new Thread(this,"Поток"); // создаём поток
    thred.start(); // запускаем поток
    }

    @Override
    public void run() {
        int maX = Arr[0]; // присваиваем переменным первый элемент массива
        int miN = Arr[0];
        for(int i =1 ; i < Arr.length; i++){
            if (maX<Arr[i]) maX = Arr[i]; // находим в массиве максимальное и минимальное значение
            if (miN>Arr[i]) miN = Arr[i];
        }
        max = maX; // передаем их в основные переменные
        min = miN;
    }
    public Thread getThread() { return thred; } // создание геттеров, которые будут возвращать значения
    public int getMax() { return max; }
    public int getMin() { return min; }
}

