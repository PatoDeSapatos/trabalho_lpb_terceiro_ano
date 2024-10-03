package model;

public class Passagem {

    private int id;
    private String origem;
    private String destino;
    private String data;
    private double valor;
    private String horario;

    public Passagem(int id, String origem, String destino, String data, double valor, String horario) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.valor = valor;
        this.horario = horario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }


    
}