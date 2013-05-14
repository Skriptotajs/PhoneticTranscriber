package main;

import phonetic_transcriber.PhoneticTranscriber;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * TODO uzrakstīt main wrapperi
		 */
		PhoneticTranscriber transcriber= new PhoneticTranscriber("_", null);
		try {
			transcriber.transcribeFile("words.txt", "words.txt.transcribed");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
