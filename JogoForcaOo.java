/*
 * Globalcode - "The Developers Company"
 * 
 * Academia do Java
 * 
 * Jogo da Forca versão Orientada a Objeto
 * Classe JogoForcaOo
 *
 * Manipulação da palavra selecionada para o jogo
 * @handlerMaskWord() - manibula a mascara da palavra, retorna palavra com mascara nas letras não adivinhadas;
 * @verifyLetterAttempt() - verifica se acertou a letra, retorna boolean; 
 *	
 * @autor : Franklin
 * @data: 11/11/2018  * @version: 1.0
 * @data: 11/22/2018  
 * 
 * @version 1.1 :
 *		- class upgrading;
 *		- separation in methods;
 */

 
class JogoForcaOo {
	
	static int sizeListInGame;
	static String wordSelected;
	static char typeMaskletter;
	static int sizeMaxListUsed;
	static String listUsedLetters;
	static byte errorsPlayer;
	static byte maxErrorPlayer;
	static byte sizeKeyword;
	static int inLevelPlayer;
	static int totalLevelPlayer;
	static int inStageGamePlayer;
	static byte stage;
	static int listInUse;
	static int totalListGame;
	
	static String listStage[][] = {		
		{"abstract","assert","boolean","break","byte",
		"case","catch","char","class","const",
		"continue","default","do","double","else",
		"enum","extends","final","finally","float",
		"for","goto","if","implements","import",
		"instanceof","int","interface","long","native",
		"new","package","private","protected","public",
		"return","short","static","strictfp","super",
		"switch","synchronized","this","throw","throws",
		"transient","try","void","volatile","while"},	

		{"byte","char","short","int","long",
		"float","double","boolean","String","enum"}			
	}; 
    
	
	
	
   public static void main (String [] args) {	
	
		 
		//	Initialization HandlerList				
		HandlerList listIngame = new HandlerList();					
		listInUse = 0; // the first list 		
		
		// Loading list of the first stage
		listIngame.setList(listStage[listInUse]);
		
	
		// Initialization Handler letter
		listUsedLetters = "";
		wordSelected = "";		
		typeMaskletter = '*';
		sizeMaxListUsed = 26;  // total of letters in the alphabet		 
		HandlerLetter wordInGame = new HandlerLetter(wordSelected,typeMaskletter,sizeMaxListUsed);		
			
		//	Initialization Player		
		java.util.Scanner sc = new java.util.Scanner(System.in);
		System.out.print("Nome do Jogador: ");
		String inputName = sc.next();		
		Player player1 = new Player(inputName);				
		
		
		// Initialization the first stage
		stage = 1;
		initialStage(stage, player1, listIngame);
				 
		// initialization screenGame		 		
		char typeCharacter = '*';
		byte numberCharacter = 100;		
		ScreenGame screen1 = new ScreenGame(inputName, typeCharacter, numberCharacter);
		screen1.mensageToPlayer = "Pronto para jogar? Escolha uma letra e boa sorte!";
		
		//show the introdution
		screen1.helpGame();
	
		// 	Initialization the Game!	
		theGame(player1, listIngame, wordInGame, screen1);
   
   }		
			
			
			
	static void theGame(Player player1, HandlerList listIngame, HandlerLetter wordInGame, ScreenGame screen1){
		String resultAttempt;
		boolean theGame = true;
		boolean inGame = true;
		String inputPlayer;
		
		theGameLoop: while(theGame) {	
			// Select a keywor, return size keyword
			sizeKeyword = getAttempt(listIngame, wordInGame, screen1);			
			
			// show screen 
			screen1.initializeScreenGame(screen1.mensageToPlayer, inLevelPlayer,
					totalLevelPlayer, listUsedLetters, errorsPlayer, maxErrorPlayer);

			//Player attempts to hit the keyword.				
			attemptsLoop: while(inGame) {
				
				// Show the screen1.
				screen1.screenGame();
				
				//Get the player's guess			
				inputPlayer = player1.getInputPlayer("De o seu palpite para a letra:");
				
				//Analyzing the guess
				resultAttempt = wordInGame.handlerAttempt(inputPlayer);
				
				// take action, if return false go back to second loop
				if(!checkActions(resultAttempt, player1, listIngame, wordInGame, screen1)){ 
					continue attemptsLoop;
				}

				//Check if the player hits the keyword. If yes go back the first loop.				
				if(checkFinishStage(player1, listIngame, wordInGame, screen1)) {
					continue theGameLoop;
				}				 
				
				//The odds are over, the game over.
				if (wordInGame.getcountErrs()== maxErrorPlayer){
					// mensages to player.
					screen1.genericMessage("Errou a palavra! [" + wordSelected + "]");					
					screen1.genericMessage("Sorry baby! Game Over for you!!");
					
					//Leavethe game!
					System.exit(0);
				}					
			}
		}   
	} 

	
	
	
	
	// set parameters of the Player
	static void initialStage(byte stage, Player player1, HandlerList listIngame){	
				
		if (stage == 1) { 
			errorsPlayer = 0;
			maxErrorPlayer = 6;
			inLevelPlayer = 1;
			totalLevelPlayer = listIngame.getSizeList(); //== size list.
			inStageGamePlayer = 1;		
		}
		else {
			errorsPlayer = 0;
			maxErrorPlayer = 5;
			inLevelPlayer = 1;
			totalLevelPlayer = listIngame.getSizeList(); //== size list.
			inStageGamePlayer = 2;
		}

		// Total lists in listStage;
		totalListGame = listStage.length - 1; 	// (-1) because the first is zero!		
		sizeListInGame = totalLevelPlayer; 		// Number of elements in the current list
		
		player1.initializePlayer(errorsPlayer,inLevelPlayer, inStageGamePlayer,maxErrorPlayer);
	}	
	
	
			
	
	// Analyzing and take action. - return true for Ok, and false for go back the loop;
	
	static boolean checkActions(String resultAttempt,Player player1, HandlerList listIngame, HandlerLetter wordInGame, ScreenGame screen1){
		String input;		
		
		switch(resultAttempt) {
			
			case "Okey":
				screen1.formatLine();
				screen1.setMaskedKeyword(wordInGame.getMaskedString());
				screen1.setListUsedWords(wordInGame.getLettersUsed());
				screen1.mensageToPlayer = "Acertou a letra, vamos para a proxima!";
				listIngame.setUsedWordInList();	
				return true;	
				//break;
			
			case "Missed":
				screen1.formatLine();
				screen1.setErrorPlayer(wordInGame.getcountErrs());
				screen1.setListUsedWords(wordInGame.getLettersUsed());
				screen1.mensageToPlayer = "Errou a letra,tente de novo!";
				return true;	
				//break;
			
			case "Repeated":
				screen1.formatLine();
				screen1.mensageToPlayer = "Ops voce ja usou esta letra, tente outra!";
				return false;
			
			case "Help":
				screen1.mensageToPlayer = "Exibida a tela de Ajuda.";
				screen1.helpGame();						
				return false;				
			
			case "Quit":					
				screen1.formatLine();
				//Ask the player if he really wants to quit the game.
				input = player1.getInputPlayer("Quer realmente sair do jogo? [S] para sair!");
				char responsePlayer = Character.toLowerCase(input.charAt(0));
				
				//if true the player want to leave the game.
				if ( responsePlayer == 's') {
					screen1.genericMessage("Ah!Saindo do jogo! Tchau, tchau! Ate breve!");
					System.exit(0);
				}
				screen1.formatLine();
				screen1.mensageToPlayer = "Quem bom que ficou, tente uma letra.";
				return true;	
				//break;
			
			case "Numeral":
				screen1.formatLine();
				screen1.mensageToPlayer = "Ops! Digitos 0,1,3,4,5,6,7 e 8 sao invalidos.!";
				return false;
			
			default:
				screen1.formatLine();
				screen1.genericMessage("Ops alguma coisa deu MUITO errado! Saindo. Letra [" + resultAttempt + "] Palavra["+ wordSelected +"]");
				System.exit(0);
		}			
		return false;
	}
	
	
	
		
	// Select a keyword to use in the game 
	static byte getAttempt(HandlerList listIngame, HandlerLetter wordInGame, ScreenGame screen1) {
	
		java.util.Random numRandom = new java.util.Random();		
		int numRandomInList;
		byte sizeKeyword;
		String maskWordGame;
		wordSelected = "";
		
		while(wordSelected == "") {			
			numRandomInList = numRandom.nextInt(sizeListInGame);					
			wordSelected = listIngame.getWordInList(numRandomInList);
		}
		
		// Input mask in keyword seleceted
		wordInGame.initialized(wordSelected,typeMaskletter,sizeMaxListUsed);
			
		// Get word with mask;
		maskWordGame = wordInGame.getMaskedString();
			
		// Set on screen the keyword with mask
		screen1.setMaskedKeyword(maskWordGame);
			
		// Set on screen the size keyword
		sizeKeyword = (byte) wordSelected.length();
		screen1.setSizeKeyword(sizeKeyword);
			
		// Get list letter used in String.		
		listUsedLetters = wordInGame.getLettersUsed(); // In this moment is empty!	
		
		return sizeKeyword;		
	}

			
	static 	boolean checkFinishStage(Player player1, HandlerList listIngame, HandlerLetter wordInGame, ScreenGame screen1){
				
		if (sizeKeyword == wordInGame.getcountSucess()) {								 
								
			// verify if complete the stage,if true go to next list or go to finish game.
			if (inLevelPlayer == sizeListInGame) {
				listInUse++;				
				if ( listInUse <= totalListGame) {
					screen1.genericMessage("Acertou a palavra [" + wordSelected + "] PARABENS!" );	
					screen1.genericMessage("Parbens finalizou a lista, vamos para a proxima!\n");													
												
					// Loading list of the next stage
					listIngame.setList(listStage[listInUse]);
					
					// Set player - stage '1;
					stage = 2;
					initialStage(stage, player1, listIngame);						
				}
				//  If finished the last list of the game - congratulations. End of the Game!
				else {
					screen1.genericMessage("Acertou a palavra [" + wordSelected + "]" );
					screen1.genericMessage("******* P A R A B E N S   V O C E   F I N A L I Z O U   O   J O G O ! *******");
					System.exit(0);
				}						
			}
			// Just hit the word, prepare for the next word on the list. 
			else {
				// set new level;
				inLevelPlayer++;
				screen1.setInLevelPlayer(inLevelPlayer);
				// set errs to zero
				player1.resetPointPlayer();						
				screen1.setErrorPlayer(0);
				// mensages to player.
				screen1.genericMessage("Acertou a palavra {" + wordSelected + "}. Parabens!");					
				screen1.mensageToPlayer = "Nova lista!";						
			}					
			// If it get here, go back to the beginning of the loop to load a new keyword.
			return true;
		} 
		
		return false;
	}	
}


