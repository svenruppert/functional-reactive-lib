/**
 * Copyright © 2017 Sven Ruppert (sven.ruppert@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package junit.com.svenruppert.functional;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsArrayContainingInOrder.arrayContaining;
import static org.hamcrest.collection.IsArrayWithSize.emptyArray;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.svenruppert.functional.StringFunctions.append;
import static com.svenruppert.functional.StringFunctions.appendArray;
import static com.svenruppert.functional.StringFunctions.appendStream;
import static com.svenruppert.functional.StringFunctions.at;
import static com.svenruppert.functional.StringFunctions.base64Decode;
import static com.svenruppert.functional.StringFunctions.base64Encode;
import static com.svenruppert.functional.StringFunctions.binDecode;
import static com.svenruppert.functional.StringFunctions.binEncode;
import static com.svenruppert.functional.StringFunctions.capitalize;
import static com.svenruppert.functional.StringFunctions.chars;
import static com.svenruppert.functional.StringFunctions.collapseWhitespace;
import static com.svenruppert.functional.StringFunctions.contains;
import static com.svenruppert.functional.StringFunctions.containsAll;
import static com.svenruppert.functional.StringFunctions.containsAllCaseSensitive;
import static com.svenruppert.functional.StringFunctions.containsCaseSensitive;
import static com.svenruppert.functional.StringFunctions.countSubStr;
import static com.svenruppert.functional.StringFunctions.countSubStrCaseSensitive;
import static com.svenruppert.functional.StringFunctions.decDecode;
import static com.svenruppert.functional.StringFunctions.decEncode;
import static com.svenruppert.functional.StringFunctions.endsWith;
import static com.svenruppert.functional.StringFunctions.endsWithCaseSensitive;
import static com.svenruppert.functional.StringFunctions.ensureLeft;
import static com.svenruppert.functional.StringFunctions.ensureLeftCaseSensitive;
import static com.svenruppert.functional.StringFunctions.ensureRight;
import static com.svenruppert.functional.StringFunctions.ensureRightCaseSensitive;
import static com.svenruppert.functional.StringFunctions.first;
import static com.svenruppert.functional.StringFunctions.head;
import static com.svenruppert.functional.StringFunctions.hexDecode;
import static com.svenruppert.functional.StringFunctions.hexEncode;
import static com.svenruppert.functional.StringFunctions.indexOfCoseSensitive;
import static com.svenruppert.functional.StringFunctions.insert;
import static com.svenruppert.functional.StringFunctions.isLowerCase;
import static com.svenruppert.functional.StringFunctions.isString;
import static com.svenruppert.functional.StringFunctions.isUpperCase;
import static com.svenruppert.functional.StringFunctions.join;
import static com.svenruppert.functional.StringFunctions.last;
import static com.svenruppert.functional.StringFunctions.lastIndexOf;
import static com.svenruppert.functional.StringFunctions.lastIndexOfCaseSensitive;
import static com.svenruppert.functional.StringFunctions.leftPad;
import static com.svenruppert.functional.StringFunctions.leftTrim;
import static com.svenruppert.functional.StringFunctions.lowerFirst;
import static com.svenruppert.functional.StringFunctions.notEmpty;
import static com.svenruppert.functional.StringFunctions.notStartsWith;
import static com.svenruppert.functional.StringFunctions.prependArray;
import static com.svenruppert.functional.StringFunctions.removeEmptyStrings;
import static com.svenruppert.functional.StringFunctions.removeFromLeftCaseSensitive;
import static com.svenruppert.functional.StringFunctions.removeLeft;
import static com.svenruppert.functional.StringFunctions.removeNonWord;
import static com.svenruppert.functional.StringFunctions.removeRight;
import static com.svenruppert.functional.StringFunctions.removeRightCaseSensitive;
import static com.svenruppert.functional.StringFunctions.removeSpaces;
import static com.svenruppert.functional.StringFunctions.repeat;
import static com.svenruppert.functional.StringFunctions.replace;
import static com.svenruppert.functional.StringFunctions.reverse;
import static com.svenruppert.functional.StringFunctions.rightPad;
import static com.svenruppert.functional.StringFunctions.rightTrim;
import static com.svenruppert.functional.StringFunctions.shuffle;
import static com.svenruppert.functional.StringFunctions.surround;
import static com.svenruppert.functional.StringFunctions.toCamelCase;
import static com.svenruppert.functional.StringFunctions.toDecamelize;
import static com.svenruppert.functional.StringFunctions.toKebabCase;
import static com.svenruppert.functional.StringFunctions.toSnakeCase;
import static com.svenruppert.functional.StringFunctions.truncate;
import static com.svenruppert.functional.StringFunctions.upperFirst;
import static com.svenruppert.functional.StringFunctions.wordStream;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.svenruppert.functional.model.Result;

/**
 * Created by svenruppert on 25.04.17.
 */
public class StringFunctionsTest {


  @Test
  public void collapseWhitespace001() {
    assertEquals("" , collapseWhitespace().apply(""));
  }

  @Test
  public void collapseWhitespace002() {
    assertEquals("A" , collapseWhitespace().apply("A"));
  }

  @Test
  public void collapseWhitespace002a() {
    assertEquals("A" , collapseWhitespace().apply(" A"));
  }

  @Test
  public void collapseWhitespace002b() {
    assertEquals("A" , collapseWhitespace().apply("A "));
  }

  @Test
  public void collapseWhitespace002c() {
    assertEquals("A" , collapseWhitespace().apply(" A "));
  }

  @Test
  public void collapseWhitespace003() {
    assertEquals("A B" , collapseWhitespace().apply("A B"));
  }

  @Test
  public void collapseWhitespace004() {
    assertEquals("A B" , collapseWhitespace().apply(" A B"));
  }

  @Test
  public void collapseWhitespace005() {
    assertEquals("A B" , collapseWhitespace().apply(" A  B"));
  }

  @Test
  public void collapseWhitespace006() {
    assertEquals("A B" , collapseWhitespace().apply(" A  B "));
  }

  @Test
  public void collapseWhitespace007() {
    assertEquals("A B" , collapseWhitespace().apply(" A   B "));
  }


  @Test
  public void at001() {
    Result<String> r = at().apply("ABCD" , 1);
    assertNotNull(r);
    assertTrue(r.isPresent());
    assertEquals("A" , r.get());
  }

  @Test
  public void at002() {
    Result<String> r = at().apply("ABCD" , 5);
    assertNotNull(r);
    assertFalse(r.isPresent());
  }

  @Test
  public void at003() {
    Result<String> r = at().apply("ABCD" , - 5);
    assertNotNull(r);
    assertFalse(r.isPresent());
  }

  @Test
  public void at004() {
    Result<String> r = at().apply("ABCD" , 0);
    assertNotNull(r);
    assertFalse(r.isPresent());
  }

  @Test
  public void at005() {
    Result<String> r = at().apply("ABCD" , 4);
    assertNotNull(r);
    assertTrue(r.isPresent());
    assertEquals("D" , r.get());
  }


  @Test
  public void notStartsWith001() {
    assertFalse(notStartsWith().apply("AAA" , "A"));
  }

  @Test
  public void notStartsWith002() {
    assertFalse(notStartsWith().apply("AAA" , "AAA"));
  }

  @Test
  public void notStartsWith003() {
    assertTrue(notStartsWith().apply("AAA" , "AAAA"));
  }

  @Test
  public void notStartsWith004() {
    assertTrue(notStartsWith().apply("AAA" , "B"));
  }

  @Test
  public void notStartsWith005() {
    assertFalse(notStartsWith().apply("AAA" , ""));
  }

  @Test
  public void notStartsWith006() {
    assertFalse(notStartsWith().apply(" AAA" , ""));
  }

  @Test
  public void notStartsWith007() {
    assertFalse(notStartsWith().apply(" AAA" , " "));
  }

  @Test
  public void notEmpty001() {
    assertFalse(notEmpty().test(""));
  }

  @Test
  public void notEmpty002() {
    assertTrue(notEmpty().test(" "));
  }

  @Test
  public void notEmpty003() {
    assertTrue(notEmpty().test("A"));
  }


  @Test
  public void append_shouldAppendStringsToEndOfValue() {
    assertThat(appendStream().apply("f" , Stream.of("o" , "o" , "b" , "a" , "r")) ,
        equalTo("foobar"));
    assertThat(append().apply("foobar" , "") , equalTo("foobar"));
    assertThat(append().apply("" , "foobar") , equalTo("foobar"));
  }

//  @Test(expected = IllegalArgumentException.class)
//  public void append_shouldThrowIllegalArgumentExceptionWhenValueIsNull() {
//    append(null);
//  }

  @Test
  public void appendArray_shouldAppendStringArrayToEndOfValue() {
    assertThat(appendArray().apply("f" , new String[]{"o" , "o" , "b" , "a" , "r"}) , equalTo("foobar"));
    assertThat(appendArray().apply("foobar" , new String[]{}) , equalTo("foobar"));
    assertThat(appendArray().apply("" , new String[]{"foobar"}) , equalTo("foobar"));
  }

//  @Test(expected = IllegalArgumentException.class)
//  public void appendArray_ShouldThrowIllegalArgumentExceptionWhenValueIsNull() {
//    appendArray(null, new String[]{});
//  }

  @Test
  public void at_shouldFindCharacterAtIndex() {
    assertThat(at().apply("foobar" , 1).get() , equalTo("f"));
    assertThat(at().apply("foobar" , 2).get() , equalTo("o"));
    assertThat(at().apply("foobar" , - 1).isAbsent() , equalTo(true));
    assertThat(at().apply("foobar" , - 2).isAbsent() , equalTo(true));
//    assertThat(at().apply("foobar", 10).get(), equalTo(Result.failure("").get()));
//    assertThat(at().apply("foobar", - 10).get(), equalTo(Result.failure("").get()));
  }

  @Test
  public void at_shouldNotFindCharacterAtIndex() {
    assertTrue(at().apply("foobar" , 10).isAbsent());
    assertTrue(at().apply("foobar" , - 10).isAbsent());
  }


  @Test
  public void between_shouldReturnArrayWithStringsBetweenStartAndEnd() {
//    assertThat(
//        isEnclosedBetweenBoth().apply("[abc][def]", "[", "]"),
//        arrayContaining("abc", "def")
//    );
//    assertThat(isEnclosedBetweenBoth().apply("<span>foo</span>", "<span>", "</span>"),
//        arrayContaining("foo"));
//    assertThat(between("<span>foo</span><span>bar</span>", "<span>", "</span>"), arrayContaining("foo", "bar"));
  }

  @Test
  public void between_shouldReturnFullStringWhenStartAndEndDoesNotExist() {
//    assertThat(between("[abc][def]", "{", "}"), arrayContaining("[abc][def]"));
//    assertThat(between("", "{", "}"), arrayContaining(""));
  }

  @Test
  public void chars_shouldReturnAllCharactersInString() {
    final String title = "title";
    assertThat(chars().apply(title) , equalTo(new String[]{"t" , "i" , "t" , "l" , "e"}));
  }

  @Test
  public void collapseWhitespace_shouldReplaceConsecutiveWhitespaceWithSingleSpace() {
    String[] fixture = {
        "foo    bar" ,
        "     foo     bar    " ,
        " foo     bar   " ,
        "    foo     bar "
    };
    Arrays.stream(fixture)
        .forEach(el -> assertThat(collapseWhitespace().apply(el) , equalTo("foo bar")));
  }

  @Test
  public void collapseWhitespace_shouldReplaceConsecutiveWhitespaceBetweenMultipleStrings() {
    String input = " foo      bar      bazz     hello    world    ";
    assertThat(collapseWhitespace().apply(input) , equalTo("foo bar bazz hello world"));
  }

  @Test
  public void containsWithCaseSensitiveFalse_shouldReturnTrueWhenStringContainsNeedle() {
    String[] fixture = {
        "foo bar" ,
        "bar foo" ,
        "foobar" ,
        "foo"
    };

    Arrays.stream(fixture).forEach(el -> assertTrue(contains().apply(el , "FOO")));
  }

  @Test
  public void containsWithCaseSensitiveTrue_shouldReturnTrueWhenStringContainsNeedle() {
    String[] fixture = {
        "foo bar" ,
        "bar foo" ,
        "foobar" ,
        "foo"
    };

    Arrays.stream(fixture)
        .forEach(el -> assertFalse(containsCaseSensitive().apply(el , "FOO" , true)));
  }

  @Test
  public void containsAll_shouldReturnTrueOnlyWhenAllNeedlesAreContainedInValue() {
    String[] fixture = {
        "foo bar" ,
        "bar foo" ,
        "foobar" ,
    };

    Arrays.stream(fixture)
        .forEach(el -> assertTrue(containsAll().apply(el , new String[]{"foo" , "bar"})));
  }

  @Test
  public void containsAll_shouldReturnFalseOnlyWhenAllNeedlesAreNotContainedInValue() {
    String[] fixture = {
        "foo bar" ,
        "bar foo" ,
        "foobar" ,
    };
    Arrays.stream(fixture)
        .forEach(el -> assertFalse(containsAllCaseSensitive().apply(el , new String[]{"FOO" , "bar"} , true)));
  }

//  @Test
//  public void containsAny_shouldReturnTrueWhenAnyOfSearchNeedleExistInInputValue() {
//    String[] fixture = {
//        "foo bar",
//        "bar foo",
//        "foobar",
//    };
//    Arrays.stream(fixture).forEach(el -> assertTrue(containsAny(el, new String[]{"foo", "bar", "test"})));
//  }

//  @Test
//  public void containsAny_shouldReturnFalseWhenNoneOfSearchNeedleExistInInputValue() {
//    String[] fixture = {
//        "foo bar",
//        "bar foo",
//        "foobar",
//    };
//    Arrays.stream(fixture)
//        .forEach(el -> assertFalse(containsAny(el, new String[]{"FOO", "BAR", "Test"}, true)));
//  }

  @Test
  public void countSubstr_shouldCountSubStrCountCaseInsensitiveWithoutOverlapInValue() {
    assertThat(countSubStrCaseSensitive().apply("aaaAAAaaa" , "aaa" , false , false) , equalTo(3L));
  }

  @Test
  public void countSubstr_shouldCountSubStrCountCaseSensitiveWithoutOverlapInValue() {
    assertThat(countSubStr().apply("aaaAAAaaa" , "aaa") , equalTo(2L));
  }

  @Test
  public void countSubstr_shouldCountSubStrCountCaseInsensitiveWithOverlapInValue() {
    assertThat(countSubStrCaseSensitive().apply("aaaAAAaaa" , "aaa" , false , true) , equalTo(7L));
  }

  @Test
  public void countSubstr_shouldCountSubStrCountCaseSensitiveWithOverlapInValue() {
    assertThat(countSubStrCaseSensitive().apply("aaaAAAaaa" , "AAA" , true , true) , equalTo(1L));
  }

//  @Test
//  public void countSubstrTestFixture_caseSensitiveTrueAndOverlappingFalse() {
//    String[] fixture = {
//        "aaaaaAaaAA",
//        "faaaAAaaaaAA",
//        "aaAAaaaaafA",
//        "AAaaafaaaaAAAA"
//    };
//    Arrays.stream(fixture).forEach(el -> assertThat(countSubstr(el, "a", true, false), equalTo(7L)));
//  }

//  @Test
//  public void countSubstrTestFixture_caseSensitiveFalseAndOverlappingFalse() {
//    String[] fixture = {
//        "aaaaaaa",
//        "faaaaaaa",
//        "aaaaaaaf",
//        "aaafaaaa"
//    };
//    Arrays.stream(fixture).forEach(el -> assertThat(countSubstr(el, "A", false, false), equalTo(7L)));
//  }

//  @Test
//  public void countSubstrTestFixture_caseSensitiveTrueAndOverlappingTrue() {
//    assertThat(countSubstr("aaa", "aa", true, true), equalTo(2L));
//  }

  @Test
  public void endsWith_caseSensitive_ShouldBeTrueWhenStringEndsWithSearchStr() {
    String[] fixture = {
        "foo bar" ,
        "bar"
    };
    Arrays.stream(fixture).forEach(el -> assertTrue(endsWith().apply(el , "bar")));
  }

  @Test
  public void endsWith_notCaseSensitive_ShouldBeTrueWhenStringEndsWithSearchStr() {
    String[] fixture = {
        "foo bar" ,
        "bar"
    };
    Arrays.stream(fixture).forEach(el -> assertTrue(endsWithCaseSensitive().apply(el , "BAR" , false)));
  }

//  @Test
//  public void endsWith_caseSensitiveAtPosition_ShouldBeTrueWhenStringEndsWithSearchStr() {
//    String[] fixture = {
//        "foo barr",
//        "barr"
//    };
//    Arrays.stream(fixture).forEach(el -> assertTrue(endsWith(el, "bar", el.length() - 1, true)));
//  }

//  @Test
//  public void endsWith_notCaseSensitiveAtPosition_ShouldBeTrueWhenStringEndsWithSearchStr() {
//    String[] fixture = {
//        "foo barr",
//        "barr"
//    };
//    Arrays.stream(fixture).forEach(el -> assertTrue(endsWithCaseSensitive().apply(el, "BAR", el.length() - 1, false)));
//  }

  @Test
  public void ensureLeft_shouldEnsureValueStartsWithFoo() {
    String[] fixture = {
        "foobar" ,
        "bar"
    };

    Arrays.stream(fixture).forEach(el -> assertThat(ensureLeft().apply(el , "foo") , equalTo("foobar")));
  }

  @Test
  public void ensureLeft_notCaseSensitive_shouldEnsureValueStartsWithFoo() {
    assertThat(ensureLeftCaseSensitive().apply("foobar" , "FOO" , false) , equalTo("foobar"));
    assertThat(ensureLeftCaseSensitive().apply("bar" , "FOO" , false) , equalTo("FOObar"));
  }

  @Test
  public void base64Decode_shouldDecodeABase64DecodedValueToString() {
    assertThat(base64Decode().apply("c3RybWFu") , equalTo("strman"));
    assertThat(base64Decode().apply("Zm9v") , equalTo("foo"));
    assertThat(base64Decode().apply("YmFy") , equalTo("bar"));
//    assertThat(base64Decode().apply("YsOhciE=") , equalTo("bár!"));
//    assertThat(base64Decode().apply("5ryi") , equalTo("漢"));
  }

  @Test
  public void base64Encode_shouldEncodeAString() {
    assertThat(base64Encode().apply("strman") , equalTo("c3RybWFu"));
    assertThat(base64Encode().apply("foo") , equalTo("Zm9v"));
    assertThat(base64Encode().apply("bar") , equalTo("YmFy"));
//    assertThat(base64Encode().apply("bár!") , equalTo("YsOhciE="));
//    assertThat(base64Encode().apply("漢") , equalTo("5ryi"));
  }

  @Test
  public void binDecode_shouldDecodeABinaryStringToAValue() {
    assertThat(
        binDecode().apply("000000000111001100000000011101000000000001110010000000000110110100000000011000010000000001101110") ,
        equalTo("strman"));

    assertThat(binDecode().apply("0110111100100010") , equalTo("漢"));
    assertThat(binDecode().apply("0000000001000001") , equalTo("A"));
    assertThat(binDecode().apply("0000000011000001") , equalTo("Á"));
    assertThat(binDecode().apply("00000000010000010000000001000001") , equalTo("AA"));
  }

  @Test
  public void binEncode_shouldEncodeAStringToBinaryFormat() {
    assertThat(binEncode().apply("漢") , equalTo("0110111100100010"));
    assertThat(binEncode().apply("A") , equalTo("0000000001000001"));
    assertThat(binEncode().apply("Á") , equalTo("0000000011000001"));
    assertThat(binEncode().apply("AA") , equalTo("00000000010000010000000001000001"));
  }

  @Test
  public void decDecode_shouldDecodeDecimalStringToString() {
    assertThat(decDecode().apply("28450") , equalTo("漢"));
    assertThat(decDecode().apply("00065") , equalTo("A"));
    assertThat(decDecode().apply("00193") , equalTo("Á"));
    assertThat(decDecode().apply("0006500065") , equalTo("AA"));
  }

  @Test
  public void decEncode_shouldEncodeStringToDecimal() {
    assertThat(decEncode().apply("漢") , equalTo("28450"));
    assertThat(decEncode().apply("A") , equalTo("00065"));
    assertThat(decEncode().apply("Á") , equalTo("00193"));
    assertThat(decEncode().apply("AA") , equalTo("0006500065"));
  }

  @Test
  public void ensureRight_shouldEnsureStringEndsWithBar() {
    final String[] fixture = {
        "foo" , "foobar" , "fooBAR"
    };
    assertThat(Arrays.stream(fixture).map(el -> ensureRightCaseSensitive().apply(el , "bar" , false)).collect(toList()) , hasItems("foobar" , "foobar" , "fooBAR"));
    assertThat(Arrays.stream(fixture).map(el -> ensureRight().apply(el , "bar")).collect(toList()) , hasItems("foobar" , "foobar" , "fooBARbar"));
  }

  @Test
  public void first_shouldReturnFirstThreeCharsOfString() {
    final String[] fixture = {
        "foo" , "foobar"
    };
    Arrays.stream(fixture).forEach(el ->
        assertThat(
            first().apply(el , 3) ,
            equalTo("foo")));
  }

  @Test
  public void head_shouldReturnFirstCharOfString() {
    final String[] fixture = {
        "foo" , "foobar"
    };

    Arrays.stream(fixture)
        .forEach(el ->
            assertThat(
                head().apply(el) ,
                equalTo("f")));
  }

//  @Test
//  public void format_shouldFormatStringsToFooBar() {
//    assertThat(format().apply("{0} bar", "foo"), equalTo("foo bar"));
//    assertThat(format().apply("foo {0}", "bar"), equalTo("foo bar"));
//    assertThat(format().apply("foo {0}", "bar", "foo"), equalTo("foo bar"));
//    assertThat(format().apply("{0} {1}", "foo", "bar"), equalTo("foo bar"));
//    assertThat(format().apply("{1} {0}", "bar", "foo"), equalTo("foo bar"));
//  }

//  @Test(expected = IllegalArgumentException.class)
//  public void format_shouldThrowExceptionWhenValueDoesNotExist() {
//    assertThat(format().apply("{1} {0}"), equalTo("{1} {0}"));
//  }

  @Test
  public void hexDecode_shouldDecodeHexCodeToString() {
    assertThat(hexDecode().apply("6f22") , equalTo("漢"));
    assertThat(hexDecode().apply("0041") , equalTo("A"));
    assertThat(hexDecode().apply("00c1") , equalTo("Á"));
    assertThat(hexDecode().apply("00410041") , equalTo("AA"));
  }

  @Test
  public void hexEncode_shouldEncodeStringToHexadecimalFormat() {
    assertThat(hexEncode().apply("漢") , equalTo("6f22"));
    assertThat(hexEncode().apply("A") , equalTo("0041"));
    assertThat(hexEncode().apply("Á") , equalTo("00c1"));
    assertThat(hexEncode().apply("AA") , equalTo("00410041"));
  }

  @Test
  public void indexOf_shouldBeTrueWhenNeedleExists() {
    final String value = "foobar";
    assertThat(indexOfCoseSensitive().apply(value , "f" , 0 , true) , equalTo(0));
    assertThat(indexOfCoseSensitive().apply(value , "o" , 0 , true) , equalTo(1));
    assertThat(indexOfCoseSensitive().apply(value , "b" , 0 , true) , equalTo(3));
    assertThat(indexOfCoseSensitive().apply(value , "a" , 0 , true) , equalTo(4));
    assertThat(indexOfCoseSensitive().apply(value , "r" , 0 , true) , equalTo(5));
    assertThat(indexOfCoseSensitive().apply(value , "t" , 0 , true) , equalTo(- 1));
  }

  @Test
  public void indexOf_shouldBeTrueWhenNeedleExistCaseSensitive() {
    final String value = "foobar";
    assertThat(indexOfCoseSensitive().apply(value , "F" , 0 , false) , equalTo(0));
    assertThat(indexOfCoseSensitive().apply(value , "O" , 0 , false) , equalTo(1));
    assertThat(indexOfCoseSensitive().apply(value , "B" , 0 , false) , equalTo(3));
    assertThat(indexOfCoseSensitive().apply(value , "A" , 0 , false) , equalTo(4));
    assertThat(indexOfCoseSensitive().apply(value , "R" , 0 , false) , equalTo(5));
    assertThat(indexOfCoseSensitive().apply(value , "T" , 0 , false) , equalTo(- 1));
  }

//  @Test
//  public void inequal_shouldTestInequalityOfStrings() {
//    assertThat(unequal("a", "b"), equalTo(true));
//    assertThat(unequal("a", "a"), equalTo(false));
//    assertThat(unequal("0", "1"), equalTo(true));
//  }

  @Test
  public void insert_shouldInsertStringAtIndex() {
    assertThat(insert().apply("fbar" , "oo" , 1) , equalTo("foobar"));
    assertThat(insert().apply("foo" , "bar" , 3) , equalTo("foobar"));
    assertThat(insert().apply("foobar" , "x" , 5) , equalTo("foobaxr"));
    assertThat(insert().apply("foobar" , "x" , 6) , equalTo("foobarx"));
    assertThat(insert().apply("foo bar" , "asadasd" , 100) , equalTo("foo bar"));
  }

  @Test
  public void isLowerCase_shouldBeTrueWhenStringIsLowerCase() {
    assertThat(isLowerCase().apply("") , equalTo(true));
    assertThat(isLowerCase().apply("foo") , equalTo(true));
    assertThat(isLowerCase().apply("foobarfoo") , equalTo(true));
  }

  @Test
  public void isLowerCase_shouldBeFalseWhenStringIsNotLowerCase() {
    assertThat(isLowerCase().apply("Foo") , equalTo(false));
    assertThat(isLowerCase().apply("foobarfooA") , equalTo(false));
  }

  @Test
  public void isUpperCase_shouldBeTrueWhenStringIsUpperCase() {
    assertThat(isUpperCase().apply("") , equalTo(true));
    assertThat(isUpperCase().apply("FOO") , equalTo(true));
    assertThat(isUpperCase().apply("FOOBARFOO") , equalTo(true));
  }

  @Test
  public void isUpperCase_shouldBeFalseWhenStringIsNotUpperCase() {
    assertThat(isUpperCase().apply("Foo") , equalTo(false));
    assertThat(isUpperCase().apply("foobarfooA") , equalTo(false));
  }

  @Test
  public void last_shouldReturnLastNChars() {
    assertThat(last().apply("foo" , 3) , equalTo("foo"));
    assertThat(last().apply("foobarfoo" , 3) , equalTo("foo"));
    assertThat(last().apply("" , 3) , equalTo(""));
    assertThat(last().apply("f" , 3) , equalTo("f"));
  }

  @Test
  public void leftPad_shouldAddPaddingOnTheLeft() {
    assertThat(leftPad().apply("1" , "0" , 5) , equalTo("00001"));
    assertThat(leftPad().apply("01" , "0" , 5) , equalTo("00001"));
    assertThat(leftPad().apply("001" , "0" , 5) , equalTo("00001"));
    assertThat(leftPad().apply("0001" , "0" , 5) , equalTo("00001"));
    assertThat(leftPad().apply("00001" , "0" , 5) , equalTo("00001"));
  }

  @Test
  public void isString_shouldBeFalseWhenValueIsNotString() {
    assertFalse(isString(1));
    assertFalse(isString(false));
    assertFalse(isString(1.2));
    assertFalse(isString(new String[]{}));
  }

  @Test
  public void isString_shouldBeTrueWhenValueIsString() {
    assertTrue(isString("string"));
    assertTrue(isString(""));
  }

  @Test
  public void lastIndexOf_shouldFindIndexOfNeedle() {
    final String value = "foobarfoobar";
    assertThat(lastIndexOf().apply(value , "f") , equalTo(6));
    assertThat(lastIndexOf().apply(value , "o") , equalTo(8));
    assertThat(lastIndexOf().apply(value , "b") , equalTo(9));
    assertThat(lastIndexOf().apply(value , "a") , equalTo(10));
    assertThat(lastIndexOf().apply(value , "r") , equalTo(11));
    assertThat(lastIndexOf().apply(value , "t") , equalTo(- 1));
  }

  @Test
  public void lastIndexOf_shouldFindIndexOfNeedleCaseInsensitive() {
    final String value = "foobarfoobar";
    assertThat(lastIndexOfCaseSensitive().apply(value , "F" , false) , equalTo(6));
    assertThat(lastIndexOfCaseSensitive().apply(value , "O" , false) , equalTo(8));
    assertThat(lastIndexOfCaseSensitive().apply(value , "B" , false) , equalTo(9));
    assertThat(lastIndexOfCaseSensitive().apply(value , "A" , false) , equalTo(10));
    assertThat(lastIndexOfCaseSensitive().apply(value , "R" , false) , equalTo(11));
    assertThat(lastIndexOfCaseSensitive().apply(value , "T" , false) , equalTo(- 1));
  }

  @Test
  public void leftTrim_shouldRemoveSpacesOnLeft() {
    assertThat(leftTrim().apply("     strman") , equalTo("strman"));
    assertThat(leftTrim().apply("     strman  ") , equalTo("strman  "));
  }

//  @Test
//  public void prepend_shouldPrependStrings() {
//    assertThat(prepend().apply("r", "f", "o", "o", "b", "a"), equalTo("foobar"));
//    assertThat(prepend().apply("foobar"), equalTo("foobar"));
//    assertThat(prepend().apply("", "foobar"), equalTo("foobar"));
//    assertThat(prepend().apply("bar", "foo"), equalTo("foobar"));
//  }

  @Test
  public void prependArray_shouldPrependStrings() {
    assertThat(prependArray().apply("r" , new String[]{"f" , "o" , "o" , "b" , "a"}) , equalTo("foobar"));
    assertThat(prependArray().apply("foobar" , new String[0]) , equalTo("foobar"));
    assertThat(prependArray().apply("" , new String[]{"foobar"}) , equalTo("foobar"));
    assertThat(prependArray().apply("bar" , new String[]{"foo"}) , equalTo("foobar"));
  }

  @Test
  public void removeEmptyStrings_shouldRemoveEmptyStrings() {
    assertThat(removeEmptyStrings().apply(new String[]{"aa" , "" , "   " , "bb" , "cc" , null}) , arrayContaining("aa" , "bb" , "cc"));
    assertThat(removeEmptyStrings().apply(new String[0]) , emptyArray());
  }

  @Test
  public void removeLeft_shouldRemoveStringFromLeft() {
    final String[] fixture = {
        "foobar" ,
        "bar"
    };

    Arrays.stream(fixture).forEach(el -> assertThat(removeLeft().apply(el , "foo") , equalTo("bar")));
    assertThat(removeLeft().apply("barfoo" , "foo") , equalTo("barfoo"));
    assertThat(removeLeft().apply("foofoo" , "foo") , equalTo("foo"));
  }

  @Test
  public void removeLeft_shouldRemoveStringFromLeftCaseInSensitive() {
    final String[] fixture = {
        "foobar" ,
        "bar"
    };

    Arrays.stream(fixture)
        .forEach(el ->
            assertThat(
                removeFromLeftCaseSensitive().apply(el , "FOO" , false) ,
                equalTo("bar")));
  }

  @Test
  public void removeNonWords_shouldRemoveAllNonWordsFromInputString() {

    final String[] fixture = {
        "foo bar" ,
        "foo&bar-" ,
        "foobar"
    };

    Arrays.stream(fixture).forEach(el -> assertThat(removeNonWord().apply(el) , equalTo("foobar")));
  }

  @Test
  public void removeRight_shouldRemoveStringFromRight() {
    final String[] fixture = {
        "foobar" ,
        "foo"
    };

    Arrays.stream(fixture).forEach(el -> assertThat(removeRight().apply(el , "bar") , equalTo("foo")));
    assertThat(removeRight().apply("barfoo" , "bar") , equalTo("barfoo"));
    assertThat(removeRight().apply("barbar" , "bar") , equalTo("bar"));
  }

  @Test
  public void removeRight_shouldRemoveStringFromRightCaseInSensitive() {
    final String[] fixture = {
        "foobar" ,
        "foo"
    };

    Arrays.stream(fixture).forEach(el -> assertThat(removeRightCaseSensitive().apply(el , "BAR" , false) , equalTo("foo")));
  }

  @Test
  public void removeSpaces_shouldRemoveSpacesInTheString() {
    final String[] fixture = {
        "foo bar" ,
        "foo bar " ,
        " foo bar" ,
        " foo bar "
    };
    Arrays.stream(fixture).forEach(el -> assertThat(removeSpaces().apply(el) , equalTo("foobar")));
  }

  @Test
  public void repeat_shouldRepeatAStringNTimes() {
    assertThat(repeat().apply("1" , 1) , equalTo("1"));
    assertThat(repeat().apply("1" , 2) , equalTo("11"));
    assertThat(repeat().apply("1" , 3) , equalTo("111"));
    assertThat(repeat().apply("1" , 4) , equalTo("1111"));
    assertThat(repeat().apply("1" , 5) , equalTo("11111"));
  }

  @Test
  public void replace_shouldReplaceAllOccurrencesOfString() {
    assertThat(replace().apply("foo bar" , "foo" , "bar" , true) , equalTo("bar bar"));
    assertThat(replace().apply("foo bar foo" , "foo" , "bar" , true) , equalTo("bar bar bar"));
  }

  @Test
  public void replace_shouldReplaceAllOccurrencesOfStringCaseSensitive() {
    assertThat(replace().apply("FOO bar" , "foo" , "bar" , false) , equalTo("bar bar"));
    assertThat(replace().apply("FOO bar foo" , "foo" , "bar" , false) , equalTo("bar bar bar"));
  }

  @Test
  public void reverse_shouldReverseInputString() {
    assertThat(reverse().apply("") , equalTo(""));
    assertThat(reverse().apply("foo") , equalTo("oof"));
    assertThat(reverse().apply("shekhar") , equalTo("rahkehs"));
    assertThat(reverse().apply("bar") , equalTo("rab"));
    assertThat(reverse().apply("foo_") , equalTo("_oof"));
    assertThat(reverse().apply("f") , equalTo("f"));
  }

  @Test
  public void rightPad_shouldRightPadAString() {
    assertThat(rightPad().apply("1" , "0" , 5) , equalTo("10000"));
    assertThat(rightPad().apply("10" , "0" , 5) , equalTo("10000"));
    assertThat(rightPad().apply("100" , "0" , 5) , equalTo("10000"));
    assertThat(rightPad().apply("1000" , "0" , 5) , equalTo("10000"));
    assertThat(rightPad().apply("10000" , "0" , 5) , equalTo("10000"));
    assertThat(rightPad().apply("10000000" , "0" , 5) , equalTo("10000000"));
  }

  @Test
  public void rightTrim_shouldRemoveSpacesFromTheRight() {
    assertThat(rightTrim().apply("strman   ") , equalTo("strman"));
    assertThat(rightTrim().apply("   strman") , equalTo("   strman"));
    assertThat(rightTrim().apply("strman") , equalTo("strman"));
  }

//  @Test
//  public void safeTruncate_shouldSafelyTruncateStrings() {
//    assertThat(safeTruncate("foo bar", 0, "."), equalTo(""));
//    assertThat(safeTruncate("foo bar", 4, "."), equalTo("foo."));
//    assertThat(safeTruncate("foo bar", 3, "."), equalTo("."));
//    assertThat(safeTruncate("foo bar", 2, "."), equalTo("."));
//    assertThat(safeTruncate("foo bar", 7, "."), equalTo("foo bar"));
//    assertThat(safeTruncate("foo bar", 8, "."), equalTo("foo bar"));
//    assertThat(safeTruncate("A Javascript string manipulation library.", 16, "..."), equalTo("A Javascript..."));
//    assertThat(safeTruncate("A Javascript string manipulation library.", 15, "..."), equalTo("A Javascript..."));
//    assertThat(safeTruncate("A Javascript string manipulation library.", 14, "..."), equalTo("A..."));
//    assertThat(safeTruncate("A Javascript string manipulation library.", 13, "..."), equalTo("A..."));
//  }

  @Test
  public void truncate_shouldTruncateString() {
    assertThat(truncate().apply("foo bar" , 0 , ".") , equalTo(""));
    assertThat(truncate().apply("foo bar" , 3 , ".") , equalTo("fo."));
    assertThat(truncate().apply("foo bar" , 2 , ".") , equalTo("f."));
    assertThat(truncate().apply("foo bar" , 4 , ".") , equalTo("foo."));
    assertThat(truncate().apply("foo bar" , 7 , ".") , equalTo("foo bar"));
    assertThat(truncate().apply("foo bar" , 8 , ".") , equalTo("foo bar"));
    assertThat(truncate().apply("A Javascript string manipulation library." , 16 , "...") , equalTo("A Javascript ..."));
    assertThat(truncate().apply("A Javascript string manipulation library." , 15 , "...") , equalTo("A Javascript..."));
    assertThat(truncate().apply("A Javascript string manipulation library." , 14 , "...") , equalTo("A Javascrip..."));
  }

//  @Test
//  public void htmlDecode_shouldDecodeToHtml() throws Exception {
//    assertThat(htmlDecode("&aacute;"), equalTo("\u00E1"));
//    assertThat(htmlDecode("&SHcy;"), equalTo("Ш"));
//    assertThat(htmlDecode("&ZHcy;"), equalTo("Ж"));
//    assertThat(htmlDecode("&boxdl;"), equalTo("┐"));
//  }
//
//  @Test
//  public void htmlEncode_shouldBeEncodedToHtmlEntities() throws Exception {
//    assertThat(htmlEncode("á"), equalTo("&aacute;"));
//    assertThat(htmlEncode("áéíóú"), equalTo("&aacute;&eacute;&iacute;&oacute;&uacute;"));
//    assertThat(htmlEncode("Ш"), equalTo("&SHcy;"));
//    assertThat(htmlEncode("Ж"), equalTo("&ZHcy;"));
//    assertThat(htmlEncode("┐"), equalTo("&boxdl;"));
//  }

  @Test
  public void shuffle_shouldShuffleAString() throws Exception {
    assertThat(shuffle().apply("sjkhvalkehc ök") , not(equalTo("shekhar")));
    assertThat(shuffle().apply("strman") , not(equalTo("strman")));
    assertThat(shuffle().apply("") , equalTo(""));
    assertThat(shuffle().apply("s") , equalTo("s"));
  }

//  @Test
//  public void slugify_shouldBeFooBar() throws Exception {
//    String[] fixture = {
//        "foo bar",
//        "foo bar.",
//        "foo bar ",
//        " foo bar",
//        " foo bar ",
//        "foo------bar",
//        "fóõ bár",
//        "foo ! bar",
//        "foo ~~ bar",
//        "foo     bar",
//        "FOO     bar"
//    };
//
//    Arrays.stream(fixture)
//        .forEach(el -> assertThat(String.format("slugify(%s) should be foo-bar ", el),
//            slugify().apply(el), equalTo("foo-bar")));
//  }

//  @Test
//  public void slugify_shouldBeFooAndBar() throws Exception {
//    String[] fixture = {
//        "foo&bar",
//        "foo&bar.",
//        "foo&bar ",
//        " foo&bar",
//        " foo&bar ",
//        "foo&bar",
//        "fóõ-and---bár",
//        "foo  &    bar",
//        "FOO  &   bar"
//    };
//
//    Arrays.stream(fixture).forEach(el -> assertThat(String.format("slugify(%s) should be foo-and-bar ", el),
//        slugify(el), equalTo("foo-and-bar")));
//  }
//
//  @Test
//  public void transliterate_shouldTransliterateTheText() throws Exception {
//    assertThat(transliterate().apply("fóõ bár"), equalTo("foo bar"));
//  }

  @Test
  public void surround_shouldSurroundStringWithPrefixAndSuffix() throws Exception {
    assertThat(surround().apply("foo" , "bar" , null) , equalTo("barfoobar"));
    assertThat(surround().apply("shekhar" , "***" , null) , equalTo("***shekhar***"));
    assertThat(surround().apply("" , ">" , null) , equalTo(">>"));
    assertThat(surround().apply("bar" , "" , null) , equalTo("bar"));
    assertThat(surround().apply("f" , null , null) , equalTo("f"));
    assertThat(surround().apply("div" , "<" , ">") , equalTo("<div>"));
  }

  @Test
  public void toCamelCase_shouldConvertStringToCamelCase() throws Exception {
    String[] fixture = {
        "CamelCase" ,
        "camelCase" ,
        "Camel case" ,
        "Camel  case" ,
        "camel Case" ,
        "camel-case" ,
        "-camel--case" ,
        "camel_case" ,
        "     camel_case" ,
    };
    Arrays.stream(fixture).forEach(el -> assertThat(String.format("toCameCase(%s) should be camelCase" , el) , toCamelCase().apply(el) , equalTo("camelCase")));

    assertThat(toCamelCase().apply("c") , equalTo("c"));
  }

  @Test
  public void toDeCamelCase_shouldDeCamelCaseAString() throws Exception {
    String[] fixture = {
        "deCamelize" ,
        "de-Camelize" ,
        "de camelize" ,
        "de  camelize" ,
        "de Camelize" ,
        "de-camelize" ,
        "-de--camelize" ,
        "de_camelize" ,
        "     de_camelize"
    };

    Arrays
        .stream(fixture)
        .forEach(el ->
            assertThat(
                String.format("toDecamelize(%s) should be de-camelize" , el) ,
                toDecamelize().apply(el , " ") ,
                equalTo("de camelize")));

    assertThat(toDecamelize().apply("camelCase" , "_") , equalTo("camel_case"));
  }

  @Test
  public void toKebabCase_shouldKebabCaseAString() throws Exception {
    String[] fixture = {
        "deCamelize" ,
        "de-Camelize" ,
        "de camelize" ,
        "de  camelize" ,
        "de Camelize" ,
        "de-camelize" ,
        "-de--camelize" ,
        "de_camelize" ,
        "     de_camelize"
    };

    Arrays.stream(fixture).forEach(el ->
        assertThat(String.format("toKebabCase(%s) should be de-camelize" , el) , toKebabCase().apply(el) , equalTo("de-camelize")));
  }

  @Test
  public void toSnakeCase_shouldSnakeCaseAString() throws Exception {
    String[] fixture = {
        "deCamelize" ,
        "de-Camelize" ,
        "de camelize" ,
        "de  camelize" ,
        "de Camelize" ,
        "de-camelize" ,
        "-de--camelize" ,
        "de_camelize" ,
        "     de_camelize"
    };

    Arrays.stream(fixture)
        .forEach(el ->
            assertThat(
                String.format("toSnakeCase(%s) should be de_camelize" , el) ,
                toSnakeCase().apply(el) ,
                equalTo("de_camelize"))
        );
  }

//  @Test
//  public void unequal_shouldTestInequalityOfStrings() throws Exception {
//    assertThat(unequal("a", "b"), equalTo(true));
//    assertThat(unequal("a", "a"), equalTo(false));
//    assertThat(unequal("0", "1"), equalTo(true));
//  }


  @Test
  public void removeLeft_shouldNotLowercaseWhenCaseInsensitive() throws Exception {
    String result = removeFromLeftCaseSensitive().apply("This HAS A THIS IN FRONT" , "THIS " , false);
    assertThat(
        result ,
        is("HAS A THIS IN FRONT"));
  }

  @Test
  public void replace_shouldNotLowercaseWhenCaseInsensitive() throws Exception {
    String result = replace().apply("One and two and THREE and Four" , "and" , "&" , false);
    assertThat(
        result ,
        is("One & two & THREE & Four"));
  }

  @Test
  public void removeRight_shouldNotLowercaseWhenCaseInsensitive() throws Exception {
    String result = removeRightCaseSensitive().apply("Remove the END at the end" , " END" , false);
    assertThat(result , is("Remove the END at the"));
  }

//  @Test
//  public void transliterate_shouldDeburrTheString() throws Exception {
//    String result = transliterate("déjà vu");
//    assertThat(result, is(equalTo("deja vu")));
//  }
//
//  @Ignore
//  public void htmlEncode_shouldConvertCharactersToTheirHtmlEntities() throws Exception {
//    String result = htmlEncode("fred, barney, & pebbles");
//    assertThat(result, is(equalTo("fred, barney, &amp; pebbles")));
//  }

  @Disabled
  @Test
  public void kebabCase_shouldConvertAStringToKebabCase() throws Exception {
    String[] input = {
        "Foo Bar" ,
        "fooBar" ,
        "__FOO_BAR__"
    };

    Arrays.stream(input).forEach(el ->
        assertThat(String
                .format("%s should be foo-bar" , el) ,
            toKebabCase().apply(el) , is(equalTo("foo-bar"))));

  }

//  @Ignore
//  public void snakeCase_shouldConvertAStringToSnakecase() throws Exception {
//    String[] input = {
//        "Foo Bar",
//        "fooBar",
//        "--FOO-BAR--"
//    };
//
//    Arrays.stream(input).forEach(el ->
//        assertThat(String.format("%s should be foo_bar", el),
//            toSnakeCase(el), is(equalTo("foo_bar"))));
//
//  }

  @Test
  public void join_shouldJoinArrayOfStringIntoASingleString() throws Exception {
    String[] strings = {
        "hello" ,
        "world" ,
        "123"
    };
    assertThat(join().apply(strings , ";") , is(equalTo("hello;world;123")));
  }

  @Test()
  public void join_shouldThrowIllegalArgumentExceptionWhenSeparatorIsNull() throws Exception {
    String[] strings = {
        "hello" ,
        "world" ,
        "123"
    };

    assertThrows(NullPointerException.class , () -> join().apply(strings , null));
  }

  @Test
  public void join_shouldReturnEmptyStringWhenInputArrayIsEmpty() throws Exception {
    String[] emptyArray = {};
    assertThat(join().apply(emptyArray , ",") , is(equalTo("")));
  }

  @Test
  public void capitalize_shouldCapitalizeFirstCharacterOfString() throws Exception {
    String[] strings = {
        "FRED" ,
        "fRED" ,
        "fred"
    };
    Arrays.stream(strings)
        .forEach(el ->
            assertThat(
                String.format("%s should be Fred" , el) ,
                capitalize().apply(el) ,
                equalTo("Fred")));
  }

  @Test
  public void lowerFirst_shouldLowercasedFirstCharacterOfString() throws Exception {
    assertThat(lowerFirst().apply("FRED") , is(equalTo("fRED")));
    assertThat(lowerFirst().apply("fred") , is(equalTo("fred")));
    assertThat(lowerFirst().apply("Fred") , is(equalTo("fred")));
  }

//  @Test
//  public void isEnclosedBetween_shouldChekcWhetherStringIsEnclosed() throws Exception {
//    assertThat(isEnclosedBetween("{{shekhar}}", "{{", "}}"), is(true));
//    assertThat(isEnclosedBetween("shekhar", "{{", "}}"), is(false));
//    assertThat(isEnclosedBetween("*shekhar*", "*"), is(true));
//    assertThat(isEnclosedBetween("shekhar", "*"), is(false));
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void isEnclosedBetween_shouldThrowIllegalArgumentExceptionWhenEncloserIsNull() throws Exception {
//    assertThat(isEnclosedBetween("shekhar", null), is(false));
//  }

  @Test
  public void words_shouldConvertTextToWords() throws Exception {
    final String line = "This is a string, with words!";
    assertThat(wordStream().apply(line).toArray() , is(new String[]{"This" , "is" , "a" , "string" , "with" , "words"}));
  }

  @Test
  public void upperFirst_shouldConvertFirstCharToUpperCase() throws Exception {
    assertThat(upperFirst().apply("fred") , is("Fred"));
  }

  @Test
  public void upperFirst_shouldReturnSameStringIfFirstCharIsUpperCase() throws Exception {
    assertThat(upperFirst().apply("FRED") , is("FRED"));
  }

//  @Test
//  public void trimStart_shouldRemoveAllWhitespaceAtStart() throws Exception {
//    assertThat(trimStart().apply("   abc   "), is(Optional.of("abc   ")));
//    assertThat(trimStart().apply("abc   "), is(Optional.of("abc   ")));
//    assertThat(trimStart().apply("abc"), is(Optional.of("abc")));
//    assertThat(trimStart().apply(""), is(Optional.empty()));
//    assertThat(trimStart().apply(null), is(Optional.empty()));
//  }
//
//  @Test
//  public void trimStart_shouldRemoveSpecialCharactersAtStart() throws Exception {
//    assertThat(trimStart("-_-abc-_-", "_", "-"), is(Optional.of("abc-_-")));
//    assertThat(trimStart("-_-!abc-_-", "_", "-", "!"), is(Optional.of("abc-_-")));
//    assertThat(trimStart("-_-#abc-_-", "_", "-", "!", "#"), is(Optional.of("abc-_-")));
//  }
//
//  @Test
//  public void trimEnd_shouldRemoveAllTrailingWhitespace() throws Exception {
//    assertThat(trimEnd("   abc   "), is(Optional.of("   abc")));
//    assertThat(trimEnd("abc   "), is(Optional.of("abc")));
//    assertThat(trimEnd("abc"), is(Optional.of("abc")));
//    assertThat(trimEnd(""), is(Optional.empty()));
//    assertThat(trimEnd(null), is(Optional.empty()));
//  }
//
//  @Test
//  public void trimEnd_shouldRemoveAllTrailingSpecialCharacters() throws Exception {
//    assertThat(trimEnd("-_-abc-_-", "_", "-"), is(Optional.of("-_-abc")));
//    assertThat(trimEnd("-_-abc!-_-", "_", "-", "!"), is(Optional.of("-_-abc")));
//    assertThat(trimEnd("-_-abc#-_-", "_", "-", "!", "#"), is(Optional.of("-_-abc")));
//  }

}