package org.rapidpm.frp;

import static java.lang.Character.toChars;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.stream.Collectors.joining;
import static org.rapidpm.frp.Transformations.not;
import static org.rapidpm.frp.matcher.Case.match;
import static org.rapidpm.frp.matcher.Case.matchCase;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.rapidpm.frp.functions.QuadFunction;
import org.rapidpm.frp.functions.TriFunction;
import org.rapidpm.frp.matcher.Case;
import org.rapidpm.frp.model.Result;

/**
 * Created by svenruppert on 25.04.17.
 */
public interface StringFunctions {

  static BiFunction<String, String, Boolean> notStartsWith() {
    return (s, prefix) -> ! s.startsWith(prefix);
  }

  static TriFunction<String, String, Boolean, Boolean> notStartsWithCaseSensitive() {
    return (input, prefix, caseSensitive) ->
        (caseSensitive)
            ? not().apply(input.startsWith(prefix))
            : not().apply(input.toLowerCase().startsWith(prefix.toLowerCase()));
  }

  static Predicate<String> notEmpty() {
    return (str) -> not().apply(str.isEmpty());
  }


  /**
   * Get the character at index.
   * <p>
   * This is not 0 based. The first Element will addressed with a 1 !!
   * <p>
   * For values which don't fall under this range Result.failure will be returned.
   * <p>
   * param value input value
   * param index location
   *
   * @return an Optional String if found else empty
   */
  static BiFunction<String, Integer, Result<String>> at() {
    return (String value, Integer humanIndex) ->
        match(
            matchCase(() -> Result.success(valueOf(value.charAt(humanIndex - 1)))),
            matchCase(() -> value == null, () -> Result.failure("value should not be null")),
            matchCase(value::isEmpty, () -> Result.failure("value should not be empty")),
            matchCase(() -> humanIndex - 1 >= value.length(), () -> Result.failure("index out of bounds")),
            matchCase(() -> humanIndex - 1 < 0, () -> Result.failure("index out of lower bounds"))
        );
  }

  /**
   * Replace consecutive whitespace characters with a single space.
   * <p>
   * param input input String
   *
   * @return collapsed String
   */
  static Function<String, String> collapseWhitespace() {
    return (input) -> input.trim().replaceAll("\\s\\s+", " ");
  }

  /**
   * Verifies that the needle is contained in the value. The search is case insensitive
   * <p>
   * param value  to search
   * param needle to find
   *
   * @return true if found else false.
   */

  static BiFunction<String, String, Boolean> contains() {
    return (value, needle) -> containsCaseSensitive().apply(value, needle, false);
  }

  /**
   * Verifies that the needle is contained in the value.
   * <p>
   * param value         to search
   * param needle        to find
   * param caseSensitive true or false
   *
   * @return true if found else false.
   */

  static TriFunction<String, String, Boolean, Boolean> containsCaseSensitive() {
    return (value, needle, caseSensitive) ->
        match(
            matchCase(() -> Result.success(value.toLowerCase().contains(needle.toLowerCase()))),
            matchCase(() -> caseSensitive, () -> Result.success(value.contains(needle)))
        ).get();
  }


  /**
   * Verifies that all needles are contained in value. The search is case insensitive
   * <p>
   * param value   input String to search
   * param needles needles to find
   *
   * @return true if all needles are found else false.
   */
  static BiFunction<String, String[], Boolean> containsAll() {
    return (value, needles) -> Arrays
        .stream(needles)
        .allMatch(needle -> containsCaseSensitive().apply(value, needle, true));
  }


  /**
   * Verifies that all needles are contained in value
   * <p>
   * param value         input String to search
   * param needles       needles to find
   * param caseSensitive true or false
   *
   * @return true if all needles are found else false.
   */
  static TriFunction<String, String[], Boolean, Boolean> containsAllCaseSensitive() {
    return (value, needles, caseSensitive) ->
        Arrays
            .stream(needles)
            .allMatch(needle -> containsCaseSensitive().apply(value, needle, caseSensitive));
  }


  /**
   * Verifies that one or more of needles are contained in value. This is case insensitive
   * <p>
   * param value   input
   * param needles needles to search
   *
   * @return boolean true if any needle is found else false
   */
  static BiFunction<String, String[], Boolean> containsAny() {
    return (value, needles) -> containsAnyCaseSensitive().apply(value, needles, false);
  }

  /**
   * Verifies that one or more of needles are contained in value.
   * <p>
   * param value         input
   * param needles       needles to search
   * param caseSensitive true or false
   *
   * @return boolean true if any needle is found else false
   */
  static TriFunction<String, String[], Boolean, Boolean> containsAnyCaseSensitive() {
    return (value, needles, caseSensitive) -> Arrays
        .stream(needles)
        .anyMatch(needle -> containsCaseSensitive().apply(value, needle, caseSensitive));
  }


  /**
   * Count the number of times substr appears in value
   * <p>
   * param value  input
   * param subStr to search
   *
   * @return count of times substring exists
   */
  static BiFunction<String, String, Long> countSubStr() {
    return (value, subStr) -> countSubStrCaseSensitive().apply(value, subStr, true, false);
  }

  /**
   * Count the number of times substr appears in value
   * <p>
   * param value            input
   * param subStr           search string
   * param caseSensitive    whether search should be case sensitive
   * param allowOverlapping boolean to take into account overlapping
   *
   * @return count of times substring exists
   */
  static QuadFunction<String, String, Boolean, Boolean, Long> countSubStrCaseSensitive() {
    return (value, subStr, caseSensitive, allowOverlapping) ->
        countSubStrCaseSensitiveOverlapping()
            .apply(
                caseSensitive ? value : value.toLowerCase(),
                caseSensitive ? subStr : subStr.toLowerCase(),
                allowOverlapping,
                0L);
  }

  static QuadFunction<String, String, Boolean, Long, Long> countSubStrCaseSensitiveOverlapping() {
    return (value, subStr, allowOverlapping, count) -> {
      int position = value.indexOf(subStr);
      if (position == - 1) {
        return count;
      }
      int offset;
      if (! allowOverlapping) {
        offset = position + subStr.length();
      } else {
        offset = position + 1;
      }
      return countSubStrCaseSensitiveOverlapping().apply(value.substring(offset), subStr, allowOverlapping, ++ count);
    };
  }


  /**
   * Test if value ends with search. The search is case sensitive.
   * <p>
   * param value  input string
   * param search string to search
   *
   * @return true or false
   */
  static BiFunction<String, String, Boolean> endsWith() {
    return (value, search) -> endsWithCaseSensitive().apply(value, search, true);
  }

  /**
   * Test if value ends with search.
   * <p>
   * param value         input string
   * param search        string to search
   * param caseSensitive true or false
   *
   * @return true or false
   */
  static TriFunction<String, String, Boolean, Boolean> endsWithCaseSensitive() {
    return (value, search, caseSensitive) -> endsWithCaseSensitiveUpTo()
        .apply(value, search, value.length(), caseSensitive);
  }

  /**
   * Test if value ends with search.
   * <p>
   * param value         input string
   * param search        string to search
   * param position      position till which you want to search.
   * param caseSensitive true or false
   *
   * @return true or false
   */
  static QuadFunction<String, String, Integer, Boolean, Boolean> endsWithCaseSensitiveUpTo() {
    return (value, search, position, caseSensitive) -> {
      int remainingLength = position - search.length();
      return (caseSensitive)
          ? value.indexOf(search, remainingLength) > - 1
          : value.toLowerCase().indexOf(search.toLowerCase(), remainingLength) > - 1;
    };
  }


  /**
   * Ensures that the value begins with prefix. If it doesn't exist, it's prepended. It is case sensitive.
   * <p>
   * param value  input
   * param prefix prefix
   *
   * @return string with prefix if it was not present.
   */
//  public static String ensureLeft(final String value, final String prefix) {
//    return ensureLeft(value, prefix, true);
//  }
  static BiFunction<String, String, String> ensureLeft() {
    return (input, prefix) -> ensureLeftCaseSensitive().apply(input, prefix, true);
  }


  /**
   * Ensures that the value begins with prefix. If it doesn't exist, it's prepended.
   * <p>
   * param value         input
   * param prefix        prefix
   * param caseSensitive true or false
   *
   * @return string with prefix if it was not present.
   */
  static TriFunction<String, String, Boolean, String> ensureLeftCaseSensitive() {
    return (input, prefix, caseSensitive) ->
        (caseSensitive)
            ? (input.startsWith(prefix) ? input : (prefix + input))
            : (input.toLowerCase().startsWith(prefix.toLowerCase()) ? input : (prefix + input));
  }

  /**
   * Decodes data encoded with MIME base64
   * <p>
   * param value The data to decode
   *
   * @return decoded data
   */

  static Function<String, String> base64Decode() {
    return (input) -> new String(Base64.getDecoder().decode(input)).intern();
  }

  /**
   * Encodes data with MIME base64.
   * <p>
   * param value The data to encode
   *
   * @return The encoded String
   */
  static Function<String, String> base64Encode() {
    return (input) -> Base64.getEncoder().encodeToString(input.getBytes());
  }


  /**
   * Append an array of String to value
   * <p>
   * param value   initial String
   * param appends an array of strings to append
   *
   * @return full String
   */
  static BiFunction<String, String[], String> appendArray() {
    return (input, appends) -> appendStream()
        .apply(input, Arrays.stream(appends));
  }

  static BiFunction<String, Stream<String>, String> appendStream() {
    return (input, appendStream) -> Stream
        .concat(
            Stream.of(input),
            appendStream)
        .collect(joining());
  }

  /**
   * Appends Strings to value
   * <p>
   * param value   initial String
   * param appends an array of strings to append
   *
   * @return full String
   */
  static BiFunction<String, String, String> append() {
    return (input, append) -> appendStream()
        .apply(input, Stream.of(append));
  }

  static BiFunction<String, Collection<String>, String> appendCollection() {
    return (input, appendCollection) ->
        appendStream().apply(input, appendCollection.stream());
  }

  /**
   * Returns a repeated string given a multiplier.
   * <p>
   * param value      The input String
   * param multiplier Number of repeats
   *
   * @return The String repeated
   */
  static BiFunction<String, Integer, String> repeat() {
    return (input, repeat) -> Stream
        .generate(() -> input)
        .limit(repeat)
        .collect(joining());
  }

  /**
   * Returns a new string of a given length such that the beginning of the string is padded.
   * <p>
   * param value  The input String
   * param pad    The pad
   * param length Length of the String we want
   *
   * @return Padded String
   */
  static TriFunction<String, String, Integer, String> leftPad() {
    return (value, pad, length) ->
        (value.length() > length)
            ? value
            : append().apply(repeat().apply(pad, length - value.length()), value);
  }


  static TriFunction<String, Integer, Integer, String> decode() {
    return (value, digits, radix) -> Arrays
        .stream(value.split("(?<=\\G.{" + digits + "})"))
        .map(data -> valueOf(toChars(parseInt(data, radix))))
        .collect(joining());
  }

  static TriFunction<String, Integer, Integer, String> encode() {
    return (value, digits, radix) -> value
        .chars()
        .mapToObj(ch -> leftPad()
            .apply(Integer.toString(ch, radix), "0", digits))
        .collect(joining());
  }

  /**
   * Convert binary unicode (16 digits) string to string chars
   * <p>
   * param value The value to decode
   *
   * @return The decoded String
   */
  static Function<String, String> binDecode() {
    return (value) -> decode().apply(value, 16, 2);
  }

  /**
   * Convert string chars to binary unicode (16 digits)
   * <p>
   * param value The value to encode
   *
   * @return String in binary format
   */
  static Function<String, String> binEncode() {
    return (value) -> encode().apply(value, 16, 2);
  }

  /**
   * Convert decimal unicode (5 digits) string to string chars
   * <p>
   * param value The value to decode
   *
   * @return decoded String
   */

  static Function<String, String> decDecode() {
    return (value) -> decode().apply(value, 5, 10);
  }

  /**
   * Convert string chars to decimal unicode (5 digits)
   * <p>
   * param value The value to encode
   *
   * @return Encoded value
   */

  static Function<String, String> decEncode() {
    return (value) -> encode().apply(value, 5, 10);
  }

  /**
   * Ensures that the value ends with suffix. If it doesn't, it's appended. This operation is case sensitive.
   * <p>
   * param value  The input String
   * param suffix The substr to be ensured to be right
   *
   * @return The string which is guarenteed to start with substr
   */

  static BiFunction<String, String, String> ensureRight() {
    return (value, suffix) -> ensureRightCaseSensitive().apply(value, suffix, true);
  }

  /**
   * Ensures that the value ends with suffix. If it doesn't, it's appended.
   * <p>
   * param value         The input String
   * param suffix        The substr to be ensured to be right
   * param caseSensitive Use case (in-)sensitive matching for determining if value already ends with suffix
   *
   * @return The string which is guarenteed to start with substr
   */
  static TriFunction<String, String, Boolean, String> ensureRightCaseSensitive() {
    return (value, suffix, caseSensitive) ->
        endsWithCaseSensitive().apply(value, suffix, caseSensitive)
            ? value
            : append().apply(value, suffix);
  }

  /**
   * Returns the first n chars of String
   * <p>
   * param value The input String
   * param n     Number of chars to return
   *
   * @return The first n chars
   */
  static BiFunction<String, Integer, String> first() {
    return (value, n) -> value.substring(0, n);
  }

  /**
   * Return the first char of String
   * <p>
   * param value The input String
   *
   * @return The first char
   */
  static Function<String, String> head() {
    return (value) -> first().apply(value, 1);
  }


  /**
   * Formats a string using parameters
   * <p>
   * param value  The value to be formatted
   * param params Parameters to be described in the string
   *
   * @return The formatted string
   */
  static BiFunction<String, String[], String> format() {
    return (input, params) -> {
      //TODO refactor to fp
      final Pattern p = Pattern.compile("\\{(\\w+)\\}");
      final Matcher m = p.matcher(input);
      String result = input;
      while (m.find()) {
        int paramNumber = Integer.parseInt(m.group(1));
        if (params == null || paramNumber >= params.length) {
          throw new IllegalArgumentException("params does not have value for " + m.group());
        }
        result = result.replace(m.group(), params[paramNumber]);
      }
      return result;
    };
  }


  /**
   * Convert hexadecimal unicode (4 digits) string to string chars
   * <p>
   * param value The value to decode
   *
   * @return The decoded String
   */
  static Function<String, String> hexDecode() {
    return (input) -> decode().apply(input, 4, 16);
  }

  /**
   * Convert string chars to hexadecimal unicode (4 digits)
   * <p>
   * param value The value to encode
   *
   * @return String in hexadecimal format.
   */
  static Function<String, String> hexEncode() {
    return (input) -> encode().apply(input, 4, 16);
  }

  /**
   * The indexOf() method returns the index within the calling String of the first occurrence of the specified value, starting the search at fromIndex.
   * Returns -1 if the value is not found.
   * <p>
   * param value         The input String
   * param needle        The search String
   * param offset        The offset to start searching from.
   * param caseSensitive boolean to indicate whether search should be case sensitive
   *
   * @return Returns position of first occurrence of needle.
   */
  static QuadFunction<String, String, Integer, Boolean, Integer> indexOfCoseSensitive() {
    return (value, needle, offset, caseSensitive) ->
        (caseSensitive)
            ? value.indexOf(needle, offset)
            : value.toLowerCase().indexOf(needle.toLowerCase(), offset);
  }

  /**
   * Tests if two Strings are inequal
   * <p>
   * param first  The first String
   * param second The second String
   *
   * @return true if first and second are not equal false otherwise
   */
  static BiFunction<String, String, Boolean> inEqual() {
    return (first, second) -> ! Objects.equals(first, second);
  }

  /**
   * Inserts 'substr' into the 'value' at the 'index' provided.
   * <p>
   * param value  The input String
   * param substr The String to insert
   * param index  The index to insert substr
   *
   * @return String with substr added
   */
  static TriFunction<String, String, Integer, String> insert() {
    return (value, subStr, index) ->
        (index > value.length())
            ? value
            : appendStream()
            .apply(
                value.substring(0, index),
                Stream.of(
                    subStr,
                    value.substring(index)));
  }

  /**
   * Verifies if String is uppercase
   * <p>
   * param value The input String
   *
   * @return true if String is uppercase false otherwise
   */
  static Function<String, Boolean> isUpperCase() {
    return (input) -> Objects.equals(input, input.toUpperCase());
  }

  /**
   * Verifies if String is lower case
   * <p>
   * param value The input String
   *
   * @return true if String is lowercase false otherwise
   */

  static Function<String, Boolean> isLowerCase() {
    return (input) -> Objects.equals(input, input.toLowerCase());
  }

  /**
   * Return the last n chars of String
   * <p>
   * param value The input String
   * param n     Number of chars to return
   *
   * @return n Last characters
   */
  static BiFunction<String, Integer, String> last() {
    return (value, n) -> (n > value.length())
        ? value
        : value.substring(value.length() - n);
  }

  /**
   * Checks whether Object is String
   *
   * @param value The input String
   * @return true if Object is a String false otherwise
   */
  public static boolean isString(final Object value) {
    if (Objects.isNull(value)) {
      throw new IllegalArgumentException("value can't be null");
    }
    return value instanceof String;
  }

  /**
   * This method returns the index within the calling String object of the last occurrence of the specified value, searching backwards from the offset.
   * Returns -1 if the value is not found. The search starts from the end and case sensitive.
   * <p>
   * param value  The input String
   * param needle The search String
   *
   * @return Return position of the last occurrence of 'needle'.
   */

  static BiFunction<String, String, Integer> lastIndexOf() {
    return (value, needle) -> lastIndexOfCaseSensitive().apply(value, needle, true);
  }

  /**
   * This method returns the index within the calling String object of the last occurrence of the specified value, searching backwards from the offset.
   * Returns -1 if the value is not found. The search starts from the end and case sensitive.
   * <p>
   * param value         The input String
   * param needle        The search String
   * param caseSensitive true or false
   *
   * @return Return position of the last occurrence of 'needle'.
   */
  static TriFunction<String, String, Boolean, Integer> lastIndexOfCaseSensitive() {
    return (value, needle, caseSensitive) ->
        lastIndexOfCaseSensitiveWithIndexOf().apply(value, needle, value.length(), caseSensitive);
  }


  /**
   * This method returns the index within the calling String object of the last occurrence of the specified value, searching backwards from the offset.
   * Returns -1 if the value is not found.
   * <p>
   * param value         The input String
   * param needle        The search String
   * param offset        The index to start search from
   * param caseSensitive whether search should be case sensitive
   *
   * @return Return position of the last occurrence of 'needle'.
   */
  static QuadFunction<String, String, Integer, Boolean, Integer> lastIndexOfCaseSensitiveWithIndexOf() {
    return (value, needle, offset, caseSensitive) ->
        (caseSensitive)
            ? value.lastIndexOf(needle, offset)
            : value.toLowerCase().lastIndexOf(needle.toLowerCase(), offset);
  }

  /**
   * Removes all spaces on left
   * <p>
   * param value The input String
   *
   * @return String without left border spaces
   */
  static Function<String, String> leftTrim() {
    return (value) -> value.replaceAll("^\\s+", "");
  }

  /**
   * Returns length of String. Delegates to java.lang.String length method.
   * <p>
   * param value The input String
   *
   * @return Length of the String
   */
  static Function<String, Integer> length() {
    return String::length;
  }

  /**
   * Return a new String starting with prepends
   * <p>
   * param value    The input String
   * param prepends Strings to prepend
   *
   * @return The prepended String
   */
  static BiFunction<String, String[], String> prependArray() {
    return (value, array) -> Arrays.stream(array).collect(joining()) + value;
  }

  /**
   * Return a new String starting with prepends
   * <p>
   * param value    The input String
   * param prepends Strings to prepend
   *
   * @return The prepended String
   */

  static BiFunction<String, Stream<String>, String> prependStream() {
    return (value, stream) -> stream.collect(joining()) + value;
  }

  /**
   * Remove empty Strings from string array
   * <p>
   * param strings Array of String to be cleaned
   *
   * @return Array of String without empty Strings
   */
  static Function<String[], String[]> removeEmptyStrings() {
    return (input) -> Arrays
        .stream(input)
        .filter(Objects::nonNull)
        .map(String::trim)
        .filter(notEmpty())
        .toArray(String[]::new);
  }

  /**
   * Returns a new String with the prefix removed, if present. This is case sensitive.
   * <p>
   * param value  The input String
   * param prefix String to remove on left
   *
   * @return The String without prefix
   */
  static BiFunction<String, String, String> removeLeft() {
    return (input, prefix) ->
        input.startsWith(prefix)
            ? input.replaceFirst(prefix, "")
            : input;

  }


  static TriFunction<String, String, Boolean, String> removeFromLeftCaseSensitive() {
    return (input, prefix, caseSenstive) ->
        (caseSenstive)
            ? removeLeft().apply(input, prefix)
            : (notStartsWithCaseSensitive().apply(input, prefix, false))
            ? input
            : input.substring(prefix.length());
  }


  static BiFunction<String, String, Boolean> startsWith() {
    return String::startsWith;
  }

  static Function<String, String> removeNonWord() {
    return (value) -> value.replaceAll("[^\\w]+", "");
  }



  static BiFunction<String, String, String> removeRight() {
    return (value, suffix) -> removeRightCaseSensitive().apply(value, suffix, true);
  }


  static TriFunction<String, String, Boolean, String> removeRightCaseSensitive() {
    return (value, suffix, caseSensitive) ->
        endsWithCaseSensitive().apply(value, suffix, caseSensitive)
            ? value.substring(0, value.toLowerCase().lastIndexOf(suffix.toLowerCase()))
            : value;
  }

  /**
   * Remove all spaces and replace for value.
   * <p>
   * param value The input String
   *
   * @return String without spaces
   */
  static Function<String, String> removeSpaces() {
    return (value) -> value.replaceAll("\\s", "");
  }

  /**
   * Replace all occurrences of 'search' value to 'newvalue'. Uses String replace method.
   * <p>
   * param value         The input
   * param search        The String to search
   * param newValue      The String to replace
   * param caseSensitive whether search should be case sensitive or not
   *
   * @return String replaced with 'newvalue'.
   */
  static QuadFunction<String, String, String, Boolean, String> replace() {
    return (value, search, newValue, caseSensitive) ->
        (caseSensitive)
            ? value.replaceAll(search, newValue)
            : Pattern
            .compile(search, CASE_INSENSITIVE)
            .matcher(value)
            .replaceAll(Matcher.quoteReplacement(newValue));
  }

  /**
   * Reverse the input String
   * <p>
   * param value The input String
   *
   * @return Reversed String
   */
  static Function<String, String> reverse() {
    return (value) -> new StringBuilder(value).reverse().toString();
  }

  /**
   * Returns a new string of a given length such that the ending of the string is padded.
   * <p>
   * param value  The input String
   * param length Max length of String.
   * param pad    Character to repeat
   *
   * @return Right padded String
   */
  static TriFunction<String, String, Integer, String> rightPad() {
    return (value, pad, length) ->
        (value.length() > length)
            ? value
            : append().apply(value, repeat().apply(pad, length - value.length()));
  }

  /**
   * Remove all spaces on right.
   * <p>
   * param value The String
   *
   * @return String without right boarders spaces.
   */
  static Function<String, String> rightTrim() {
    return (value) -> value.replaceAll("\\s+$", "");
  }


  //TODO safeTruncate
  /**
   * Truncate the string securely, not cutting a word in half. It always returns the last full word.
   *
   * @param value  The input String
   * @param length Max size of the truncated String
   * @param filler String that will be added to the end of the return string. Example: '...'
   * @return The truncated String
   */
//  public static String safeTruncate(final String value, final int length, final String filler) {
//    validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
//    if (length == 0) {
//      return "";
//    }
//    if (length >= value.length()) {
//      return value;
//    }
//
//    String[] words = words(value);
//    StringJoiner result = new StringJoiner(" ");
//    int spaceCount = 0;
//    for (String word : words) {
//      if (result.length() + word.length() + filler.length() + spaceCount > length) {
//        break;
//      } else {
//        result.add(word);
//        spaceCount++;
//      }
//    }
//    return append(result.toString(), filler);
//  }
//
//  static TriFunction<String, Integer, String, String> safeTruncate() {
//    return (value, length, filler) -> {
//      Result<String> result = Case
//          .match(
//              Case.matchCase(() -> {
//                wordStream().apply(value)
//
//
//
//                return Result.success((String) null);
//              }),
//              Case.matchCase(() -> length == 0, () -> Result.success("")),
//              Case.matchCase(() -> length >= value.length(), () -> Result.success(value))
//          );
//
//      return append().apply(result.get(), filler);
//    };
//  }


  /**
   * Alias to String split function. Defined only for completeness.
   * <p>
   * param value The input String
   * param regex The delimiting regular expression
   *
   * @return String Array
   */
  static BiFunction<String, String, String[]> split() {
    return String::split;
  }

  static BiFunction<String, String, Stream<String>> splitStream() {
    return (input, regex) -> Arrays.stream(input.split(regex));
  }


  /**
   * Join concatenates all the elements of the strings array into a single String. The separator string is placed between elements in the resulting string.
   * <p>
   * param strings   The input array to concatenate
   * param separator The separator to use
   *
   * @return Concatenated String
   */
  static BiFunction<String[], String, String> join() {
    return (strings, sperator) -> Arrays.stream(strings).collect(joining(sperator));
  }

  /**
   * Splits a String to words
   * <p>
   * param value The input String
   *
   * @return word Stream
   */
  static Function<String, Stream<String>> wordStream() {
    return (input) -> Arrays.stream(input.split("\\W+"));
  }

  /**
   * Truncate the unsecured form string, cutting the independent string of required position.
   * <p>
   * param value  Value will be truncated unsecurely.
   * param length Size of the returned string.
   * param filler Value that will be added to the end of the return string. Example: '...'
   *
   * @return String truncated unsafely.
   */
  static TriFunction<String, Integer, String, String> truncate() {
    return (value, length, filler) -> Case
        .match(
            Case.matchCase(
                () -> Result.success(
                    append().apply(value.substring(0, length - filler.length()), filler))),
            Case.matchCase(() -> length == 0, () -> Result.success("")),
            Case.matchCase(() -> length >= value.length(), () -> Result.success(value))
        ).get();
  }

  /**
   * Returns a String array consisting of the characters in the String.
   * <p>
   * param value input
   *
   * @return character array
   */
  static Function<String, String[]> chars() {
    return (input) -> input.split("");
  }

  static Function<String, Stream<String>> charsStream() {
    return (input) -> Arrays.stream(input.split(""));
  }


  /**
   * It returns a string with its characters in random order.
   * <p>
   * param value The input String
   *
   * @return The shuffled String
   */
  static Function<String, String> shuffle() {
    return (input) -> {
      final String[] chars = chars().apply(input);
      Random random = new Random();
      for (int i = 0; i < chars.length; i++) {
        int r = random.nextInt(chars.length);
        String tmp = chars[i];
        chars[i] = chars[r];
        chars[r] = tmp;
      }
      return Arrays.stream(chars).collect(joining());
    };
  }


  /**
   * Alias of substring method
   * <p>
   * param value The input String
   * param begin Start of slice.
   * param end   End of slice.
   *
   * @return The String sliced!
   */
  static TriFunction<String, Integer, Integer, String> slice() {
    return String::substring;
  }

//  /**
//   * Convert a String to a slug
//   *
//   * @param value The value to slugify
//   * @return The slugified value
//   */
//  public static String slugify(final String value) {
//    validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
//    String transliterated = transliterate(collapseWhitespace(value.trim().toLowerCase()));
//    return Arrays.stream(words(transliterated.replace("&", "-and-"))).collect(joining("-"));
//  }
//
//  static Function<String, String> slugify(){
//
//  }

//  /**
//   * Remove all non valid characters.
//   *
//   * @param value The input String
//   * @return String without non valid characters.
//   */
//  public static String transliterate(final String value) {
//    validate(value, NULL_STRING_PREDICATE, NULL_STRING_MSG_SUPPLIER);
//    String result = value;
//    Set<Map.Entry<String, List<String>>> entries = Ascii.ascii.entrySet();
//    for (Map.Entry<String, List<String>> entry : entries) {
//      for (String ch : entry.getValue()) {
//        result = result.replace(ch, entry.getKey());
//      }
//    }
//    return result;
//  }
//
//  static Function<String, String> transliterate(){
//    return (value)->{
//
//
//    };
//  }


  /**
   * Surrounds a 'value' with the given 'prefix' and 'suffix'.
   * <p>
   * param value  The input String
   * param prefix prefix. If suffix is null then prefix is used
   * param suffix suffix
   *
   * @return The String with surround substrs!
   */
  static TriFunction<String, String, String, String> surround() {
    return (value, prefix, suffix) -> Case
        .match(
            Case.matchCase(() -> Result.success(prefix + value + suffix)),
            Case.matchCase(() -> prefix == null && suffix == null, () -> Result.success(value)),
            Case.matchCase(() -> prefix != null && suffix == null, () -> Result.success(prefix + value + prefix)),
            Case.matchCase(() -> prefix == null && suffix != null, () -> Result.success(suffix + value + suffix))
        ).get();
  }


  /**
   * Transform to camelCase
   * <p>
   * param value The input String
   *
   * @return String in camelCase.
   */

  static Function<String, String> toCamelCase() {
    return (value) -> toStudlyCase()
        .apply(value)
        .substring(0, 1)
        .toLowerCase()
        +
        toStudlyCase()
            .apply(value)
            .substring(1);
  }


  /**
   * Transform to StudlyCaps.
   * <p>
   * param value The input String
   *
   * @return String in StudlyCaps.
   */
  static Function<String, String> toStudlyCase() {
    return (value) -> splitStream()
        .apply(collapseWhitespace().apply(value.trim()), "\\s*(_|-|\\s)\\s*")
        .filter(w -> ! w.trim().isEmpty())
        .map(w -> head().apply(w).toUpperCase() + tail().apply(w))
        .collect(joining());
  }

  /**
   * Return tail of the String
   * <p>
   * param value The input String
   *
   * @return String tail
   */
  static Function<String, String> tail() {
    return (value) -> last().apply(value, value.length() - 1);
  }

  /**
   * Decamelize String
   * <p>
   * param value The input String
   * param chr   string to use
   *
   * @return String decamelized.
   */
  static BiFunction<String, String, String> toDecamelize() {
    return (value, chr) ->
        splitStream()
            .apply(toCamelCase().apply(value), "(?=\\p{Upper})")
            .map(String::toLowerCase)
            .collect(joining(chr));
  }

  /**
   * Transform to kebab-case.
   * <p>
   * param value The input String
   *
   * @return String in kebab-case.
   */
  static Function<String, String> toKebabCase() {
    return (value) -> toDecamelize().apply(value, "-");
  }

  /**
   * Transform to snake_case.
   * <p>
   * param value The input String
   *
   * @return String in snake_case.
   */
  static Function<String, String> toSnakeCase() {
    return (value) -> toDecamelize().apply(value, "_");
  }


  /**
   * Converts the first character of string to upper case and the remaining to lower case.
   * <p>
   * param input The string to capitalize
   *
   * @return The capitalized string
   */
  //TODO refactoring - remove optinal
  static Function<String, String> capitalize() {
    return (input) -> (input.length() == 0)
        ? ""
        : Optional
        .ofNullable(head().apply(input))
        .map(String::toUpperCase)
        .map(h ->
            Optional
                .ofNullable(tail().apply(input))
                .map(t -> h + t.toLowerCase())
                .orElse(h))
        .get();
  }

  /**
   * Converts the first character of string to lower case.
   * <p>
   * param input The string to convert
   *
   * @return The converted string
   * @throws IllegalArgumentException
   */
  //TODO refactoring - remove optinal
  static Function<String, String> lowerFirst() {
    return (input) -> (input.length() == 0)
        ? ""
        : Optional.ofNullable(head().apply(input))
        .map(String::toLowerCase)
        .map(h ->
            Optional.ofNullable(tail().apply(input)).map(t -> h + t).orElse(h))
        .get();
  }


  /**
   * Verifies whether String is enclosed by encloser
   * <p>
   * param input    The input String
   * param encloser String which encloses input String
   *
   * @return true if enclosed false otherwise
   */
  static BiFunction<String, String, Boolean> isEnclosedBetween() {
    return (input, encloser) -> input.startsWith(encloser) && input.startsWith(encloser);
  }

  /**
   * Verifies whether String is enclosed by encloser
   * <p>
   * param input         The input String
   * param leftEncloser  String which encloses input String at left start
   * param rightEncloser String which encloses input String at the right end
   *
   * @return true if enclosed false otherwise
   */
  static TriFunction<String, String, String, Boolean> isEnclosedBetweenBoth() {
    return (input, left, right) -> input.startsWith(left) && input.startsWith(right);
  }

  /**
   * Converts the first character of string to upper case.
   * <p>
   * param input The string to convert.
   *
   * @return Returns the converted string.
   */
  static Function<String, String> upperFirst() {
    return (input) ->
        Optional
            .ofNullable(head().apply(input))
            .map(String::toUpperCase)
            .map(h ->
                Optional
                    .ofNullable(tail().apply(input))
                    .map(t -> h + t)
                    .orElse(h))
            .get();
  }

//  /**
//   * Removes leading whitespace from string.
//   *
//   * @param input The string to trim.
//   * @return Returns the trimmed string.
//   */
//  public static Optional<String> trimStart(final String input) {
//    return Optional.ofNullable(input)
//        .filter(v -> ! v.isEmpty())
//        .map(Strman::leftTrim);
//  }
//
//
//  /**
//   * Removes leading characters from string.
//   *
//   * @param input The string to trim.
//   * @param chars The characters to trim.
//   * @return Returns the trimmed string.
//   */
//  public static Optional<String> trimStart(final String input, String... chars) {
//    return Optional.ofNullable(input)
//        .filter(v -> ! v.isEmpty())
//        .map(v -> {
//          String pattern = String.format("^[%s]+", join(chars, "\\"));
//          return v.replaceAll(pattern, "");
//        });
//  }
//
//  /**
//   * Removes trailing whitespace from string.
//   *
//   * @param input The string to trim.
//   * @return Returns the trimmed string.
//   */
//  public static Optional<String> trimEnd(final String input) {
//    return Optional.ofNullable(input)
//        .filter(v -> ! v.isEmpty())
//        .map(Strman::rightTrim);
//  }


//  /**
//   * Removes trailing characters from string.
//   *
//   * @param input The string to trim.
//   * @param chars The characters to trim.
//   * @return Returns the trimmed string.
//   */
//  public static Optional<String> trimEnd(final String input, String... chars) {
//    return Optional.ofNullable(input)
//        .filter(v -> ! v.isEmpty())
//        .map(v -> {
//          String pattern = String.format("[%s]+$", join(chars, "\\"));
//          return v.replaceAll(pattern, "");
//        });
//  }


}
