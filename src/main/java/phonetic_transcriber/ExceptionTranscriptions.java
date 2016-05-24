/**
 * Original author: Mārcis Pinnis
*/
package phonetic_transcriber;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;


/**
 * Full word dictionary (class) for a DB interface.
 */
public class ExceptionTranscriptions
{
	/**
	 * File name of the exception dictionary.
	 */
	String _exceptionFileName;
	/**
	 * Dictionary object.
	 */
	StringDictionary _exceptions;
	/**
	 * Constructor.
	 * @throws Exception 
	 */
	public void InitExceptionTranscriptions(String fileName) throws Exception
	{
		_exceptionFileName = fileName;
		_exceptions = new StringDictionary();
		ReadData();
	}

	/**
	 * Read data from the data base.
	 * @throws Exception 
	 */
	private void ReadData() throws Exception
	{
		//As an exception could occur in the loading process, an exception handler is used.
		String key="", value="";
		try
		{
            Scanner sc;
            InputStream stream = getClass().getClassLoader().getResourceAsStream(this._exceptionFileName);
            if (stream != null) {
                sc = new Scanner(stream);
            } else {
                sc = new Scanner(new File("dist/PhoneticTranscriber/" + this._exceptionFileName));
            }
			while (sc.hasNext())
			{
				key=sc.next();
				if(key.contains("_"))
				{
					throw new Exception("Invalid key - "+key);
				}
				value=sc.next();
				if (key!="" && key!="\0")
				{
					_exceptions.Insert(key, value);
				}
			}
			sc.close();
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
	/**
	 * Print all dictionary data to the console.
	 */
	public void PrintDataToConsole()
	{
		System.out.println();
		System.out.println("Printing FullWordDictionary contents...");
		_exceptions.PrintDataToConsole();
	}
	/**
	 * Finds a value for a String key ... if not found, returns an empty String.
	 */
	public String GetValue(String key)
	{
		return _exceptions.GetValue(key);
	}
};
