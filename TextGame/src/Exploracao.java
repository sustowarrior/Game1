import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Exploracao {

	public static List<Area> getListArea() {
		
		ArrayList<Area> areas = new ArrayList<Area>();
		areas.add(new Area("Floresta Esmeralda", "Uma floresta verde, linda e cheia de vida."));
		return areas;
	}

	public static AreaTexto carregarTextExploracao(Area area) {
		
		ArrayList<AreaTexto> lista = new ArrayList<AreaTexto>();
		lista.add(new AreaTexto(""));
		
		Random randomGenerator = new Random();

	    int index = randomGenerator.nextInt(lista.size());
	    AreaTexto item = lista.get(index);
	    return item;
	}
}
