/**
 * 
 */
package nucleo;

import java.util.ArrayList;

/**
 * @author frazz
 *
 */
public class Giocatore {
	
	public static final String INSERIMENTO_NICKNAME = "INSERISCI UN NICKNAME PER IL GIOCATORE: ";
	public static final String INSERIMENTO_POSIZIONE_ARMATA = "INSERISCI UNA POSIZIONE PER LA TUA ARMATA TRA QUELLE DISPONIBILI: ";
	public static final String INSERIMENTO_POSIZIONE_ARMATA_ERRATA = "LA POSIZIONE CHE HAI SCLETO NON E' AMMESSA: ";
	public static final String INSERIMENTO_NOME_TERRITORIO = "INSERISCI IL NOME DEL TERRITORIO (max 3 caratteri alfabetici): ";
	public static final String INSERIMENTO_NOME_TERRITORIO_SBAGLIATO = "NOME DEL TERRITORIO NON AMMESSO!";
	public static final String INSERIMENTO_TIPO_TERRITORIO = "INSERISCI IL TIPO DI TERRITORIO ( mare / terra): ";
	public static final String INSERIMENTO_TIPO_TERRITORIO_ERRATO = "FORMATO TIPO TERRITORIO ERRATO!: ";
	public static final String QUANTI_TERRITORI = "QUANTI TERRITORI DESIDERI CREARE? INSERIRE IL NUMERO POSITIVO:  ";
	public static final String CREAZIONE_TERRITORI_TERMINATA = "CREAZIONE TERRITORI TERMINATA ! ";
	public static final String CREAZIONE_ARMATE_TERMINATA = "CREAZIONE ARMATE TERMINATA ! ";
	public static final String TERRITORI_DISPONIBILI = "TERRITORI DISPONIBILI PER POSIZIONARE LA TUA ARMATA: \n ";
	public static final String CREAZIONE_TERRITORIO_TERMINATA = "CREAZIONE TERRITORIO %d TERMINATA ";
	
	private String nickName;
	private ArrayList<Armata> armate;
	
	public Giocatore(String nickName, Armata armata) {
		this.armate = new ArrayList<Armata>();
		this.nickName = nickName;
		armate.add(armata);
	}
	
	public void addArmataNuova(Mappa mappa) {
		System.out.println(TERRITORI_DISPONIBILI);
		ArrayList<Territorio> territoriDsiponibili = mappa.getTerritoriDisponibili();
		ArrayList<String> nomiTerritoriDisponibili = new ArrayList<>();
		for(int i = 0; i < territoriDsiponibili.size(); i++) {
			nomiTerritoriDisponibili.add(territoriDsiponibili.get(i).getNome());
			System.out.println(territoriDsiponibili.get(i).getNome());
		}
		boolean posizioneValida = false;
		String posizione = "";
		while(!posizioneValida) {
			posizione = InputDati.leggiStringaNonVuota(INSERIMENTO_POSIZIONE_ARMATA);
			if(nomiTerritoriDisponibili.contains(posizione))
				posizioneValida = true;
			else
				System.err.println(INSERIMENTO_POSIZIONE_ARMATA_ERRATA);
		}
		Armata armata = new Armata(posizione);
		armate.add(armata);
		mappa.aggiornaTerritorio(nickName, posizione, armata);
		System.out.println(CREAZIONE_ARMATE_TERMINATA);
	}
	
	public Armata getArmata(String posizione) {
		for(int i = 0; i < armate.size(); i++) {
			if(armate.get(i).getPosizione().equalsIgnoreCase(posizione))
				return armate.get(i);
		}
		return null;
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public ArrayList<Armata> getArmate() {
		return armate;
	}

	public void setArmate(ArrayList<Armata> armate) {
		this.armate = armate;
	}

	@Override
	public String toString() {
		return "Giocatore [nickName=" + nickName + ", armate=" + armate + "]";
	}
	
	
	
}
