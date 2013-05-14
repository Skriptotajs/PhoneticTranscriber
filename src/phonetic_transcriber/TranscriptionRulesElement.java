/**
 * Original author: Mārcis Pinnis
*/
package phonetic_transcriber;

/**
Transcription rules element type describes what type of rule element is the current element.
*/
enum TranscriptionRulesElementType
{
   /**
    Indicates that the type has not been initiated.
    */
   TranscriptionRulesnullType,
   /**
    Indicates that the rules element is a meta element.
    */
   TranscriptionRulesMetaType,
   /**
    Indicates that the rules element is a static text element.
    */
   TranscriptionRulesUnusedTextType,
   /**
    Indicates that the rules element is a text element.
    */
   TranscriptionRulesTextType
};


/**
Transcription rules element holds information about a single rules element fragment, whether it is text element, meta element or a unused text element.
*/
public class TranscriptionRulesElement
{
	/**
	 Text describing the element.
	 */
	public String _text;
	/**
	 If the element is a rules text element, it will have a replacement text element.
	 */
	public String _replacementText;
	/**
	 Pointer to the next rules element.
	 */
	public TranscriptionRulesElement _next;
	/**
	 Pointer to the next rules element with a different first character;
	 */
	public TranscriptionRulesElement _nextCharElement;
	/**
	 Pointer to the element left of the current element.
	 */
	public TranscriptionRulesElement _left;
	/**
	 Pointer to the element right of the current element.
	 */
	public TranscriptionRulesElement _right;
	/**
	 Type of the current element.
	 */
	public TranscriptionRulesElementType _type;
	/**
	 Function that recursively deletes all elements further of the element passed to the function.
	 Deletes also the element passed to the function.
	 */
	public void DeleteRecursively(TranscriptionRulesElement temp)
	{/*
		if (temp._left!=null)
		{
			//Delete the left side.
			DeleteRecursively(temp._left);
		}
		if (temp._right!=null)
		{
			//Delete the right side.
			DeleteRecursively(temp._right);
		}
		if (temp._next!=null)
		{
			//Delete further elements.
			DeleteRecursively(temp._next);
		}
		if (temp._nextCharElement!=null)
		{
			//Delete further elements.
			DeleteRecursively(temp._nextCharElement);
		}
		delete temp;*/
	}
	/**
	 Constructor that sets the default values.
	 */
	public TranscriptionRulesElement()
	{
		_text="";
		_replacementText="";
		_next = null;
		_left = null;
		_right = null;
		_type = TranscriptionRulesElementType.TranscriptionRulesnullType;
		_nextCharElement = null;
	}
	/**
	 Sets the text of the current rule element.
	 */
	public void SetText(String text)
	{
		_text = text;
	}
	/**
	 Adds the left side of the current element.
	 */
	public void AddLeftSide (TranscriptionRulesElement left)
	{
		DeleteRecursively(_left);
		_left = left;
	}
	/**
	 Add the right side of the current element.
	 */
	public void AddRightSide(TranscriptionRulesElement right)
	{
		DeleteRecursively(_right);
		_right = right;
	}
};