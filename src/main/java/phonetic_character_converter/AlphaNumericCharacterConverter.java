package phonetic_character_converter;

public class AlphaNumericCharacterConverter implements
		PhoneticCharacterConverter {

	@Override
	public String toIPAchar(String character) {
		String result=null;
		String before=null;
		String after=null;
		int length=character.length();
		if(length>4)//ja garāks par 1 simbolu, sākumā vai beigās var parādīties speciālie simboli
		{
			//speciālie simboli, kuri var parādīties beigās
			switch(character.substring(length-4))
			{
			case "0306":
				after="̆";
				break;
			case "02D1":
				after="ˑ";
				break;
			case "02C0":
				after="ˀ";
				break;
			case "032F":
				after="̯";
				break;
			case "0329":
				after="̩";
				break;
			}
			
			
			//speciālie simboli, kuri var parādīties sākumā
			switch(character.substring(0,4))
			{
			case "02CC":
				before="ˌ";
				break;
			case "02C8":
				before="ˈ";
				break;
			}
			
			if(after!=null)//ja ir simbols, kuru likt beigās, no vārda atmetam šo simboli
			{
				character=character.substring(0, length-4); //-2, jo pēdējais simbols ir length-1
			}
			
			if(before!=null)//ja speciālais simbols bija sākumā, atnet speciālo simbolu
			{
				character=character.substring(4);
			}
			
		}
		
		switch(character) //atrod atbilstošo IPA simbolu atlikušajai daļai bez speciālajiem simboliem
		{
		case "i":
			result="i";
			break;
		case "SIL":
		case "":
			result="";
			break;
		case "i02D0":
			result="iː";
			break;
		case "e":
			result="e";
			break;
		case "e02D0":
			result="eː";
			break;
		case "0251":
			result="ɑ";
			break;
		case "025102D0":
			result="ɑː";
			break;
		case "00E6":
			result="æ";
			break;
		case "00E602D0":
			result="æː";
			break;
		case "u":
			result="u";
		break;
		case "u02D0":
			result="uː";
			break;
		case "0254":
			result="o";
			break;
		case "025402D0":
			result="oː";
			break;
		case "i035Cu":
			result="i͜u";
			break;
		case "i035Ce":
			result="i͜e";
			break;
		case "0251035Ci":
			result="ɑ͜i";
			break;
		case "0251035Cu":
			result="ɑ͜u";
			break;
		case "e035Ci":
			result="e͜i";
			break;
		case "u035Co":
			result="u͜o";
			break;
		case "ui":
			result="u͜i";
			break;
		case "eu":
			result="e͜u";
			break;
		case "o035Cu":
			result="o͜u";
			break;
		case "0254035Ci":
			result="o͜i";
			break;
		case "b":
			result="b";
			break;
		case "b02D0":
			result="bː";
			break;
		case "d":
			result="d";
			break;
		case "d02D0":
			result="dː";
			break;
		case "f":
			result="f";
			break;
		case "f02D0":
			result="fː";
			break;
		case "g":
			result="ɡ";
			break;
		case "ɡ02D0":
			result="ɡː";
			break;
		case "025F":
			result="ɟ";
			break;
		case "025F02D0":
			result="ɟː";
			break;
		case "x":
			result="x";
			break;
		case "x02D0":
			result="xː";
			break;
		case "029D":
			result="ʝ";
			break;
		case "029D02D0":
			result="ʝː";
			break;
		case "k":
			result="k";
			break;
		case "k02D0":
			result="kː";
			break;
		case "c":
			result="c";
			break;
		case "c02D0":
			result="cː";
			break;
		case "l":
			result="l";
			break;
		case "l02D0":
			result="lː";
			break;
		case "028E":
			result="ʎ";
			break;
		case "028E02D0":
			result="ʎː";
			break;
		case "m":
			result="m";
			break;
		case "m02D0":
			result="mː";
			break;
		case "n":
			result="n";
			break;
		case "n02D0":
			result="nː";
			break;
		case "0272":
			result="ɲ";
			break;
		case "027202D0":
			result="ɲː";
			break;
		case "0148":
			result="ň";
			break;
		case "014802D0":
			result="ňː";
			break;
		case "p":
			result="p";
			break;
		case "p02D0":
			result="pː";
			break;
		case "r":
			result="r";
			break;
		case "r02D0":
			result="rː";
			break;
		case "s":
			result="s";
			break;
		case "s02D0":
			result="sː";
			break;
		case "0283":
			result="ʃ";
			break;
		case "028302D0":
			result="ʃː";
			break;
		case "t":
			result="t";
			break;
		case "t02D0":
			result="tː";
			break;
		case "v":
			result="v";
			break;
		case "v02D0":
			result="vː";
			break;
		case "z":
			result="z";
			break;
		case "z02D0":
			result="zː";
			break;
		case "0293":
			result="ʓ";
			break;
		case "029302D0":
			result="ʓː";
			break;
		case "02A3":
			result="ʣ";
			break;
		case "02A302D0":
			result="ʣː";
			break;
		case "02A4":
			result="ʤ";
			break;
		case "02A402D0":
			result="ʤː";
			break;
		case "02A6":
			result="ʦ";
			break;
		case "02A602D0":
			result="ʦː";
			break;
		case "02A7":
			result="ʧ";
			break;
		case "02A702D0":
			result="ʧː";
			break;
		}
		
		if(result==null)
		{
			return null;
		}
		
		if(before!=null) //ja bija speciālais simbols sākumā, pievienot pārkonvertēto sākuma simbolu rezultātam
		{
			result=before+result;
		}
		
		if(after!=null) //pēc alternatīvas kā sākumā 
		{
			result+=after;
		}
		
		return result;
	}

	@Override
	public String formIPAchar(String character) {
		String result=null;
		String before=null;
		String after=null;
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
			result="00E6";
			break;
		case "æː":
			result="00E602D0";
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
			result="b02D0";
			break;
		case "d":
			result="d";
			break;
		case "dː":
			result="d02D0";
			break;
		case "f":
			result="f";
			break;
		case "fː":
			result="f02D0";
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
			result="025F02D0";
			break;
		case "x":
			result="x";
			break;
		case "xː":
			result="x02D0";
			break;
		case "ʝ":
			result="029D";
			break;
		case "ʝː":
			result="029D02D0";
			break;
		case "k":
			result="k";
			break;
		case "kː":
			result="k02D0";
			break;
		case "c":
			result="c";
			break;
		case "cː":
			result="c02D0";
			break;
		case "l":
			result="l";
			break;
		case "lː":
			result="l02D0";
			break;
		case "ʎ":
			result="028E";
			break;
		case "ʎː":
			result="028E02D0";
			break;
		case "m":
			result="m";
			break;
		case "mː":
			result="m02D0";
			break;
		case "n":
			result="n";
			break;
		case "nː":
			result="n02D0";
			break;
		case "ɲ":
			result="0272";
			break;
		case "ɲː":
			result="027202D0";
			break;
		case "ň":
			result="0148";
			break;
		case "ňː":
			result="014802D0";
			break;
		case "p":
			result="p";
			break;
		case "pː":
			result="p02D0";
			break;
		case "r":
			result="r";
			break;
		case "rː":
			result="r02D0";
			break;
		case "s":
			result="s";
			break;
		case "sː":
			result="s02D0";
			break;
		case "ʃ":
			result="0283";
			break;
		case "ʃː":
			result="028302D0";
			break;
		case "t":
			result="t";
			break;
		case "tː":
			result="t02D0";
			break;
		case "v":
			result="v";
			break;
		case "vː":
			result="v02D0";
			break;
		case "z":
			result="z";
			break;
		case "zː":
			result="z02D0";
			break;
		case "ʓ":
			result="0293";
			break;
		case "ʓː":
			result="029302D0";
			break;
		case "ʣ":
			result="02A3";
			break;
		case "ʣː":
			result="02A302D0";
			break;
		case "ʤ":
			result="02A4";
			break;
		case "ʤː":
			result="02A402D0";
			break;
		case "ʦ":
			result="02A6";
			break;
		case "ʦː":
			result="02A602D0";
			break;
		case "ʧ":
			result="02A7";
			break;
		case "ʧː":
			result="02A702D0";
			break;
		}
		
		if(result==null)
		{
			return null;
		}
		
		if(before!=null) //ja bija speciālais simbols sākumā, pievienot pārkonvertēto sākuma simbolu rezultātam
		{
			result=before+result;
		}
		
		if(after!=null) //pēc alternatīvas kā sākumā 
		{
			result+=after;
		}
		
		return result;
	}

	@Override
	public String[] tokenize(String word) {
		// TODO Auto-generated method stub
		return null;
	}

}
