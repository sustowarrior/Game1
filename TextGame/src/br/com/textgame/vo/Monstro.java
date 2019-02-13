package br.com.textgame.vo;

public class Monstro {
	private Integer vida;
	private Integer xp;
	private String nome;

	public Monstro(Integer vida, Integer xp, String nome) {
		this.vida = vida;
		this.xp = xp;
		this.nome = nome;
	}

	public Monstro() {
	}

	public Integer getVida() {
		return vida;
	}

	public void setVida(Integer vida) {
		this.vida = vida;
	}

	public Integer getXp() {
		return xp;
	}

	public void setXp(Integer xp) {
		this.xp = xp;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
