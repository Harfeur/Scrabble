package fr.scrabble.game;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.JSONObject;

import fr.scrabble.menu.vues.ErrorFrame;
import fr.scrabble.structures.Chevalet;
import fr.scrabble.structures.Plateau;

public class Ordinateur {
	
	public static JSONObject solutions(Plateau plateau, Chevalet chevalet, String langue) {
		JSONObject json = new JSONObject();
		DataOutputStream dos = null;
		BufferedReader bf = null;
		try {
			URL url = new URL("https://www.scrabble-word-finder.com/api/solver/getSolutions");
			
			JSONObject data = new JSONObject();

			JSONObject input_array = new JSONObject();
			JSONObject design = new JSONObject();
			JSONObject option_array = new JSONObject();

			design.put("name", "standard_fr");
			design.put("designName", "standard");
			
			input_array.put("language", langue);
			input_array.put("rack", chevalet.toString());
			input_array.put("board", plateau.toString());
			if (langue.equals("FR"))
				input_array.put("lexicon", "ODS6");
			else
				input_array.put("lexicon", "TWL2014");
			input_array.put("design", design);
			
			option_array.put("what", "scrabble-solver");
			option_array.put("source", "DATABASE");
			option_array.put("output", "JSON");
			
			data.put("input_array", input_array);
			String[] display_array = {"word"};
			data.put("display_array", display_array);
			data.put("option_array", option_array);
			
			String postData = "data="+URLEncoder.encode(data.toString().replaceAll("\\s+",""), "UTF-8");
			
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", Integer.toString(postData.length()));
			
			dos = new DataOutputStream(conn.getOutputStream());
			dos.writeBytes(postData);
			
			bf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = bf.readLine()) != null)
				json = new JSONObject(line);
		} catch (IOException e) {
			new ErrorFrame("Connexion internet nécessaire pour jouer contre l'ordinateur.");
		} finally {
			try {
				if (dos != null) dos.close();
				if (bf != null) bf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return json;
	}
}
