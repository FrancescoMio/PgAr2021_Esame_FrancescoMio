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

	private ArrayList<Territorio> territori;

	public Mappa(ArrayList<Territorio> territori) {
		this.territori = territori;
	}


	public ArrayList<Territorio> getTerritori() {
		return territori;
	}

	public void setTerritori(ArrayList<Territorio> territori) {
		this.territori = territori;
	}
	
	public ArrayList<Territorio> getTerritoriDisponibili() {
		ArrayList<Territorio> territoriDisponibili = new ArrayList<>();
		for(int i = 0; i < territori.size(); i++) {
			if(territori.get(i).getSituazioneTerritorio() == 0)
				territoriDisponibili.add(territori.get(i));
		}
		return territoriDisponibili;
	}
	
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
			string += "Nome: "+territori.get(i).getNome() + "\n";
			string += "    ";
			string += "Tipo: "+territori.get(i).getTipo() + "\n";
			string += "    ";
			string += "Armate presenti: "+territori.get(i).getSituazioneTerritorio() + "\n";
			string += "\n\n";
		}
		return string;
	}
	
}
