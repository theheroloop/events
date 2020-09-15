package com.theheroloop.events.validation;

import com.theheroloop.events.Message;
import com.theheroloop.events.MessageField;

public interface FieldValidation
{

  public static FieldValidation requires( MessageField field )
    { return new RequiredMessageFieldValidation( field ); }

  public static FieldValidation requiresEither( MessageField... fields )
  {
    return new RequiredOneOfMessageFieldsValidation( fields );
  }

  /////////////////////////////////////////////////////////////////////////////
  // Public API

  /**
   * Check if the given message passes the rule set by this field validation
   * object.
   */
  public boolean check( Message message );

}
