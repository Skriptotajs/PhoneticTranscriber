/**
 * Original author: Mārcis Pinnis
*/
package phonetic_transcriber;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 Transcription rules list with searching and replacement functionality for getting Strings transcribed in the phonetic alphabet.
 */
public class RulesTranscription
{
	/**
	 A list of transcription rules.
	 The list is not linear. That means that rules are distributed in a two dimensional list.
	 The first dimension is the first letter of the variable "text".
	 The second dimension consists of sequential rules starting with that character.
	 */
	TranscriptionRulesElement _rules;
	/**
	 The file name of the rules file.
	 */
	String _rulesFileName;
	/**
	 List of meta rules.
	 */
	TranscriptionMetaRules _metaRules;

	/**
	 Constructor that sets the default values.
	 A necessity is the rules file, as the constructor also initializes rules.
	 * @throws Exception 
	 */
	public void InitTranscriptionRules(String rulesFileName, String metaRulesFileName) throws Exception
	{
		_metaRules=new TranscriptionMetaRules();
		_metaRules.InitTranscriptionMetaRules(metaRulesFileName);
		_rules = null;
		_rulesFileName = rulesFileName;
		//Call the initialization function
		InitializeRules();

	}
	/**
	 Destructor clears the memory.
	 */
	/*~TranscriptionRules()
	{
		DeleteRecursively(_rules);
	}*/

	/**
	 Function that recursively deletes all elements further of the element passed to the function.
	 Deletes also the element passed to the function.
	 */
	private void DeleteRecursively(TranscriptionRulesElement temp)
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
	 Adds a transcription rules text element to the list.
	 The function places the element in the right position in the list.
	 */
	private void AddTextElementToRulesList(TranscriptionRulesElement textElement)
	{
		//At first we need to validate whether the rule element has the minimum required data.
		if (textElement!=null && textElement._text.length()>0)
		{
			if (_rules==null)
			{
				//If this is the first rule in the list:
				_rules = textElement;
			}
			else
			{//If there is at lest one rule in the list.
				TranscriptionRulesElement iterator = _rules;
				boolean attached = false;
				//We iterate through rules.
				do
				{
					if (iterator._text.charAt(0)==textElement._text.charAt(0))
					{
						//If we have found a rule with the same first character, we have to iterate through the second dimension (same character rules).
						if (iterator._next!=null)
						{//When we have found the last rule in the list, we attach the new rule at the end.
							iterator = iterator._next;
						}
						else
						{
							iterator._next = textElement;
							attached = true;
						}
					}
					else if (iterator._nextCharElement!=null)
					{
						//The current rule is with a different first character, so we have to iterate through the first dimension (different first character rules).
						iterator = iterator._nextCharElement;
					}
					else
					{
						//If there are no rules with the same first character as in the new rule, we attach the new rule at the end of the first dimension rules.
						iterator._nextCharElement = textElement;
						attached = true;
					}
				}
				while (!attached);
			}
		}
	}
	/**
	 Initialization of rules.
	 Reads data from the rules data base.
	 Builds the rules list from the data in the data base.
	 * @throws Exception 
	 */
	private void InitializeRules() throws Exception
	{
		//To validate chars, we need access to String functions.
		//StringFunctions StringFunctions;
		char data;
		//Open an input stream to a rules xml file.
		FileInputStream fstream = new FileInputStream(this._rulesFileName);
		// Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		//Current tag name
		String tagName = "";
		//Current tag value (used only if the tag is supposed to have only value
		String tagValue = "";
		//Current tag closing tag name
		String closingTagName = "";
		//Right most data is used for Transcription element right side generation.
		//It is a pointer to the right most meta or unused text rules element of a text element.
		TranscriptionRulesElement rightMostMeta = null;
		//The actual rules text element that holds information about a single rule.
		TranscriptionRulesElement textElement = null;
		//As we will read a structured xml document, we need to know where in a rule we will be located.
		//To solve this problem, we will use a status variable:
		int positionInXmlDocument = 0;
		/*
		 The xml file structure is as follows:
		 <r>
		 *	 <d>
		 *		 <m>metaSymbol</m>
		 *		 <u>text</u>
		 *		 <t>text</t>
		 *		 <m>metaSymbol</m>
		 *		 <u>text</u>
		 *	 </d>
		 *	 <p>replacementText</p>
		 </r>
		 There can be an unlimited amount of <r> elements in the file.
		 There can be an unlimited amount of <m> or <u> elements before and after the <t> element.
		 There can be only one <t>, <d> and <p> element in a single rule.
		 No other tags are allowed!
		 *
		 The position variable gets changed after the previous character processing:
		 <1r>2
		 *	 <3d>4
		 *		 <5m>6metaSymbol<7/m>4
		 *		 <5u>6text<7/u>4
		 *		 <5t>8text<9/t>10
		 *		 <11m>12metaSymbol<13/m>10
		 *		 <11u>12text<13/u>10
		 *	 <11/d>14
		 *	 <15p>16replacementText<17/p>18
		 <19/r>0
		 *
		 At the beginning the position variable has the value 0.
		 */

		
			try
			{
				int s;
				while((s= br.read())!=-1)
				{
					data=(char)s;
					//While no EOF is found, we process the previously read character.
					if ("ertuioplkjhgfdsazcvbnmēūīāšģķļžčņĒŪĪĀŠĢĶĻŽČŅERTUIOPLKJHGFDSAZCVBNM0123456789_:#/Xx<>\"^*%?{=\\".contains(String.valueOf(data)))
					{
						switch (positionInXmlDocument)
						{//We check in which position in the current rule we are located.
							//The position is before <r> tag.
							case 0:
							//The position is before <d> tag.
							case 2:
							//The position is before <m>, <u> or <t> tag.
							case 4:
								if (data=='<')
								{
									//We found a tag opening character.
									if (tagName.equals(""))
									{
										tagName = "<";
									}
									//We are now in the tag <r>,<d>, <m>, <u> or <t>
									positionInXmlDocument++;
								}
								break;
							//We are in the <r> tag.
							case 1:
								//Basically we read till the end of the tag and look that the tag is strictly <r>.
								if (data=='r' && tagName.equals("<"))
								{
									tagName += data;
								}
								else if (data=='>' && tagName.equals("<r"))
								{
									//When we reach the <r> tag closing character, we advance to position 2.
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
									//When we reach the <d> tag closing character, we advance to position 4.
									tagName="";
									positionInXmlDocument++;
								}
								break;
							//We are int the <m>, <u> or <t> tag.
							case 5:
								//Basically we read till the end of the tag and look that the tag is strictly <m>, <u> or <t>.
								if ((data=='m' || data=='u' || data=='t') && tagName.equals("<"))
								{
									tagName += data;
								}
								else if (data=='>' && (tagName.equals("<m") || tagName.equals("<u")))
								{
									//If the tag is <m> or <u> we process it differently because there are allowed more than one <m> or <u> tags before a <t> tag.
									tagName += data;
									tagValue="";
									//We advance to the position 6.
									positionInXmlDocument = 6;
								}
								else if (data=='>' && tagName.equals("<t"))
								{
									//If the tag is <t>, we can not have any more <m> or <u> tags before the <t> tag.
									tagName += data;
									//So We advance to the position 8.
									positionInXmlDocument = 8;
									tagValue="";
								}
								break;
							//We are in a <u> or <m> tag value part.
							case 6:
								if (data!='<')
								{
									//We build up the value till we find a tag opening character.
									tagValue+=data;
								}
								else
								{
									//When we find a tag opening character, we advance to the position 7.
									closingTagName = "<";
									positionInXmlDocument++;
								}
								break;
							//We are in the </u> or </m> closing tag.
							case 7:
								if (data=='/' && closingTagName.equals("<"))
								{
									closingTagName+=data;
								}
								else if ((data=='m' || data=='u') && closingTagName.equals("</"))
								{
									closingTagName+=data;
								}
								else if (data=='>' && ((closingTagName.equals("</m") && tagName.equals("<m>")) || (closingTagName.equals("</u") && tagName.equals("<u>"))))
								{//When we find the closing character of the tag:
									if (textElement==null)
									{//We make a new rules text element, if none present.
										textElement = new TranscriptionRulesElement();
										textElement._type = TranscriptionRulesElementType.TranscriptionRulesTextType;
									}
									if (textElement._left==null)
									{//We make the first left side meta element, if none present.
										textElement._left = new TranscriptionRulesElement();
									}
									else
									{//We make a new left side meta element before the first one, if present.
										TranscriptionRulesElement tempElement = new TranscriptionRulesElement();
										tempElement._left = textElement._left;
										textElement._left = tempElement;
									}
									//We set the meta element text.
									textElement._left._text = tagValue;
									//And we set the type accordingly to the tag name.
									if (tagName.equals("<m>"))
									{
										textElement._left._type = TranscriptionRulesElementType.TranscriptionRulesMetaType;
									}
									else
									{
										textElement._left._type = TranscriptionRulesElementType.TranscriptionRulesUnusedTextType;
									}
									//We clear all the values for further use.
									tagValue="";
									tagName="";
									closingTagName="";
									//We advance to the position 4.
									positionInXmlDocument = 4;
								}
								break;
							//We are int the vale part of the <t> tag.
							case 8:
								if (data!='<')
								{
									//We build up the value till we find a tag opening character.
									tagValue+=data;
								}
								else
								{
									//When we find a tag opening character, we advance to the position 9.
									closingTagName = "<";
									positionInXmlDocument++;
								}
								break;
							//We are in the </t> closing tag.
							case 9:
								if (data=='/' && closingTagName.equals("<"))
								{
									closingTagName+=data;
								}
								else if (data=='t' && closingTagName.equals("</"))
								{
									closingTagName+=data;
								}
								else if (data=='>' && (closingTagName.equals("</t") && tagName.equals("<t>")))
								{//When we find the closing character of the tag:
									if (textElement==null)
									{//We make a new rules text element, if none present.
										textElement = new TranscriptionRulesElement();
									}
									//We set the text values and the type of the element.
									textElement._text = tagValue;
									textElement._type = TranscriptionRulesElementType.TranscriptionRulesTextType;
									//We clear all the values for further use.
									tagValue="";
									tagName="";
									closingTagName="";
									//We advance to the position 10.
									positionInXmlDocument = 10;
								}
								break;
							//We are in a position before <m>, <u> or <d> tags and we are past a <t> tag.
							case 10:
								if (data=='<')
								{
									//We found a tag opening character.
									if (tagName.equals(""))
									{
										tagName = "<";
									}
									//We are now in a <m>, <u> or <d> tag.
									positionInXmlDocument++;
								}
								break;
							//We are in a <m>, <u> or </d> tag.
							case 11:
								//Basically we read till the end of the tag and look that the tag is strictly <m>, <u> or </d>.
								if (((data=='m' || data=='u' || data=='/') && tagName.equals("<")) || (data=='d' && tagName.equals("</")))
								{
									tagName += data;
								}
								else if (data=='>' && (tagName.equals("<m") || tagName.equals("<u")))
								{
									//If the tag is <m> or <u>, we process it differently because there are allowed more than one <m> or <u> tags after a </t> and before a </d> tag.
									tagName += data;
									tagValue="";
									//We advance to the position 12.
									positionInXmlDocument++;
								}else if (data=='>' && tagName.equals("</d"))
								{
									//If we find a closing tag </d>, we end the processing of the definition part of the rule.
									tagName="";
									closingTagName="";
									//We advance to the position 14.
									positionInXmlDocument = 14;
									tagValue="";
								}
								break;
							//We are in the value part of the <m> or <u> tags.
							case 12:
								if (data!='<')
								{
									//We build up the value till we find a tag opening character.
									tagValue+=data;
								}
								else
								{
									//When we find a tag opening character, we advance to the position 13.
									closingTagName = "<";
									positionInXmlDocument++;
								}
								break;
							//We are in the </m> or </u> closing tag.
							case 13:
								if (data=='/' && closingTagName.equals("<"))
								{
									closingTagName+=data;
								}
								else if ((data=='m' || data=='u') && closingTagName.equals("</"))
								{
									closingTagName+=data;
								}
								else if (data=='>' && ((closingTagName.equals("</m") && tagName.equals("<m>")) || (closingTagName.equals("</u") && tagName.equals("<u>"))))
								{
									//When we find the closing character of the tag:
									if (textElement==null)
									{
										//We make a new rules text element, if none present.
										textElement = new TranscriptionRulesElement();
										textElement._type = TranscriptionRulesElementType.TranscriptionRulesTextType;
									}
									if (textElement._right==null)
									{
										//We make a new rules meta element to the right of the tet element, if none present.
										textElement._right = new TranscriptionRulesElement();
										rightMostMeta = textElement._right;
									}
									else
									{
										//We make a new rules meta element at he right most position of the text element,
										//if there already are some elements to the right of the text element.
										rightMostMeta._right = new TranscriptionRulesElement();
										rightMostMeta = rightMostMeta._right;
									}
									//We set the text value of the new element.
									rightMostMeta._text = tagValue;
									//We set the type of the new element acordingly to the tag name.
									if (tagName.equals("<m>"))
									{
										rightMostMeta._type = TranscriptionRulesElementType.TranscriptionRulesMetaType;
									}
									else
									{
										rightMostMeta._type = TranscriptionRulesElementType.TranscriptionRulesUnusedTextType;
									}
									//We clear all the values for further use.
									tagValue="";
									tagName="";
									closingTagName="";
									//We advance to the position 10.
									positionInXmlDocument = 10;
								}
								break;
							//We are located before a <p> tag.
							case 14:
								if (data=='<')
								{
									//We found a tag opening character.
									if (tagName.equals(""))
									{
										tagName = "<";
									}
									//We are now in the <p> tag.
									positionInXmlDocument++;
								}
								break;
							//We are in a <p> tag.
							case 15:
								//Basically we read till the end of the tag and look that the tag is strictly <p>.
								if (data=='p' && tagName.equals("<"))
								{
									tagName += data;
								}
								else if (data=='>' && tagName.equals("<p"))
								{
									//If we find the tag closing character and the tag name is <p>, we advance to the position 16.
									tagName += data;
									tagValue="";
									positionInXmlDocument++;
								}
								break;
							//We are in the value part of the <p> tag.
							case 16:
								if (data!='<')
								{
									//We build up the value till we find a tag opening character.
									tagValue+=data;
								}
								else
								{
									//When we find a tag opening character, we advance to the position 17.
									closingTagName = "<";
									positionInXmlDocument++;
								}
								break;
							//We are in a </p> tag.
							case 17:
								if (data=='/' && closingTagName.equals("<"))
								{
									closingTagName+=data;
								}
								else if (data=='p' && closingTagName.equals("</"))
								{
									closingTagName+=data;
								}
								else if (data=='>' && closingTagName.equals("</p") && tagName.equals("<p>"))
								{//When we find the tag closing character and the tag is a <p></p> tag:
									if (textElement!=null)
									{//We set the replacement text value in the rules text element.
										textElement._replacementText = tagValue;
									}
									//We clear all the values for further use.
									tagValue="";
									tagName="";
									closingTagName="";
									//We advance to the position 18.
									positionInXmlDocument++;
								}
								break;
							//We are before a </r> tag and after a </p> tag.
							case 18:
								if (data=='<')
								{
									if (tagName.equals(""))
									{
										 //We found a tag opening character.
										tagName = "<";
									}
									//We are now in the </r> tag.
									positionInXmlDocument++;
								}
								break;
							//We are in the </r> tag.
							case 19:
								if ((data=='r' && tagName.equals("</")) || (data=='/' && tagName.equals("<")))
								{
									tagName += data;
								}
								else if (data=='>' && tagName.equals("</r"))
								{//When we find the closing character of the tag and the tag is </r>:
									if(textElement!=null)
									{//We add a new rules element to the rules list.
										AddTextElementToRulesList(textElement);
										//We clear the pointers, so that they can not be misused.
										textElement = null;
										rightMostMeta = null;
									}
									//We clear other values for further use.
									tagName="";
									closingTagName="";
									//We return to the position 0 and try to read other rules.
									positionInXmlDocument = 0;
									tagValue="";
								}
								break;
						}
					}

				}//End of the loop (do while).
				br.close();
			}
			catch(Exception e)
			{
				br.close();
				//In case of an error while reading the file, rules are cleared and an exception is thrown!
				DeleteRecursively(_rules);
				throw new Exception("Error occurred while loading data from the transcription rules file \"" + _rulesFileName + "\".");
			}
		
	}

	/**
	 Finds the starting rule for the transcription.
	 The starting rule of a new position is always the rule with the appropriate starting character.
	 As the rules are dislocated into a two-dimensional list, we iterate through the first dimension to get the starting rule.
	 */
	private TranscriptionRulesElement FindRulesWithTheStartingCharacter(char charTofind)
	{
		boolean found = false;
		//Declaration of the rules iterator
		TranscriptionRulesElement probableRule = _rules;
		//Iteration through the first dimension of the rules list.
		while (probableRule!=null && !found)
		{
			if (probableRule._text.charAt(0)==charTofind)
			{
				//When a rule with the appropriate starting character is found, we end the iteration.
				found = true;
			}
			else
			{
				//If the current rule is not the correct one, we iterate further.
				probableRule = probableRule._nextCharElement;
			}
		}
		//Return the result. The result may also be null.
		return probableRule;
	}
	/**
	 Checks if the current rule in processing is the right one in the current String position.
	 * @throws Exception 
	 */
	private boolean IsRuleSuitable(String textToTranscribe,TranscriptionRulesElement rule, int positionInTheString) throws Exception
	{
		//If the position exceeds the limits of the source text, false is returned.
		if (positionInTheString>=textToTranscribe.length()||positionInTheString<0)
		{
			return false;
		}
		//Position copy is needed for iteration.
		int position = positionInTheString;
		//Indicator stating if allowable String in a meta rule was found successfully.
		boolean foundAllowableString = false;
		//The actual allowable String (from a meta rule).
		String allowableString = "";
		//Checking if the rule passed to the rule is not empty.
		if (rule!=null)
		{
			//If the rule text exceeds the length of the source text, false is returned.
			if (textToTranscribe.length()-position<rule._text.length())
			{
				return false;
			}
			//The rule text is compared to the text in the source text.
			for (int i=0;i<rule._text.length();i++)
			{
				//If a character doeas not match, false is returned.
				if (rule._text.charAt(i)!=textToTranscribe.charAt(position+i))
				{
					return false;
				}
			}
			//Position iterator in the source text is set after the rule text (for meta rule comparison to the right of the rule).
			position+=rule._text.length();
			//Rule iterator for meta rule comparison.
			TranscriptionRulesElement iterator = rule._right;
			//While there are meta rules to compare (iterator is not empty), iterator rule is processed.
			while (iterator!=null)
			{
				//If the iterator rule is an unused text rule:
				if (iterator._type==TranscriptionRulesElementType.TranscriptionRulesUnusedTextType)
				{
					//If the rule text exceeds the length of the source text (to the right), false is returned.
					if (textToTranscribe.length()-position<iterator._text.length())
					{
						return false;
					}
					//The rule text is compared to the text in the source text.
					for (int i=0;i<iterator._text.length();i++)
					{
						//If a character doeas not match, false is returned.
						if (iterator._text.charAt(i)!=textToTranscribe.charAt(position+i))
						{
							return false;
						}
					}
					position+=iterator._text.length();
				}
				//If the iterator rule is a meta rule:
				else if (iterator._type==TranscriptionRulesElementType.TranscriptionRulesMetaType)
				{
					//If the meta rule defines that ONE character is allowed:
					if (iterator._text.equals("?"))
					{
						//If there is at least one character left in the source text, position is increased.
						if (position<textToTranscribe.length())
						{
							position++;
						}
						//If there are no more characters left, false is returned.
						else
						{
							return false;
						}
					}
					//If the rule defines that there can not be any characters left in the source text:
					else if (iterator._text.equals("#"))
					{
						//If the position is at the end (at least after the last character) of the source text, no more rules to the right are processed.
						if (position>=textToTranscribe.length())
						{
							//After this meta element, no other elements are allowed
							break;
						}
						//If there are still some characters to the right of the position, the rule is not suitable.
						else
						{
							return false;
						}
					}
					//If the rule defines that there must be one or more characters to the right of the position:
					else if (iterator._text.equals("^"))
					{
						//If there is at least one character left to the right, no more rules to the right are processed.
						if (position<textToTranscribe.length())
						{
							break;
						}
						//If there are no characters left, the rule is not suitable.
						else
						{
							return false;
						}
					}
					//If the rule defines that there can be any amount of characters to the right of the position, no more rules to the right are processed.
					else if (iterator._text.equals("*"))
					{
						break;
					}
					//But if the rule defines that the text must contain a specific String, further processing is needed.
					else
					{
						//The meta rules list is set to the correct meta rule according to the currently processed rule.
						_metaRules.SetCurrentMetaElement(iterator._text);
						foundAllowableString = false;
						//Try to find an allowable String from the meta rule (found in the meta rules list) that matches the source text at the current position.
						while (_metaRules.HasTheCurrentMetaRuleMoreAllowableStrings()&&!foundAllowableString)
						{
							//Get the next allowable String from the meta rule.
							allowableString = _metaRules.NextAllowableStringInTheCurrentMetaRule();
							//If the length  of the allowable String exceeds the source text, continue with the next allowable String.
							if (textToTranscribe.length()-position<allowableString.length())
							{
								continue;
							}
							//Match the allowable String to the source text at the current position.
							for (int i=0;i<allowableString.length();i++)
							{
								//If a character does not match, continue with the next allowable String.
								if (allowableString.charAt(i)!=textToTranscribe.charAt(position+i))
								{
									break;
								}
								//If all characters are matched, a parameter is set that a suitable allowable String has been found.
								else if (allowableString.length()==i+1)
								{
									foundAllowableString = true;
								}
							}
						}
						//If an allowable String was not found, the rule is not suitable, therefore, false is returned.
						if (!foundAllowableString)
						{
							return false;
						}
						//The position iterator is set to the new position.
						position+=allowableString.length();
					}
				}
				//If the rule has not the right type, false is returned as we can not be sure what the meta rule was standing for.
				else
				{
					//A defective rule is not a valid rule! This should not happen!
					return false;
				}
				//Continue with the next rule (meta or unused text) to the right of the current.
				iterator = iterator._right;
			}
			//Now we have to check the left side of the rule and the transcription String.
			position=positionInTheString-1;
			iterator = rule._left;
			while (iterator!=null)
			{
				//If the iterator rule is an unused text rule:
				if (iterator._type==TranscriptionRulesElementType.TranscriptionRulesUnusedTextType)
				{
					//If the rule text exceeds the length of the source text (to the left), false is returned.
					if (position+1<iterator._text.length())
					{
						return false;
					}
					//The rule text is compared to the text in the source text.
					for (int i=0;i<iterator._text.length();i++)
					{
						//If a character doeas not match, false is returned.
						if (iterator._text.charAt(iterator._text.length()-1-i)!=textToTranscribe.charAt(position-i))
						{
							return false;
						}
					}
					position-=iterator._text.length();
				}
				//If the iterator rule is a meta rule:
				else if (iterator._type==TranscriptionRulesElementType.TranscriptionRulesMetaType)
				{
					//If the meta rule defines that ONE character is allowed:
					if (iterator._text.equals("?"))
					{
						//If there is at least one character left in the source text, position is decreased.
						if (position>=0)
						{
							position--;
						}
						//If there are no more characters left, false is returned.
						else
						{
							return false;
						}
					}
					//If the rule defines that there can not be any characters left in the source text:
					else if (iterator._text.equals("#"))
					{
						//If the position is at the beginning (at least before the first character) of the source text, no more rules to the left are processed.
						if (position<=-1)
						{
							//Before this meta element, no other elements are allowed
							break;
						}
						//If there are still some characters to the left of the position, the rule is not suitable.
						else
						{
							return false;
						}
					}
					//If the rule defines that there must be one or more characters to the left of the position:
					else if (iterator._text.equals("^"))
					{
						//If there is at least one character left to the left, no more rules to the left are processed.
						if (position>=0)
						{
							break;
						}
						//If there are no characters left, the rule is not suitable.
						else
						{
							return false;
						}
					}
					//If the rule defines that there can be any amount of characters to the left of the position, no more rules to the left are processed.
					else if (iterator._text.equals("*"))
					{
						break;
					}
					//But if the rule defines that the text must contain a specific String, further processing is needed.
					else
					{
						//The meta rules list is set to the correct meta rule according to the currently processed rule.
						_metaRules.SetCurrentMetaElement(iterator._text);
						foundAllowableString = false;
						//Try to find an allowable String from the meta rule (found in the meta rules list) that matches the source text at the current position (to the left of course).
						while (_metaRules.HasTheCurrentMetaRuleMoreAllowableStrings()&&!foundAllowableString)
						{
							//Get the next allowable String from the meta rule.
							allowableString = _metaRules.NextAllowableStringInTheCurrentMetaRule();
							//If the length  of the allowable String exceeds the source text, continue with the next allowable String.
							if (position+1<allowableString.length())
							{
								continue;
							}
							//Match the allowable String to the source text at the current position (matching direction - to the left).
							for (int i=0;i<allowableString.length();i++)
							{
								//If a character does not match, continue with the next allowable String.
								if (allowableString.charAt(allowableString.length()-1-i)!=textToTranscribe.charAt(position-i))
								{
									break;
								}
								//If all characters are matched, a parameter is set that a suitable allowable String has been found.
								else if (allowableString.length()==i+1)
								{
									foundAllowableString = true;
								}
							}
						}
						//If an allowable String was not found, the rule is not suitable, therefore, false is returned.
						if (!foundAllowableString)
						{
							return false;
						}
						//The position iterator is set to the new position.
						position-=allowableString.length();
					}
				}
				//If the rule has not the right type, false is returned as we can not be sure what the meta rule was standing for.
				else
				{
					//A defective rule is not a valid rule! This should not happen!
					return false;
				}
				//Continue with the next rule (meta or unused text) to the left of the current.
				iterator = iterator._left;
			}
		}
		else
		{
			//If the rule passed to the function is null, false is returned.
			return false;
		}
		//If this position is reached, the rule is suitable.
		return true;
	}

	/**
	 Transcribes a String into the phonetic alphabet.
	 * @throws Exception 
	 */
	public String TranscribeAString(String textToTranscribe) throws Exception
	{
		//Declaration of the resulting transcription.
		String resultingTranscription="";
		//Declaration of the source text index iterator.
		int positionInTheString = 0;
		//Indicator, whether a rule suitable in a position was found or not.
		boolean foundRuleForCurrentPosition;
		//Iterator through the rule library.
		TranscriptionRulesElement ruleIterator = null;
		//While the position iterator does not exceed the source text limits, rules are searched and the transcription is built up.
		while(positionInTheString<textToTranscribe.length())
		{
			foundRuleForCurrentPosition = false;
			char firstChar = textToTranscribe.charAt(positionInTheString);
			//As the rules library (list) is not linear, but is two dimensional, we try to find the right dimension in which to search for a suitable rule.
			ruleIterator = FindRulesWithTheStartingCharacter(firstChar);
			if (ruleIterator!=null)
			{
				//If a suitable dimension was found, iteration through this dimension starts to find the first matching rule.
				do
				{
					if (IsRuleSuitable(textToTranscribe,ruleIterator,positionInTheString))
					{
						//If the current rule matches the source text in the current position, parameter indicating a found rule is set to true.
						foundRuleForCurrentPosition = true;
					}
					else
					{
						//Otherwise, iteration continues.
						ruleIterator = ruleIterator._next;
					}
				}
				while (!foundRuleForCurrentPosition&&ruleIterator!=null);
				//If a suitable rule was not found, index is increased.
				//This means that the current character will be excluded from the transcription.
				if (!foundRuleForCurrentPosition)
				{
					positionInTheString++;
				}
				else
				{
					//In the case when a suitable rule was found, resulting transcription is built up.
					//If the resulting transcription is empty:
					if ((ruleIterator._replacementText.length()>0  && ruleIterator._replacementText.charAt(0)=='#') || resultingTranscription.equals(""))
					{
						resultingTranscription+=ruleIterator._replacementText;
					}
					//If the resulting transcription is not empty:
					else
					{
						resultingTranscription+="_"+ruleIterator._replacementText;
					}
					positionInTheString+=ruleIterator._text.length();
				}
			}
			else
			{
				throw new Exception("Unrecognized character \""+textToTranscribe.charAt(positionInTheString)+"\" in word \""+textToTranscribe+"\"");
			}
		}
		return resultingTranscription;
	}
};
