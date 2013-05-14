package phonetic_character_converter;

public class AlphabeticCharacterConverter implements PhoneticCharacterConverter {

	@Override
	public String toIPAchar(String character) {
		String result=null;
		String before=null;
		String after=null;
		int length=character.length();
		if(length>1)//ja garāks par 1 simbolu, sākumā vai beigās var parādīties speciālie simboli
		{
			//speciālie simboli, kuri var parādīties beigās
			switch(character.charAt(length-1))
			{
			case 'x':
				after="̆";
				break;
			case '\\':
				after="ˑ";
				break;
			case 'q':
				after="ˀ";
				break;
			case '^':
				after="̯";
				break;
			case '+':
				after="̩";
				break;
			}
			
			
			//speciālie simboli, kuri var parādīties sākumā
			switch(character.charAt(0))
			{
			case '%':
				before="ˌ";
				break;
			case '"':
			case '”':
				before="ˈ";
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
		case "ii":
			result="iː";
			break;
		case "e":
			result="e";
			break;
		case "ee":
			result="eː";
			break;
		case "a":
			result="ɑ";
			break;
		case "aa":
			result="ɑː";
			break;
		case "E":
			result="æ";
			break;
		case "EE":
			result="æː";
			break;
		case "u":
			result="u";
		break;
		case "uu":
			result="uː";
			break;
		case "o":
			result="ɔ";
			break;
		case "oo":
			result="ɔː";
			break;
		case "iu":
			result="i͜u";
			break;
		case "ie":
			result="i͜e";
			break;
		case "ai":
			result="ɑ͜i";
			break;
		case "au":
			result="ɑ͜u";
			break;
		case "ei":
			result="e͜i";
			break;
		case "uo":
			result="u͜o";
			break;
		case "ui":
			result="u͜i";
			break;
		case "eu":
			result="e͜u";
			break;
		case "ou":
			result="ɔ͜u";
			break;
		case "oi":
			result="ɔ͜i";
			break;
		case "b":
			result="b";
			break;
		case "bb":
			result="bː";
			break;
		case "d":
			result="d";
			break;
		case "dd":
			result="dː";
			break;
		case "f":
			result="f";
			break;
		case "ff":
			result="fː";
			break;
		case "g":
			result="ɡ";
			break;
		case "gg":
			result="ɡː";
			break;
		case "G":
			result="ɟ";
			break;
		case "GG":
			result="ɟː";
			break;
		case "x":
			result="x";
			break;
		case "xx":
			result="xː";
			break;
		case "j":
			result="ʝ";
			break;
		case "jj":
			result="ʝː";
			break;
		case "k":
			result="k";
			break;
		case "kk":
			result="kː";
			break;
		case "K":
			result="c";
			break;
		case "KK":
			result="cː";
			break;
		case "l":
			result="l";
			break;
		case "ll":
			result="lː";
			break;
		case "L":
			result="ʎ";
			break;
		case "LL":
			result="ʎː";
			break;
		case "m":
			result="m";
			break;
		case "mm":
			result="mː";
			break;
		case "n":
			result="n";
			break;
		case "nn":
			result="nː";
			break;
		case "J":
			result="ɲ";
			break;
		case "JJ":
			result="ɲː";
			break;
		case "N":
			result="ň";
			break;
		case "NN":
			result="ňː";
			break;
		case "p":
			result="p";
			break;
		case "pp":
			result="pː";
			break;
		case "r":
			result="r";
			break;
		case "rr":
			result="rː";
			break;
		case "s":
			result="s";
			break;
		case "ss":
			result="sː";
			break;
		case "S":
			result="ʃ";
			break;
		case "SS":
			result="ʃː";
			break;
		case "t":
			result="t";
			break;
		case "tt":
			result="tː";
			break;
		case "v":
			result="v";
			break;
		case "vv":
			result="vː";
			break;
		case "z":
			result="z";
			break;
		case "zz":
			result="zː";
			break;
		case "Z":
			result="ʓ";
			break;
		case "ZZ":
			result="ʓː";
			break;
		case "dz":
			result="ʣ";
			break;
		case "dzdz":
			result="ʣː";
			break;
		case "dZ":
			result="ʤ";
			break;
		case "dZdZ":
			result="ʤː";
			break;
		case "ts":
			result="ʦ";
			break;
		case "tsts":
			result="ʦː";
			break;
		case "tS":
			result="ʧ";
			break;
		case "tStS":
			result="ʧː";
			break;
		}
		
		if(result==null)
		{
			return result;
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
				after="x";
				break;
			case 'ˑ':
				after="\\";
				break;
			case 'ˀ':
				after="q";
				break;
			case '̯':
				after="^";
				break;
			case '̩':
				after="+";
				break;
			}
			
			
			//speciālie simboli, kuri var parādīties sākumā
			switch(character.charAt(0))
			{
			case 'ˌ':
				before="%";
				break;
			case 'ˈ':
				before="\"";
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
			result="ii";
			break;
		case "e":
			result="e";
			break;
		case "eː":
			result="ee";
			break;
		case "ɑ":
			result="a";
			break;
		case "ɑː":
			result="aa";
			break;
		case "æ":
			result="E";
			break;
		case "æː":
			result="EE";
			break;
		case "u":
			result="u";
		break;
		case "uː":
			result="uu";
			break;
		case "ɔ":
		case "o":
			result="o";
			break;
		case "ɔː":
		case "oː":
			result="oo";
			break;
		case "i͜u":
			result="iu";
			break;
		case "i͜e":
			result="ie";
			break;
		case "ɑ͜i":
			result="ai";
			break;
		case "ɑ͜u":
			result="au";
			break;
		case "e͜i":
			result="ei";
			break;
		case "u͜o":
			result="uo";
			break;
		case "u͜i":
			result="ui";
			break;
		case "e͜u":
			result="eu";
			break;
		case "ɔ͜u":
		case "o͜u":
			result="ou";
			break;
		case "ɔ͜i":
		case "o͜i":
			result="oi";
			break;
		case "b":
			result="b";
			break;
		case "bː":
			result="bb";
			break;
		case "d":
			result="d";
			break;
		case "dː":
			result="dd";
			break;
		case "f":
			result="f";
			break;
		case "fː":
			result="ff";
			break;
		case "ɡ":
			result="g";
			break;
		case "ɡː":
			result="gg";
			break;
		case "ɟ":
			result="G";
			break;
		case "ɟː":
			result="GG";
			break;
		case "x":
			result="x";
			break;
		case "xː":
			result="xx";
			break;
		case "ʝ":
			result="j";
			break;
		case "ʝː":
			result="jj";
			break;
		case "k":
			result="k";
			break;
		case "kː":
			result="kk";
			break;
		case "c":
			result="K";
			break;
		case "cː":
			result="KK";
			break;
		case "l":
			result="l";
			break;
		case "lː":
			result="ll";
			break;
		case "ʎ":
			result="L";
			break;
		case "ʎː":
			result="LL";
			break;
		case "m":
			result="m";
			break;
		case "mː":
			result="mm";
			break;
		case "n":
			result="n";
			break;
		case "nː":
			result="nn";
			break;
		case "ɲ":
			result="J";
			break;
		case "ɲː":
			result="JJ";
			break;
		case "ň":
			result="N";
			break;
		case "ňː":
			result="NN";
			break;
		case "p":
			result="p";
			break;
		case "pː":
			result="pp";
			break;
		case "r":
			result="r";
			break;
		case "rː":
			result="rr";
			break;
		case "s":
			result="s";
			break;
		case "sː":
			result="ss";
			break;
		case "ʃ":
			result="S";
			break;
		case "ʃː":
			result="SS";
			break;
		case "t":
			result="t";
			break;
		case "tː":
			result="tt";
			break;
		case "v":
			result="v";
			break;
		case "vː":
			result="vv";
			break;
		case "z":
			result="z";
			break;
		case "zː":
			result="zz";
			break;
		case "ʓ":
			result="Z";
			break;
		case "ʓː":
			result="ZZ";
			break;
		case "ʣ":
			result="dz";
			break;
		case "ʣː":
			result="dzdz";
			break;
		case "ʤ":
			result="dZ";
			break;
		case "ʤː":
			result="dZdZ";
			break;
		case "ʦ":
			result="ts";
			break;
		case "ʦː":
			result="tsts";
			break;
		case "ʧ":
			result="tS";
			break;
		case "ʧː":
			result="tStS";
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
