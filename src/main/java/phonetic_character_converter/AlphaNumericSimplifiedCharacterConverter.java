package phonetic_character_converter;

public class AlphaNumericSimplifiedCharacterConverter implements
		PhoneticCharacterConverter {

	@Override
	public String toIPAchar(String character) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String formIPAchar(String character) {
		String result=null;
		String before=null;
		String after=null;
		String original=character;
		int length=character.length();
		if(length>1)//ja garāks par 1 simbolu, sākumā vai beigās var parādīties speciālie simboli
		{
			//speciālie simboli, kuri var parādīties beigās
			switch(character.charAt(length-1))
			{
			case '̆':
				after="0306";
				break;
			case 'ˑ':
				after="02D1";
				break;
			case 'ˀ':
				after="02C0";
				break;
			case '̯':
				after="032F";
				break;
			case '̩':
				after="0329";
				break;
			}
			
			
			//speciālie simboli, kuri var parādīties sākumā
			switch(character.charAt(0))
			{
			case 'ˌ':
				before="02CC";
				break;
			case 'ˈ':
				before="02C8";
				break;
			}
			
			if(after!=null)//ja ir simbols, kuru likt beigās, no vārda atmetam šo simboli
			{
				character=character.substring(0, length-1); //-2, jo pēdējais simbols ir length-1
			}
			
			if(before!=null)//ja speciālais simbols bija sākumā, atnet speciālo simbolu
			{
				character=character.substring(1);
			}
			
		}
		
		switch(character) //atrod atbilstošo IPA simbolu atlikušajai daļai bez speciālajiem simboliem
		{
		case "i":
			result="i";
			break;
		case "iː":
			result="i02D0";
			break;
		case "e":
			result="e";
			break;
		case "eː":
			result="e02D0";
			break;
		case "ɑ":
			result="0251";
			break;
		case "ɑː":
			result="025102D0";
			break;
		case "æ":
			result="e";
			break;
		case "æː":
			result="e02D0";
			break;
		case "u":
			result="u";
		break;
		case "uː":
			result="u02D0";
			break;
		case "ɔ":
		case "o":
			result="0254";
			break;
		case "ɔː":
		case "oː":
			result="025402D0";
			break;
		case "i͜u":
			result="i035Cu";
			break;
		case "i͜e":
			result="i035Ce";
			break;
		case "ɑ͜i":
			result="0251035Ci";
			break;
		case "ɑ͜u":
			result="0251035Cu";
			break;
		case "e͜i":
			result="e035Ci";
			break;
		case "u͜o":
			result="u035Co";
			break;
		case "u͜i":
			result="ui";
			break;
		case "e͜u":
			result="eu";
			break;
		case "ɔ͜u":
		case "o͜u":
			result="o035Cu";
			break;
		case "ɔ͜i":
		case "o͜i":
			result="0254035Ci";
			break;
		case "b":
			result="b";
			break;
		case "bː":
			result="b";
			break;
		case "d":
			result="d";
			break;
		case "dː":
			result="d";
			break;
		case "f":
			result="f";
			break;
		case "fː":
			result="f";
			break;
		case "ɡ":
			result="g";
			break;
		case "ɡː":
			result="ɡ02D0";
			break;
		case "ɟ":
			result="025F";
			break;
		case "ɟː":
			result="025F";
			break;
		case "x":
			result="x";
			break;
		case "xː":
			result="x";
			break;
		case "ʝ":
			result="029D";
			break;
		case "ʝː":
			result="029D";
			break;
		case "k":
			result="k";
			break;
		case "kː":
			result="k";
			break;
		case "c":
			result="c";
			break;
		case "cː":
			result="c";
			break;
		case "l":
			result="l";
			break;
		case "lː":
			result="l";
			break;
		case "ʎ":
			result="028E";
			break;
		case "ʎː":
			result="028E";
			break;
		case "m":
			result="m";
			break;
		case "mː":
			result="m";
			break;
		case "n":
			result="n";
			break;
		case "nː":
			result="n";
			break;
		case "ɲ":
			result="0272";
			break;
		case "ɲː":
			result="0272";
			break;
		case "ň":
			result="0148";
			break;
		case "ňː":
			result="0148";
			break;
		case "p":
			result="p";
			break;
		case "pː":
			result="p";
			break;
		case "r":
			result="r";
			break;
		case "rː":
			result="r";
			break;
		case "s":
			result="s";
			break;
		case "sː":
			result="s";
			break;
		case "ʃ":
			result="0283";
			break;
		case "ʃː":
			result="0283";
			break;
		case "t":
			result="t";
			break;
		case "tː":
			result="t";
			break;
		case "v":
			result="v";
			break;
		case "vː":
			result="v";
			break;
		case "z":
			result="z";
			break;
		case "zː":
			result="z";
			break;
		case "ʓ":
			result="0293";
			break;
		case "ʓː":
			result="0293";
			break;
		case "ʣ":
			result="02A3";
			break;
		case "ʣː":
			result="02A3";
			break;
		case "ʤ":
			result="02A4";
			break;
		case "ʤː":
			result="02A4";
			break;
		case "ʦ":
			result="02A6";
			break;
		case "ʦː":
			result="02A6";
			break;
		case "ʧ":
			result="02A7";
			break;
		case "ʧː":
			result="02A7";
			break;
		}
		
		
		return result;
	}

	@Override
	public String[] tokenize(String word) {
		// TODO Auto-generated method stub
		return null;
	}

}
