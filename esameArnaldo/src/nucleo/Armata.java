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
	
	public Armata(String posizione) {
		this.codice = 'A';
		this.posizione = posizione;
	}
	
	public Character getCodice() {
		return codice;
	}

	public void setCodice(Character codice) {
		this.codice = codice;
	}

	public String getPosizione() {
		return posizione;
	}

	public void setPosizione(String posizione) {
		this.posizione = posizione;
	}

	@Override
	public String toString() {
		return "Armata [codice=" + codice + ", posizione=" + posizione + "]";
	}
	
	
	
}
