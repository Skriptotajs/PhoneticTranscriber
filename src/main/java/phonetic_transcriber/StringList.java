/**
 * Original author: Mārcis Pinnis
*/
package phonetic_transcriber;

/**
 * A String list class.
 * Used for data storage purposes.
*/
public class StringList{

	/**
	 * Ponter to the firs element in the list.
	 */
	private StringListElement _first;
	/**
	 * Pointer to the last element in the list.
	 */
	private StringListElement _last;
	/**
	 * Pinter to the current element in the list (list iterator).
	 */
	private StringListElement _current;
	/**
	 * Length of the list (count of elements).
	 */
	private int _length;
	/**
	 * Current index. Starts from one, when the current element is set to the first element.
	 */
	private int _currentIndex;

	/**
	 * Gets the length of the list.
	 */
	public int Length()
	{
		return _length;
	}
	/**
	 * Checks if the list has more elements.
	 */
	public boolean HasNext()
	{
		if (_length>_currentIndex)
		{
			return true;
		}
		return false;
	}
	/**
	 * Adds a new element to the list.
	 */
	public void Add ( String value )
	{
		//The iterator is reset when adding new elements.
		ResetCurrent();
		if (_first == null) {
			_first = new StringListElement (value);
			_last = _first;
		}
		else {
			_last._next= new StringListElement (value);
			_last = _last._next;
		}
		_length++;
	}
	/**
	 * Prints the current element to the console.
	 * Each printing slows down performance so it is important to restrict printing wherever possible.
	 */
	public void PrintCurrent ()
	{
		if (_current!=null)
		{
			System.out.println(_current._value);
		}
	}
	/**
	 * Deletes all elements in the list.
	 */
	
	public void DeleteList ()
	{/*
		StringListElement next;
		while (_first!=null) {
				next=_first._next;
				delete _first;
				_first=next;
		 }
		 _first = null;
		 _last = null;
		 _current = null;
		 _length = 0;*/
	}
	/**
	 * Destructor - clears the memory.
	 */
	/*~StringList()
	{
		DeleteList();
	}*/
	/**
	 * Resets the iterator to the start of the list (before the first element).
	 */
	public void ResetCurrent()
	{
		_current = null;
		_currentIndex = 0;
	}
	/**
	 * Gets the next element in the list.
	 */
	public String Next()
	{
		//When the current index is before the first element:
		if (_current == null && _currentIndex<_length)
		{
			//If the list has an element left:
			if (_first != null)
			{
				_current = _first;
				_currentIndex++;
				return _current._value;
			}
			else
			{
				//If the list has no more elements left, null is returned.
				return null;
			}
		}
		//If the current index is not before the first element:
		else if (_currentIndex<_length)
		{
			//A new current element is set:
			_current = _current._next;
			_currentIndex++;
			//If the new element is not null, the new value is returned.
			if (_current != null)
			{
				return _current._value;
			}
			else
			{
				//If the new element is null, null is also returned.
				_currentIndex = _length;
				return null;
			}
		}
		else
		{
			//In all other cases, null is returned.
			return null;
		}
	}
	/**
	 * Gets the current element value from the list.
	 */
	public String Current()
	{
		//If the current element is set to an existing element, its value is returned.
		if (_current != null)
		{
			return _current._value;
		}
		else
		{
			//In other cases null is returned.
			return null;
		}
	}
	/**
	 * Constructor sets the starting parameters.
	 */
	public StringList()
	{
		_first = null;
		_last = null;
		_current = null;
		_length = 0;
		_currentIndex = 0;
	}
};

/**
 * Class that represents a single element in the StringList class.
 */
class StringListElement{
	/**
	 * Value of the element.
	 */
	public String _value;
	/**
	 * Pointer to the next element in the class.
	 */
	public StringListElement _next;
	/**
	 * Constructor that sets the starting parameters of the element.
	 */
	public StringListElement (String value)
	{
		_value = value;
		_next = null;
	}
};
