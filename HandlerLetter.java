/*
 * Globalcode - "The Developers Company"
 * 
 * Academia do Java
 * 
 * Jogo da Forca versão Orientada a Objeto
 * Classe HandlerWord
 *
 * Manipulação da palavra selecionada para o jogo
 * @handlerMaskWord() - manibula a mascara da palavra, retorna palavra com mascara nas letras não adivinhadas;
 * @verifyLetterAttempt() - verifica se Okey a letra, retorna boolean; 
 *	
 * @autor : Franklin
 * @data: 11/02/2018
 * @version: 1.0
 */

 
class HandlerLetter {
	
	private char[] wordMasked;
	private  String stringMasked;	
	private char[] keyword;
	private char[] lettersUsed;
	private int sizeMaxListUsed;	
	private int sizeWord;		
	private char typeMaskletter;
	private int countSucess;
	private int countErrs;
	
	/*
		Initialization class
		@wordSelected - string that have word key;
		@typeMask - What prototype the mask ex: *****
	*/
	
	public HandlerLetter(String wordSelected, char typeMaskletter, int sizeMaxListUsed) {
		this.initialized(wordSelected, typeMaskletter, sizeMaxListUsed);	
	}
	
	public void initialized(String wordSelected, char typeMaskletter, int sizeMaxListUsed) {
      
		keyword = wordSelected.toCharArray();		
		sizeWord = keyword.length;
		countSucess = 0;
		countErrs = 0;		
        this.sizeMaxListUsed = sizeMaxListUsed;
		this.lettersUsed = new char[sizeMaxListUsed]; 	
		this.typeMaskletter = typeMaskletter;
		this.wordMasked = wordSelected.toCharArray();;
				
		// load wordMasked with  typemask ;		
		for (byte y = 0; y < sizeWord; y++)
			wordMasked[y] = typeMaskletter;
		
		// load letterAttempt with ' ';
		for (byte y = 0; y <sizeMaxListUsed; y++)
			lettersUsed[y] = ' ';			
	}
	
	/*
		getter keyword
	*/			  	
	public String getkeyword() {
		String name = new String(keyword);		
		return name;
	}
	
	/*
		getter lettersUsed
	*/			  	
	public String getLettersUsed() {
		String used = new String(lettersUsed);		
		return used;
	}
	
	/*
		getter  string Masked
	*/			  	
	public String getMaskedString() {
		stringMasked = new String(wordMasked);		
		return stringMasked;
	}
	
	/*
		getter numberSuccess
	*/
	public int getcountSucess() {
		return countSucess;
	}
	
	/*
		getter countErrs
	*/
	public int getcountErrs() {
		return countErrs;
	}
	
	/*
		handlerAttempt check player response
		@answer default return: "Missed"
		When letterAttempt is on word return: "Okey" 
		if the letter already been used return: "Repeated"
		if the letter '2' return: "Help"
		if the letter '9'return: "Quit"
		if the letter '0','1' or '3' until '8' return: "Numeral"
	*/	
	public String handlerAttempt(String attempt) {
		char letterAttempt = Character.toLowerCase(attempt.charAt(0));
		
		String answer = null;
		
		// check that word was initialized
		if (keyword == null) 
			answer = "@Error";		
		
		// '2' for help
		if (letterAttempt == '2'){
			answer = "Help";	
		}
		// '9' to quit the game.
		else if (letterAttempt == '9'){
			answer = "Quit";	
		}	
		//If the attempt is another number returns: numeral. And get out!!
		else if ( ((int)letterAttempt) > 47 && ((int)letterAttempt) < 57){
			answer = "Numeral";
		}
		else {			
			answer = checkLetter(letterAttempt);
		}
		
		return answer;
	}
	
	/* 
		@checkLetter - check and return:
		"Repeated","Okey", "Missed";
	*/
	
	private String checkLetter(char letterAttempt){
		String answer = "Missed";
		char test;
		
		// check if the letter already been used 				
		for( int y = 0; y < lettersUsed.length;y++){				
			
			// If letter already used, return Repeated.
			if (lettersUsed[y] == letterAttempt){			
				return answer = "Repeated";				
			}
			//if the position is blank, then this letter not was used.
			
			else if (lettersUsed[y] == ' '){					
				lettersUsed[y] = letterAttempt;
				answer = "Missed";
				
				//Check if the word has the letter tried
				for ( byte b = 0; b < sizeWord; b++){
					// Select the letter of the keyword;		
					test = keyword[b];						
					
					// If the letter is correct, it shows the letter in the mask and returns okey. 	
					if ( test == letterAttempt) {
						wordMasked[b] = letterAttempt;
						countSucess++;						
						answer = "Okey";						
					}						
				}
				
				//Finished the survey. If didn't hit the letter, count the error;
				if (answer == "Missed") {
					countErrs++;
					return answer;	
				}
				
				break;		
			}					
		}	
		return answer;
	}
	
	
}
	