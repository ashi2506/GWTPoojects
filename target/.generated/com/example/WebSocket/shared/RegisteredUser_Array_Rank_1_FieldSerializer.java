package com.example.WebSocket.shared;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class RegisteredUser_Array_Rank_1_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  public static void deserialize(SerializationStreamReader streamReader, com.example.WebSocket.shared.RegisteredUser[] instance) throws SerializationException {
    com.google.gwt.user.client.rpc.core.java.lang.Object_Array_CustomFieldSerializer.deserialize(streamReader, instance);
  }
  
  public static com.example.WebSocket.shared.RegisteredUser[] instantiate(SerializationStreamReader streamReader) throws SerializationException {
    int size = streamReader.readInt();
    return new com.example.WebSocket.shared.RegisteredUser[size];
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, com.example.WebSocket.shared.RegisteredUser[] instance) throws SerializationException {
    com.google.gwt.user.client.rpc.core.java.lang.Object_Array_CustomFieldSerializer.serialize(streamWriter, instance);
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return com.example.WebSocket.shared.RegisteredUser_Array_Rank_1_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    com.example.WebSocket.shared.RegisteredUser_Array_Rank_1_FieldSerializer.deserialize(reader, (com.example.WebSocket.shared.RegisteredUser[])object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    com.example.WebSocket.shared.RegisteredUser_Array_Rank_1_FieldSerializer.serialize(writer, (com.example.WebSocket.shared.RegisteredUser[])object);
  }
  
}
