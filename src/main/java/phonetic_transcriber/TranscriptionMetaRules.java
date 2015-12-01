/**
 * Original author: Mārcis Pinnis
*/
package phonetic_transcriber;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class TranscriptionMetaRules {
	/*
	 A list of transcription meta rules.
	 The list is linear and each element in the list contains an other list (StringList).
	 */
	TranscriptionMetaRulesElement _metaRules;
	/*
	 Poiter to the currently processed meta rule
	 */
	TranscriptionMetaRulesElement _currentRule;
	/*
	 The file name of the meta rules file.
	 */
	String _metaRulesFileName;
	/*
	 Constructor that sets the default values.
	 A necessity is the meta rules file, as the constructor also initializes meta rules.
	 */
	public void InitTranscriptionMetaRules(String metaRulesFileName) throws Exception
	{
		_currentRule = null;
		_metaRules = null;
		_metaRulesFileName = metaRulesFileName;
		//Call the initialization function
		InitializeMetaRules();

	}
	/*
	 Clears the memory and resets the list iterator.
	 */
	public void DeleteMetaRules()
	{/*
		TranscriptionMetaRulesElement tempElem;
		while (_metaRules!=null)
		{
			tempElem = _metaRules._next;
			delete _metaRules;
			_metaRules = tempElem;
		}
		_currentRule = null;*/
	}
	/*
	 Destructor clears the memory.
	 */
	/*~TranscriptionMetaRules()
	{
		DeleteMetaRules();
	}*/

	/*
	 Adds a transcription meta rules element to the list.
	 The element is added at the beginning of the list.
	 */
	private void AddElementToMetaRulesList(TranscriptionMetaRulesElement singleMetaRule)
	{
		singleMetaRule._next = _metaRules;
		_metaRules = singleMetaRule;
	}
	/*
	 Initialization of meta rules.
	 Reads data from the meta rules data base.
	 Builds the meta rules list from the data in the data base.
	 */
	private void InitializeMetaRules() throws Exception
	{
		//To validate chars, we need access to String functions.

		char data;
		//Open an input stream to a rules xml file.
		FileInputStream fstream = new FileInputStream(_metaRulesFileName);
		// Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		//Current tag name
		String tagName = "";
		//Current tag value (used only if the tag is supposed to have only value
		String tagValue = "";
		//Current tag closing tag name
		String closingTagName = "";
		//A pointer for meta rule elements is needed to allocate memory.
		TranscriptionMetaRulesElement singleRule = null;
		//As we will read a structured xml document, we need to know where in a meta rule we will be located.
		//To solve this problem, we will use a status variable:
		int positionInXmlDocument = 0;
		/*
		 The xml file structure is as follows:
		 <m>
			 <d>d</d>
			 <t>z</t>
			 <t>ž</t>
		 </m>
		 There can be an unlimited amount of <m> elements in the file.
		 There can be an unlimited amount of <t> elements after the <d> element in a single meta rule.
		 There can be only one <d> element in a single rule.
		 No other tags are allowed!
		 
		 The position variable gets changed after the previous character processing:
		 <1m>2
			 <3d>4d<5/d>6
			 <7t>8z<9/t>6
			 <7t>8ž<9/t>6
		 <7/m>0
		 
		 At the beginning the position variable has the value 0.
		 */

			try
			{
				//If we successfully opened the meta rules file, we can start reading the file.
				int s;
				while((s= br.read())!=-1)
				{
					data=(char)s;
					//While no EOF is found, we process the previously read character.
					if ("ertuioplkjhgfdsazcvbnmēūīāšģķļžčņĒŪĪĀŠĢĶĻŽČŅERTUIOPLKJHGFDSAZCVBNM0123456789_:#/Xx<>\"^*%?{=\\".contains(String.valueOf(data)))
					{
						switch (positionInXmlDocument)
						{//We check in which position in the current meta rule we are located.
							//The position is before <m> tag.
							case 0:
							//The position is before <d> tag.
							case 2:
								if (data=='<')
								{
									//We found a tag opening character.
									if (tagName.equals(""))
									{
										tagName = "<";
									}
									//We are now in the tag <m> or <d>
									positionInXmlDocument++;
								}
								break;
							//We are in the <m> tag.
							case 1:
								//Basically we read till the end of the tag and look that the tag is strictly <m>.
								if (data=='m' && tagName.equals("<"))
								{
									tagName += data;
								}
								else if (data=='>' && tagName.equals("<m"))
								{
									//When we reach the <m> tag closing character, we advance to position 2.
									tagName="";
									positionInXmlDocument++;
								}
								break;
							//We are in the <d> tag.
							case 3:
								//Basically we read till the end of the tag and look that the tag is strictly <d>.
								if (data=='d' && tagName.equals("<"))
								{
									tagName += data;
								}
								else if (data=='>' && tagName.equals("<d"))
								{
									tagName+=data;
									tagValue="";
									//When we reach the <d> tag closing character, we advance to position 4.
									positionInXmlDocument++;
								}
								break;
							//We are in the <d> tag value part.
							case 4:
								if (data!='<')
								{
									//We build up the value till we find a tag opening character.
									tagValue+=data;
								}
								else
								{
									//When we find a tag opening character, we advance to the position 5.
									closingTagName = "<";
									positionInXmlDocument++;
								}
								break;
							//We are int the </d> closing tag.
							case 5:
								if (data=='/' && closingTagName.equals("<"))
								{
									closingTagName+=data;
								}
								else if (data=='d' && closingTagName.equals("</"))
								{
									closingTagName+=data;
								}
								else if (data=='>' && closingTagName.equals("</d") && tagName.equals("<d>"))
								{//When we find the closing character of the tag:
									if (singleRule==null)
									{//We make a new rules text element, if none present.
										singleRule = new TranscriptionMetaRulesElement();
									}
									//We set the meta rule definition String.
									singleRule._text = tagValue;
									//We clear all the values for further use.
									tagValue="";
									tagName="";
									closingTagName="";
									//We advance to the position 6.
									positionInXmlDocument++;
								}
								break;
							//We are before the <t> tag.
							case 6:
								if (data=='<')
								{
									//We found a tag opening character.
									if (tagName.equals(""))
									{
										tagName = "<";
									}
									//We are now in the tag <t> or in the </m> closing tag.
									positionInXmlDocument++;
								}
								break;
							//We are in the <t> tag or in the </m> closing tag.
							case 7:
								//Basically we read till the end of the tag and look that the tag is strictly <m>, <u> or </d>.
								if (((data=='t' || data=='/') && tagName.equals("<")) || (data=='m' && tagName.equals("</")))
								{
									tagName += data;
								}
								else if (data=='>' && tagName.equals("<t"))
								{
									//If the tag is <t>, we process it differently because there are allowed more than one <t> tags after a </d> and before a </m> tag.
									tagName += data;
									tagValue="";
									//We advance to the position 8.
									positionInXmlDocument++;
								}else if (data=='>' && tagName.equals("</m"))
								{
									//If we find a closing tag </m>, we have reached the end of the current meta rule.
									if(singleRule!=null)
									{//We add a new meta rules element to the meta rules list.
										AddElementToMetaRulesList(singleRule);
										//We clear the pointer, so that it can not be misused.
										singleRule = null;
									}
									tagName="";
									closingTagName="";
									//We advance to the position 0.
									positionInXmlDocument = 0;
									tagValue="";
								}
								break;
							//We are in the <t> tag value part.
							case 8:
								if (data!='<')
								{
									//We build up the value till we find a tag opening character.
									tagValue+=data;
								}
								else
								{
									//When we find a tag opening character, we advance to the position 5.
									closingTagName = "<";
									positionInXmlDocument++;
								}
								break;
							//We are in a </t> closing tag.
							case 9:
								if (data=='/' && closingTagName.equals("<"))
								{
									closingTagName+=data;
								}
								else if (data=='t' && closingTagName.equals("</"))
								{
									closingTagName+=data;
								}
								else if (data=='>' && closingTagName.equals("</t") && tagName.equals("<t>"))
								{
									//When we find the closing character of the tag:
									if (singleRule!=null)
									{
										//If the meta rules element is not null then we add the allowed String to the meta rules allowed String list.
										singleRule.AddAllowedString(tagValue);
									}
									//We clear all the values for further use.
									tagValue="";
									tagName="";
									closingTagName="";
									//We advance to the position 10.
									positionInXmlDocument = 6;
								}
								break;
						}
					}
					//Before the loop ends, we read a new character from the input.

				}//End of the loop (do while).
				br.close();
			}
			catch(Exception e)
			{
				DeleteMetaRules();
				br.close();
				throw new Exception("Error occurred while loading data from the transcription meta rules file \"" + _metaRulesFileName + "\".");
			}
	}

	/*
	 Sets the currently processed meta element
	 */
	public void SetCurrentMetaElement(String metaString)
	{
		//Declaration of an iterator through the meta rules list.
		TranscriptionMetaRulesElement rulesIterator = _metaRules;
		//We set the current element pointer to null, to ensure that when no suitable meta rule is found, we set the current element to none.
		_currentRule = null;
		//Iteration through the meta rules
		while (rulesIterator!=null)
		{
			if (rulesIterator._text.equals(metaString))
			{
				//If a suitable rule is found, we set the current rule and reset the rules allowable String list.
				_currentRule = rulesIterator;
				_currentRule._allowedStringList.ResetCurrent();
				break;
			}
			else
			{
				//We iterate further till we find a suitable meta rule or there are no meta rules left.
				rulesIterator = rulesIterator._next;
			}
		}
	}
	/*
	 Checks if the current meta rule String list has more elements.
	 */
	public boolean HasTheCurrentMetaRuleMoreAllowableStrings ()
	{
		if (_currentRule==null)
		{
			//If the current element is not set, we return false.
			return false;
		}
		else
		{
			return _currentRule._allowedStringList.HasNext();
		}
	}
	/*
	 Gets the next element in the current element allowable String list.
	 If there are no elements left or the current element pointer is not set to an element, an exception is raised.
	 */
	public String NextAllowableStringInTheCurrentMetaRule() throws Exception
	{
		if (_currentRule == null)
		{
			//If the current element pointer is not set to an element.
			throw new Exception("Try checking, if there are any allowable Strings left, first. The current element pointer is not even set to an element.");
		}
		else
		{
			if (_currentRule._allowedStringList.HasNext())
			{
				//If the next element is found, we return it.
				return _currentRule._allowedStringList.Next();
			}
			else
			{
				//If there are no further elements, we raise an exception.
				throw new Exception("Try checking, if there are any allowable Strings left, first. The list has no more elements left");
			}
		}
	}


}
