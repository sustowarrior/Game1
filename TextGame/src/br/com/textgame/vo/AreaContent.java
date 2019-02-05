package br.com.textgame.vo;

public class AreaContent {
	
	private String descExploracao;
	private int enemyid;
	
	public AreaContent(String descExploracao, int enemyid) {
		this.descExploracao = descExploracao;
		this.enemyid = enemyid;
	}
	public String getDescExploracao() {
		return descExploracao;
	}
	public void setDescExploracao(String descExploracao) {
		this.descExploracao = descExploracao;
	}
	public int getEnemyid() {
		return enemyid;
	}
	public void setEnemyid(int enemyid) {
		this.enemyid = enemyid;
	}
	
}
