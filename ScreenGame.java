/*
 * Globalcode - "The Developers Company"
 * 
 * Academia do Java
 * 
 * Jogo da Forca versão Orientada a Objeto
 * Classe ScreenGame
 *
 * Tela de exibição do jogo.
 * @
 *	
 * @autor : Franklin
 * @data: 11/03/2018 - version: 1.0
 * @data: 11/21/2018 - version: 1.1
 *		- Included Creator;
 *		- Format methods has become a new class
 */

 
class ScreenGame {
	
	private String namePlayer;
	private String maskedKeyword;
	public String mensageToPlayer;
	private int inLevelPlayer;
	private int totalLevelPlayer;
	private int sizeKeyword;
	private String listUsedWords;
	private char typeCharacter;
	private int errorsPlayer;
	private byte maximumerrorsPlayer;	
	private byte numberCharacter;
	
	Format umformat = new Format();
	
	
	public ScreenGame(String namePlayer, char typeCharacter, byte numberCharacter){
		this.setNamePlayer(namePlayer);
		this.setTypeCharacterAndNumber(typeCharacter,numberCharacter);		
	}
	
	/*
	   setter the general inf to Game.
	*/
	void initializeScreenGame( String mensageToPlayer, int inLevelPlayer, int totalLevelPlayer,
								String listUsedWords, byte errorsPlayer, byte maximumerrorsPlayer){				
		
		this.mensageToPlayer = mensageToPlayer;
		this.inLevelPlayer = inLevelPlayer;
		this.totalLevelPlayer = totalLevelPlayer;
		this.listUsedWords = listUsedWords;
		this.errorsPlayer = errorsPlayer;
		this.maximumerrorsPlayer = maximumerrorsPlayer;				
	}
	
	/*
	   setter name player
	*/
	void setNamePlayer(String namePlayer) {
		this.namePlayer = namePlayer;
	}
	/*
	   setter mask Keyword
	*/
	void setMaskedKeyword(String maskedKeyword) {
		this.maskedKeyword = maskedKeyword;
	}
	/*
	   setter Error player
	*/
	void setErrorPlayer(int errorsPlayer) {
		this.errorsPlayer = errorsPlayer;
	}
	
	/*
	   setter inLevelPlayer
	*/
	void setInLevelPlayer(int inLevelPlayer) {
		this.inLevelPlayer = inLevelPlayer;
	}
	
	/*
	   setter listUsedWords
	*/
	void setListUsedWords(String listUsedWords) {
		this.listUsedWords = listUsedWords;
	}
	
	/*
	   setter size word
	*/
	void setSizeKeyword(int sizeKeyword) {
		this.sizeKeyword = sizeKeyword;
	}
	
	public void helpGame(){
	   
		umformat.formatLine();
		
		System.out.println ("\n" + umformat.formatCenterText("- Jogo da Forca - Versao Orientada a Objeto" + "\n")); 		 
		System.out.println ("Este e um jogo de adivinhar palavras, com dois estagios:"); 
		System.out.println ("1 - Palavras reservadas do Java             \t Maximo de tentativa sao 6!"); 
		System.out.println ("2 - Palavras reservadas relacionada a tipos \t Maximo de tentativa sao 5!\n");
		System.out.println ("Para tela de ajuda digite [2]. \tPara sair digite [9].\n");
		System.out.println (umformat.formatCenterText("B O A  S O R T E"));
		umformat.formatLine();
		
	}							
	
	public void screenGame() {
	
		String masKey = maskedKeyword;
		String listUsed = listUsedWords; 	 
		System.out.println (umformat.formatCenterText("- Jogo da Forca -")+"\n"); 
			
		System.out.printf("Jogador[%s]\t nivel[%d/%d] \tErros [%d/%d]\n", 
						namePlayer, inLevelPlayer, totalLevelPlayer,errorsPlayer,maximumerrorsPlayer);	
						
		System.out.printf("Letras ja usadas [%s]\n",listUsed);
		System.out.printf("PALAVRA: [%s] / [%d] Quantidade de Letras.\n", masKey,sizeKeyword);
		String text_formatted = "Para ajuda digite [2] Para sair digite [9]\n\n"; 
		System.out.printf(umformat.formatRightText(text_formatted));
		System.out.printf("[%s]\n", mensageToPlayer); 
  }
  
     
   /* 
		Formatt to generic menssage.
   */
  public void genericMessage(String text) {
	umformat.formatLine();		
	System.out.print(umformat.formatCenterText(text));
	umformat.formatLine();	
  }
  
  public void formatLine(){
	  umformat.formatLine();
  }
    
  /* 
	Set type character (ex.: '*' or '#'..) and quantity number of character in line.
  */
  public void setTypeCharacterAndNumber(char typeCharacter, byte numberCharacter){
	this.typeCharacter = typeCharacter;
	this.numberCharacter = numberCharacter;
  }  
  
}