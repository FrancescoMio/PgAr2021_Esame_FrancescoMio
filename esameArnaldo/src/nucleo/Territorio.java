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
	
	public Territorio(String nome, String tipo, int situazioneTerritorio) {
		this.nome = nome;
		this.tipo = tipo;
		this.situazioneTerritorio = situazioneTerritorio;
	}
	
	
	public String getNomeProprietarioTerritorio() {
		return nomeProprietarioTerritorio;
	}

	public Armata getArmataProprietarioTerritorio() {
		return armataProprietarioTerritorio;
	}


	public void setArmataProprietarioTerritorio(Armata armataProprietarioTerritorio) {
		this.armataProprietarioTerritorio = armataProprietarioTerritorio;
	}


	public void setNomeProprietarioTerritorio(String nomeProprietarioTerritorio) {
		this.nomeProprietarioTerritorio = nomeProprietarioTerritorio;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
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
