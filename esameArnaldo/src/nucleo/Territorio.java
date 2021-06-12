/**
 * 
 */
package nucleo;

/**
 * @author frazz
 *
 */
public class Territorio {
	
	private String nomeProprietarioTerritorio;
	private String nome;
	private String tipo;
	private int situazioneTerritorio;
	private Armata armataProprietarioTerritorio;
	private int forzaArmata;
	
	/**
	 * creazione del territorio
	 * @param nome
	 * @param tipo
	 * @param situazioneTerritorio
	 */
	public Territorio(String nome, String tipo, int situazioneTerritorio) {
		this.nome = nome;
		this.tipo = tipo;
		this.situazioneTerritorio = situazioneTerritorio;
		this.forzaArmata = 1;
	}
	
	/**
	 * ritorna la forza di un'armata
	 * @return
	 */
	public int getForzaArmata() {
		if(armataProprietarioTerritorio != null)
			return armataProprietarioTerritorio.getForzaArmata();
		else
			return 0;
	}

	/**
	 * setta la forza di un'armata
	 * @param forzaArmata
	 */
	public void setForzaArmata(int forzaArmata) {
		this.forzaArmata = forzaArmata;
	}

	/**
	 * ritorna il nome del proprietario di quel territorio
	 * @return
	 */
	public String getNomeProprietarioTerritorio() {
		return nomeProprietarioTerritorio;
	}
	/**
	 * ritorna l'armata del proprietario del territorio
	 * @return
	 */
	public Armata getArmataProprietarioTerritorio() {
		return armataProprietarioTerritorio;
	}

	/**
	 * viene settata l'armata del proprietario del territorio
	 * @param armataProprietarioTerritorio
	 */
	public void setArmataProprietarioTerritorio(Armata armataProprietarioTerritorio) {
		this.armataProprietarioTerritorio = armataProprietarioTerritorio;
	}

/**
 * viene settato il nome del proprietario del territorio
 * @param nomeProprietarioTerritorio
 */
	public void setNomeProprietarioTerritorio(String nomeProprietarioTerritorio) {
		this.nomeProprietarioTerritorio = nomeProprietarioTerritorio;
	}
	/**
	 * ritorna il nome del teritorio
	 * @return
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * setta il nome del territorio
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * ritorna i ltipo di territorio
	 * @return
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * setta i ltipo di territorio
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getSituazioneTerritorio() {
		return situazioneTerritorio;
	}
	public void setSituazioneTerritorio(int situazioneTerritorio) {
		this.situazioneTerritorio = situazioneTerritorio;
	}


	@Override
	public String toString() {
		return "Territorio [nomeProprietarioTerritorio=" + nomeProprietarioTerritorio + ", nome=" + nome + ", tipo="
				+ tipo + ", situazioneTerritorio=" + situazioneTerritorio + ", armataProprietarioTerritorio="
				+ armataProprietarioTerritorio + "]";
	}
	
	
	
	
	
}
