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
package org.openo.policy.designer.wrapper;

import javax.ws.rs.core.Response;

import org.openo.policy.designer.entity.HelloWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DesignerServerWrapper {
  private static DesignerServerWrapper packageWrapper;
  private static final Logger LOG = LoggerFactory.getLogger(DesignerServerWrapper.class);

  /**
   * get PackageWrapper instance.
   * @return package wrapper instance
   */
  public static DesignerServerWrapper getInstance() {
    if (packageWrapper == null) {
      packageWrapper = new DesignerServerWrapper();
    }
    return packageWrapper;
  }
  
  /**
   * query package by id.
   * @param csarId package id
   * @return Response
   */
  public Response queryTest() {
    HelloWorld test = new HelloWorld();
    test.setTest("HelloWorld");
    return Response.ok(test).build();
  }
}
