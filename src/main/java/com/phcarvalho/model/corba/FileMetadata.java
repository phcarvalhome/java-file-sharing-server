package com.phcarvalho.model.corba;


/**
* com/phcarvalho/model/corba/FileMetadata.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from FileSharingIDL.idl
* Quarta-feira, 16 de Outubro de 2019 03h30min06s BRT
*/

public final class FileMetadata implements org.omg.CORBA.portable.IDLEntity
{
  public com.phcarvalho.model.corba.User user = null;
  public String name = null;

  public FileMetadata ()
  {
  } // ctor

  public FileMetadata (com.phcarvalho.model.corba.User _user, String _name)
  {
    user = _user;
    name = _name;
  } // ctor

} // class FileMetadata