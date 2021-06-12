/**
 * 
 */
package nucleo;

import java.util.ArrayList;

/**
 * @author frazz
 *
 */
public class Partita {
	
	public final static String SCELTA_MOSSA = "1) UTILIZZA UNA NUOVA ARMATA \n 2) FAI DELLE MOSSE \n 3)TERMINA PARTITA \n INSERISCI LA TUA SCELTA: ";
	public final static String INIZIO_TURNO = "---INIZIO NUOVO TURNO---";
	public final static String RICHIEDI_MOSSA = "INSERISCI UNA MOSSA - oppure digita exit per terminare il turno : ";
	public final static String TERMINAZIONE_MOSSE = "PER CONFERMARE LE MOSSE SCELTE DIGITARE EXIT";
	public final static String PARTITA_CONCLUSA = "-------PARTITA TERMINATA-------";
	public final static String MOSSA_SUPPORTO = "INSERISCI UNA MOSSA DI SUPPORTO";
	
	
	
	private Giocatore giocatore;
	private Mappa mappa;
	
	
	public Partita() {
		nuovaPartita();
	}
	/**
	 * creazione di una nuova partita
	 */
	public void nuovaPartita() {
		mappa = InputOutput.creaMappa();
		giocatore = InputOutput.creaGiocatore(mappa);
		System.out.println(mappa.toString());
		turnoSuccessivo();
	}
	/**
	 * metodo che gestisce il turno dell'utente
	 */
	public void turnoSuccessivo() {
		System.out.println(INIZIO_TURNO);
		boolean partitaFinita = false;
		while(!partitaFinita) {  // ciclo che memorizza le mosse che l'utente scrive da tastiera
			boolean mosseFinite = false;
			int sceltaMossa = InputDati.leggiInteroConMinimo(SCELTA_MOSSA, 1);
			if(sceltaMossa == 1) {
				giocatore.addArmataNuova(mappa);
			}
			else if(sceltaMossa == 2){
				System.out.println(TERMINAZIONE_MOSSE);
				ArrayList<Territorio> territoriMappa = mappa.getTerritori();
				ArrayList<String> mosseUtilizzate = new ArrayList<>();
				while(!mosseFinite) {
					String mossa = InputDati.leggiStringaNonVuota(RICHIEDI_MOSSA);
					if(mossa.equalsIgnoreCase("exit")) {
						mosseFinite = true;
					}
					else
						mosseUtilizzate.add(mossa);	
				}
				for(int j = 0; j < mosseUtilizzate.size();j++) {
					String mossa = mosseUtilizzate.get(j);
					int quantiSpazi = 0;
					for(int i = 0; i < mossa.length(); i++) {
						if(mossa.charAt(i) == ' ')
							quantiSpazi++;
					}
					String[] parts = mossa.split(" ");  //viene splittata la stringa contente spazi per ricavare le istruzioni della mossa
					String tipoArmata="",partenza= "",destinazione= "",partenzaSupporto="",partenzaOffensiva="",destinazioneOffensiva="",tipoArmataSupporto = "",tipoArmataDaSupportare="",carattereSupporto = "";
					boolean supporto = false;
					if(quantiSpazi == 1) {
						tipoArmata = parts[0];
						partenza = parts[1];
						destinazione = " ";
					}
					else if(quantiSpazi == 0) {
						tipoArmata = parts[0];
						partenza = " ";
						destinazione = " ";
					}
					else if(quantiSpazi == 2){
						tipoArmata = parts[0];
						partenza = parts[1];
						destinazione = parts[2];
					}
					else if(quantiSpazi == 3){
						tipoArmata = parts[0];
						partenza = parts[1];
						destinazione = parts[2];
					}
					else if(quantiSpazi == 4){
						tipoArmata = " ";
						partenza = " ";
						destinazione = " ";
					}
					else{
						if(parts[2].equalsIgnoreCase("S")) {
							supporto = true;
							tipoArmataSupporto = parts[0];
							partenzaSupporto = parts[1];
							tipoArmataDaSupportare = parts[3];	
							partenzaOffensiva = parts[4];
							destinazioneOffensiva = parts[5];
							carattereSupporto = parts[2];
						}
					}
					
					if(tipoArmata.equalsIgnoreCase("A")) {
						for(int i = 0; i < territoriMappa.size(); i++) {
							if(territoriMappa.get(i).getNome().equalsIgnoreCase(partenza) && territoriMappa.get(i).getSituazioneTerritorio() == 1) {
								Territorio territorioPartenza = territoriMappa.get(i);
								int indicePosizioneTerritorioPartenza = i;
								if(indicePosizioneTerritorioPartenza == 0) { // se l'indice del territorio di partenza è zero allora l'unico territorio adicente è quello che si trova nella posizione successiva
									if(territoriMappa.get(indicePosizioneTerritorioPartenza+1) != null) {
										if(territoriMappa.get(indicePosizioneTerritorioPartenza+1).getNome().equalsIgnoreCase(destinazione) && territoriMappa.get(indicePosizioneTerritorioPartenza+1).getSituazioneTerritorio() == 0 && territoriMappa.get(indicePosizioneTerritorioPartenza+1).getTipo().equalsIgnoreCase("terra")) {
											Territorio territorioDestinazione = territoriMappa.get(indicePosizioneTerritorioPartenza+1);
											Armata armataScelta = giocatore.getArmata(partenza);
											armataScelta.setPosizione(destinazione);
											territorioPartenza.setSituazioneTerritorio(0);
											territorioDestinazione.setSituazioneTerritorio(1);
											territorioDestinazione.setArmataProprietarioTerritorio(armataScelta); //probabile eccezione
											territorioDestinazione.setNomeProprietarioTerritorio(giocatore.getNickName());
											
										}
									}
								}
								else if(indicePosizioneTerritorioPartenza == territoriMappa.size()-1) { // se l'indice del territorio di partenza è l'ultimo allora l'unico territorio adicente è quello che si trova nella posizione precedente
									if(territoriMappa.get(indicePosizioneTerritorioPartenza-1).getNome().equalsIgnoreCase(destinazione) && territoriMappa.get(indicePosizioneTerritorioPartenza-1).getSituazioneTerritorio() == 0 && territoriMappa.get(indicePosizioneTerritorioPartenza-1).getTipo().equalsIgnoreCase("terra")) {
										Territorio territorioDestinazione = territoriMappa.get(indicePosizioneTerritorioPartenza-1);
										Armata armataScelta = giocatore.getArmata(partenza);
										armataScelta.setPosizione(destinazione);
										territorioPartenza.setSituazioneTerritorio(0);
										territorioDestinazione.setSituazioneTerritorio(1);
										territorioDestinazione.setArmataProprietarioTerritorio(armataScelta); //probabile eccezione
										territorioDestinazione.setNomeProprietarioTerritorio(giocatore.getNickName());
									}
								}
								else{ // se l'indice del territorio di partenza si trova in mezzo all'array allora i territori adicenti sono quelli che si trovano nella posizione successiva e precedente
									if(territoriMappa.get(indicePosizioneTerritorioPartenza-1).getNome().equalsIgnoreCase(destinazione) && territoriMappa.get(indicePosizioneTerritorioPartenza-1).getSituazioneTerritorio() == 0 && territoriMappa.get(indicePosizioneTerritorioPartenza-1).getTipo().equalsIgnoreCase("terra")) {
										Territorio territorioDestinazione = territoriMappa.get(indicePosizioneTerritorioPartenza-1);
										Armata armataScelta = giocatore.getArmata(partenza);
										armataScelta.setPosizione(destinazione);
										territorioPartenza.setSituazioneTerritorio(0);
										territorioDestinazione.setSituazioneTerritorio(1);
										territorioDestinazione.setArmataProprietarioTerritorio(armataScelta); //probabile eccezione
										territorioDestinazione.setNomeProprietarioTerritorio(giocatore.getNickName());
									}
									if(territoriMappa.get(indicePosizioneTerritorioPartenza+1).getNome().equalsIgnoreCase(destinazione) && territoriMappa.get(indicePosizioneTerritorioPartenza+1).getSituazioneTerritorio() == 0 && territoriMappa.get(indicePosizioneTerritorioPartenza+1).getTipo().equalsIgnoreCase("terra")) {
										Territorio territorioDestinazione = territoriMappa.get(indicePosizioneTerritorioPartenza+1);
										Armata armataScelta = giocatore.getArmata(partenza);
										armataScelta.setPosizione(destinazione);
										territorioPartenza.setSituazioneTerritorio(0);
										territorioDestinazione.setSituazioneTerritorio(1);
										territorioDestinazione.setArmataProprietarioTerritorio(armataScelta); //probabile eccezione
										territorioDestinazione.setNomeProprietarioTerritorio(giocatore.getNickName());
									}
								}
							}
						}
						
					}
					if(supporto) {  // se si è verificata un'azione di supporto
						if(tipoArmataSupporto.equalsIgnoreCase("A") && tipoArmataDaSupportare.equalsIgnoreCase("A") && carattereSupporto.equalsIgnoreCase("S") && destinazioneOffensiva.equalsIgnoreCase("H")) { // si vuole fare un supporto all'hold adiacente
							for(int i = 0; i < territoriMappa.size(); i++) {
								if(territoriMappa.get(i).getNome().equalsIgnoreCase(partenzaSupporto) && territoriMappa.get(i).getSituazioneTerritorio() == 1) {
									Territorio territorioPartenza = territoriMappa.get(i);
									int indicePosizioneTerritorioPartenza = i;
									if(indicePosizioneTerritorioPartenza == 0) {
										if(territoriMappa.get(indicePosizioneTerritorioPartenza+1) != null) {
											if(territoriMappa.get(indicePosizioneTerritorioPartenza+1).getNome().equalsIgnoreCase(partenzaOffensiva) ) {
												Territorio territorioDestinazioneSupporto = territoriMappa.get(indicePosizioneTerritorioPartenza+1);
												Armata armataScelta = giocatore.getArmata(partenzaOffensiva);
												armataScelta.setForzaArmata(armataScelta.getForzaArmata()+1);
												territorioDestinazioneSupporto.setArmataProprietarioTerritorio(armataScelta);
												break;
											}
										}
									}
									
								}
							}
						}
						else {  // se si vuole supportare un attacco di un'armata verso un territorio adiacente all'attacco dell'armata
							for(int i = 0; i < territoriMappa.size(); i++) {
								if(territoriMappa.get(i).getNome().equalsIgnoreCase(partenzaOffensiva) && territoriMappa.get(i).getSituazioneTerritorio() == 1) {
									Territorio territorioPartenza = territoriMappa.get(i);
									int indicePosizioneTerritorioPartenza = i;
									if(indicePosizioneTerritorioPartenza == 0) {  //stessi controlli di prima - sarebbe stato meglio gestire questi casi separatamente con metodi appositi per non riutilizzare codice già scritto
										if(territoriMappa.get(indicePosizioneTerritorioPartenza+1) != null) {
											if(territoriMappa.get(indicePosizioneTerritorioPartenza+1).getNome().equalsIgnoreCase(destinazioneOffensiva) && territoriMappa.get(indicePosizioneTerritorioPartenza+1).getSituazioneTerritorio() == 0 && territoriMappa.get(indicePosizioneTerritorioPartenza+1).getTipo().equalsIgnoreCase("terra")) {
												Territorio territorioDestinazione = territoriMappa.get(indicePosizioneTerritorioPartenza+1);
												Armata armataScelta = giocatore.getArmata(partenzaOffensiva);
												armataScelta.setForzaArmata(armataScelta.getForzaArmata()+1);
												armataScelta.setPosizione(destinazioneOffensiva);
												territorioPartenza.setSituazioneTerritorio(0);
												territorioDestinazione.setSituazioneTerritorio(1);
												territorioDestinazione.setArmataProprietarioTerritorio(armataScelta); //probabile eccezione
												territorioDestinazione.setNomeProprietarioTerritorio(giocatore.getNickName());
												
											}
										}
									}
									else if(indicePosizioneTerritorioPartenza == territoriMappa.size()-1) {
										if(territoriMappa.get(indicePosizioneTerritorioPartenza-1).getNome().equalsIgnoreCase(destinazioneOffensiva) && territoriMappa.get(indicePosizioneTerritorioPartenza-1).getSituazioneTerritorio() == 0 && territoriMappa.get(indicePosizioneTerritorioPartenza-1).getTipo().equalsIgnoreCase("terra")) {
											Territorio territorioDestinazione = territoriMappa.get(indicePosizioneTerritorioPartenza-1);
											Armata armataScelta = giocatore.getArmata(partenzaOffensiva);
											armataScelta.setForzaArmata(armataScelta.getForzaArmata()+1);
											armataScelta.setPosizione(destinazioneOffensiva);
											territorioPartenza.setSituazioneTerritorio(0);
											territorioDestinazione.setSituazioneTerritorio(1);
											territorioDestinazione.setArmataProprietarioTerritorio(armataScelta); //probabile eccezione
											territorioDestinazione.setNomeProprietarioTerritorio(giocatore.getNickName());
										}
									}
									else{
										if(territoriMappa.get(indicePosizioneTerritorioPartenza-1).getNome().equalsIgnoreCase(destinazioneOffensiva) && territoriMappa.get(indicePosizioneTerritorioPartenza-1).getSituazioneTerritorio() == 0 && territoriMappa.get(indicePosizioneTerritorioPartenza-1).getTipo().equalsIgnoreCase("terra")) {
											Territorio territorioDestinazione = territoriMappa.get(indicePosizioneTerritorioPartenza-1);
											Armata armataScelta = giocatore.getArmata(partenzaOffensiva);
											armataScelta.setForzaArmata(armataScelta.getForzaArmata()+1);
											armataScelta.setPosizione(destinazioneOffensiva);
											territorioPartenza.setSituazioneTerritorio(0);
											territorioDestinazione.setSituazioneTerritorio(1);
											territorioDestinazione.setArmataProprietarioTerritorio(armataScelta); //probabile eccezione
											territorioDestinazione.setNomeProprietarioTerritorio(giocatore.getNickName());
										}
										if(territoriMappa.get(indicePosizioneTerritorioPartenza+1).getNome().equalsIgnoreCase(destinazioneOffensiva) && territoriMappa.get(indicePosizioneTerritorioPartenza+1).getSituazioneTerritorio() == 0 && territoriMappa.get(indicePosizioneTerritorioPartenza+1).getTipo().equalsIgnoreCase("terra")) {
											Territorio territorioDestinazione = territoriMappa.get(indicePosizioneTerritorioPartenza+1);
											Armata armataScelta = giocatore.getArmata(partenzaOffensiva);
											armataScelta.setPosizione(destinazioneOffensiva);
											armataScelta.setForzaArmata(armataScelta.getForzaArmata()+1);
											territorioPartenza.setSituazioneTerritorio(0);
											territorioDestinazione.setSituazioneTerritorio(1);
											territorioDestinazione.setArmataProprietarioTerritorio(armataScelta); //probabile eccezione
											territorioDestinazione.setNomeProprietarioTerritorio(giocatore.getNickName());
										}
									}
								}
							}
						}
					}
				}
					System.out.println(mappa);
			}
				
			else {
				partitaFinita = true;
			}
		}
		System.out.println(PARTITA_CONCLUSA);
	}
}
