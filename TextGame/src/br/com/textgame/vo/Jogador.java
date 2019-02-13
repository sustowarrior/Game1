package br.com.textgame.vo;

public class Jogador {
	private Integer vida;
	private Integer nivel;
	private Integer progresso;

	public Jogador(Integer vida, Integer nivel, Integer progresso) {
		this.vida = vida;
		this.nivel = nivel;
		this.progresso = progresso;
	}

	public Integer getVida() {
		return vida;
	}

	public void setVida(Integer vida) {
		this.vida = vida;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Integer getProgresso() {
		return progresso;
	}

	public void setProgresso(Integer progresso) {
		this.progresso = progresso;
	}
}
