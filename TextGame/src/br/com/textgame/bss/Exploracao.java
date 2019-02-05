package br.com.textgame.bss;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.textgame.vo.Area;
import br.com.textgame.vo.AreaContent;

public class Exploracao {

	public static List<Area> getListArea() {
		
		ArrayList<Area> areas = new ArrayList<Area>();
		areas.add(new Area("Floresta Esmeralda", "Uma floresta verde, linda e cheia de vida."));
		return areas;
	}

	public static AreaContent carregarTextExploracao(Area area) {
		
		ArrayList<AreaContent> lista = new ArrayList<AreaContent>();
		lista.add(new AreaContent(area.getNome()+"\nVocê está explorando a area...\nVoce encontrou um goblin. O que deseja fazer?", 1));
		
		lista.add(new AreaContent(area.getNome()+"\nVocê está explorando a area...\n"
				+ "Voce encontrou uma cabana abandona. Ao adentrar, você não acha nada.\nO que deseja fazer?", 0));
		
		
		Random randomGenerator = new Random();

	    int index = randomGenerator.nextInt(lista.size());
	    AreaContent item = lista.get(index);
	    return item;
	}
}
