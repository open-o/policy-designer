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

package org.openo.policy.designer.externalservice.msb;

import com.eclipsesource.jaxrs.consumer.ConsumerFactory;
import org.glassfish.jersey.client.ClientConfig;
import org.openo.policy.designer.common.Config;
import org.openo.policy.designer.common.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MicroserviceBusConsumer {
  private static final Logger LOG = LoggerFactory.getLogger(MicroserviceBusConsumer.class);

  /**
   * register service to MSB.
   * 
   * @param entity ServiceRegisterEntity
   * @return boolean
   */
  public static boolean registerService(ServiceRegisterEntity entity) {
    ClientConfig config = new ClientConfig();
    LOG.info("microservice register body:" + ToolUtil.objectToString(entity));
    try {
      MicroserviceBusRest resourceserviceproxy = ConsumerFactory.createConsumer(
          Config.getConfigration().getMsbServerAddr(), config, MicroserviceBusRest.class);
      resourceserviceproxy.registerServce("false", entity);
    } catch (Exception e1) {
      LOG.error("microservice register failed!" + e1.getMessage());
      return false;
    }
    return true;
  }

}
