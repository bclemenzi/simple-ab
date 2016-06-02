package com.nfbsoftware.util;

import java.util.ArrayList;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * <p>
 * This glorious Java class, details the best JDK 1.5 has to offer. In future
 * years this small piece of wonder will be looked upon as legendary.
 * </p>
 * <p>
 * Day after day, I am amazed by the beauty of this code. It really doesn't get
 * any better than this, it truly is a masterpiece in every definition of the
 * word. It truly is art in motion...an infinite living piece of our computer
 * science history. WOW, I just really can't get over it, so beautiful, it's
 * just so damn beautiful.
 * </p>
 * <p>
 * Simply amazing...it's like looking at the world through the eyes of a child;
 * much like enjoying the wind and the moon and the stars, with a soul
 * undefiled. There is only one more thing I could possibly say...and that is
 * "You're Welcome"
 * </p>
 *
 * @author Brendan Clemenzi 
 * @email brendan@clemenzi.com
 */
public class StringUtil
{
   /**
    * An empty string that is only constructed once for performance.
    */
   public final static String EMPTY_STRING = "";

   /**
    * table to convert a nibble to a hex character
    */
   public static char[] hexChar = {
		'0' , '1' , '2' , '3' ,
		'4' , '5' , '6' , '7' ,
		'8' , '9' , 'A' , 'B' ,
		'C' , 'D' , 'E' , 'F' };

   /**
    * Test if passed string <b>s</b> is <b>null</b> and return an empty
    * string if true.
    * @param s A string or null
    * @return Returns <b>s</b> if it is not null, otherwise returns empty
    * string.
    */
   public static String emptyIfNull(String s)
   {
       return s != null ? s : EMPTY_STRING;
   }

   /**
    * Test if passed string <b>s</b> is <b>null</b> and return the default
    * string passed in if true.
    * @param s A string or null
    * @return Returns <b>s</b> if it is not null, otherwise returns empty
    * string.
    */
   public static String replaceIfNull(String s, String defaultValue)
   {
       return (!isNullOrEmpty(s)) ? s : defaultValue;
   }

   /**
    * Test if passed string <b>s</b> is an empty string and return null
    * if true.
    * @param s A string or null
    * @return Returns <b>s</b> if it is not empty, otherwise returns null.
    */
   public static String nullIfEmpty(String s)
   {
       if (s != null)
       {
           // s = s.trim();
           if (s.length() == 0)
           {
               s = null;
           }
       }

       return s;
   }

   /**
    * Test if passed string <b>s</b> is an empty string (after being trimmed
    * of leading/trailing whitespace) and return null if true.
    * @param s A string or null
    * @return Returns <b>s</b> if it is not empty, otherwise returns null.
    */
   public static String nullIfEmptyTrim(String s)
   {
       if (s != null)
       {
           s = s.trim();
           if (s.length() == 0)
           {
               s = null;
           }
       }

       return s;
   }

   /**
    * Test if passed string <b>s</b> is an empty string or null.
    * @param s A string or null
    * @return If null or empty, return true, else return false.
    */
   public static boolean isNullOrEmpty(String s)
   {
       if ((s == null) || (s.length() == 0)) {
		return true;
	}

       return false;
   }

   /**
    * Test if passed string <b>s</b> is an empty string or null
    * (after being trimmed)
    * @param s A string or null
    * @return If null or empty, return true, else return false.
    */
   public static boolean isNullOrEmptyTrim(String s)
   {
       if (s == null) {
		return true;
	}

       s = s.trim();
       if (s.length() == 0) {
		return true;
	}

       return false;
   }

	/**
	 *
	 * @param str
	 * @param oldsubstr
	 * @param newsubstr
	 * @return
	 */
	public static String replaceSubstr(String str, String oldsubstr, String newsubstr)
	{
		int substrPos = 0;
		int startPos = 0;
		StringBuffer strBuf = new StringBuffer();

		while ((substrPos = str.indexOf(oldsubstr, startPos)) > -1)
		{
			strBuf.append(str.substring(startPos, substrPos));
			strBuf.append(newsubstr);
			startPos = substrPos + oldsubstr.length();
		}

		strBuf.append(str.substring(startPos));
		return strBuf.toString();
	}

	public static String[] split(String s, String sToken)
	{
		int iTokenLength=sToken.length();
		if (sToken == null || (iTokenLength) == 0){
			return new String[] {s};
		}

		int iCount=0;
		int iBegin=0;
		int iEnd;

		while((iEnd = s.indexOf(sToken, iBegin)) != -1) {
			iCount++;
			iBegin = iEnd + iTokenLength;
		}
		iCount++;

		// allocate an array to return the tokens,
		// we now know how big it should be
		String[] result = new String[iCount];

		// Scan s again, but this time pick out the tokens
		iCount = 0;
		iBegin = 0;
		while((iEnd = s.indexOf(sToken, iBegin)) != -1) {
			result[iCount] = (s.substring(iBegin, iEnd));
			iCount++;
			iBegin = iEnd + iTokenLength;
		}
		iEnd = s.length();
		result[iCount] = s.substring(iBegin, iEnd);

		return (result);
	}

	/**
	 * Replaces characters that are not allowed in a Java style
	 * string literal with their escape characters.
	 * Quote ("), single quote ('), new line (\n), carriage return (\r),
	 * and backslash (\), and tab (\t) are replaced.
	 *
	 * @param s String to be escaped
	 * @return  String
	 * @throws NullPointerException if s is null.
	 */
	public static String replaceJavaLiteral(String s){
		int length = s.length();
		int newLength = length;
		// first check for characters that migh
		// be dangerous and calculate a length
		// of the string that has escapes.
		for (int i=0; i<length; i++){
			char c = s.charAt(i);
			switch(c){
				case '\"':
				case '\'':
				case '\n':
				case '\r':
				case '\t':
				case '\\':{
					newLength += 1;
				} break;
			}
		}
		if (length == newLength){
			// nothing to escape in the string
			return s;
		}
		StringBuffer sb = new StringBuffer(newLength);
		for (int i=0; i<length; i++){
			char c = s.charAt(i);
			switch(c){
				case '\"':{
					sb.append("\\\"");
				} break;
				case '\'':{
					sb.append("\\\'");
				} break;
				case '\n':{
					sb.append("\\n");
				} break;
				case '\r':{
					sb.append("\\r");
				} break;
				case '\t':{
					sb.append("\\t");
				} break;
				case '\\':{
					sb.append("\\\\");
				} break;
				default: {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * If the first string is not null or empty it trims it
	 * else it returns the second string
	 * @param value
	 * @param other
	 * @return if not null or empty returns value else it returns other.
	 */
	public static String OnEmptyUseOther(String value, String other)
	{
	    //Is it empty?
	    if (isNullOrEmptyTrim(value))
	    {
	        return other;
	    }

	    return value.trim();
	}

	/**
	 * Used to add character padding before the string value.  Helps with form layouts.
	 *
	 * @param stringValue
	 * @param paddingChar
	 * @param paddingLength
	 * @return
	 *
	 * @author Brendan Clemenzi
	 */
    public static String stringPaddingBefore(String stringValue, String paddingChar, int paddingLength)
    {
        StringBuffer buffer = new StringBuffer(128);

        for (int i = 0, length = stringValue.length(); i < (paddingLength - length); i++)
        {
            buffer.append(paddingChar);
        }

        buffer.append(stringValue);

        return buffer.toString();
    }

    /**
     * Used to add character padding after the string value.  Helps with form layouts.
     *
     * @param stringValue
     * @param paddingChar
     * @param paddingLength
     * @return
     *
     * @author Brendan Clemenzi
     */
    public static String stringPaddingAfter(String stringValue, String paddingChar, int paddingLength)
    {
        StringBuffer buffer = new StringBuffer(128);
        buffer.append(stringValue);

        for (int i = 0, length = stringValue.length(); i < (paddingLength - length); i++)
        {
            buffer.append(paddingChar);
        }

        return buffer.toString();
    }

    /**
     * Parses a <B>tokenString</B> into a String array of sub-strings
     * which are separated by characters in the <b>separators</b> string.
     * There is no escape sequence in the input string.
     * @param tokenString A string containing sub-strings separated by zero
     * or more separator characters. Can't be null. <br>
     * Example: "A/B,C.D"
     * @param separators A string of characters which separate the
     * sub-strings in the tokenString. Can be null or empty.<br>
     * Example: "/\\|,"
     * <i>NOTE:</i> Multiple consecutive separators are treated as a single
     * separator which means that you will never have "empty" entries in
     * the output array.
     * <i>NOTE:</i> If there is only one separator character, this function is
     * <b>much</b> faster.
     * @return A string array of each of the non-empty sub-strings in the path.
     */
    public static String[] parseString(String tokenString, String separators)
    {
        // Get the character array from the string for fast index lookups.
        char[] tokenStringChars = tokenString.toCharArray();
        int len = tokenStringChars.length;
        // Guess at the initial capacity of the array to be one third of
        // the total length of the input string.
        ArrayList<String> tokens = new ArrayList<String>((len/3)+1);

        // If there are no separators, then just return the input string.
        if ((separators == null) || (separators.length() == 0))
        {
            // Add entire input string as one token unless it is empty.
            if (tokenString.length() > 0)
            {
                tokens.add(tokenString);
            }
            String[] tokenArray = new String[tokens.size()];
            tokens.toArray(tokenArray);
            return tokenArray;
        }

        // Speed optimization if there is only a single separator.
        if (separators.length() == 1)
        {
            int i1=0, i2;
            char separatorChar = separators.charAt(0);

            // Strip leading separators
            while ((i1 < len) && (tokenStringChars[i1] == separatorChar)) {
				i1++;
			}

            while (i1 < len)
            {
                i2 = tokenString.indexOf(separatorChar, i1);
                if (i2 < 0)
                {
                    tokens.add(tokenString.substring(i1));
                    break;
                }

                tokens.add(tokenString.substring(i1, i2));

                i1 = i2 + 1;

                // Strip consecutive separators
                while ((i1 < len) && (tokenStringChars[i1] == separatorChar)) {
					i1++;
				}
            }
        }
        else // Multiple separators
        {
            int i1=0, i2;

            // Strip leading separators
            while ((i1 < len) &&
                   (separators.indexOf(tokenStringChars[i1]) >= 0)) {
				i1++;
			}

            while (i1 < len)
            {
                i2 = i1;

                while ((i2 < len) &&
                       (separators.indexOf(tokenStringChars[i2]) < 0))
                {
                    i2++;
                }

                tokens.add(tokenString.substring(i1, i2));

                i1 = i2 + 1;

                // Strip consecutive separators
                while ((i1 < len) &&
                       (separators.indexOf(tokenStringChars[i1]) >= 0)) {
					i1++;
				}
            }
        }

        String[] tokenArray = new String[tokens.size()];
        if (tokens.size() > 0) {
			tokens.toArray(tokenArray);
		}

        return tokenArray;
    }

	/**
	 *
	 * @param b
	 * @return
	 */
	public static String toHexString( byte[] b )
	{
		StringBuffer sb = new StringBuffer( b.length * 2 );

		for ( int i=0 ; i<b.length ; i++ )
		{
			// look up high nibble char
			sb.append( hexChar [ ( b[ i] & 0xf0 ) >>> 4 ] ) ;

			// look up low nibble char
			sb.append( hexChar [ b[ i] & 0x0f ] ) ;
		}

		return sb.toString();
	}

    /**
     *
     * @param inputString
     * @return
     */
    public static String stringPreview(String inputString)
    {
        String tmpString = "";

        if(inputString.length() >= 20)
        {
            tmpString = inputString.substring(0, 19);
        }
        else
        {
            tmpString = inputString.substring(0, inputString.length());
        }

        return tmpString;
    }

    /**
     * Check for a string, if an exception is thrown return default value.
     *
     * @param s
     * @param defaultValue
     * @return
     */
    public static String replaceIfNullOrError(String s, String defaultValue)
    {
        String tmpString = EMPTY_STRING;

        try
        {
            tmpString = replaceIfNull(s, defaultValue);
        }
        catch(Exception e)
        {
            tmpString = defaultValue;
        }

        return tmpString;
    }

    /**
     * Strip out HTML tags.  Leave plain/text content.  HREFs in links will be removed
     *
     * @param html
     * @return
     */
	public static String stripHTML (String html)
	{
		if (html == null || html.isEmpty())
		{
			return "";
		}

		if (html.toLowerCase().contains("<body") && html.toLowerCase().contains("</body>"))
		{
			int beginIndex = html.toLowerCase().indexOf("<body");
			int endIndex = html.toLowerCase().indexOf("</body>");
			endIndex += "</body>".length();
			html = html.substring(beginIndex, endIndex);
		}

		for (String tag : new String[]{"style", "script"})
		{
			int tries = 10;
			while (tries-- > 0 && html.toLowerCase().contains("<" + tag))
			{
				html = removeHTMLBlock(html, tag);
			}
		}

		html = html.replaceAll("&#8226;", "");
		html = html.replaceAll("&bull;", "");

		// OK.  I should have "simple" markup left.
		// Since I do not care about HREFs or IMG sources here,
		// I can be draconian in my clensing
		html = html.replaceAll("</?\\w+[^><]*>", " ");
		html = StringEscapeUtils.unescapeHtml(html);
		return html;
	}

	public static String removeHTMLBlock(String html, String tag)
	{
		if (html.toLowerCase().contains("<" + tag) && html.toLowerCase().contains("</" + tag))
		{
				int beginIndex = html.toLowerCase().indexOf("<" + tag);
				int endIndex = html.toLowerCase().indexOf("</" + tag);
				endIndex += ("</" + tag + ">").length();
				html = html.substring(0, beginIndex) + html.substring(endIndex);
		}

		return html;
	}
}
