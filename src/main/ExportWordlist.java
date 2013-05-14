package main;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import lv.semti.morphology.analyzer.Analyzer;
import lv.semti.morphology.analyzer.Wordform;
import lv.semti.morphology.attributes.AttributeNames;
import lv.semti.morphology.corpus.Statistics;
import lv.semti.morphology.lexicon.Lexeme;
import lv.semti.morphology.lexicon.Paradigm;
import phonetic_transcriber.PhoneticTranscriber;

public class ExportWordlist {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Analyzer analizators = new Analyzer("../Morphology/dist/Lexicon.xml",false);
		Statistics stats = Statistics.getStatistics("../Morphology/dist/Statistics.xml");
		PhoneticTranscriber transcriber = new PhoneticTranscriber();
		
		//PrintWriter izeja = new PrintWriter(new PrintStream(System.out, true, "UTF8"));
		BufferedWriter izeja = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("vaardi.txt"), "UTF-8"));
		
		int count = 1;
		for (Paradigm p : analizators.paradigms)
			for (Lexeme l : p.lexemes) {
				count++;
				if (count % 10000 == 0) System.out.print(".");
				if (count % 100000 == 0) System.out.println();
				//if (count > 100) break;

				ArrayList<Wordform> formas = analizators.generateInflections(l);
				for (Wordform forma : formas) {
					String literal = forma.getToken();
					String phonetic = transcriber.transcribe(literal);
					double estimate = stats.getEstimate(forma);
					izeja.append(String.format("%s\t%s\t%f\n",literal,phonetic,estimate));
					
				}
			}
	
		izeja.flush();
		izeja.close();
		System.out.println("done!");

	}

}
