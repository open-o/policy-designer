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

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openo.policy.designer.DesignerApp;
import org.openo.policy.designer.DesignerAppConfiguration;
import org.openo.policy.designer.common.Config;
import org.openo.policy.designer.common.ServiceRegistrer;
import org.openo.policy.designer.common.ToolUtil;
import org.openo.policy.designer.entity.HelloWorld;
import org.openo.policy.designer.externalservice.msb.MicroserviceBusConsumer;
import org.openo.policy.designer.externalservice.msb.ServiceRegisterEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

public class DesignerServerWrapperTest {
  private static String resourcePath;
  private static final Logger LOG = LoggerFactory.getLogger(ServiceRegistrer.class);
  
  static {
    
    DesignerAppConfiguration configuration = new DesignerAppConfiguration();
    Config.setConfigration(configuration);
    Config.getConfigration().setMsbServerAddr("http://127.0.0.1:80");
  }
  

  /**
   * startup db session before class.
   * @throws Exception e
   */
  @BeforeClass
  public static void setUpBeforeClass() {
    DesignerAppConfiguration configuration = new DesignerAppConfiguration();
    Config.setConfigration(configuration);
    System.out.println("Set up before class");
  }

  /**
   * create data before test.
   */
  @Before
  public void setUp() throws Exception {
    
  }

  @Test
  public void testQueryTest() throws Exception {
    HelloWorld expectResult = new HelloWorld();
    expectResult.setTest("HelloWorld");
    Response result = DesignerServerWrapper.getInstance().queryTest();
    assertEquals(200, result.getStatus());
  }
  
  @Test
  public void testMsbRegister() {
//    boolean flag = false;
//    ServiceRegisterEntity entity = new ServiceRegisterEntity();
//    entity = initServiceEntity();
//    flag = MicroserviceBusConsumer.registerService(entity);
//    assertEquals(true, flag);
    Thread registerPolDesignerService = new Thread(new ServiceRegistrer());
  }
  
  @Test
  public void testMicroserviceBusConsumer() {
    ServiceRegisterEntity entity = new ServiceRegisterEntity();
    entity = initServiceEntity();
    boolean flag = MicroserviceBusConsumer.registerService(entity);
  }

  /**
   * delete data after test.
   */
  @After
  public void tearDown() throws Exception {
    
    System.out.println("Tear down");
  }

  /**
   * destory db session after class.
   * @throws Exception e
   */
  @AfterClass
  public static void tearDownAfterClass() {

  }
  
  private ServiceRegisterEntity initServiceEntity() {
    ServiceRegisterEntity polDesignerEntity = new ServiceRegisterEntity();
    polDesignerEntity.setServiceName("poldesigner");
    polDesignerEntity.setProtocol("REST");
    polDesignerEntity.setVersion("v1");
    polDesignerEntity.setUrl("/openoapi/poldesigner/v1");
    polDesignerEntity.setSingleNode(Config.getConfigration().getServiceIp(), "8901", 0);
    polDesignerEntity.setVisualRange("1");
    return polDesignerEntity;
  }
}
