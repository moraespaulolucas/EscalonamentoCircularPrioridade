package com.company;

import com.company.modelo.Processo;
import com.company.modelo.Escalonamento;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Escalonamento escalonamento = new Escalonamento();
        Processo processo[] = new Processo[20];

        int quantum;
        int TempoMedioEspera = 0;
        int NumeroSelecinado = 0;
        int CalculoPriorizado;
        int Total = 0;
        int Escolha;
        int Inicio = 0;
        int i;
        int j;
        int Prioridades[] = new int[20];
        int aux = 0;
        int Termino;
        int TesteTermino = 0;
        int TempoDecorrido = 0;
        int TPT = 0;
        int auxTPE;
        float TempoMedioEsperaCalculo;
        int TempoRetorno = 0;
        
        escalonamento.escolherAlgoritmo();

        do {
            System.out.println("Insira o quantum:");
            quantum = sc.nextInt();
            if (quantum <= 0) {
                System.out.println("------------------------------");
                System.out.println("Entrada inválida");
                System.out.println("------------------------------");
            }
        }while (quantum <= 0);
        escalonamento.setQuantum(quantum);

        do {
            System.out.println("------------------------------");
            System.out.println("1-Inserir");
            System.out.println("2-Mostrar");
            System.out.println("------------------------------");
            do {
                Escolha = sc.nextInt();
                if (Escolha != 1 && Escolha != 2) {
                    System.out.println("Por favor inserir numero valido");
                }
            } while (Escolha != 1 && Escolha != 2);
            sc.nextLine();
            if (Escolha == 1) {
                Total = Total + 1;
                Inicio = Inicio + 1;

                processo[Inicio] = new Processo();
                System.out.println("Inserir Nome do Processo");
                processo[Inicio].setNomeDado(sc.nextLine());
                System.out.println("Inserir quantidade de CPU necessaria");
                processo[Inicio].setCPU(sc.nextInt());
                System.out.println("Inserir prioridade");
                processo[Inicio].setPrioridade(sc.nextInt());
                Prioridades[((Inicio) - 1)] = processo[Inicio].getPrioridade();
                processo[Inicio].setFinalizado(0);
                TPT = TPT + processo[Inicio].getCPU();
                processo[Inicio].setDuracao(processo[Inicio].getCPU());

            }


        } while (Escolha == 1);
        Arrays.sort(Prioridades);
        do{
            for (i = 19; i > (19 - Total); i--) {
                NumeroSelecinado = Prioridades[i];

                for (j = 1; j <= Total; j++) {
                    if (NumeroSelecinado == processo[j].getPrioridade()) {
                        if (processo[j].getCPU() > 0) {
                            aux = 0;
                            if(Escalonamento.getQuantum() > processo[j].getCPU()){
                                aux = processo[j].getCPU() - Escalonamento.getQuantum();
                            }

                            CalculoPriorizado = processo[j].getCPU() - Escalonamento.getQuantum();
                            processo[j].setCPU(CalculoPriorizado);

                                TempoDecorrido = TempoDecorrido + Escalonamento.getQuantum() + aux;
                            //System.out.print(processo[j].getNomeDado());
                            //System.out.print(":" + CalculoPriorizado + " ");
                            //System.out.print("TempoDecorrido:" + TempoDecorrido + "\n");

                            //Teste para ver se terminou
                            if(processo[j].getFinalizado() == 0) {
                                if (processo[j].getCPU() <= 0) {
                                    processo[j].setTurnaround(TempoDecorrido);
                                    processo[j].setFinalizado(1);
                                    TesteTermino = TesteTermino + 1;
                                }
                            }
                        } else {

                        }
                    }
                }

            }



        }while(TesteTermino != Total);

        System.out.println("Tempo de Turnaround de cada processo: ");
        for (j = 1; j <= Total; j++){

            System.out.print(processo[j].getNomeDado() + " : " + processo[j].getTurnaround() + ",");
            System.out.print("  ");

            TempoRetorno = TempoRetorno + processo[j].getTurnaround();
        }

        System.out.print("\n");

        System.out.println("Tempo de Espera de cada processo: ");
        for (j = 1; j <= Total; j++){
            auxTPE = processo[j].getTurnaround() - processo[j].getDuracao();
            TempoMedioEspera =  TempoMedioEspera + auxTPE;
            processo[j].setTPE(auxTPE);

            System.out.print(processo[j].getNomeDado() + " : " + processo[j].getTPE() + ",");
            System.out.print("  ");

        }
        System.out.print("\n");
        System.out.print("Tempo medio de espera: ");
        TempoMedioEsperaCalculo = TempoMedioEspera / (float)Total;
        System.out.print(TempoMedioEsperaCalculo);
        System.out.print("\n");

        System.out.print("Tempo de retorno: ");
        System.out.print(TempoRetorno);
        System.out.print("\n");

        System.out.print("Tempo de processamento total: ");
        System.out.println(TPT);



        }
    }

