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

package org.openo.policy.designer;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.server.SimpleServerFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.openo.policy.designer.common.Config;
import org.openo.policy.designer.common.MsbAddrConfig;
import org.openo.policy.designer.common.ServiceRegistrer;

import org.openo.policy.designer.health.ConsoleHealthCheck;
import org.openo.policy.designer.resources.DesignerServerResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;
import javax.servlet.DispatcherType;

public class DesignerApp extends Application<DesignerAppConfiguration> {

  private static final Logger LOGGER = LoggerFactory.getLogger(DesignerApp.class);

  public static void main(String[] args) throws Exception {
    new DesignerApp().run(args);
  }

  @Override
  public String getName() {
    return "OPENO-Policy-Designer";
  }
  // private final HibernateBundleAgent bundle = new HibernateBundleAgent();

  @Override
  public void initialize(Bootstrap<DesignerAppConfiguration> bootstrap) {
    bootstrap.addBundle(new AssetsBundle("/api-doc", "/api-doc", "index.html", "api-doc"));
  }

  @Override
  public void run(DesignerAppConfiguration configuration, Environment environment) {
    LOGGER.info("Start to initialize policy designer.");
    MsbAddrConfig.setMsbAddress(configuration.getMsbServerAddr());
    final ConsoleHealthCheck healthCheck = new ConsoleHealthCheck(configuration.getTemplate());
    environment.healthChecks().register("template", healthCheck);

    environment.jersey().register(new DesignerServerResource());

    // register rest interface
    environment.jersey().packages("org.openo.policy.designer.resources");
    // upload file by inputstream need to register MultiPartFeature
    environment.jersey().register(MultiPartFeature.class);

    initSwaggerConfig(environment, configuration);
    Config.setConfigration(configuration);
    initService();
    LOGGER.info("Initialize policy designer finished.");
  }

  /**
   * initialize swagger configuration.
   * 
   * @param environment environment information
   * @param configuration catalogue configuration
   */
  private void initSwaggerConfig(Environment environment, DesignerAppConfiguration configuration) {
    environment.jersey().register(new ApiListingResource());
    environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    BeanConfig config = new BeanConfig();
    config.setTitle("Open-o Policy Designer Service rest API");
    config.setVersion("1.0.0");
    config.setResourcePackage("org.openo.policy.designer.resources");
    // set rest api basepath in swagger
    SimpleServerFactory simpleServerFactory =
        (SimpleServerFactory) configuration.getServerFactory();
    String basePath = simpleServerFactory.getApplicationContextPath();
    String rootPath = simpleServerFactory.getJerseyRootPath();
    rootPath = rootPath.substring(0, rootPath.indexOf("/*"));
    basePath =
        basePath.equals("/") ? rootPath : (new StringBuilder()).append(basePath).append(rootPath)
            .toString();
    config.setBasePath(basePath);
    config.setScan(true);
  }

  private void initService() {
    Thread registerCatalogService = new Thread(new ServiceRegistrer());
    registerCatalogService.setName("register policy designer service to Microservice Bus");
    registerCatalogService.start();
  }

}
