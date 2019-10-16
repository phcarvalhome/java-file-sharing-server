package com.phcarvalho.model.corba;


/**
* com/phcarvalho/model/corba/UserHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from FileSharingIDL.idl
* Quarta-feira, 16 de Outubro de 2019 03h30min06s BRT
*/

abstract public class UserHelper
{
  private static String  _id = "IDL:com/phcarvalho/model/corba/User:1.0";

  public static void insert (org.omg.CORBA.Any a, User that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static User extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [1];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "id",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (UserHelper.id (), "User", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static User read (org.omg.CORBA.portable.InputStream istream)
  {
    User value = new User ();
    value.id = istream.read_string ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, User value)
  {
    ostream.write_string (value.id);
  }

}