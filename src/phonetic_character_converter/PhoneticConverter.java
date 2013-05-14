package phonetic_character_converter;

import helpers.ArrayHelper;

public class PhoneticConverter {
	public PhoneticCharacterConverter decoder;
	public PhoneticCharacterConverter encoder;
	
	public PhoneticConverter(PhoneticCharacterConverter decoder, PhoneticCharacterConverter encoder)
	{
		this.decoder=decoder;
		this.encoder=encoder;
	}
	
	public String convertCharacter(String character) throws Exception
	{
		String token=decoder.toIPAchar(character);
		
		if(token==null)
		{
			throw new Exception("Couldn't decode character \""+character+"\"");
		}
		
		token=encoder.formIPAchar(token);
		
		if(token==null)
		{
			throw new Exception("Couldn't encode character \""+character+"\"");
		}
		
		return token;
	}
	
	public String convert(String word, String seperator) throws Exception
	{
		
		String[] tokens=decoder.tokenize(word);
		
		if(tokens==null)
		{
			throw new Exception("Couldn't tokenize word \""+word+"\"");
		}
		
		tokens=convert(tokens);
		
		return ArrayHelper.implode(tokens,seperator);
	}
	
	public String[] convert(String[] tokens) throws Exception
	{		
		String result[]=new String[tokens.length];
		int i=0;
		for(String token : tokens)
		{			
			try
			{
				token=decoder.toIPAchar(token);
				result[i]=encoder.formIPAchar(token);
				i++;
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage()+" in \""+tokens.toString()+"\"", e);
			}
		}
		
		return result;
	}
	
	public String convert(String word) throws Exception
	{
		return convert(word,null);
	}
}
