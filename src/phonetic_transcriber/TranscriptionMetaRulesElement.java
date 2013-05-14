/**
 * Original author: Mārcis Pinnis
*/
package phonetic_transcriber;

public class TranscriptionMetaRulesElement {
	/**
	 Text describing the meta rule element.
	 It is the definition of the meta rule.
	 */
	public String _text;
	/**
	 The allowed String list contains all the Strings that describe the current meta rule.
	 */
	public StringList _allowedStringList;
	/**
	 Pointer to the next meta rules element.
	 */
	public TranscriptionMetaRulesElement _next;
	/**
	 Constructor that sets the default values.
	 */
	public TranscriptionMetaRulesElement()
	{
		_text="";
		_next = null;
		_allowedStringList=new StringList();
	}
	/**
	 Sets the text of the current meta rule element.
	 */
	public void SetText(String text)
	{
		_text = text;
	}
	/**
	 Adds a new allowed String to the allowed String list.
	 */
	public void AddAllowedString (String allowedString)
	{
		_allowedStringList.Add(allowedString);
		_allowedStringList.ResetCurrent();
	}

}
