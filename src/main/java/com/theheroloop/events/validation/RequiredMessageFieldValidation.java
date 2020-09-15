package com.theheroloop.events.validation;

import com.theheroloop.events.Message;
import com.theheroloop.events.MessageField;

public class RequiredMessageFieldValidation implements FieldValidation
{

  private final MessageField _field;

  public RequiredMessageFieldValidation( MessageField field )
    { _field = field; }

  @Override
  public boolean check( Message message )
  {
    return message.has( _field ) &&
      _field.validateRequiredFieldOnMessage( message )
    ;
  }

}
