/*
 * Copyright 2012-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoreFilesTest {
  private String testDataPath(String fileName) {
    return "testdata/com/facebook/buck/util/" + fileName;
  }

  @Test
  public void testDiffFileContents() throws IOException {
    List<String> diffLines;
    String testPath = testDataPath("MoreFilesTest.txt");
    File testFile = new File(testPath);

    diffLines = MoreFiles.diffFileContents(
        Arrays.asList("AAA", "BBB", "CCC"),
        testFile);
    assertEquals(diffLines, new ArrayList<String>());

    diffLines = MoreFiles.diffFileContents(Arrays.asList("AAA", "BBB"), testFile);
    assertEquals(diffLines, Arrays.asList(
        "| CCC |  |"
    ));

    diffLines = MoreFiles.diffFileContents(Arrays.asList("AAA", "CCC"), testFile);
    assertEquals(diffLines, Arrays.asList(
        "| BBB | CCC |",
        "| CCC |  |"
    ));

    diffLines = MoreFiles.diffFileContents(Arrays.asList("BBB", "CCC"), testFile);
    assertEquals(diffLines, Arrays.asList(
        "| AAA | BBB |",
        "| BBB | CCC |",
        "| CCC |  |"
    ));

    diffLines = MoreFiles.diffFileContents(Arrays.asList("AAA"), testFile);
    assertEquals(diffLines, Arrays.asList(
        "| BBB |  |",
        "| CCC |  |"
    ));

    diffLines = MoreFiles.diffFileContents(Arrays.asList("BBB"), testFile);
    assertEquals(diffLines, Arrays.asList(
        "| AAA | BBB |",
        "| BBB |  |",
        "| CCC |  |"
    ));

    diffLines = MoreFiles.diffFileContents(Arrays.asList("CCC"), testFile);
    assertEquals(diffLines, Arrays.asList(
        "| AAA | CCC |",
        "| BBB |  |",
        "| CCC |  |"
    ));

    diffLines = MoreFiles.diffFileContents(new ArrayList<String>(), testFile);
    assertEquals(diffLines, Arrays.asList(
        "| AAA |  |",
        "| BBB |  |",
        "| CCC |  |"
    ));

    diffLines = MoreFiles.diffFileContents(Arrays.asList("AAA", "BBB", "CCC", "xxx"), testFile);
    assertEquals(diffLines, Arrays.asList(
        "|  | xxx |"
    ));

    diffLines = MoreFiles.diffFileContents(Arrays.asList("AAA", "BBB", "xxx", "CCC"), testFile);
    assertEquals(diffLines, Arrays.asList(
        "| CCC | xxx |",
        "|  | CCC |"
    ));

    diffLines = MoreFiles.diffFileContents(Arrays.asList("AAA", "xxx", "BBB", "CCC"), testFile);
    assertEquals(diffLines, Arrays.asList(
        "| BBB | xxx |",
        "| CCC | BBB |",
        "|  | CCC |"
    ));

    diffLines = MoreFiles.diffFileContents(Arrays.asList("xxx", "AAA", "BBB", "CCC"), testFile);
    assertEquals(diffLines, Arrays.asList(
        "| AAA | xxx |",
        "| BBB | AAA |",
        "| CCC | BBB |",
        "|  | CCC |"
    ));

    diffLines = MoreFiles.diffFileContents(Arrays.asList("AAA", "BBB", "xxx"), testFile);
    assertEquals(diffLines, Arrays.asList(
        "| CCC | xxx |"
    ));

    diffLines = MoreFiles.diffFileContents(Arrays.asList("AAA", "xxx", "CCC"), testFile);
    assertEquals(diffLines, Arrays.asList(
        "| BBB | xxx |"
    ));

    diffLines = MoreFiles.diffFileContents(Arrays.asList("xxx", "BBB", "CCC"), testFile);
    assertEquals(diffLines, Arrays.asList(
        "| AAA | xxx |"
    ));

    diffLines = MoreFiles.diffFileContents(Arrays.asList("AAA", "xxx", "yyy"), testFile);
    assertEquals(diffLines, Arrays.asList(
        "| BBB | xxx |",
        "| CCC | yyy |"
    ));

    diffLines = MoreFiles.diffFileContents(Arrays.asList("xxx", "BBB", "yyy"), testFile);
    assertEquals(diffLines, Arrays.asList(
        "| AAA | xxx |",
        "| CCC | yyy |"
    ));

    diffLines = MoreFiles.diffFileContents(Arrays.asList("xxx", "yyy", "CCC"), testFile);
    assertEquals(diffLines, Arrays.asList(
        "| AAA | xxx |",
        "| BBB | yyy |"
    ));

    diffLines = MoreFiles.diffFileContents(Arrays.asList("xxx", "yyy", "zzz"), testFile);
    assertEquals(diffLines, Arrays.asList(
        "| AAA | xxx |",
        "| BBB | yyy |",
        "| CCC | zzz |"
    ));
  }
}
