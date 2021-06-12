/**
 * 
 */
package nucleo;

/**
 * @author frazz
 *
 */
public class Armata {

	private Character codice;
	private String posizione;
	private int forzaArmata;
	
	/**
	 * viene creata un'armata
	 * @param posizione
	 */
	public Armata(String posizione) {
		this.codice = 'A';
		this.posizione = posizione;
		this.forzaArmata = 1;
	}
	/**
	 * ritorna la forza di un 'armata
	 * @return
	 */
	public int getForzaArmata() {
		return forzaArmata;
	}
/**
 * setta la forza armata
 * @param forzaArmata
 */
	public void setForzaArmata(int forzaArmata) {
		this.forzaArmata = forzaArmata;
	}

	public Character getCodice() {
		return codice;
	}

	public void setCodice(Character codice) {
		this.codice = codice;
	}
/**
 * ritorna la posizione
 * @return
 */
	public String getPosizione() {
		return posizione;
	}
/**
 * setta la posizione
 * @param posizione
 */
	public void setPosizione(String posizione) {
		this.posizione = posizione;
	}

	@Override
	public String toString() {
		return "Armata [codice=" + codice + ", posizione=" + posizione + "]";
	}
	
	
	
}
