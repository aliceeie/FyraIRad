package TESTAR;

import java.util.ArrayList;

import Version1.Circle;

public class testaArrayList {

	
	public static void main(String[] args) {
		
		ArrayList<ArrayList<String>> arrayOfTiles = new ArrayList<ArrayList<String>>();
		ArrayList<String> yLed = new ArrayList<String>();

		
		for(int y = 0; y < 6; y++){
			arrayOfTiles.add(yLed);									//L�gger till alla kolumners cirklar i arrayOfTiles
			for(int x = 0; x < 7; x++){
				yLed.add(Integer.toString(x) + "," + Integer.toString(y));										//L�gger till 7 vita cirklar i yLed
			}
		}
		
		
		for(int y = 0; y < 6; y++){
			arrayOfTiles.add(yLed);
			for(int x = 0; x < 7; x++){
				System.out.print();
		}
		

/*		for(ArrayList<String> a : arrayOfTiles) {
			for(String c : yLed) {
				System.out.print(c);
			}
		}
		*/
		
	//	System.out.print(arrayOfTiles.get(0));
			
			
	}
}
