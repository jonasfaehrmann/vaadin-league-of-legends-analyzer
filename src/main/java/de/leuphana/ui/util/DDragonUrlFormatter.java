package de.leuphana.ui.util;

public class DDragonUrlFormatter {

	private static final String baseUrl = "http://ddragon.leagueoflegends.com/cdn/7.17.2/img/";
//http://ddragon.leagueoflegends.com/cdn/7.5.1/img/profileicon/1387.png
	public String getUrlByChampionImageName(String imgName) {
		return baseUrl + "champion/" + imgName;

		// "http://ddragon.leagueoflegends.com/cdn/7.17.2/img/champion/" +
		// champion.getImage().getFull()
	}

	public String getUrlforSplashImgByImageName(String heroName) {
		return baseUrl + "champion/splash/" + heroName + "_0.jpg";

		// http://ddragon.leagueoflegends.com/cdn/img/champion/splash/Aatrox_0.jpg
	}

	public String getUrlForSummonerIconByImageName(String imgName) {
		return "http://ddragon.leagueoflegends.com/cdn/7.5.1/img/profileicon/"+ imgName+".png";

		// https://ddragon.leagueoflegends.com/cdn/7.12.1/img/profileicon/1665.png.
	}

}