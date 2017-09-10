package de.leuphana.ui.util;

public class DDragonUrlFormatter {

	private static final String baseUrl = "http://ddragon.leagueoflegends.com/cdn/7.17.2/img/";
	private static final String baseSpellUrl = "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/";
	
	
	
	public String getUrlbyItemImageName(String imgName) {
		return baseUrl+"item/"+imgName;
	}
	
	public String getUrlbySumSpellImageName(String imgName) {
	
		return baseSpellUrl+"spell/"+imgName;
	}
	
	
	
	public String getUrlByChampionImageName(String imgName){
		return baseUrl+"champion/"+imgName;
		
		//"http://ddragon.leagueoflegends.com/cdn/7.17.2/img/champion/" + champion.getImage().getFull()
	}
}
