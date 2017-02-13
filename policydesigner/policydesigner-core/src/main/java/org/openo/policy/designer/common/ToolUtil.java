/**
 * Copyright 2017 ZTE Corporation.
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
package org.openo.policy.designer.common;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Collection;
import java.util.UUID;



/**
 * common utility class.
 * 
 */
public class ToolUtil {
  private static final Logger LOG = LoggerFactory.getLogger(ToolUtil.class);

  public static final String CATALOGUE_CSAR_DIR_NAME = "csar";

  public static final String CATALOGUE_IMAGE_DIR_NAME = "image";

  public static final int FILE_PERCENT = 1024 * 1024; // 1M

  public static boolean isEmptyString(String val) {
    return val == null || "".equals(val);
  }

  public static boolean isTrimedEmptyString(String val) {
    return val == null || "".equals(val.trim());
  }

  public static boolean isTrimedEmptyArray(String[] val) {
    return val == null || val.length == 0;
  }

  /**
   * trimed string.
   * 
   * @param val string array to trim
   * @return String[]
   */
  public static String[] trimedStringArray(String[] val) {
    if (isTrimedEmptyArray(val)) {
      return val;
    }

    String[] rets = new String[val.length];
    for (int i = 0; i < val.length; i++) {
      rets[i] = val[i].trim();
    }
    return rets;
  }

  public static boolean isEmptyCollection(Collection<?> coll) {
    return null == coll || coll.isEmpty();
  }


  /**
   * get temp dirctory when upload package.
   * 
   * @param dirName temp directory name
   * @param fileName package name
   * @return String
   */
  public static String getTempDir(String dirName, String fileName) {
    // File tmpDir = new File(File.separator + dirName);
    return Class.class.getClass().getResource("/").getPath() + dirName + File.separator
        + fileName.replace(".csar", "");
  }

  /**
   * exchange object to string.
   * 
   * @param obj object
   * @return String
   */
  public static String objectToString(Object obj) {
    if (obj == null) {
      return "";
    }
    Gson gson = new Gson();
    String str = gson.toJson(obj);
    return str;
  }

  public static String generateId() {
    return UUID.randomUUID().toString();
  }

  /**
   * get gson from json.
   * @param jsonString json string
   * @param templateClass template class
   * @return Template
   */
  public static <T> T fromJson(String jsonString, Class<T> templateClass) {
    Gson gson = new Gson();
    return gson.fromJson(jsonString, templateClass);
  }

  /**
   * gson to json.
   * @param template class name
   * @return String
   */
  public static <T> String toJson(T template) {
    Gson gson = new Gson();
    return gson.toJson(template);
  }
  
  /**
   * @param value
   * @return
   */
  public static String getAsString(JsonElement value) {
    if (value.isJsonPrimitive()) {
      return value.getAsString();
    }

    return value.toString();
  }
}
