package com.example.WebSocket.client;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class User_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  private static native java.lang.String getPassword(com.example.WebSocket.client.User instance) /*-{
    return instance.@com.example.WebSocket.client.User::password;
  }-*/;
  
  private static native void setPassword(com.example.WebSocket.client.User instance, java.lang.String value) 
  /*-{
    instance.@com.example.WebSocket.client.User::password = value;
  }-*/;
  
  private static native java.lang.String getUsername(com.example.WebSocket.client.User instance) /*-{
    return instance.@com.example.WebSocket.client.User::username;
  }-*/;
  
  private static native void setUsername(com.example.WebSocket.client.User instance, java.lang.String value) 
  /*-{
    instance.@com.example.WebSocket.client.User::username = value;
  }-*/;
  
  public static void deserialize(SerializationStreamReader streamReader, com.example.WebSocket.client.User instance) throws SerializationException {
    setPassword(instance, streamReader.readString());
    setUsername(instance, streamReader.readString());
    
  }
  
  public static com.example.WebSocket.client.User instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new com.example.WebSocket.client.User();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, com.example.WebSocket.client.User instance) throws SerializationException {
    streamWriter.writeString(getPassword(instance));
    streamWriter.writeString(getUsername(instance));
    
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return com.example.WebSocket.client.User_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    com.example.WebSocket.client.User_FieldSerializer.deserialize(reader, (com.example.WebSocket.client.User)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    com.example.WebSocket.client.User_FieldSerializer.serialize(writer, (com.example.WebSocket.client.User)object);
  }
  
}
