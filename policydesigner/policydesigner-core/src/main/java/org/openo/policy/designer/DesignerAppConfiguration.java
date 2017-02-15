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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class DesignerAppConfiguration extends Configuration {
  @NotEmpty
  private String template;

  @NotEmpty
  private String defaultName = "OPENO-Policy-Designer";

  @NotEmpty
  private String msbServerAddr;
  
  @NotEmpty
  private String httpServerAddr;

  @NotEmpty
  private String designerPath;
  
  @NotEmpty
  private String httpServerPath;

  @Valid
  private String serviceIp;

  @JsonProperty
  public String getTemplate() {
    return template;
  }

  @JsonProperty
  public void setTemplate(String template) {
    this.template = template;
  }

  @JsonProperty
  public String getDefaultName() {
    return defaultName;
  }

  @JsonProperty
  public void setDefaultName(String name) {
    this.defaultName = name;
  }

  @JsonProperty
  public String getMsbServerAddr() {
    return msbServerAddr;
  }

  @JsonProperty
  public void setMsbServerAddr(String msbServerAddr) {
    this.msbServerAddr = msbServerAddr;
  }

  @JsonProperty
  public String getCataloguePath() {
    return designerPath;
  }

  @JsonProperty
  public void setDesignerPath(String designerPath) {
    this.designerPath = designerPath;
  }
  
  @JsonProperty
  public String getServiceIp() {
    return serviceIp;
  }

  @JsonProperty
  public void setServiceIp(String serviceIp) {
    this.serviceIp = serviceIp;
  }


  @JsonProperty
  public String getHttpServerPath() {
    return httpServerPath;
  }

  @JsonProperty
  public void setHttpServerPath(String httpServerPath) {
    this.httpServerPath = httpServerPath;
  }
  
  @JsonProperty
  public String getHttpServerAddr() {
    return httpServerAddr;
  }

  @JsonProperty
  public void setHttpServerAddr(String httpServerAddr) {
    this.httpServerAddr = httpServerAddr;
  }
}
