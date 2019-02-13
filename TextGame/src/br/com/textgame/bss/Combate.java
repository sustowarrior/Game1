package br.com.textgame.bss;

import java.util.Random;

import br.com.textgame.vo.Jogador;
import br.com.textgame.vo.Monstro;

public class Combate {

	public static String jogadorAtaca(Monstro monstro, Jogador jogador) {
		
		int forca = 50 + (100 + (jogador.getNivel() * 2)/3);
		int dano = getDano(forca);
		monstro.setVida(monstro.getVida() - dano);
		return "Voce atacou causando um dano total de " + dano;
	}

	private static int getDano(int forca) {
		
		Random randomGenerator = new Random();
	    int dano = randomGenerator.nextInt(forca);
		return dano;
	}

	public static String monstroAtaca(Jogador jogador, Monstro m) {
		
		int forca = 25 + (((m.getXp() * 4) / 3)) + (jogador.getNivel() * 6);
		int dano = getDano(forca);
		jogador.setVida(jogador.getVida() - dano);
		return "Voce foi atacado recebendo um dano total de " + dano;
	}

	public static Monstro carregaInimigo(int enemyid, int nivel) {
		
		Monstro m = new Monstro();
		int calc = ( 50 + nivel/2);
		if(enemyid == 1){
			m.setNome("Goblin");
			m.setVida(100 + calc);
			m.setXp(25);
		}
		
		if(enemyid == 2){
			m.setNome("Troll");
			m.setVida(200 + calc);
			m.setXp(70);
		}
		
		if(enemyid == 3){
			m.setNome("Lich");
			m.setVida(500 + calc);
			m.setXp(100);
		}
		return m;
	}

	public static void gainExp(Jogador jogador, Monstro inimigo) {
		
		int expAtual = jogador.getProgresso();
		int expMonstro = inimigo.getXp();
		int result = expAtual + expMonstro;
		int proximoNivel = jogador.getNivel() * 100;
		
		if(result >= proximoNivel)
			jogador.setNivel(jogador.getNivel() + 1);
		
		jogador.setProgresso(result);
	}
}
