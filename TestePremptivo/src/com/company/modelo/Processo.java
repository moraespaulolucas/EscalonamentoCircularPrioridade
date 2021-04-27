package com.company.modelo;

public class Processo implements Comparable<Processo>{
    private String nome;
    private int tempoRestante;
    private int prioridade;
    private  int turnaround;
    private int tempoExecutado;
    private int tempoEspera;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempoRestante() {
        return tempoRestante;
    }

    public void setTempoRestante(int tempoDeExecucao) {
        this.tempoRestante = tempoDeExecucao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getTurnaround() {
        return turnaround;
    }

    public void setTurnaround(int turnaround) {
        this.turnaround = turnaround;
    }

    public int getTempoExecutado() {
        return tempoExecutado;
    }

    public int getTempoEspera() {
        return tempoEspera;
    }

    public void setTempoEspera(int tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public void setTempoExecutado(int duracao) {
        tempoExecutado = duracao;
    }

    @Override
    public int compareTo(Processo outroProcesso) {
        if (this.prioridade > outroProcesso.getPrioridade()) {
            return -1;
        } if (this.prioridade < outroProcesso.getPrioridade()) {
            return 1;
        }
        return 0;
    }
}
