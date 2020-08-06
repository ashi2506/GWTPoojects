package com.example.WebSocket.shared;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class RegisteredUser_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  public static void deserialize(SerializationStreamReader streamReader, com.example.WebSocket.shared.RegisteredUser instance) throws SerializationException {
    instance.date = streamReader.readString();
    instance.message = streamReader.readString();
    instance.room = streamReader.readString();
    instance.username = streamReader.readString();
    
  }
  
  public static com.example.WebSocket.shared.RegisteredUser instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new com.example.WebSocket.shared.RegisteredUser();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, com.example.WebSocket.shared.RegisteredUser instance) throws SerializationException {
    streamWriter.writeString(instance.date);
    streamWriter.writeString(instance.message);
    streamWriter.writeString(instance.room);
    streamWriter.writeString(instance.username);
    
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return com.example.WebSocket.shared.RegisteredUser_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    com.example.WebSocket.shared.RegisteredUser_FieldSerializer.deserialize(reader, (com.example.WebSocket.shared.RegisteredUser)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    com.example.WebSocket.shared.RegisteredUser_FieldSerializer.serialize(writer, (com.example.WebSocket.shared.RegisteredUser)object);
  }
  
}
