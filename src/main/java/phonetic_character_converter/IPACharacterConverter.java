package phonetic_character_converter;

import java.util.ArrayList;

public class IPACharacterConverter implements PhoneticCharacterConverter {

	@Override
	public String toIPAchar(String character) {
		return character;
	}

	@Override
	public String formIPAchar(String character) {
		return character;
	}

	@Override
	public String[] tokenize(String word) {
		
		ArrayList<String> tokens = new ArrayList<String>();
		int state=0;
		String token="";
		char c;
		for(int i=0;i<word.length();i++)
		{
			c=word.charAt(i);
			switch(state)
			{
			case 0: //if character is extra beginning letter or IPA letter, output separator
				if(("ˌˈ".contains(String.valueOf(c)) || "oieɑæuɔbdfɡɟxʝkclʎmnɲňprsʃtvzʓʣʤʦʧ".contains(String.valueOf(c))) && i!=0)
				{
					tokens.add(token);
					token="";
				}
				
				if("ˌˈ".contains(String.valueOf(c)) || c=='͜') //if it's one of these symbols, we don't need to output seperator before next letter
				{
					state=1;
				}
				
				token+=c;
				
				break;
			case 1://in this state we don't output separator, regardless input character
				token+=c;
				state=0;
				break;
			}
		}
		
		return (String[]) tokens.toArray();
	}

}
