/**
 * Original author: Mārcis Pinnis
*/
package phonetic_transcriber;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Class to store String, String pairs, from which one is the key and other is the value.
 */
public class StringDictionary {
	
	
	
	/**
	 * A map of two Strings holding the full word text and the name of the audio file representing the word.
	 */
	HashMap<String, String> _dictionary;
	

	public StringDictionary()
	{
		_dictionary=new HashMap<String,String>();
	}
	
	/**
	 * Inserts an element into the String-String dictionary.
	 */
	public void Insert(String key, String value)
	{
		_dictionary.put(key,value);
	}
	/**
	 * Finds a value for a String key ... if not found, returns an empty String.
	 */
	public String GetValue(String key)
	{
		return _dictionary.get(key);
	}
	/**
	 * All the dictionary is printed to the console.
	 */
	public void PrintDataToConsole()
	{
		for(Entry<String, String> item : _dictionary.entrySet())
		{
			System.out.println(item.toString());
		}
	}
}
