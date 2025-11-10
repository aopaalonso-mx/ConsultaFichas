/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fdconsultawebservice;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 *
 * @author telecom
 */
public class FormatoLog extends Formatter{
    @Override
    public String format(LogRecord lr) {
      return lr.getLevel() + "::" + lr.getThreadID() + "::" + lr.getSourceClassName() + "::" + lr.getSourceMethodName() + "::" + new Date(lr.getMillis()) + "::" + lr.getMessage() + "\n";
   }
}
