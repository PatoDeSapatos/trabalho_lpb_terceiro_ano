package model;

public class Pesquisa {
    private String origem;
    private String destino;
    private String dataIda;
    private String dataVolta;
    private int nPessoas;

    public Pesquisa(String origem, String destino, String dataIda, String dataVolta, int nPessoas) {
        this.origem = origem;
        this.dataIda = dataIda;
        this.dataVolta = dataVolta;
        this.nPessoas = nPessoas;
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
    public String getDataIda() {
        return dataIda;
    }
    public void setDataIda(String dataIda) {
        this.dataIda = dataIda;
    }
    public String getDataVolta() {
        return dataVolta;
    }
    public void setDataVolta(String dataVolta) {
        this.dataVolta = dataVolta;
    }
    public int getnPessoas() {
        return nPessoas;
    }
    public void setnPessoas(int nPessoas) {
        this.nPessoas = nPessoas;
    }

	@Override
	public String toString() {
		return "origem=" + origem + "&destino=" + destino + "&dataIda=" + dataIda + "&dataVolta=" + dataVolta + "&nPessoas=" + nPessoas;
	}
}
