package helpers;

public class ArrayHelper {

	
	public static String implode(String[] tokens, String seperator)
	{
		StringBuilder result=new StringBuilder();
		int i=0;
		for(String token : tokens)
		{
			i++;
			
			result.append(token);
			
			if(seperator!=null && i<tokens.length)
			{
				result.append(seperator);
			}
		}
		
		return result.toString();
	}
}
