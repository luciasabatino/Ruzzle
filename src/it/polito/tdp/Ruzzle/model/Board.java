package it.polito.tdp.Ruzzle.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Memorizza le lettere presenti nella scacchiera Ruzzle.
 * @author Fulvio
 *
 */
public class Board {
	private List<Pos> positions;
	//Mappa che mette in relazione la posizione con la stringa inserita in quella posizione
	private Map<Pos, StringProperty> cells;

	private int size;

	/**
	 * Crea una nuova scacchiera della dimensione specificata
	 * @param size
	 */
	public Board(int size) {
		this.size = size;

		this.positions = new ArrayList<>();
		for (int row = 0; row < this.size; row++) {
			for (int col = 0; col < this.size; col++) {
				this.positions.add(new Pos(row, col));
			}
		}

		this.cells = new HashMap<>();

		for (Pos p : this.positions) {
			this.cells.put(p, new SimpleStringProperty());
		}
	}
	
	/**
	 * Fornisce la {@link StringProperty} corrispondente alla {@link Pos} specificata. <p>
	 * 
	 * Pu√≤ essere usata per sapere che lettera √® presente
	 * (es. {@code getCellValueProperty(p).get()}) oppure per fare un binding della propriet√† stessa sulla mappa visuale.
	 * @param p
	 * @return
	 */
	public StringProperty getCellValueProperty(Pos p) {
		return this.cells.get(p) ;
	}

	/**
	 * Restituisce la lista di oggetti {@link  Pos} che corrispondono alle posizioni lecite sulla scacchiera. Gli elementi sono ordinati per righe.
	 * @return
	 */
	public List<Pos> getPositions() {
		return positions;
	}

	/**
	 * Crea una nuova scacchiera generando tutte lettere casuali
	 */
	public void reset() {
		for(Pos p: this.positions) {
			int random = (int)(Math.random()*26) ;
			String letter = Character.toString((char)('A'+random)) ;
			this.cells.get(p).set(letter); 
		}
	}
	
	public List<Pos> getAdiacenti(Pos ultima){
		List<Pos> result = new ArrayList<>();
		for(int riga=-1; riga<=1;riga++) {
			for(int colonna=-1; colonna<=1;colonna++) {
				//tutte le 9 posizioni nell'intorno della cella
				if(riga!=0 || colonna!=0) {
					//escludo la cella stessa: basta che o la riga o la colonna siano diverse da zero affinchÈ la cella mi interessi
					Pos p = new Pos(ultima.getRow()+riga,ultima.getCol()+colonna);
					if(positions.contains(p)) {
						//se la posizione Ë contenuta nella board, fa parte degli adiacenti
						result.add(p);
					}
				}
			}
		}
		return result;
	}

	
}
