package com.theheroloop.events.validation;

import com.theheroloop.events.Message;
import com.theheroloop.events.MessageField;

public class RequiredOneOfMessageFieldsValidation implements FieldValidation
{

  private final MessageField[] _fields;

  public RequiredOneOfMessageFieldsValidation( MessageField[] fields )
    { _fields = fields; }

  @Override
  public boolean check( Message message )
  {
    boolean found = false;

    for( MessageField field : _fields )
      if( message.has( field ) )
      {
        found = true;

        if( ! field.validateRequiredFieldOnMessage( message ) )
          return false;
      }

    return found;
  }

}
