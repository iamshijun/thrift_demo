/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package thrift.test;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2016-06-13")
public class GuessProtocolStruct implements org.apache.thrift.TBase<GuessProtocolStruct, GuessProtocolStruct._Fields>, java.io.Serializable, Cloneable, Comparable<GuessProtocolStruct> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("GuessProtocolStruct");

  private static final org.apache.thrift.protocol.TField MAP_FIELD_FIELD_DESC = new org.apache.thrift.protocol.TField("map_field", org.apache.thrift.protocol.TType.MAP, (short)7);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new GuessProtocolStructStandardSchemeFactory());
    schemes.put(TupleScheme.class, new GuessProtocolStructTupleSchemeFactory());
  }

  public Map<String,String> map_field; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    MAP_FIELD((short)7, "map_field");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 7: // MAP_FIELD
          return MAP_FIELD;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.MAP_FIELD, new org.apache.thrift.meta_data.FieldMetaData("map_field", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(GuessProtocolStruct.class, metaDataMap);
  }

  public GuessProtocolStruct() {
  }

  public GuessProtocolStruct(
    Map<String,String> map_field)
  {
    this();
    this.map_field = map_field;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public GuessProtocolStruct(GuessProtocolStruct other) {
    if (other.isSetMap_field()) {
      Map<String,String> __this__map_field = new HashMap<String,String>(other.map_field);
      this.map_field = __this__map_field;
    }
  }

  public GuessProtocolStruct deepCopy() {
    return new GuessProtocolStruct(this);
  }

  @Override
  public void clear() {
    this.map_field = null;
  }

  public int getMap_fieldSize() {
    return (this.map_field == null) ? 0 : this.map_field.size();
  }

  public void putToMap_field(String key, String val) {
    if (this.map_field == null) {
      this.map_field = new HashMap<String,String>();
    }
    this.map_field.put(key, val);
  }

  public Map<String,String> getMap_field() {
    return this.map_field;
  }

  public GuessProtocolStruct setMap_field(Map<String,String> map_field) {
    this.map_field = map_field;
    return this;
  }

  public void unsetMap_field() {
    this.map_field = null;
  }

  /** Returns true if field map_field is set (has been assigned a value) and false otherwise */
  public boolean isSetMap_field() {
    return this.map_field != null;
  }

  public void setMap_fieldIsSet(boolean value) {
    if (!value) {
      this.map_field = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case MAP_FIELD:
      if (value == null) {
        unsetMap_field();
      } else {
        setMap_field((Map<String,String>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case MAP_FIELD:
      return getMap_field();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case MAP_FIELD:
      return isSetMap_field();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof GuessProtocolStruct)
      return this.equals((GuessProtocolStruct)that);
    return false;
  }

  public boolean equals(GuessProtocolStruct that) {
    if (that == null)
      return false;

    boolean this_present_map_field = true && this.isSetMap_field();
    boolean that_present_map_field = true && that.isSetMap_field();
    if (this_present_map_field || that_present_map_field) {
      if (!(this_present_map_field && that_present_map_field))
        return false;
      if (!this.map_field.equals(that.map_field))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_map_field = true && (isSetMap_field());
    list.add(present_map_field);
    if (present_map_field)
      list.add(map_field);

    return list.hashCode();
  }

  @Override
  public int compareTo(GuessProtocolStruct other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetMap_field()).compareTo(other.isSetMap_field());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMap_field()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.map_field, other.map_field);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("GuessProtocolStruct(");
    boolean first = true;

    sb.append("map_field:");
    if (this.map_field == null) {
      sb.append("null");
    } else {
      sb.append(this.map_field);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class GuessProtocolStructStandardSchemeFactory implements SchemeFactory {
    public GuessProtocolStructStandardScheme getScheme() {
      return new GuessProtocolStructStandardScheme();
    }
  }

  private static class GuessProtocolStructStandardScheme extends StandardScheme<GuessProtocolStruct> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, GuessProtocolStruct struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 7: // MAP_FIELD
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map130 = iprot.readMapBegin();
                struct.map_field = new HashMap<String,String>(2*_map130.size);
                String _key131;
                String _val132;
                for (int _i133 = 0; _i133 < _map130.size; ++_i133)
                {
                  _key131 = iprot.readString();
                  _val132 = iprot.readString();
                  struct.map_field.put(_key131, _val132);
                }
                iprot.readMapEnd();
              }
              struct.setMap_fieldIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, GuessProtocolStruct struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.map_field != null) {
        oprot.writeFieldBegin(MAP_FIELD_FIELD_DESC);
        {
          oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, struct.map_field.size()));
          for (Map.Entry<String, String> _iter134 : struct.map_field.entrySet())
          {
            oprot.writeString(_iter134.getKey());
            oprot.writeString(_iter134.getValue());
          }
          oprot.writeMapEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class GuessProtocolStructTupleSchemeFactory implements SchemeFactory {
    public GuessProtocolStructTupleScheme getScheme() {
      return new GuessProtocolStructTupleScheme();
    }
  }

  private static class GuessProtocolStructTupleScheme extends TupleScheme<GuessProtocolStruct> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, GuessProtocolStruct struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetMap_field()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetMap_field()) {
        {
          oprot.writeI32(struct.map_field.size());
          for (Map.Entry<String, String> _iter135 : struct.map_field.entrySet())
          {
            oprot.writeString(_iter135.getKey());
            oprot.writeString(_iter135.getValue());
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, GuessProtocolStruct struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TMap _map136 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.map_field = new HashMap<String,String>(2*_map136.size);
          String _key137;
          String _val138;
          for (int _i139 = 0; _i139 < _map136.size; ++_i139)
          {
            _key137 = iprot.readString();
            _val138 = iprot.readString();
            struct.map_field.put(_key137, _val138);
          }
        }
        struct.setMap_fieldIsSet(true);
      }
    }
  }

}

