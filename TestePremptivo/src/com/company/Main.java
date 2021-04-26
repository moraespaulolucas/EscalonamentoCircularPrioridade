package com.company;

import com.company.modelo.Escalonamento;

public class Main {

    public static void main(String[] args) {
        Escalonamento escalonamento = new Escalonamento();
        escalonamento.escolherAlgoritmo();
        escalonamento.inserirQuantum();
        escalonamento.menuInserirMostrar();
        escalonamento.mostrarResultado();
    }
}
