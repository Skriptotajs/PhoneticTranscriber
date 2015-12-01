package helpers;

public class ArrayHelper {

	
	public static String implode(Object[] tokens, String seperator)
	{
		StringBuilder result=new StringBuilder();
		int i=0;
		for(i=0; i<tokens.length; i++)
		{
			
			result.append(tokens[i].toString());
			
			if(seperator!=null && (i+1)<tokens.length)
			{
				result.append(seperator);
			}
		}
		
		return result.toString();
	}
}
