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
	
	public final static String SCELTA_MOSSA = "1) UTILIZZA UNA NUOVA ARMATA \n 2) SPOSTA UN'ARMATA GIA' PRESENTE SUL CAMPO \n 3)TERMINA PARTITA \n INSERISCI LA TUA SCELTA: ";
	public final static String INIZIO_TURNO = "---INIZIO NUOVO TURNO---";
	public final static String RICHIEDI_MOSSA = "INSERISCI UNA MOSSA NEL FORMATO: A <Territorio di partenza> <Territorio di destinazione> - oppure digita exit per terminare il turno : ";
	public final static String TERMINAZIONE_MOSSE = "PER CONFERMARE LE MOSSE SCELTE DIGITARE EXIT";
	public final static String PARTITA_CONCLUSA = "------PARTITA TERMINATA-------";
	
	
	
	private Giocatore giocatore;
	private Mappa mappa;
	
	public Partita() {
		nuovaPartita();
	}
	
	public void nuovaPartita() {
		mappa = InputOutput.creaMappa();
		giocatore = InputOutput.creaGiocatore(mappa);
		System.out.println(mappa.toString());
		turnoSuccessivo();
	}
	
	public void turnoSuccessivo() {
		System.out.println(INIZIO_TURNO);
		boolean partitaFinita = false;
		while(!partitaFinita) {
			boolean mosseFinite = false;
			int sceltaMossa = InputDati.leggiInteroConMinimo(SCELTA_MOSSA, 1);
			if(sceltaMossa == 1) {
				giocatore.addArmataNuova(mappa);
			}
			else if(sceltaMossa == 2){
				System.out.println(TERMINAZIONE_MOSSE);
				ArrayList<Territorio> territoriMappa = mappa.getTerritori();
				while(!mosseFinite) {
					boolean mossaCorretta = false;
					String mossa = InputDati.leggiStringaNonVuota(RICHIEDI_MOSSA);
					if(mossa.equalsIgnoreCase("exit")) {
						mosseFinite = true;
					}
					else {
						int quantiSpazi = 0;
						for(int i = 0; i < mossa.length(); i++) {
							if(mossa.charAt(i) == ' ')
								quantiSpazi++;
						}
						String[] parts = mossa.split(" ");
						String tipoArmata="",partenza= "",destinazione= "";
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
						else {
							tipoArmata = parts[0];
							partenza = parts[1];
							destinazione = parts[2];
						}
						
						/*String tipoArmata = parts[0];
						String partenza = parts[1];
						String destinazione = parts[2];*/
						
						if(tipoArmata.equalsIgnoreCase("A")) {
							for(int i = 0; i < territoriMappa.size(); i++) {
								if(territoriMappa.get(i).getNome().equalsIgnoreCase(partenza) && territoriMappa.get(i).getSituazioneTerritorio() == 1) {
									Territorio territorioPartenza = territoriMappa.get(i);
									int indicePosizioneTerritorioPartenza = i;
									if(indicePosizioneTerritorioPartenza == 0) {
										if(territoriMappa.get(indicePosizioneTerritorioPartenza+1) != null) {
											if(territoriMappa.get(indicePosizioneTerritorioPartenza+1).getNome().equalsIgnoreCase(destinazione) && territoriMappa.get(indicePosizioneTerritorioPartenza+1).getSituazioneTerritorio() == 0 && territoriMappa.get(indicePosizioneTerritorioPartenza+1).getTipo().equalsIgnoreCase("terra")) {
												Territorio territorioDestinazione = territoriMappa.get(indicePosizioneTerritorioPartenza+1);
												Armata armataScelta = giocatore.getArmata(partenza);
												armataScelta.setPosizione(destinazione);
												territorioPartenza.setSituazioneTerritorio(0);
												territorioDestinazione.setSituazioneTerritorio(1);
												territorioDestinazione.setArmataProprietarioTerritorio(armataScelta); //probabile eccezione
												territorioDestinazione.setNomeProprietarioTerritorio(giocatore.getNickName());
												mossaCorretta = true;
												System.out.println("giusto");
											}
										}
									}
									else if(indicePosizioneTerritorioPartenza == territoriMappa.size()-1) {
										if(territoriMappa.get(indicePosizioneTerritorioPartenza-1).getNome().equalsIgnoreCase(destinazione) && territoriMappa.get(indicePosizioneTerritorioPartenza-1).getSituazioneTerritorio() == 0 && territoriMappa.get(indicePosizioneTerritorioPartenza-1).getTipo().equalsIgnoreCase("terra")) {
											Territorio territorioDestinazione = territoriMappa.get(indicePosizioneTerritorioPartenza-1);
											Armata armataScelta = giocatore.getArmata(partenza);
											armataScelta.setPosizione(destinazione);
											territorioPartenza.setSituazioneTerritorio(0);
											territorioDestinazione.setSituazioneTerritorio(1);
											territorioDestinazione.setArmataProprietarioTerritorio(armataScelta); //probabile eccezione
											territorioDestinazione.setNomeProprietarioTerritorio(giocatore.getNickName());
											mossaCorretta = true;
										}
									}
									else{
										if(territoriMappa.get(indicePosizioneTerritorioPartenza-1).getNome().equalsIgnoreCase(destinazione) && territoriMappa.get(indicePosizioneTerritorioPartenza-1).getSituazioneTerritorio() == 0 && territoriMappa.get(indicePosizioneTerritorioPartenza-1).getTipo().equalsIgnoreCase("terra")) {
											Territorio territorioDestinazione = territoriMappa.get(indicePosizioneTerritorioPartenza-1);
											Armata armataScelta = giocatore.getArmata(partenza);
											armataScelta.setPosizione(destinazione);
											territorioPartenza.setSituazioneTerritorio(0);
											territorioDestinazione.setSituazioneTerritorio(1);
											territorioDestinazione.setArmataProprietarioTerritorio(armataScelta); //probabile eccezione
											territorioDestinazione.setNomeProprietarioTerritorio(giocatore.getNickName());
											mossaCorretta = true;
										}
										if(territoriMappa.get(indicePosizioneTerritorioPartenza+1).getNome().equalsIgnoreCase(destinazione) && territoriMappa.get(indicePosizioneTerritorioPartenza+1).getSituazioneTerritorio() == 0 && territoriMappa.get(indicePosizioneTerritorioPartenza+1).getTipo().equalsIgnoreCase("terra")) {
											Territorio territorioDestinazione = territoriMappa.get(indicePosizioneTerritorioPartenza+1);
											Armata armataScelta = giocatore.getArmata(partenza);
											armataScelta.setPosizione(destinazione);
											territorioPartenza.setSituazioneTerritorio(0);
											territorioDestinazione.setSituazioneTerritorio(1);
											territorioDestinazione.setArmataProprietarioTerritorio(armataScelta); //probabile eccezione
											territorioDestinazione.setNomeProprietarioTerritorio(giocatore.getNickName());
											mossaCorretta = true;
										}
									}
								}
							}
							
						}
					}
					System.out.println(mappa);
				
			}
				
		}
			else {
				partitaFinita = true;
			}
	}
		System.out.println(PARTITA_CONCLUSA);
	
}
}
