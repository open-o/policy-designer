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

  /**
   * exchange object to string.
   * 
   * @param obj object
   * @return String
   */
  public static String objectToString(Object obj) {
    Gson gson = new Gson();
    String str = gson.toJson(obj);
    return str;
  }
}
