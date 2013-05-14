package phonetic_transcriber;

import helpers.ArrayHelper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import phonetic_character_converter.*;

public class PhoneticTranscriber {
	private ExceptionTranscriptions _exceptionTranscriptions;
	private RulesTranscription _rulesTranscription;
	
	private PhoneticConverter converter=null;
	public String seperator=null;
	
	/**
	 * 
	 * @param seperator - simbolu virkne, ar kuru atdalīt fonētiskos simbolus (null - neatdalīt)
	 * @param converter - fonētisko simbolu konverteros (null - Aplhabetic)
	 */
	public PhoneticTranscriber(String seperator, PhoneticCharacterConverter converter)
	{
		this.seperator=seperator;
		setConverter(converter);
		
		try {
			_exceptionTranscriptions=new ExceptionTranscriptions();
			_exceptionTranscriptions.InitExceptionTranscriptions("resources/PhoneticTranscriber/exceptionTranscriptions.db");
			_rulesTranscription=new RulesTranscription();
			_rulesTranscription.InitTranscriptionRules("resources/PhoneticTranscriber/rules.xml", "resources/PhoneticTranscriber/metas.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setConverter(PhoneticCharacterConverter converter)
	{
		if(converter==null)
		{
			this.converter=null;
		}
		else
		{
			if(this.converter==null)
			{
				this.converter=new PhoneticConverter(new AplhabeticCharacterConverter(), converter);
			}
			else
			{
				this.converter.encoder=converter;
			}
		}
	}
	
	public String transcribe(String word)
	{
		String result=null;
		String[] tokens;
		try {
			result=_exceptionTranscriptions.GetValue(word);
			if(result==null)
			{
				result=_rulesTranscription.TranscribeAString(word);
			}
			
			tokens=result.split("_");
			
			if(converter!=null)
			{
				tokens=converter.convert(tokens);
			}
			
			result=ArrayHelper.implode(tokens, seperator);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void transcribeFile(String inputFilename, String outputFilename) throws Exception
	{
		FileInputStream ifstream = new FileInputStream(inputFilename);
		BufferedReader in = new BufferedReader(new InputStreamReader(ifstream, "UTF8"));
		
		FileOutputStream ofstream = new FileOutputStream(outputFilename);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(ofstream, "UTF8"));
		
		String word;
		while((word=in.readLine())!=null)
		{
			out.write(word);
			out.write("\t");
			out.write(transcribe(word));
			out.write("\n");
		}
		
		in.close();
		out.close();
	
	}
	
	
}
