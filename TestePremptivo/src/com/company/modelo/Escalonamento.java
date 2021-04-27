package com.company.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Escalonamento {
    private int quantum;
    private Tipo algoritmoEscolhido;
    private ArrayList<Processo> processos = new ArrayList<Processo>();
    private int index = 0;
    private int tempoProcessamentoTotal = 0; // TPT
    private int tempoProcessamentoRestante = 0;
    private int tempoDecorrido = 0;
    private float somaRetorno = 0;
    private float somaEspera = 0;
    private float tempoMedioRetorno;
    private float tempoMedioEspera;

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public Tipo getAlgoritmoEscolhido() {
        return algoritmoEscolhido;
    }

    public void setAlgoritmoEscolhido(Tipo algoritmoEscolhido) {
        this.algoritmoEscolhido = algoritmoEscolhido;
    }

    public ArrayList<Processo> getProcessos() {
        return processos;
    }

    public void setProcessos(ArrayList<Processo> processos) {
        this.processos = processos;
    }

    public void escolherAlgoritmo() {
        Scanner scanner = new Scanner(System.in);
        int x;
        do {
            System.out.println("Escolha o algoritmo a ser representado:");
            System.out.println("1- Escalonamento Circular");
            System.out.println("2- Escalonamento Circular por Prioridade");
            x = scanner.nextInt();
            if ((x != 1)&&(x != 2)){
                System.out.println("------------------------------");
                System.out.println("Entrada inválida");
                System.out.println("------------------------------");
            }
        }while ((x != 1)&&(x != 2));
        switch (x) {
            case 1: this.algoritmoEscolhido = Tipo.circular; break;
            case 2: this.algoritmoEscolhido = Tipo.circularPorPrioridade; break;
        }
    }

    public void inserirQuantum() {
        Scanner scanner = new Scanner(System.in);
        int quantum;
        do {
            System.out.println("Insira o quantum:");
            quantum = scanner.nextInt();
            if (quantum <= 0) {
                System.out.println("------------------------------");
                System.out.println("Entrada inválida");
                System.out.println("------------------------------");
            }
        }while (quantum <= 0);
        this.quantum = quantum;
    }
    
    public void menuInserirMostrar() {
        Scanner sc = new Scanner(System.in);
        int x;
        do {
            System.out.println("------------------------------");
            System.out.println("1-Inserir processo");
            System.out.println("2-Mostrar resultado");
            System.out.println("------------------------------");
            x = sc.nextInt();
            switch (x) {
                case 1: this.inserirProcesso(); break;
                case 2: break;
                default:
                    System.out.println("------------------------------");
                    System.out.println("Entrada inválida");
                    System.out.println("------------------------------");
            }
        }while (x != 2);
    }

    private void inserirProcesso() {
        Scanner sc = new Scanner(System.in);
        Processo processo = new Processo();

        index = index + 1;

        System.out.println("Inserir o nome do processo:");
        processo.setNome(sc.nextLine());
        System.out.println("Inserir tempo de execução necessária:");
        processo.setTempoRestante(sc.nextInt());
        if (this.algoritmoEscolhido == Tipo.circularPorPrioridade) {
            System.out.println("Inserir prioridade");
            processo.setPrioridade(sc.nextInt());
        }
        processo.setTempoExecutado(processo.getTempoRestante());

        this.processos.add(processo);// adiciona o processo q acabou de ser cadastrado na lista de processos

        this.tempoProcessamentoTotal += processo.getTempoRestante();
        this.tempoProcessamentoRestante += processo.getTempoRestante();

        if (this.algoritmoEscolhido == Tipo.circularPorPrioridade){
            Collections.sort(processos);
        }

    }

    public void mostrarResultado() {
        int i;
        this.fazerEscalonamento();
        for (i = 0; i < this.processos.size(); i++) {
            Processo processo = (Processo) processos.get(i);
            System.out.println("------------------------------");
            System.out.println("Nome: "+processo.getNome()+" | "+"Tempo de execução: "+processo.getTempoExecutado());
            if (this.algoritmoEscolhido == Tipo.circularPorPrioridade) {
                System.out.println("Prioridade: "+processo.getPrioridade());
            }
            System.out.println("Turnaround: "+processo.getTurnaround());
            System.out.println("Tempo de espera: "+processo.getTempoEspera());
            System.out.println("------------------------------");
        }
        System.out.println("Tempo total de processamento: "+this.tempoProcessamentoTotal);
        System.out.println("Tempo médio de retorno: "+this.tempoMedioRetorno);
        System.out.println("Tempo médio de espera: "+this.tempoMedioEspera);
    }

    private void fazerEscalonamento () {
        int i, j, k;
        while (this.tempoProcessamentoRestante > 0) {
            for (i = 0; i < this.processos.size(); i++) {
                Processo processo = (Processo) processos.get(i);
                if (processo.getTempoRestante() == 0) {
                    continue;
                }
                if (processo.getTempoRestante() >= this.quantum) {
                    processo.setTempoRestante(processo.getTempoRestante() - this.quantum);
                    this.tempoProcessamentoRestante -= this.quantum;
                    this.tempoDecorrido += this.quantum;
                    System.out.printf("%s -> ", processo.getNome());
                    for (j = 0; j < this.quantum; j++) {
                        System.out.printf("X");
                    }
                    System.out.printf("\n");
                }else {
                    this.tempoProcessamentoRestante -= processo.getTempoRestante();
                    this.tempoDecorrido += processo.getTempoRestante();
                    System.out.printf("%s -> ", processo.getNome());
                    for (j = 0; j < processo.getTempoRestante(); j++) {
                        System.out.printf("X");
                    }
                    System.out.printf("\n");
                    processo.setTempoRestante(0);
                }
                processo.setTurnaround(this.tempoDecorrido);
                processo.setTempoEspera(this.tempoDecorrido - processo.getTempoExecutado());
            }
        }
        for (k = 0; k < this.processos.size(); k++) {
            Processo processo = (Processo) processos.get(k);
            this.somaEspera += processo.getTempoEspera();
            this.somaRetorno += processo.getTurnaround();
        }
        float x = this.processos.size();
        this.tempoMedioRetorno = this.somaRetorno / x;
        this.tempoMedioEspera = this.somaEspera / x;
    }
}
