package com.caituo.plugin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransitionUtils {

	/*
	* 	^[1-9]\d*|0$　  //匹配非负整数（正整数 + 0）
	* 	^[1-9]\d*$　 　 //匹配正整数
	*
	* */

    private static final Pattern CJK_ANS = Pattern.compile("([\\p{InHiragana}\\p{InKatakana}\\p{InBopomofo}\\p{InCJKCompatibilityIdeographs}\\p{InCJKUnifiedIdeographs}])([a-z0-9`~@\\$%\\^&\\*\\-_\\+=\\|\\\\/])", 2);
	private static final Pattern ANS_CJK = Pattern.compile("([a-z0-9`~!\\$%\\^&\\*\\-_\\+=\\|\\\\;:,\\./\\?])([\\p{InHiragana}\\p{InKatakana}\\p{InBopomofo}\\p{InCJKCompatibilityIdeographs}\\p{InCJKUnifiedIdeographs}])", 2);
	private static final Pattern CJK_QUOTE = Pattern.compile("([\\p{InHiragana}\\p{InKatakana}\\p{InBopomofo}\\p{InCJKCompatibilityIdeographs}\\p{InCJKUnifiedIdeographs}])([\"'])");
	private static final Pattern QUOTE_CJK = Pattern.compile("([\"'])([\\p{InHiragana}\\p{InKatakana}\\p{InBopomofo}\\p{InCJKCompatibilityIdeographs}\\p{InCJKUnifiedIdeographs}])");
	private static final Pattern FIX_QUOTE = Pattern.compile("([\"'])(\\s*)(.+?)(\\s*)([\"'])");
	private static final Pattern CJK_BRACKET_CJK = Pattern.compile("([\\p{InHiragana}\\p{InKatakana}\\p{InBopomofo}\\p{InCJKCompatibilityIdeographs}\\p{InCJKUnifiedIdeographs}])([\\({\\[]+(.*?)[\\)}\\]]+)([\\p{InHiragana}\\p{InKatakana}\\p{InBopomofo}\\p{InCJKCompatibilityIdeographs}\\p{InCJKUnifiedIdeographs}])");
	private static final Pattern CJK_BRACKET = Pattern.compile("([\\p{InHiragana}\\p{InKatakana}\\p{InBopomofo}\\p{InCJKCompatibilityIdeographs}\\p{InCJKUnifiedIdeographs}])([\\(\\){}\\[\\]<>])");
	private static final Pattern BRACKET_CJK = Pattern.compile("([\\(\\){}\\[\\]<>])([\\p{InHiragana}\\p{InKatakana}\\p{InBopomofo}\\p{InCJKCompatibilityIdeographs}\\p{InCJKUnifiedIdeographs}])");
	private static final Pattern FIX_BRACKET = Pattern.compile("([(\\({\\[)]+)(\\s*)(.+?)(\\s*)([\\)}\\]]+)");
	private static final Pattern CJK_HASH = Pattern.compile("([\\p{InHiragana}\\p{InKatakana}\\p{InBopomofo}\\p{InCJKCompatibilityIdeographs}\\p{InCJKUnifiedIdeographs}])(#(\\S+))");
	private static final Pattern HASH_CJK = Pattern.compile("((\\S+)#)([\\p{InHiragana}\\p{InKatakana}\\p{InBopomofo}\\p{InCJKCompatibilityIdeographs}\\p{InCJKUnifiedIdeographs}])");
	private static final Pattern STYLE_FIRST = Pattern.compile("style=\"");
	private static final Pattern STYLE_MIDDLE = Pattern.compile(": \'?");
	private static final Pattern STYLE_LAST = Pattern.compile(";\"");
	private static final Pattern CLASS_NAME = Pattern.compile("class=");
	private static final Pattern HTML_FOR = Pattern.compile("for=");

	// style="width: 26%;"
	// style={{width: '26%'}}

	public static String spacingText(String text) {
		Matcher matcher = CLASS_NAME.matcher(text);
//		text = matcher.replaceAll("$1 $2");
//
//		matcher = QUOTE_CJK.matcher(text);
//		text = matcher.replaceAll("$1 $2");
//
//		matcher = FIX_QUOTE.matcher(text);
//		text = matcher.replaceAll("$1$3$5");
//
//		String oldText = text;
//		matcher = CJK_BRACKET_CJK.matcher(text);
//		String newText = matcher.replaceAll("$1 $2 $4");
//		text = newText;
//		if (oldText.equals(newText)) {
//			matcher = CJK_BRACKET.matcher(text);
//			text = matcher.replaceAll("$1 $2");
//
//			matcher = BRACKET_CJK.matcher(text);
//			text = matcher.replaceAll("$1 $2");
//		}
//		matcher = FIX_BRACKET.matcher(text);
//		text = matcher.replaceAll("$1$3$5");
//
//		matcher = CJK_HASH.matcher(text);
//		text = matcher.replaceAll("$1 $2");
//
//		matcher = HASH_CJK.matcher(text);
//		text = matcher.replaceAll("$1 $3");
//
//		matcher = CJK_ANS.matcher(text);
//		text = matcher.replaceAll("$1 $2");
//
//		matcher = ANS_CJK.matcher(text);
//		text = matcher.replaceAll("$1 $2");
//
//		matcher = CLASSNAME.matcher(text);
		text = matcher.replaceAll("className=");

        matcher = HTML_FOR.matcher(text);
        text = matcher.replaceAll("htmlFor=");

        matcher = STYLE_FIRST.matcher(text);
        text = matcher.replaceAll("style={{");

        matcher = STYLE_MIDDLE.matcher(text);
        text = matcher.replaceAll(": \'");

        matcher = STYLE_LAST.matcher(text);
        text = matcher.replaceAll("\'}}");


		return text;
	}
}