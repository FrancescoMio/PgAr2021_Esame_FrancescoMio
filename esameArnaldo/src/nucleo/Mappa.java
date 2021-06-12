/**
 * 
 */
package nucleo;

import java.util.ArrayList;

/**
 * @author frazz
 *
 */
public class Mappa {

	private ArrayList<Territorio> territori;  //i territori sono considerati adicenti ripsetto agli indici che hanno nell'arrayLIst

	public Mappa(ArrayList<Territorio> territori) {
		this.territori = territori;
	}

/**
 * ritorna la lista di territori
 * @return
 */
	public ArrayList<Territorio> getTerritori() {
		return territori;
	}
/**
 * setta i territori
 * @param territori
 */
	public void setTerritori(ArrayList<Territorio> territori) {
		this.territori = territori;
	}
	
	/**
	 * ritorna tutti i territori disponibili ovvero quelli non occupati da un'armata
	 * @return
	 */
	public ArrayList<Territorio> getTerritoriDisponibili() {
		ArrayList<Territorio> territoriDisponibili = new ArrayList<>();
		for(int i = 0; i < territori.size(); i++) {
			if(territori.get(i).getSituazioneTerritorio() == 0)
				territoriDisponibili.add(territori.get(i));
		}
		return territoriDisponibili;
	}
	
	/**
	 * viene aggiornato un determinato territorio in base all'input dell'utente
	 * @param proprietarioTerritorio
	 * @param posizione
	 * @param armataProprietarioTerritorio
	 */
	public void aggiornaTerritorio(String proprietarioTerritorio,String posizione,Armata armataProprietarioTerritorio) {
		for(int i = 0; i < territori.size(); i++) {
			if(territori.get(i).getNome().equalsIgnoreCase(posizione)) {
				territori.get(i).setNomeProprietarioTerritorio(proprietarioTerritorio);
				territori.get(i).setSituazioneTerritorio(1); // 1 rappresenta l'armata che si trova sul territorio
				territori.get(i).setArmataProprietarioTerritorio(armataProprietarioTerritorio);
				break;
			}
		}
	}

	@Override
	public String toString() {
		String string = "-----MAPPA----- \n\n";
		for(int i = 0; i < territori.size(); i++) {
			string += "    ";
			string += "Nome territorio: "+territori.get(i).getNome() + "\n";
			string += "    ";
			string += "Nome propritario territorio: "+territori.get(i).getNomeProprietarioTerritorio() + "\n";
			string += "    ";
			string += "Tipo: "+territori.get(i).getTipo() + "\n";
			string += "    ";
			string += "Armate presenti: "+territori.get(i).getSituazioneTerritorio() + "\n";
			string += "    ";
			string += "Forza armate: "+territori.get(i).getForzaArmata() + "\n";
			string += "\n\n";
		}
		return string;
	}
	
}
