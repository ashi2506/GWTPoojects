package com.example.WebSocket.shared;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class Message_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  private static native java.lang.String getData(com.example.WebSocket.shared.Message instance) /*-{
    return instance.@com.example.WebSocket.shared.Message::data;
  }-*/;
  
  private static native void setData(com.example.WebSocket.shared.Message instance, java.lang.String value) 
  /*-{
    instance.@com.example.WebSocket.shared.Message::data = value;
  }-*/;
  
  private static native java.util.List getOldMessages(com.example.WebSocket.shared.Message instance) /*-{
    return instance.@com.example.WebSocket.shared.Message::oldMessages;
  }-*/;
  
  private static native void setOldMessages(com.example.WebSocket.shared.Message instance, java.util.List value) 
  /*-{
    instance.@com.example.WebSocket.shared.Message::oldMessages = value;
  }-*/;
  
  private static native java.lang.String getTime(com.example.WebSocket.shared.Message instance) /*-{
    return instance.@com.example.WebSocket.shared.Message::time;
  }-*/;
  
  private static native void setTime(com.example.WebSocket.shared.Message instance, java.lang.String value) 
  /*-{
    instance.@com.example.WebSocket.shared.Message::time = value;
  }-*/;
  
  private static native java.lang.String getUsername(com.example.WebSocket.shared.Message instance) /*-{
    return instance.@com.example.WebSocket.shared.Message::username;
  }-*/;
  
  private static native void setUsername(com.example.WebSocket.shared.Message instance, java.lang.String value) 
  /*-{
    instance.@com.example.WebSocket.shared.Message::username = value;
  }-*/;
  
  public static void deserialize(SerializationStreamReader streamReader, com.example.WebSocket.shared.Message instance) throws SerializationException {
    setData(instance, streamReader.readString());
    setOldMessages(instance, (java.util.List) streamReader.readObject());
    instance.sessionUserMap = (java.util.Map) streamReader.readObject();
    instance.sessionUserMapList = (java.util.List) streamReader.readObject();
    setTime(instance, streamReader.readString());
    setUsername(instance, streamReader.readString());
    
  }
  
  public static com.example.WebSocket.shared.Message instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new com.example.WebSocket.shared.Message();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, com.example.WebSocket.shared.Message instance) throws SerializationException {
    streamWriter.writeString(getData(instance));
    streamWriter.writeObject(getOldMessages(instance));
    streamWriter.writeObject(instance.sessionUserMap);
    streamWriter.writeObject(instance.sessionUserMapList);
    streamWriter.writeString(getTime(instance));
    streamWriter.writeString(getUsername(instance));
    
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return com.example.WebSocket.shared.Message_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    com.example.WebSocket.shared.Message_FieldSerializer.deserialize(reader, (com.example.WebSocket.shared.Message)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    com.example.WebSocket.shared.Message_FieldSerializer.serialize(writer, (com.example.WebSocket.shared.Message)object);
  }
  
}
