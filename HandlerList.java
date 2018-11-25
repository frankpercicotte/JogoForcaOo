/*
 * Globalcode - "The Developers Company"
 * 
 * Academia do Java
 * 
 * Jogo da Forca versão Orientada a Objeto
 * Classe HandlerList
 *
 * Manipulação das listas com os nomes do jogo
 *
 * @autor : Franklin
 * @data: 10/30/2018
 * @version: 1.0
 */

 
class HandlerList {
	
	
	private String[][] arrayWordsList;	
	private String wordSelected;
	private int numWordSelectInArray;	
	
	
	/*
		setter arrayWordsListst
		2 dimension [{status,word,inf},...]
		status: used / available;
		word: that send by list;
		
	*/
	public boolean setList(String[] list) {
		
		int size = list.length;
		String[][] tmparray = new String[size][2];
		
		//list are there elements?
		if ( list != null) {
			// yes - load tmparray with: [0] status [1] word of list;
			for ( int i = 0; i < size; i++){
				tmparray[i][0] = "available";
				tmparray[i][1] = list[i];				
			}
			//copy tmparray to arrayWordsList
			arrayWordsList = tmparray;
			//It´s all ok! - TRUE
			return true;
		}
		//if : Opsss something not work in here! False
		return false;
	}
	
	/*
		getter size of the List
	*/
	public int getSizeList(){
		int size;
		if (arrayWordsList != null )
			size = arrayWordsList.length;
		else
			size = 0;
		
		return size;
	}
	
	/*	
		getter the word in the requested position
	*/
	public String getWordInList(int position){
		String word;
		if(getSizeList() < position) return null;
		
		//This word is available?
		if (arrayWordsList[position][0] == "available") {			
			word = arrayWordsList[position][1];			
			numWordSelectInArray = position;
			
		} 
		// It already is used!
		else {
			word = "";			
		}
		//System.out.printf("status palavra selecionada: %s",arrayWordsList[position][0]);
		this.wordSelected = word;		
		return  word;
	}
	
	/* 
		getter size of the word selected
	*/
	
	public int getSizeWord(){
		int size;
		if (wordSelected != null)
			size = wordSelected.length();		
		else
			size = 0;
		
		return size;		
	} 
	
	/* 		
		setter the word with "used";
	*/
	public boolean setUsedWordInList() {
		
		// Verify if word is valid (not null or position major than list)
		if(wordSelected == null ||getSizeList() <  numWordSelectInArray) return false;
		
		// Is there the word in this numWordSelectInArray is valid?
		if (arrayWordsList[numWordSelectInArray][0] != null) {
			arrayWordsList[numWordSelectInArray][0] = "used";			
			return true;
		} else 						
			return false;
	}
	
	/* 
		getter status word on list "used" or "available";
	*/
	public String getStatusWordInList() {
		String status;
		
		if(wordSelected == null || getSizeList() <  numWordSelectInArray) return null;
		
		status = arrayWordsList[numWordSelectInArray][0];						
					
		return status;
	}
	
}
