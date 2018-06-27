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
package junit.org.rapidpm.frp;

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
import static org.rapidpm.frp.StringFunctions.append;
import static org.rapidpm.frp.StringFunctions.appendArray;
import static org.rapidpm.frp.StringFunctions.appendStream;
import static org.rapidpm.frp.StringFunctions.at;
import static org.rapidpm.frp.StringFunctions.base64Decode;
import static org.rapidpm.frp.StringFunctions.base64Encode;
import static org.rapidpm.frp.StringFunctions.binDecode;
import static org.rapidpm.frp.StringFunctions.binEncode;
import static org.rapidpm.frp.StringFunctions.capitalize;
import static org.rapidpm.frp.StringFunctions.chars;
import static org.rapidpm.frp.StringFunctions.collapseWhitespace;
import static org.rapidpm.frp.StringFunctions.contains;
import static org.rapidpm.frp.StringFunctions.containsAll;
import static org.rapidpm.frp.StringFunctions.containsAllCaseSensitive;
import static org.rapidpm.frp.StringFunctions.containsCaseSensitive;
import static org.rapidpm.frp.StringFunctions.countSubStr;
import static org.rapidpm.frp.StringFunctions.countSubStrCaseSensitive;
import static org.rapidpm.frp.StringFunctions.decDecode;
import static org.rapidpm.frp.StringFunctions.decEncode;
import static org.rapidpm.frp.StringFunctions.endsWith;
import static org.rapidpm.frp.StringFunctions.endsWithCaseSensitive;
import static org.rapidpm.frp.StringFunctions.ensureLeft;
import static org.rapidpm.frp.StringFunctions.ensureLeftCaseSensitive;
import static org.rapidpm.frp.StringFunctions.ensureRight;
import static org.rapidpm.frp.StringFunctions.ensureRightCaseSensitive;
import static org.rapidpm.frp.StringFunctions.first;
import static org.rapidpm.frp.StringFunctions.head;
import static org.rapidpm.frp.StringFunctions.hexDecode;
import static org.rapidpm.frp.StringFunctions.hexEncode;
import static org.rapidpm.frp.StringFunctions.indexOfCoseSensitive;
import static org.rapidpm.frp.StringFunctions.insert;
import static org.rapidpm.frp.StringFunctions.isLowerCase;
import static org.rapidpm.frp.StringFunctions.isString;
import static org.rapidpm.frp.StringFunctions.isUpperCase;
import static org.rapidpm.frp.StringFunctions.join;
import static org.rapidpm.frp.StringFunctions.last;
import static org.rapidpm.frp.StringFunctions.lastIndexOf;
import static org.rapidpm.frp.StringFunctions.lastIndexOfCaseSensitive;
import static org.rapidpm.frp.StringFunctions.leftPad;
import static org.rapidpm.frp.StringFunctions.leftTrim;
import static org.rapidpm.frp.StringFunctions.lowerFirst;
import static org.rapidpm.frp.StringFunctions.notEmpty;
import static org.rapidpm.frp.StringFunctions.notStartsWith;
import static org.rapidpm.frp.StringFunctions.prependArray;
import static org.rapidpm.frp.StringFunctions.removeEmptyStrings;
import static org.rapidpm.frp.StringFunctions.removeFromLeftCaseSensitive;
import static org.rapidpm.frp.StringFunctions.removeLeft;
import static org.rapidpm.frp.StringFunctions.removeNonWord;
import static org.rapidpm.frp.StringFunctions.removeRight;
import static org.rapidpm.frp.StringFunctions.removeRightCaseSensitive;
import static org.rapidpm.frp.StringFunctions.removeSpaces;
import static org.rapidpm.frp.StringFunctions.repeat;
import static org.rapidpm.frp.StringFunctions.replace;
import static org.rapidpm.frp.StringFunctions.reverse;
import static org.rapidpm.frp.StringFunctions.rightPad;
import static org.rapidpm.frp.StringFunctions.rightTrim;
import static org.rapidpm.frp.StringFunctions.shuffle;
import static org.rapidpm.frp.StringFunctions.surround;
import static org.rapidpm.frp.StringFunctions.toCamelCase;
import static org.rapidpm.frp.StringFunctions.toDecamelize;
import static org.rapidpm.frp.StringFunctions.toKebabCase;
import static org.rapidpm.frp.StringFunctions.toSnakeCase;
import static org.rapidpm.frp.StringFunctions.truncate;
import static org.rapidpm.frp.StringFunctions.upperFirst;
import static org.rapidpm.frp.StringFunctions.wordStream;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.rapidpm.frp.model.Result;

/**
 * Created by svenruppert on 25.04.17.
 */
public class StringFunctionsTest {


  @Test
  public void collapseWhitespace001() throws Exception {
    assertEquals("" , collapseWhitespace().apply(""));
  }

  @Test
  public void collapseWhitespace002() throws Exception {
    assertEquals("A" , collapseWhitespace().apply("A"));
  }

  @Test
  public void collapseWhitespace002a() throws Exception {
    assertEquals("A" , collapseWhitespace().apply(" A"));
  }

  @Test
  public void collapseWhitespace002b() throws Exception {
    assertEquals("A" , collapseWhitespace().apply("A "));
  }

  @Test
  public void collapseWhitespace002c() throws Exception {
    assertEquals("A" , collapseWhitespace().apply(" A "));
  }

  @Test
  public void collapseWhitespace003() throws Exception {
    assertEquals("A B" , collapseWhitespace().apply("A B"));
  }

  @Test
  public void collapseWhitespace004() throws Exception {
    assertEquals("A B" , collapseWhitespace().apply(" A B"));
  }

  @Test
  public void collapseWhitespace005() throws Exception {
    assertEquals("A B" , collapseWhitespace().apply(" A  B"));
  }

  @Test
  public void collapseWhitespace006() throws Exception {
    assertEquals("A B" , collapseWhitespace().apply(" A  B "));
  }

  @Test
  public void collapseWhitespace007() throws Exception {
    assertEquals("A B" , collapseWhitespace().apply(" A   B "));
  }


  @Test
  public void at001() throws Exception {
    Result<String> r = at().apply("ABCD" , 1);
    assertNotNull(r);
    assertTrue(r.isPresent());
    assertEquals("A" , r.get());
  }

  @Test
  public void at002() throws Exception {
    Result<String> r = at().apply("ABCD" , 5);
    assertNotNull(r);
    assertFalse(r.isPresent());
  }

  @Test
  public void at003() throws Exception {
    Result<String> r = at().apply("ABCD" , - 5);
    assertNotNull(r);
    assertFalse(r.isPresent());
  }

  @Test
  public void at004() throws Exception {
    Result<String> r = at().apply("ABCD" , 0);
    assertNotNull(r);
    assertFalse(r.isPresent());
  }

  @Test
  public void at005() throws Exception {
    Result<String> r = at().apply("ABCD" , 4);
    assertNotNull(r);
    assertTrue(r.isPresent());
    assertEquals("D" , r.get());
  }


  @Test
  public void notStartsWith001() throws Exception {
    assertFalse(notStartsWith().apply("AAA" , "A"));
  }

  @Test
  public void notStartsWith002() throws Exception {
    assertFalse(notStartsWith().apply("AAA" , "AAA"));
  }

  @Test
  public void notStartsWith003() throws Exception {
    assertTrue(notStartsWith().apply("AAA" , "AAAA"));
  }

  @Test
  public void notStartsWith004() throws Exception {
    assertTrue(notStartsWith().apply("AAA" , "B"));
  }

  @Test
  public void notStartsWith005() throws Exception {
    assertFalse(notStartsWith().apply("AAA" , ""));
  }

  @Test
  public void notStartsWith006() throws Exception {
    assertFalse(notStartsWith().apply(" AAA" , ""));
  }

  @Test
  public void notStartsWith007() throws Exception {
    assertFalse(notStartsWith().apply(" AAA" , " "));
  }

  @Test
  public void notEmpty001() throws Exception {
    assertFalse(notEmpty().test(""));
  }

  @Test
  public void notEmpty002() throws Exception {
    assertTrue(notEmpty().test(" "));
  }

  @Test
  public void notEmpty003() throws Exception {
    assertTrue(notEmpty().test("A"));
  }


  @Test
  public void append_shouldAppendStringsToEndOfValue() throws Exception {
    assertThat(appendStream().apply("f" , Stream.of("o" , "o" , "b" , "a" , "r")) ,
        equalTo("foobar"));
    assertThat(append().apply("foobar" , "") , equalTo("foobar"));
    assertThat(append().apply("" , "foobar") , equalTo("foobar"));
  }

//  @Test(expected = IllegalArgumentException.class)
//  public void append_shouldThrowIllegalArgumentExceptionWhenValueIsNull() throws Exception {
//    append(null);
//  }

  @Test
  public void appendArray_shouldAppendStringArrayToEndOfValue() throws Exception {
    assertThat(appendArray().apply("f" , new String[]{"o" , "o" , "b" , "a" , "r"}) , equalTo("foobar"));
    assertThat(appendArray().apply("foobar" , new String[]{}) , equalTo("foobar"));
    assertThat(appendArray().apply("" , new String[]{"foobar"}) , equalTo("foobar"));
  }

//  @Test(expected = IllegalArgumentException.class)
//  public void appendArray_ShouldThrowIllegalArgumentExceptionWhenValueIsNull() throws Exception {
//    appendArray(null, new String[]{});
//  }

  @Test
  public void at_shouldFindCharacterAtIndex() throws Exception {
    assertThat(at().apply("foobar" , 1).get() , equalTo("f"));
    assertThat(at().apply("foobar" , 2).get() , equalTo("o"));
    assertThat(at().apply("foobar" , - 1).isAbsent() , equalTo(true));
    assertThat(at().apply("foobar" , - 2).isAbsent() , equalTo(true));
//    assertThat(at().apply("foobar", 10).get(), equalTo(Result.failure("").get()));
//    assertThat(at().apply("foobar", - 10).get(), equalTo(Result.failure("").get()));
  }

  @Test
  public void at_shouldNotFindCharacterAtIndex() throws Exception {
    assertTrue(at().apply("foobar" , 10).isAbsent());
    assertTrue(at().apply("foobar" , - 10).isAbsent());
  }


  @Test
  public void between_shouldReturnArrayWithStringsBetweenStartAndEnd() throws Exception {
//    assertThat(
//        isEnclosedBetweenBoth().apply("[abc][def]", "[", "]"),
//        arrayContaining("abc", "def")
//    );
//    assertThat(isEnclosedBetweenBoth().apply("<span>foo</span>", "<span>", "</span>"),
//        arrayContaining("foo"));
//    assertThat(between("<span>foo</span><span>bar</span>", "<span>", "</span>"), arrayContaining("foo", "bar"));
  }

  @Test
  public void between_shouldReturnFullStringWhenStartAndEndDoesNotExist() throws Exception {
//    assertThat(between("[abc][def]", "{", "}"), arrayContaining("[abc][def]"));
//    assertThat(between("", "{", "}"), arrayContaining(""));
  }

  @Test
  public void chars_shouldReturnAllCharactersInString() throws Exception {
    final String title = "title";
    assertThat(chars().apply(title) , equalTo(new String[]{"t" , "i" , "t" , "l" , "e"}));
  }

  @Test
  public void collapseWhitespace_shouldReplaceConsecutiveWhitespaceWithSingleSpace() throws Exception {
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
  public void collapseWhitespace_shouldReplaceConsecutiveWhitespaceBetweenMultipleStrings() throws Exception {
    String input = " foo      bar      bazz     hello    world    ";
    assertThat(collapseWhitespace().apply(input) , equalTo("foo bar bazz hello world"));
  }

  @Test
  public void containsWithCaseSensitiveFalse_shouldReturnTrueWhenStringContainsNeedle() throws Exception {
    String[] fixture = {
        "foo bar" ,
        "bar foo" ,
        "foobar" ,
        "foo"
    };

    Arrays.stream(fixture).forEach(el -> assertTrue(contains().apply(el , "FOO")));
  }

  @Test
  public void containsWithCaseSensitiveTrue_shouldReturnTrueWhenStringContainsNeedle() throws Exception {
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
  public void containsAll_shouldReturnTrueOnlyWhenAllNeedlesAreContainedInValue() throws Exception {
    String[] fixture = {
        "foo bar" ,
        "bar foo" ,
        "foobar" ,
    };

    Arrays.stream(fixture)
        .forEach(el -> assertTrue(containsAll().apply(el , new String[]{"foo" , "bar"})));
  }

  @Test
  public void containsAll_shouldReturnFalseOnlyWhenAllNeedlesAreNotContainedInValue() throws Exception {
    String[] fixture = {
        "foo bar" ,
        "bar foo" ,
        "foobar" ,
    };
    Arrays.stream(fixture)
        .forEach(el -> assertFalse(containsAllCaseSensitive().apply(el , new String[]{"FOO" , "bar"} , true)));
  }

//  @Test
//  public void containsAny_shouldReturnTrueWhenAnyOfSearchNeedleExistInInputValue() throws Exception {
//    String[] fixture = {
//        "foo bar",
//        "bar foo",
//        "foobar",
//    };
//    Arrays.stream(fixture).forEach(el -> assertTrue(containsAny(el, new String[]{"foo", "bar", "test"})));
//  }

//  @Test
//  public void containsAny_shouldReturnFalseWhenNoneOfSearchNeedleExistInInputValue() throws Exception {
//    String[] fixture = {
//        "foo bar",
//        "bar foo",
//        "foobar",
//    };
//    Arrays.stream(fixture)
//        .forEach(el -> assertFalse(containsAny(el, new String[]{"FOO", "BAR", "Test"}, true)));
//  }

  @Test
  public void countSubstr_shouldCountSubStrCountCaseInsensitiveWithoutOverlapInValue() throws Exception {
    assertThat(countSubStrCaseSensitive().apply("aaaAAAaaa" , "aaa" , false , false) , equalTo(3L));
  }

  @Test
  public void countSubstr_shouldCountSubStrCountCaseSensitiveWithoutOverlapInValue() throws Exception {
    assertThat(countSubStr().apply("aaaAAAaaa" , "aaa") , equalTo(2L));
  }

  @Test
  public void countSubstr_shouldCountSubStrCountCaseInsensitiveWithOverlapInValue() throws Exception {
    assertThat(countSubStrCaseSensitive().apply("aaaAAAaaa" , "aaa" , false , true) , equalTo(7L));
  }

  @Test
  public void countSubstr_shouldCountSubStrCountCaseSensitiveWithOverlapInValue() throws Exception {
    assertThat(countSubStrCaseSensitive().apply("aaaAAAaaa" , "AAA" , true , true) , equalTo(1L));
  }

//  @Test
//  public void countSubstrTestFixture_caseSensitiveTrueAndOverlappingFalse() throws Exception {
//    String[] fixture = {
//        "aaaaaAaaAA",
//        "faaaAAaaaaAA",
//        "aaAAaaaaafA",
//        "AAaaafaaaaAAAA"
//    };
//    Arrays.stream(fixture).forEach(el -> assertThat(countSubstr(el, "a", true, false), equalTo(7L)));
//  }

//  @Test
//  public void countSubstrTestFixture_caseSensitiveFalseAndOverlappingFalse() throws Exception {
//    String[] fixture = {
//        "aaaaaaa",
//        "faaaaaaa",
//        "aaaaaaaf",
//        "aaafaaaa"
//    };
//    Arrays.stream(fixture).forEach(el -> assertThat(countSubstr(el, "A", false, false), equalTo(7L)));
//  }

//  @Test
//  public void countSubstrTestFixture_caseSensitiveTrueAndOverlappingTrue() throws Exception {
//    assertThat(countSubstr("aaa", "aa", true, true), equalTo(2L));
//  }

  @Test
  public void endsWith_caseSensitive_ShouldBeTrueWhenStringEndsWithSearchStr() throws Exception {
    String[] fixture = {
        "foo bar" ,
        "bar"
    };
    Arrays.stream(fixture).forEach(el -> assertTrue(endsWith().apply(el , "bar")));
  }

  @Test
  public void endsWith_notCaseSensitive_ShouldBeTrueWhenStringEndsWithSearchStr() throws Exception {
    String[] fixture = {
        "foo bar" ,
        "bar"
    };
    Arrays.stream(fixture).forEach(el -> assertTrue(endsWithCaseSensitive().apply(el , "BAR" , false)));
  }

//  @Test
//  public void endsWith_caseSensitiveAtPosition_ShouldBeTrueWhenStringEndsWithSearchStr() throws Exception {
//    String[] fixture = {
//        "foo barr",
//        "barr"
//    };
//    Arrays.stream(fixture).forEach(el -> assertTrue(endsWith(el, "bar", el.length() - 1, true)));
//  }

//  @Test
//  public void endsWith_notCaseSensitiveAtPosition_ShouldBeTrueWhenStringEndsWithSearchStr() throws Exception {
//    String[] fixture = {
//        "foo barr",
//        "barr"
//    };
//    Arrays.stream(fixture).forEach(el -> assertTrue(endsWithCaseSensitive().apply(el, "BAR", el.length() - 1, false)));
//  }

  @Test
  public void ensureLeft_shouldEnsureValueStartsWithFoo() throws Exception {
    String[] fixture = {
        "foobar" ,
        "bar"
    };

    Arrays.stream(fixture).forEach(el -> assertThat(ensureLeft().apply(el , "foo") , equalTo("foobar")));
  }

  @Test
  public void ensureLeft_notCaseSensitive_shouldEnsureValueStartsWithFoo() throws Exception {
    assertThat(ensureLeftCaseSensitive().apply("foobar" , "FOO" , false) , equalTo("foobar"));
    assertThat(ensureLeftCaseSensitive().apply("bar" , "FOO" , false) , equalTo("FOObar"));
  }

  @Test
  public void base64Decode_shouldDecodeABase64DecodedValueToString() throws Exception {
    assertThat(base64Decode().apply("c3RybWFu") , equalTo("strman"));
    assertThat(base64Decode().apply("Zm9v") , equalTo("foo"));
    assertThat(base64Decode().apply("YmFy") , equalTo("bar"));
//    assertThat(base64Decode().apply("YsOhciE=") , equalTo("bár!"));
//    assertThat(base64Decode().apply("5ryi") , equalTo("漢"));
  }

  @Test
  public void base64Encode_shouldEncodeAString() throws Exception {
    assertThat(base64Encode().apply("strman") , equalTo("c3RybWFu"));
    assertThat(base64Encode().apply("foo") , equalTo("Zm9v"));
    assertThat(base64Encode().apply("bar") , equalTo("YmFy"));
//    assertThat(base64Encode().apply("bár!") , equalTo("YsOhciE="));
//    assertThat(base64Encode().apply("漢") , equalTo("5ryi"));
  }

  @Test
  public void binDecode_shouldDecodeABinaryStringToAValue() throws Exception {
    assertThat(
        binDecode().apply("000000000111001100000000011101000000000001110010000000000110110100000000011000010000000001101110") ,
        equalTo("strman"));

    assertThat(binDecode().apply("0110111100100010") , equalTo("漢"));
    assertThat(binDecode().apply("0000000001000001") , equalTo("A"));
    assertThat(binDecode().apply("0000000011000001") , equalTo("Á"));
    assertThat(binDecode().apply("00000000010000010000000001000001") , equalTo("AA"));
  }

  @Test
  public void binEncode_shouldEncodeAStringToBinaryFormat() throws Exception {
    assertThat(binEncode().apply("漢") , equalTo("0110111100100010"));
    assertThat(binEncode().apply("A") , equalTo("0000000001000001"));
    assertThat(binEncode().apply("Á") , equalTo("0000000011000001"));
    assertThat(binEncode().apply("AA") , equalTo("00000000010000010000000001000001"));
  }

  @Test
  public void decDecode_shouldDecodeDecimalStringToString() throws Exception {
    assertThat(decDecode().apply("28450") , equalTo("漢"));
    assertThat(decDecode().apply("00065") , equalTo("A"));
    assertThat(decDecode().apply("00193") , equalTo("Á"));
    assertThat(decDecode().apply("0006500065") , equalTo("AA"));
  }

  @Test
  public void decEncode_shouldEncodeStringToDecimal() throws Exception {
    assertThat(decEncode().apply("漢") , equalTo("28450"));
    assertThat(decEncode().apply("A") , equalTo("00065"));
    assertThat(decEncode().apply("Á") , equalTo("00193"));
    assertThat(decEncode().apply("AA") , equalTo("0006500065"));
  }

  @Test
  public void ensureRight_shouldEnsureStringEndsWithBar() throws Exception {
    final String[] fixture = {
        "foo" , "foobar" , "fooBAR"
    };
    assertThat(Arrays.stream(fixture).map(el -> ensureRightCaseSensitive().apply(el , "bar" , false)).collect(toList()) , hasItems("foobar" , "foobar" , "fooBAR"));
    assertThat(Arrays.stream(fixture).map(el -> ensureRight().apply(el , "bar")).collect(toList()) , hasItems("foobar" , "foobar" , "fooBARbar"));
  }

  @Test
  public void first_shouldReturnFirstThreeCharsOfString() throws Exception {
    final String[] fixture = {
        "foo" , "foobar"
    };
    Arrays.stream(fixture).forEach(el ->
        assertThat(
            first().apply(el , 3) ,
            equalTo("foo")));
  }

  @Test
  public void head_shouldReturnFirstCharOfString() throws Exception {
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
//  public void format_shouldFormatStringsToFooBar() throws Exception {
//    assertThat(format().apply("{0} bar", "foo"), equalTo("foo bar"));
//    assertThat(format().apply("foo {0}", "bar"), equalTo("foo bar"));
//    assertThat(format().apply("foo {0}", "bar", "foo"), equalTo("foo bar"));
//    assertThat(format().apply("{0} {1}", "foo", "bar"), equalTo("foo bar"));
//    assertThat(format().apply("{1} {0}", "bar", "foo"), equalTo("foo bar"));
//  }

//  @Test(expected = IllegalArgumentException.class)
//  public void format_shouldThrowExceptionWhenValueDoesNotExist() throws Exception {
//    assertThat(format().apply("{1} {0}"), equalTo("{1} {0}"));
//  }

  @Test
  public void hexDecode_shouldDecodeHexCodeToString() throws Exception {
    assertThat(hexDecode().apply("6f22") , equalTo("漢"));
    assertThat(hexDecode().apply("0041") , equalTo("A"));
    assertThat(hexDecode().apply("00c1") , equalTo("Á"));
    assertThat(hexDecode().apply("00410041") , equalTo("AA"));
  }

  @Test
  public void hexEncode_shouldEncodeStringToHexadecimalFormat() throws Exception {
    assertThat(hexEncode().apply("漢") , equalTo("6f22"));
    assertThat(hexEncode().apply("A") , equalTo("0041"));
    assertThat(hexEncode().apply("Á") , equalTo("00c1"));
    assertThat(hexEncode().apply("AA") , equalTo("00410041"));
  }

  @Test
  public void indexOf_shouldBeTrueWhenNeedleExists() throws Exception {
    final String value = "foobar";
    assertThat(indexOfCoseSensitive().apply(value , "f" , 0 , true) , equalTo(0));
    assertThat(indexOfCoseSensitive().apply(value , "o" , 0 , true) , equalTo(1));
    assertThat(indexOfCoseSensitive().apply(value , "b" , 0 , true) , equalTo(3));
    assertThat(indexOfCoseSensitive().apply(value , "a" , 0 , true) , equalTo(4));
    assertThat(indexOfCoseSensitive().apply(value , "r" , 0 , true) , equalTo(5));
    assertThat(indexOfCoseSensitive().apply(value , "t" , 0 , true) , equalTo(- 1));
  }

  @Test
  public void indexOf_shouldBeTrueWhenNeedleExistCaseSensitive() throws Exception {
    final String value = "foobar";
    assertThat(indexOfCoseSensitive().apply(value , "F" , 0 , false) , equalTo(0));
    assertThat(indexOfCoseSensitive().apply(value , "O" , 0 , false) , equalTo(1));
    assertThat(indexOfCoseSensitive().apply(value , "B" , 0 , false) , equalTo(3));
    assertThat(indexOfCoseSensitive().apply(value , "A" , 0 , false) , equalTo(4));
    assertThat(indexOfCoseSensitive().apply(value , "R" , 0 , false) , equalTo(5));
    assertThat(indexOfCoseSensitive().apply(value , "T" , 0 , false) , equalTo(- 1));
  }

//  @Test
//  public void inequal_shouldTestInequalityOfStrings() throws Exception {
//    assertThat(unequal("a", "b"), equalTo(true));
//    assertThat(unequal("a", "a"), equalTo(false));
//    assertThat(unequal("0", "1"), equalTo(true));
//  }

  @Test
  public void insert_shouldInsertStringAtIndex() throws Exception {
    assertThat(insert().apply("fbar" , "oo" , 1) , equalTo("foobar"));
    assertThat(insert().apply("foo" , "bar" , 3) , equalTo("foobar"));
    assertThat(insert().apply("foobar" , "x" , 5) , equalTo("foobaxr"));
    assertThat(insert().apply("foobar" , "x" , 6) , equalTo("foobarx"));
    assertThat(insert().apply("foo bar" , "asadasd" , 100) , equalTo("foo bar"));
  }

  @Test
  public void isLowerCase_shouldBeTrueWhenStringIsLowerCase() throws Exception {
    assertThat(isLowerCase().apply("") , equalTo(true));
    assertThat(isLowerCase().apply("foo") , equalTo(true));
    assertThat(isLowerCase().apply("foobarfoo") , equalTo(true));
  }

  @Test
  public void isLowerCase_shouldBeFalseWhenStringIsNotLowerCase() throws Exception {
    assertThat(isLowerCase().apply("Foo") , equalTo(false));
    assertThat(isLowerCase().apply("foobarfooA") , equalTo(false));
  }

  @Test
  public void isUpperCase_shouldBeTrueWhenStringIsUpperCase() throws Exception {
    assertThat(isUpperCase().apply("") , equalTo(true));
    assertThat(isUpperCase().apply("FOO") , equalTo(true));
    assertThat(isUpperCase().apply("FOOBARFOO") , equalTo(true));
  }

  @Test
  public void isUpperCase_shouldBeFalseWhenStringIsNotUpperCase() throws Exception {
    assertThat(isUpperCase().apply("Foo") , equalTo(false));
    assertThat(isUpperCase().apply("foobarfooA") , equalTo(false));
  }

  @Test
  public void last_shouldReturnLastNChars() throws Exception {
    assertThat(last().apply("foo" , 3) , equalTo("foo"));
    assertThat(last().apply("foobarfoo" , 3) , equalTo("foo"));
    assertThat(last().apply("" , 3) , equalTo(""));
    assertThat(last().apply("f" , 3) , equalTo("f"));
  }

  @Test
  public void leftPad_shouldAddPaddingOnTheLeft() throws Exception {
    assertThat(leftPad().apply("1" , "0" , 5) , equalTo("00001"));
    assertThat(leftPad().apply("01" , "0" , 5) , equalTo("00001"));
    assertThat(leftPad().apply("001" , "0" , 5) , equalTo("00001"));
    assertThat(leftPad().apply("0001" , "0" , 5) , equalTo("00001"));
    assertThat(leftPad().apply("00001" , "0" , 5) , equalTo("00001"));
  }

  @Test
  public void isString_shouldBeFalseWhenValueIsNotString() throws Exception {
    assertFalse(isString(1));
    assertFalse(isString(false));
    assertFalse(isString(1.2));
    assertFalse(isString(new String[]{}));
  }

  @Test
  public void isString_shouldBeTrueWhenValueIsString() throws Exception {
    assertTrue(isString("string"));
    assertTrue(isString(""));
  }

  @Test
  public void lastIndexOf_shouldFindIndexOfNeedle() throws Exception {
    final String value = "foobarfoobar";
    assertThat(lastIndexOf().apply(value , "f") , equalTo(6));
    assertThat(lastIndexOf().apply(value , "o") , equalTo(8));
    assertThat(lastIndexOf().apply(value , "b") , equalTo(9));
    assertThat(lastIndexOf().apply(value , "a") , equalTo(10));
    assertThat(lastIndexOf().apply(value , "r") , equalTo(11));
    assertThat(lastIndexOf().apply(value , "t") , equalTo(- 1));
  }

  @Test
  public void lastIndexOf_shouldFindIndexOfNeedleCaseInsensitive() throws Exception {
    final String value = "foobarfoobar";
    assertThat(lastIndexOfCaseSensitive().apply(value , "F" , false) , equalTo(6));
    assertThat(lastIndexOfCaseSensitive().apply(value , "O" , false) , equalTo(8));
    assertThat(lastIndexOfCaseSensitive().apply(value , "B" , false) , equalTo(9));
    assertThat(lastIndexOfCaseSensitive().apply(value , "A" , false) , equalTo(10));
    assertThat(lastIndexOfCaseSensitive().apply(value , "R" , false) , equalTo(11));
    assertThat(lastIndexOfCaseSensitive().apply(value , "T" , false) , equalTo(- 1));
  }

  @Test
  public void leftTrim_shouldRemoveSpacesOnLeft() throws Exception {
    assertThat(leftTrim().apply("     strman") , equalTo("strman"));
    assertThat(leftTrim().apply("     strman  ") , equalTo("strman  "));
  }

//  @Test
//  public void prepend_shouldPrependStrings() throws Exception {
//    assertThat(prepend().apply("r", "f", "o", "o", "b", "a"), equalTo("foobar"));
//    assertThat(prepend().apply("foobar"), equalTo("foobar"));
//    assertThat(prepend().apply("", "foobar"), equalTo("foobar"));
//    assertThat(prepend().apply("bar", "foo"), equalTo("foobar"));
//  }

  @Test
  public void prependArray_shouldPrependStrings() throws Exception {
    assertThat(prependArray().apply("r" , new String[]{"f" , "o" , "o" , "b" , "a"}) , equalTo("foobar"));
    assertThat(prependArray().apply("foobar" , new String[0]) , equalTo("foobar"));
    assertThat(prependArray().apply("" , new String[]{"foobar"}) , equalTo("foobar"));
    assertThat(prependArray().apply("bar" , new String[]{"foo"}) , equalTo("foobar"));
  }

  @Test
  public void removeEmptyStrings_shouldRemoveEmptyStrings() throws Exception {
    assertThat(removeEmptyStrings().apply(new String[]{"aa" , "" , "   " , "bb" , "cc" , null}) , arrayContaining("aa" , "bb" , "cc"));
    assertThat(removeEmptyStrings().apply(new String[0]) , emptyArray());
  }

  @Test
  public void removeLeft_shouldRemoveStringFromLeft() throws Exception {
    final String[] fixture = {
        "foobar" ,
        "bar"
    };

    Arrays.stream(fixture).forEach(el -> assertThat(removeLeft().apply(el , "foo") , equalTo("bar")));
    assertThat(removeLeft().apply("barfoo" , "foo") , equalTo("barfoo"));
    assertThat(removeLeft().apply("foofoo" , "foo") , equalTo("foo"));
  }

  @Test
  public void removeLeft_shouldRemoveStringFromLeftCaseInSensitive() throws Exception {
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
  public void removeNonWords_shouldRemoveAllNonWordsFromInputString() throws Exception {

    final String[] fixture = {
        "foo bar" ,
        "foo&bar-" ,
        "foobar"
    };

    Arrays.stream(fixture).forEach(el -> assertThat(removeNonWord().apply(el) , equalTo("foobar")));
  }

  @Test
  public void removeRight_shouldRemoveStringFromRight() throws Exception {
    final String[] fixture = {
        "foobar" ,
        "foo"
    };

    Arrays.stream(fixture).forEach(el -> assertThat(removeRight().apply(el , "bar") , equalTo("foo")));
    assertThat(removeRight().apply("barfoo" , "bar") , equalTo("barfoo"));
    assertThat(removeRight().apply("barbar" , "bar") , equalTo("bar"));
  }

  @Test
  public void removeRight_shouldRemoveStringFromRightCaseInSensitive() throws Exception {
    final String[] fixture = {
        "foobar" ,
        "foo"
    };

    Arrays.stream(fixture).forEach(el -> assertThat(removeRightCaseSensitive().apply(el , "BAR" , false) , equalTo("foo")));
  }

  @Test
  public void removeSpaces_shouldRemoveSpacesInTheString() throws Exception {
    final String[] fixture = {
        "foo bar" ,
        "foo bar " ,
        " foo bar" ,
        " foo bar "
    };
    Arrays.stream(fixture).forEach(el -> assertThat(removeSpaces().apply(el) , equalTo("foobar")));
  }

  @Test
  public void repeat_shouldRepeatAStringNTimes() throws Exception {
    assertThat(repeat().apply("1" , 1) , equalTo("1"));
    assertThat(repeat().apply("1" , 2) , equalTo("11"));
    assertThat(repeat().apply("1" , 3) , equalTo("111"));
    assertThat(repeat().apply("1" , 4) , equalTo("1111"));
    assertThat(repeat().apply("1" , 5) , equalTo("11111"));
  }

  @Test
  public void replace_shouldReplaceAllOccurrencesOfString() throws Exception {
    assertThat(replace().apply("foo bar" , "foo" , "bar" , true) , equalTo("bar bar"));
    assertThat(replace().apply("foo bar foo" , "foo" , "bar" , true) , equalTo("bar bar bar"));
  }

  @Test
  public void replace_shouldReplaceAllOccurrencesOfStringCaseSensitive() throws Exception {
    assertThat(replace().apply("FOO bar" , "foo" , "bar" , false) , equalTo("bar bar"));
    assertThat(replace().apply("FOO bar foo" , "foo" , "bar" , false) , equalTo("bar bar bar"));
  }

  @Test
  public void reverse_shouldReverseInputString() throws Exception {
    assertThat(reverse().apply("") , equalTo(""));
    assertThat(reverse().apply("foo") , equalTo("oof"));
    assertThat(reverse().apply("shekhar") , equalTo("rahkehs"));
    assertThat(reverse().apply("bar") , equalTo("rab"));
    assertThat(reverse().apply("foo_") , equalTo("_oof"));
    assertThat(reverse().apply("f") , equalTo("f"));
  }

  @Test
  public void rightPad_shouldRightPadAString() throws Exception {
    assertThat(rightPad().apply("1" , "0" , 5) , equalTo("10000"));
    assertThat(rightPad().apply("10" , "0" , 5) , equalTo("10000"));
    assertThat(rightPad().apply("100" , "0" , 5) , equalTo("10000"));
    assertThat(rightPad().apply("1000" , "0" , 5) , equalTo("10000"));
    assertThat(rightPad().apply("10000" , "0" , 5) , equalTo("10000"));
    assertThat(rightPad().apply("10000000" , "0" , 5) , equalTo("10000000"));
  }

  @Test
  public void rightTrim_shouldRemoveSpacesFromTheRight() throws Exception {
    assertThat(rightTrim().apply("strman   ") , equalTo("strman"));
    assertThat(rightTrim().apply("   strman") , equalTo("   strman"));
    assertThat(rightTrim().apply("strman") , equalTo("strman"));
  }

//  @Test
//  public void safeTruncate_shouldSafelyTruncateStrings() throws Exception {
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
  public void truncate_shouldTruncateString() throws Exception {
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