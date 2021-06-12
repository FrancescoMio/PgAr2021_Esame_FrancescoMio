/**
 * 
 */
package nucleo;

import java.util.ArrayList;

/**
 * @author frazz
 *
 */
public class InputOutput {
	
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
	public static final String PER_FORZA_TERRA = "DEVI INSERIRE COME TIPO PER FORZA TERRA! ";
	

	public static Mappa creaMappa() {
		Mappa mappa = new Mappa(creaTerritori());
		return mappa;
	}
	
	public static ArrayList<Territorio> creaTerritori(){
		int numeroTerritoriDaCreare = InputDati.leggiInteroConMinimo(QUANTI_TERRITORI, 2);
		ArrayList<Territorio> territori = new  ArrayList<>();
		boolean almenoUnoDiTerra = false;
		for(int i = 0; i < numeroTerritoriDaCreare; i++) {
			boolean lunghezzaCodiceValida = false;
			String nomeTerritorio = "";
			while(!lunghezzaCodiceValida) {
				nomeTerritorio = InputDati.leggiStringaNonVuota(INSERIMENTO_NOME_TERRITORIO);
				if(nomeTerritorio.length() == 3)
					lunghezzaCodiceValida = true;
				else
					System.err.println(INSERIMENTO_NOME_TERRITORIO_SBAGLIATO);
			}
			boolean tipoValido = false;
			String tipoTerritorio = "";
			while(!tipoValido) {
				if(i == numeroTerritoriDaCreare-1 && almenoUnoDiTerra == false) {
					while(!almenoUnoDiTerra) {
						tipoTerritorio = InputDati.leggiStringaNonVuota(INSERIMENTO_TIPO_TERRITORIO);
						if(tipoTerritorio.equalsIgnoreCase("terra")) {
							almenoUnoDiTerra = true;
							tipoValido = true;
						}
						else System.err.println(PER_FORZA_TERRA);
							
					}
				}
				else {
					
					tipoTerritorio = InputDati.leggiStringaNonVuota(INSERIMENTO_TIPO_TERRITORIO);
					if(tipoTerritorio.equalsIgnoreCase("mare") || tipoTerritorio.equalsIgnoreCase("terra")) {
						if(tipoTerritorio.equalsIgnoreCase("terra"))
							almenoUnoDiTerra = true;
						tipoValido = true;
					}
					else
						System.err.println(INSERIMENTO_TIPO_TERRITORIO_ERRATO);
				}
			}
			Territorio territorio = new Territorio(nomeTerritorio, tipoTerritorio, 0); //ogni territorio alla sua creazione avrà 0 armate
			territori.add(territorio);
			System.out.println(String.format(CREAZIONE_TERRITORIO_TERMINATA, i+1));
		}
		System.out.println(CREAZIONE_TERRITORI_TERMINATA);
		return territori;
	}
	
	public static Giocatore creaGiocatore(Mappa mappa) {
		String nickName = InputDati.leggiStringaNonVuota(INSERIMENTO_NICKNAME);
		Armata armata = creaArmata(mappa,nickName);
		Giocatore giocatoreCasa = new Giocatore(nickName,armata);
		return giocatoreCasa;
	}
	
	public static Armata creaArmata(Mappa mappa, String nickName) {
		//Character codice = InputDati.leggiChar(INSERIMENTO_NICKNAME);
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
		mappa.aggiornaTerritorio(nickName, posizione, armata);
		System.out.println(CREAZIONE_ARMATE_TERMINATA);
		return armata;
	}
}
