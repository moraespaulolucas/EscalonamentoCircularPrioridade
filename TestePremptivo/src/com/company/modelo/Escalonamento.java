package com.company.modelo;

import java.util.ArrayList;
import java.util.Scanner;

public class Escalonamento {
    private static int Quantum;
    private Tipo algoritmoEscolhido;
    private ArrayList<Processo> processos = new ArrayList<Processo>();

    public static int getQuantum() {
        return Quantum;
    }

    public static void setQuantum(int quantum) {
        Quantum = quantum;
    }

    public Tipo getAlgoritmoEscolhido() {
        return algoritmoEscolhido;
    }

    public void setAlgoritmoEscolhido(Tipo algoritmoEscolhido) {
        this.algoritmoEscolhido = algoritmoEscolhido;
    }

    public Tipo escolherAlgoritmo() {
        int x;
        Tipo r;
        do {
            System.out.println("Escolha o algoritmo a ser representado:");
            System.out.println("1- Escalonamento Circular");
            System.out.println("2- Escalonamento Circular por Prioridade");
            Scanner scanner = new Scanner(System.in);
            x = scanner.nextInt();
            if ((x != 1)&&(x != 2))
                System.out.println("------------------------------");
                System.out.println("Entrada inválida");
                System.out.println("------------------------------");
        }while ((x != 1)&&(x != 2));
        switch (x) {
            case 1: r = Tipo.circular;
            case 2: r = Tipo.circularPorPrioridade;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + x);
        }
        return r;
    }

    public ArrayList<Processo> getProcessos() {
        return processos;
    }

    public void setProcessos(ArrayList<Processo> processos) {
        this.processos = processos;
    }
}
