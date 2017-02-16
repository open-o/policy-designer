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

import org.openo.policy.designer.common.Config;
import org.openo.policy.designer.externalservice.msb.MicroserviceBusConsumer;
import org.openo.policy.designer.externalservice.msb.ServiceRegisterEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;

public class ServiceRegistrer implements Runnable {
  private final ArrayList<ServiceRegisterEntity> serviceEntityList =
      new ArrayList<ServiceRegisterEntity>();
  private static final Logger LOG = LoggerFactory.getLogger(ServiceRegistrer.class);

  public ServiceRegistrer() {
    initServiceEntity();
  }

  @Override
  public void run() {
    LOG.info("start  microservice register");
    boolean flag = false;
    ServiceRegisterEntity entity = new ServiceRegisterEntity();
    int retry = 0;
    while (retry < 1000 && serviceEntityList.size() > 0) {
      Iterator<ServiceRegisterEntity> it = serviceEntityList.iterator();
      while (it.hasNext()) {
        entity = it.next();
        flag = MicroserviceBusConsumer.registerService(entity);
        if (flag == false) {
          threadSleep(30000);
        } else {
          LOG.info(entity.getServiceName() + " microservice register success!");
          it.remove();
        }
      }
      retry++;

    }
    LOG.info("policy designer microservice register end.");

  }

  /**
   * sleep thread.
   * @param second sleep second
   */
  private void threadSleep(int seconds) {
    try {
      Thread.sleep(seconds);
    } catch (InterruptedException e1) {
      LOG.error("thread sleep error.errorMsg:" + e1.getMessage());
    }
  }

  private void initServiceEntity() {
    ServiceRegisterEntity polDesignerEntity = new ServiceRegisterEntity();
    polDesignerEntity.setServiceName("poldesigner");
    polDesignerEntity.setProtocol("REST");
    polDesignerEntity.setVersion("v1");
    polDesignerEntity.setUrl("/openoapi/poldesigner/v1");
    polDesignerEntity.setSingleNode(Config.getConfigration().getServiceIp(), "8901", 0);
    polDesignerEntity.setVisualRange("1");
    serviceEntityList.add(polDesignerEntity);
  }
}
