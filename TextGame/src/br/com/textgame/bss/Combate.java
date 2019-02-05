package br.com.textgame.bss;

import java.util.Random;

import br.com.textgame.vo.Jogador;
import br.com.textgame.vo.Monstro;

public class Combate {

	public static String jogadorAtaca(Monstro monstro) {
		
		int forca = 50;
		int dano = getDano(forca);
		monstro.setVida(monstro.getVida() - dano);
		return "Voce atacou causando um dano total de " + dano;
	}

	private static int getDano(int forca) {
		
		Random randomGenerator = new Random();
	    int dano = randomGenerator.nextInt(forca);
		return dano;
	}

	public static String monstroAtaca(Jogador jogador) {
		
		int forca = 25;
		int dano = getDano(forca);
		jogador.setVida(jogador.getVida() - dano);
		return "Voce foi atacado recebendo um dano total de " + dano;
	}

}
