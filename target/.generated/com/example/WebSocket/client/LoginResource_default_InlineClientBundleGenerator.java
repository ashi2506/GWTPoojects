package com.example.WebSocket.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ResourcePrototype;

public class LoginResource_default_InlineClientBundleGenerator implements com.example.WebSocket.client.LoginResource {
  private static LoginResource_default_InlineClientBundleGenerator _instance0 = new LoginResource_default_InlineClientBundleGenerator();
  private void styleInitializer() {
    style = new com.google.gwt.resources.client.CssResource() {
      private boolean injected;
      public boolean ensureInjected() {
        if (!injected) {
          injected = true;
          com.google.gwt.dom.client.StyleInjector.inject(getText());
          return true;
        }
        return false;
      }
      public String getName() {
        return "style";
      }
      public String getText() {
        return com.google.gwt.i18n.client.LocaleInfo.getCurrentLocale().isRTL() ? ((".textbox{padding:" + ("8px")  + ";}.group{margin-right:" + ("300px")  + ";}.login{background:" + ("aqua")  + ";width:" + ("2500px")  + ";height:" + ("1000px")  + ";}")) : ((".textbox{padding:" + ("8px")  + ";}.group{margin-left:" + ("300px")  + ";}.login{background:" + ("aqua")  + ";width:" + ("2500px")  + ";height:" + ("1000px")  + ";}"));
      }
    }
    ;
  }
  private static class styleInitializer {
    static {
      _instance0.styleInitializer();
    }
    static com.google.gwt.resources.client.CssResource get() {
      return style;
    }
  }
  public com.google.gwt.resources.client.CssResource style() {
    return styleInitializer.get();
  }
  private static java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype> resourceMap;
  private static com.google.gwt.resources.client.CssResource style;
  
  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      style(), 
    };
  }
  public ResourcePrototype getResource(String name) {
    if (GWT.isScript()) {
      return getResourceNative(name);
    } else {
      if (resourceMap == null) {
        resourceMap = new java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype>();
        resourceMap.put("style", style());
      }
      return resourceMap.get(name);
    }
  }
  private native ResourcePrototype getResourceNative(String name) /*-{
    switch (name) {
      case 'style': return this.@com.example.WebSocket.client.LoginResource::style()();
    }
    return null;
  }-*/;
}
